////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 2
////////////////////////////////////////

package csc214.project02.Activities.Account;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import csc214.project02.Database.Collections.UserDatabaseCollection;
import csc214.project02.Model.User;
import csc214.project02.R;
import csc214.project02.Utility.PasswordHasher;

////////////////////////////////////////
// Account Creation Activity
////////////////////////////////////////

public class AccountCreateActivity extends AppCompatActivity {

    public EditText mAccountCreateEmail;
    public EditText mAccountCreatePassword;
    public EditText mAccountCreateUsername;
    public EditText mAccountCreateFullName;
    public EditText mAccountCreateBio;
    public EditText mAccountCreateLocation;

    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_PASSWORD = "PASSWORD";
    public static final String KEY_USERNAME = "USERNAME";
    public static final String KEY_FULL_NAME = "FULL_NAME";
    public static final String KEY_BIO = "BIO";
    public static final String KEY_LOCATION = "LOCATION";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_create);

        mAccountCreateEmail = (EditText)findViewById(R.id.account_create_email);
        mAccountCreatePassword = (EditText)findViewById(R.id.account_create_password);
        mAccountCreateUsername = (EditText)findViewById(R.id.account_create_username);
        mAccountCreateFullName = (EditText)findViewById(R.id.account_create_full_name);
        mAccountCreateBio = (EditText)findViewById(R.id.account_create_bio);
        mAccountCreateLocation = (EditText)findViewById(R.id.account_create_location);
        
        if (savedInstanceState != null){
            mAccountCreateEmail.setText(savedInstanceState.getCharSequence(KEY_EMAIL));
            mAccountCreatePassword.setText(savedInstanceState.getCharSequence(KEY_PASSWORD));
            mAccountCreateUsername.setText(savedInstanceState.getCharSequence(KEY_USERNAME));
            mAccountCreateFullName.setText(savedInstanceState.getCharSequence(KEY_FULL_NAME));
            mAccountCreateBio.setText(savedInstanceState.getCharSequence(KEY_BIO));
            mAccountCreateLocation.setText(savedInstanceState.getCharSequence(KEY_LOCATION));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        
        outState.putCharSequence(KEY_EMAIL,mAccountCreateEmail.getText());
        outState.putCharSequence(KEY_PASSWORD,mAccountCreatePassword.getText());
        outState.putCharSequence(KEY_USERNAME,mAccountCreateUsername.getText());
        outState.putCharSequence(KEY_FULL_NAME,mAccountCreateFullName.getText());
        outState.putCharSequence(KEY_BIO,mAccountCreateBio.getText());
        outState.putCharSequence(KEY_LOCATION,mAccountCreateLocation.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirm_cancel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_confirm: {

                try {
                    //Check if any of the required fields are blank
                    if (mAccountCreateEmail.getText().toString().length() == 0){
                        throw new Exception("You must enter an Email!");
                    }
                    if (mAccountCreatePassword.getText().toString().length() == 0){
                        throw new Exception("You must enter a Password!");
                    }
                    if (mAccountCreateUsername.getText().toString().length() == 0){
                        throw new Exception("You must enter a Username!");
                    }

                    //Create the new account before going back to the login screen
                    User user = new User();
                    user.setEmail(mAccountCreateEmail.getText().toString());
                    user.setHashedPassword(PasswordHasher.hash(mAccountCreatePassword.getText().toString()));
                    user.setUsername(mAccountCreateUsername.getText().toString());
                    user.setFullName(mAccountCreateFullName.getText().toString());
                    user.setBio(mAccountCreateBio.getText().toString());
                    user.setHomeLocation(mAccountCreateLocation.getText().toString());

                    //Check if the username already exists, or a user with that email already exists.
                    UserDatabaseCollection userDb = new UserDatabaseCollection(getApplicationContext());
                    if (userDb.getUserByUsername(mAccountCreateUsername.getText().toString()) != null){
                        throw new Exception("User with this username already exists!");
                    }
                    if (userDb.getUserByEmail(mAccountCreateEmail.getText().toString()) != null){
                        throw new Exception("User with this email already exists!");
                    }

                    userDb.addUser(user);

                    finish();
                } catch (Exception e){
                    //Display dialog stating the error

                    new AlertDialog.Builder(AccountCreateActivity.this)
                            .setTitle("Error")
                            .setMessage(e.getMessage())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                return true;
            }
            case R.id.menu_cancel: {
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