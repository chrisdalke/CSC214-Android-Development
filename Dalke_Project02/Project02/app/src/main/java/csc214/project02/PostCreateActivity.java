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

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import csc214.project02.Database.Collections.PostDatabaseCollection;
import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.Post;

////////////////////////////////////////
// Post Creation Activity
////////////////////////////////////////

public class PostCreateActivity extends AppCompatActivity {

    public static final String KEY_POST_TEXT = "POST_TEXT";
    public static final String KEY_POST_IMAGE_FILENAME = "POST_FILENAME";

    public TextView mPostBody;
    public Button mPostImageButton;
    public ImageView mPostImageView;
    public String postImageFilename = null;
    public File mImageFilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_create);

        mPostBody = (TextView)findViewById(R.id.activity_post_body);
        mPostImageButton = (Button)findViewById(R.id.activity_post_image_button);
        mPostImageView = (ImageView)findViewById(R.id.activity_post_image_view);

        if (savedInstanceState != null){
            mPostBody.setText(savedInstanceState.getCharSequence(KEY_POST_TEXT));
            postImageFilename = savedInstanceState.getString(KEY_POST_IMAGE_FILENAME,null);
        }

        updateImageUI();

        mPostImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (postImageFilename == null) {
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
            postImageFilename = mImageFilename.getPath();

            //Show the post image and display the image hide button
            updateImageUI();
        }
    }

    public void removePhoto(){
        mImageFilename = null;
        postImageFilename = null;
        updateImageUI();
    }

    public void takePhoto() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        String filename = "IMG_" + UUID.randomUUID().toString() + ".jpg";
        File picturesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mImageFilename = new File(picturesDir, filename);
        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", mImageFilename);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        startActivityForResult(intent, 0);
    }

    public void updateImageUI(){
        Log.i("PHOTOS","Updating Image UI...");
        if (postImageFilename != null){
            Log.i("PHOTOS","Adding image...");
            Log.i("PHOTOS","Filename="+postImageFilename);
            Bitmap photo = getScaledBitmap(postImageFilename, 256,256);
            mPostImageView.setImageBitmap(photo);
            mPostImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mPostImageButton.setText("Remove Picture");
        } else {
            Log.i("PHOTOS","Removing image...");
            mPostImageButton.setText("Add a Picture");
            mPostImageView.setImageDrawable(null);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence(KEY_POST_TEXT,mPostBody.getText());
        outState.putString(KEY_POST_IMAGE_FILENAME,postImageFilename);
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
                //Check if the post has text or an image
                if (mPostBody.getText().toString().length() > 0 | postImageFilename != null) {


                    //Create the post before returning
                    Post post = new Post();
                    post.setPostContents(mPostBody.getText().toString());
                    post.setPostImage(postImageFilename != null ? "true" : "false");
                    post.setPostImageFilename(postImageFilename);
                    post.setPostUser(ApplicationState.getInstance().getUser().getUsername());

                    Format formatter = new SimpleDateFormat("EEEE MMM dd, yyyy hh:mma");
                    String s = formatter.format(new Date());
                    post.setPostDate(s);

                    //Set the ID for the post when we add the post to the database
                    (new PostDatabaseCollection(getApplicationContext())).addPost(post);

                    finish();
                } else {
                    //Display alert saying you have to add content to the message
                    new AlertDialog.Builder(PostCreateActivity.this)
                            .setTitle("Error")
                            .setMessage("Please add text or image to the post before submitting!")
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

    //Function from Bobby's presentation to scale a bitmap
    public static Bitmap getScaledBitmap(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int sampleSize = 1;

        if(srcHeight > height || srcWidth > width ) {
            if(srcWidth > srcHeight) {
                sampleSize = Math.round(srcHeight / height);
            } else {
                sampleSize = Math.round(srcWidth / width);
            }
        }

        BitmapFactory.Options scaledOptions = new BitmapFactory.Options();
        scaledOptions.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(path, scaledOptions);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////