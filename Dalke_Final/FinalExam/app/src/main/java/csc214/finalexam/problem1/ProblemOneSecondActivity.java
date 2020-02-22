////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Final Project
////////////////////////////////////////

package csc214.finalexam.problem1;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import csc214.finalexam.R;

////////////////////////////////////////
// Problem One - Second Activity
////////////////////////////////////////

public class ProblemOneSecondActivity extends AppCompatActivity {

    public static final String TAG = "PROBLEM_1_SECOND";

    private TextView textViewOp1;
    private TextView textViewOp2;
    private TextView textViewResult;
    private EditText editTextSubtract;
    private long result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_one_second);

        textViewOp1 = (TextView)findViewById(R.id.tv_op1);
        textViewOp2 = (TextView)findViewById(R.id.tv_op2);
        textViewResult = (TextView)findViewById(R.id.tv_result);
        editTextSubtract = (EditText) findViewById(R.id.et_subtract);

        Intent i = getIntent();
        textViewOp1.setText(i.getStringExtra("OP_1"));
        textViewOp2.setText(i.getStringExtra("OP_2"));
        result = 0;
        try {
            result = (long)Math.pow(Integer.parseInt(i.getStringExtra("OP_1")),Integer.parseInt(i.getStringExtra("OP_2")));
        } catch (Exception e){

        }

        textViewResult.setText(result+"");

        if (savedInstanceState != null){
            editTextSubtract.setText(savedInstanceState.getCharSequence("editTextSubtract"));
        }

        Log.i(TAG,"Problem One Second Activity - onCreate.");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("editTextSubtract",editTextSubtract.getText());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"Problem One Second Activity - onStart.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"Problem One Second Activity - onRestart.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Problem One Second Activity - onDestroy.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"Problem One Second Activity - onStop.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"Problem One Second Activity - onPause.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"Problem One Second Activity - onResume.");
    }
    
    public void subtractClicked(View view) {
        try {
            result = result - Integer.parseInt(editTextSubtract.getText().toString());

            Intent returnIntent = new Intent();
            returnIntent.putExtra("OP_RESULT",result+"");
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        } catch (Exception e){

        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
