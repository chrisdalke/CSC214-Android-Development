////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Final Project
////////////////////////////////////////

package csc214.finalexam.problem2;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import csc214.finalexam.R;

////////////////////////////////////////
// Problem Two - Bottom Fragment
////////////////////////////////////////

public class ProblemTwoBottomFragment extends Fragment {

    private BottomCallbackListener callbackListener;

    private TextView textViewMessage;
    private Button buttonUpper;
    private Button buttonLower;

    public ProblemTwoBottomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbackListener = (BottomCallbackListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbackListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_problem_two_bottom, container, false);

        textViewMessage = (TextView)view.findViewById(R.id.tv_message);
        buttonUpper = (Button)view.findViewById(R.id.b_upper);
        buttonLower = (Button)view.findViewById(R.id.b_lower);

        Bundle bundle=getArguments();
        final String message = bundle.getString("MESSAGE");

        textViewMessage.setText(message);

        buttonLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackListener != null){
                    callbackListener.sendTop(message.toLowerCase());
                }
            }
        });

        buttonUpper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackListener != null){
                    callbackListener.sendTop(message.toUpperCase());
                }
            }
        });

        return view;
    }

    public interface BottomCallbackListener {
        void sendTop(String text);
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////
