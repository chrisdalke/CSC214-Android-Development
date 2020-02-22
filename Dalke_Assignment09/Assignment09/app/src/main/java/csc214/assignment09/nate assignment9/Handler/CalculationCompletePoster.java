////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 9
////////////////////////////////////////

package csc214.assignment09.Handler;

////////////////////////////////////////
// Calculation Poster
////////////////////////////////////////

public class CalculationCompletePoster implements Runnable {
    private final CalculationListener mListener;
    private long mResult;
    private int mResultType;

    //Redirects results of calculations back to the main calculation activity
    //takes in a result value and a result type (prime or square root)
    public CalculationCompletePoster(long result,CalculationListener listener, int resultType) {
        mListener = listener;
        mResult = result;
        mResultType = resultType;
    }

    @Override
    public void run() {
        mListener.jobComplete(mResult,mResultType);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
