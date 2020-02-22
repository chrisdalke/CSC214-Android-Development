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

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;

import csc214.project02.Database.Collections.UserDatabaseCollection;
import csc214.project02.MainActivity;
import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.User;
import csc214.project02.PostCreateActivity;
import csc214.project02.R;

import static csc214.project02.PostCreateActivity.KEY_POST_IMAGE_FILENAME;

////////////////////////////////////////
// Account Modification Activity
////////////////////////////////////////

public class AccountModifyActivity extends AppCompatActivity {

    public EditText mAccountCreateFullName;
    public EditText mAccountCreateBio;
    public EditText mAccountCreateLocation;
    public ImageView mAccountImageView;
    public Button mAccountImageButton;
    public String mAccountImageFilename = null;
    public File mAccountImageFile;

    public static final String KEY_FULL_NAME = "FULL_NAME";
    public static final String KEY_BIO = "BIO";
    public static final String KEY_LOCATION = "LOCATION";
    public static final String KEY_ACCOUNT_IMAGE_FILENAME = "IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_modify);

        mAccountCreateFullName = (EditText)findViewById(R.id.account_create_full_name);
        mAccountCreateBio = (EditText)findViewById(R.id.account_create_bio);
        mAccountCreateLocation = (EditText)findViewById(R.id.account_create_location);
        mAccountImageView = (ImageView)findViewById(R.id.edit_profile_image_view);
        mAccountImageButton = (Button)findViewById(R.id.edit_profile_image_button);

        if (savedInstanceState != null){
            mAccountCreateFullName.setText(savedInstanceState.getCharSequence(KEY_FULL_NAME));
            mAccountCreateBio.setText(savedInstanceState.getCharSequence(KEY_BIO));
            mAccountCreateLocation.setText(savedInstanceState.getCharSequence(KEY_LOCATION));
            mAccountImageFilename = savedInstanceState.getString(KEY_POST_IMAGE_FILENAME,null);
        } else {
            User user = ApplicationState.getInstance().getUser();
            mAccountCreateFullName.setText(user.getFullName());
            mAccountCreateBio.setText(user.getBio());
            mAccountCreateLocation.setText(user.getHomeLocation());
            mAccountImageFilename = user.getPhotoFile();
        }

        updateImageUI();

        mAccountImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAccountImageFilename == null) {
                    takePhoto();
                } else {
                    //Remove photo
                    removePhoto();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            mAccountImageFilename = mAccountImageFile.getPath();

            //Show the post image and display the image hide button
            updateImageUI();
        }
    }

    public void removePhoto(){
        mAccountImageFilename = null;
        mAccountImageFile = null;
        updateImageUI();
    }

    public void takePhoto() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        String filename = "IMG_" + UUID.randomUUID().toString() + ".jpg";
        File picturesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mAccountImageFile = new File(picturesDir, filename);
        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", mAccountImageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        startActivityForResult(intent, 0);
    }

    public void updateImageUI(){
        Log.i("PHOTOS","Updating Image UI...");
        if (mAccountImageFilename != null){
            Log.i("PHOTOS","Adding image to user profile...");
            Log.i("PHOTOS","Filename="+mAccountImageFilename);
            Bitmap photo = PostCreateActivity.getScaledBitmap(mAccountImageFilename, 256,256);
            mAccountImageView.setImageBitmap(photo);
            mAccountImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mAccountImageButton.setText("Remove Profile Picture");
        } else {
            Log.i("PHOTOS","Removing image...");
            mAccountImageButton.setText("Add Profile Picture");
            mAccountImageView.setImageResource(R.drawable.ic_default_profile);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence(KEY_FULL_NAME,mAccountCreateFullName.getText());
        outState.putCharSequence(KEY_BIO,mAccountCreateBio.getText());
        outState.putCharSequence(KEY_LOCATION,mAccountCreateLocation.getText());
        outState.putCharSequence(KEY_ACCOUNT_IMAGE_FILENAME,mAccountImageFilename);
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
                    //Update user profile info before returning to the main screen
                    User user = ApplicationState.getInstance().getUser();
                    user.setFullName(mAccountCreateFullName.getText().toString());
                    user.setBio(mAccountCreateBio.getText().toString());
                    user.setHomeLocation(mAccountCreateLocation.getText().toString());
                    user.setPhotoFile(mAccountImageFilename);

                    //Update user in the database
                    UserDatabaseCollection userDb = new UserDatabaseCollection(getApplicationContext());
                    userDb.updateUser(user);

                    //BAD HACK: Return to the profile page by starting a new activity
                    //For some reason I cant get it to refresh otherwise
                    startActivity(AccountViewActivity.buildIntent(getApplicationContext(),ApplicationState.getInstance().getUser()));

                    finish();
                } catch (Exception e){
                    //Display dialog stating the error
                    e.printStackTrace();

                    new AlertDialog.Builder(AccountModifyActivity.this)
                            .setTitle("Error")
                            .setMessage("Could not update user!")
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