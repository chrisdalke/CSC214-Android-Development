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
// Module Imports
////////////////////////////////////////

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

////////////////////////////////////////
// Calculation Handler Thread
////////////////////////////////////////
// Handles threaded calculations

public class CalculationHandlerThread extends HandlerThread {
    private static final String TAG = "CalculationHandlerThread";
    private static final int MESSAGE_CALCULATION_HANDLER_PRIME = 1;
    private static final int MESSAGE_CALCULATION_HANDLER_SQRT = 2;

    public interface CalculationProgressListener extends CalculationListener {
    }

    private Handler mHandler;
    private Handler mResponseHandler;
    private CalculationProgressListener mListener;

    public CalculationHandlerThread(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
    }

    //Set the listener for calculation progress
    public void setCalculationProgressListener(CalculationProgressListener listener) {
        mListener = listener;
    }

    //Trigger calculation of the prime
    public void calculatePrime(long input) {
        mHandler.obtainMessage(MESSAGE_CALCULATION_HANDLER_PRIME, input).sendToTarget();
    }

    //Trigger calculation of the sqrt
    public void calculateSqrt(long input) {
        mHandler.obtainMessage(MESSAGE_CALCULATION_HANDLER_SQRT, input).sendToTarget();
    }

    @Override
    protected void onLooperPrepared() {
        mHandler = new CalculationHandler(mResponseHandler,mListener);
    }

    //Handler class, which receives the result of the threaded calculation
    private static class CalculationHandler extends Handler {
        private final Handler mResponseHandler;
        private final CalculationProgressListener mListener;

        CalculationHandler(Handler responseHandler,CalculationProgressListener listener) {
            mResponseHandler = responseHandler;
            mListener = listener;
        }

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == MESSAGE_CALCULATION_HANDLER_PRIME) {

                final long result = csc214.assignment09.Model.CalculationHandler.calculateLargestPrime((long)msg.obj);
                mResponseHandler.post(new CalculationCompletePoster(result, mListener, MESSAGE_CALCULATION_HANDLER_PRIME));
            }
            if(msg.what == MESSAGE_CALCULATION_HANDLER_SQRT) {

                final long result = csc214.assignment09.Model.CalculationHandler.calculateSquareRoot((long)msg.obj);
                mResponseHandler.post(new CalculationCompletePoster(result, mListener, MESSAGE_CALCULATION_HANDLER_SQRT));
            }
        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
