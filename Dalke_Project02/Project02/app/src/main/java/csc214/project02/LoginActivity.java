////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 2
////////////////////////////////////////

package csc214.project02;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import csc214.project02.Activities.Account.AccountCreateActivity;
import csc214.project02.Database.Collections.UserDatabaseCollection;
import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.User;
import csc214.project02.Utility.PasswordHasher;

////////////////////////////////////////
// Login Activity
////////////////////////////////////////

public class LoginActivity extends AppCompatActivity {

    private final String KEY_LOGIN_USERNAME = "LOGIN_USERNAME";
    private final String KEY_LOGIN_PASSWORD = "LOGIN_PASSWORD";

    private EditText mLoginUsername;
    private EditText mLoginPassword;
    private Button mLoginButton;
    private Button mCreateAccountButton;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence(KEY_LOGIN_USERNAME,mLoginUsername.getText());
        outState.putCharSequence(KEY_LOGIN_PASSWORD,mLoginPassword.getText());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginUsername = (EditText)findViewById(R.id.login_username);
        mLoginPassword = (EditText)findViewById(R.id.login_password);

        mLoginButton = (Button)findViewById(R.id.login_button);
        mCreateAccountButton = (Button)findViewById(R.id.create_account_button);

        if (savedInstanceState != null){
            mLoginUsername.setText(savedInstanceState.getCharSequence(KEY_LOGIN_USERNAME));
            mLoginPassword.setText(savedInstanceState.getCharSequence(KEY_LOGIN_PASSWORD));
        }

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AccountCreateActivity.class));

            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationState state = ApplicationState.getInstance();

                UserDatabaseCollection userDb = new UserDatabaseCollection(getApplicationContext());
                System.out.println("Available users:");
                for (User user : userDb.getAllUsers()){
                    System.out.println(user);
                }
                System.out.println("Trying login as:");
                User temp = new User();
                temp.setUsername(mLoginUsername.getText().toString());
                temp.setHashedPassword(PasswordHasher.hash(mLoginPassword.getText().toString()));
                System.out.println(temp);

                User loginUser = userDb.getUserLogin(mLoginUsername.getText().toString(), PasswordHasher.hash(mLoginPassword.getText().toString()));
                System.out.println("Found user:");
                System.out.println(loginUser);

                if (loginUser != null){
                    state.setLoggedIn(true);
                    state.setUser(loginUser);
                }

                if (state.isLoggedIn()){
                    //If we are logged in, move to the main state
                    //start new activity for main login
                    startActivity(new Intent(getBaseContext(), MainActivity.class));

                    //finish login activity so we cant go back to it
                    finish();

                } else {
                    //Not logged in. Clear login prompt and display a dialog
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Error")
                            .setMessage("Could not login. Please check your information and try again!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    //Clear fields
                    mLoginUsername.setText("");
                    mLoginPassword.setText("");
                }
            }
        });
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////