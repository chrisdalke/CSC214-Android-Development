////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;

////////////////////////////////////////
// Custom Dialog Builder
////////////////////////////////////////

public class CustomDialogBuilder {

    public static void buildNumberEntryDialog(Context context, final String title, final String description, final numberEntryDialogCallback callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(description);

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(input);

        final Context tContext = context;
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    callback.handleResult(Float.parseFloat(input.getText().toString()));
                } catch (Exception e){
                    e.printStackTrace();
                    buildNumberEntryDialog(tContext,title,description,callback);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {dialog.cancel();
            }
        });
        builder.show();
    }

    public interface numberEntryDialogCallback {
        void handleResult(float result);
    }

    public static void buildDateTimeEntryDialog(Context context, final String title, final String description, final dateTimeEntryDialogCallback callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(description);

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_TIME);
        builder.setView(input);

        final Context tContext = context;
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String[] rawInput = input.getText().toString().split(":");
                    if (rawInput.length != 3){
                        throw new Exception();
                    }
                    int hours = Integer.parseInt(rawInput[0]);
                    int minutes = Integer.parseInt(rawInput[1]);
                    int seconds = Integer.parseInt(rawInput[2]);

                    if (hours < 0 | minutes < 0 | seconds < 0){ throw new Exception();}

                    if (seconds >= 60){throw new Exception();}
                    if (minutes >= 60){throw new Exception();}

                    callback.handleResult(String.format("%02d", hours)+":"+String.format("%02d", minutes)+":"+String.format("%02d", seconds));
                } catch (Exception e){
                    e.printStackTrace();
                    buildDateTimeEntryDialog(tContext,title,description,callback);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {dialog.cancel();
            }
        });
        builder.show();
    }

    public interface dateTimeEntryDialogCallback {
        void handleResult(String result);
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////