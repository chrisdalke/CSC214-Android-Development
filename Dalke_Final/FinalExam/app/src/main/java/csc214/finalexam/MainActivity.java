////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Final Project
////////////////////////////////////////

package csc214.finalexam;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import csc214.finalexam.problem1.ProblemOneFirstActivity;
import csc214.finalexam.problem1.ProblemOneSecondActivity;
import csc214.finalexam.problem2.ProblemTwoActivity;
import csc214.finalexam.problem3.AActivity;
import csc214.finalexam.problem3.BActivity;
import csc214.finalexam.problem4.ImageDownloaderActivity;

////////////////////////////////////////
// Main Activity
////////////////////////////////////////

public class MainActivity extends AppCompatActivity {

    private Button buttonProblem1;
    private Button buttonProblem2;
    private Button buttonProblem3A;
    private Button buttonProblem3B;
    private Button buttonProblem4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonProblem1 = (Button)findViewById(R.id.buttonProblem1);
        buttonProblem2 = (Button)findViewById(R.id.buttonProblem2);
        buttonProblem3A = (Button)findViewById(R.id.buttonProblem3A);
        buttonProblem3B = (Button)findViewById(R.id.buttonProblem3B);
        buttonProblem4 = (Button)findViewById(R.id.buttonProblem4);
    }

    public void problemOneClicked(View view) {
        startActivity(new Intent(this, ProblemOneFirstActivity.class));
    }

    public void problemTwoClicked(View view) {
        startActivity(new Intent(this, ProblemTwoActivity.class));
    }

    public void problemThreeAClicked(View view) {
        startActivity(new Intent(this, AActivity.class));
    }

    public void problemThreeBClicked(View view) {
        startActivity(new Intent(this, BActivity.class));
    }

    public void problemFourClicked(View view) {
        startActivity(new Intent(this, ImageDownloaderActivity.class));
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
