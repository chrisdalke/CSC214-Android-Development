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

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import csc214.assignment09.Handler.CalculationHandlerThread;
import csc214.assignment09.Model.CalculationHandler;

////////////////////////////////////////
// Threaded Test Activity
////////////////////////////////////////

public class ThreadedActivity extends AppCompatActivity {

    private EditText mInputNumber;
    private Button mButtonCalculatePrime;
    private Button mButtonCalculateSqrt;
    private TextView mResultPrime;
    private TextView mResultSqrt;

    private long mSavedNumberValue;
    private long mResultPrimeValue;
    private long mResultSqrtValue;

    private CalculationHandlerThread mCalculationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        mInputNumber = (EditText)findViewById(R.id.input_number);
        mButtonCalculatePrime = (Button)findViewById(R.id.button_calculate_prime);
        mButtonCalculateSqrt = (Button)findViewById(R.id.button_calculate_sqrt);
        mResultPrime = (TextView)findViewById(R.id.result_prime);
        mResultSqrt = (TextView)findViewById(R.id.result_sqrt);

        Handler responseHandler = new Handler();
        mCalculationHandler = new CalculationHandlerThread(responseHandler);
        mCalculationHandler.setCalculationProgressListener(new CalculationHandlerThread.CalculationProgressListener() {
            @Override
            public void jobComplete(long result, int resultType) {
                if (resultType == 1){
                    updatePrimeResult(result);
                } else if (resultType == 2){
                    updateSqrtResult(result);
                }
            }
        });
        mCalculationHandler.start();
        mCalculationHandler.getLooper();

        mButtonCalculatePrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Long.parseLong(mInputNumber.getText().toString()) >= 2) {
                    mCalculationHandler.calculatePrime(Long.parseLong(mInputNumber.getText().toString()));
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
                        mCalculationHandler.calculateSqrt(Long.parseLong(mInputNumber.getText().toString()));
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
