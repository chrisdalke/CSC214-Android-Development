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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import csc214.finalexam.R;

////////////////////////////////////////
// Problem One - First Activity
////////////////////////////////////////

public class ProblemOneFirstActivity extends AppCompatActivity {

    public static final String TAG = "PROBLEM_1_FIRST";
    static final int PROBLEM_1_REQUEST = 1;

    private EditText editTextOp1;
    private EditText editTextOp2;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_one_first);

        editTextOp1 = (EditText)findViewById(R.id.et_op1);
        editTextOp2 = (EditText)findViewById(R.id.et_op2);
        textViewResult = (TextView)findViewById(R.id.tv_result);

        if (savedInstanceState != null){
            editTextOp1.setText(savedInstanceState.getCharSequence("editTextOp1"));
            editTextOp2.setText(savedInstanceState.getCharSequence("editTextOp2"));
            textViewResult.setText(savedInstanceState.getCharSequence("textViewResult"));
        }

        Log.i(TAG,"Problem One First Activity - onCreate.");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("editTextOp1",editTextOp1.getText());
        outState.putCharSequence("editTextOp2",editTextOp2.getText());
        outState.putCharSequence("textViewResult",textViewResult.getText());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"Problem One First Activity - onStart.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"Problem One First Activity - onRestart.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Problem One First Activity - onDestroy.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"Problem One First Activity - onStop.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"Problem One First Activity - onPause.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"Problem One First Activity - onResume.");
    }

    public void powClicked(View view) {
        Intent i = new Intent(this, ProblemOneSecondActivity.class);
        i.putExtra("OP_1",editTextOp1.getText().toString());
        i.putExtra("OP_2",editTextOp2.getText().toString());
        startActivityForResult(i,PROBLEM_1_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PROBLEM_1_REQUEST){
            if (resultCode == RESULT_OK){
                textViewResult.setText(data.getStringExtra("OP_RESULT"));
            }
        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////