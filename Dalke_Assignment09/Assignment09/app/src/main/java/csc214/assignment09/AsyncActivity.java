////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 9
////////////////////////////////////////

package csc214.assignment09;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import csc214.assignment09.Model.CalculationHandler;

////////////////////////////////////////
// Async Test Activity
////////////////////////////////////////

public class AsyncActivity extends AppCompatActivity {

    private EditText mInputNumber;
    private Button mButtonCalculatePrime;
    private Button mButtonCalculateSqrt;
    private TextView mResultPrime;
    private TextView mResultSqrt;

    private long mSavedNumberValue;
    private long mResultPrimeValue;
    private long mResultSqrtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        mInputNumber = (EditText)findViewById(R.id.input_number);
        mButtonCalculatePrime = (Button)findViewById(R.id.button_calculate_prime);
        mButtonCalculateSqrt = (Button)findViewById(R.id.button_calculate_sqrt);
        mResultPrime = (TextView)findViewById(R.id.result_prime);
        mResultSqrt = (TextView)findViewById(R.id.result_sqrt);

        mButtonCalculatePrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Long.parseLong(mInputNumber.getText().toString()) >= 2) {
                        new GetPrimeTask().execute(Long.parseLong(mInputNumber.getText().toString()));
                        mResultPrime.setText("Calculating Prime...");
                    } else {
                        Toast.makeText(getBaseContext(),"Invalid number! Please enter a number above 1.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Toast.makeText(getBaseContext(),"Invalid number! Please enter a number above 1.",Toast.LENGTH_SHORT).show();

                }
            }
        });

        mButtonCalculateSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Long.parseLong(mInputNumber.getText().toString()) >= 2) {
                        new GetSqrtTask().execute(Long.parseLong(mInputNumber.getText().toString()));
                        mResultSqrt.setText("Calculating Square Root...");
                    } else {

                        Toast.makeText(getBaseContext(),"Invalid number! Please enter a number above 1.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Toast.makeText(getBaseContext(),"Invalid number! Please enter a number above 1.",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //Async tasks to find prime and sqrt

    class GetPrimeTask extends AsyncTask<Long,Void,Void> {
        @Override
        protected Void doInBackground(Long... inputNum) {
            final long result = CalculationHandler.calculateLargestPrime(inputNum[0]);

            AsyncActivity.this.runOnUiThread(new Runnable()
            { public void run() { updatePrimeResult(result); } });
            return null;
        }
    }
    class GetSqrtTask extends AsyncTask<Long,Void,Void> {
        @Override
        protected Void doInBackground(Long... inputNum) {
            final long result = CalculationHandler.calculateSquareRoot(inputNum[0]);

            AsyncActivity.this.runOnUiThread(new Runnable()
            { public void run() { updateSqrtResult(result); } });
            return null;
        }
    }

    //Functions to apply result of async tasks

    public void updatePrimeResult(long result){
        Toast.makeText(getBaseContext(),"Calculated Prime: "+result,Toast.LENGTH_SHORT).show();
        mResultPrimeValue = result;
        mResultPrime.setText("Calculated Prime: "+mResultPrimeValue+"");
    }
    public void updateSqrtResult(long result){
        Toast.makeText(getBaseContext(),"Calculated Square Root: "+result,Toast.LENGTH_SHORT).show();
        mResultSqrtValue = result;
        mResultSqrt.setText("Calculated Square Root: "+mResultSqrtValue+"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_back: {
                finish();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
