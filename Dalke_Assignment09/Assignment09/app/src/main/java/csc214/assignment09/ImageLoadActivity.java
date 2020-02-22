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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import csc214.assignment09.Model.CalculationHandler;

////////////////////////////////////////
// Image Load Test Activity
////////////////////////////////////////

public class ImageLoadActivity extends AppCompatActivity {

    private ImageView mImageView;
    private EditText mEditText;
    private Button mButton;

    private static String KEY_URL = "URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);

        mImageView = (ImageView)findViewById(R.id.image_view);
        mButton = (Button)findViewById(R.id.image_button);
        mEditText = (EditText)findViewById(R.id.image_url);

        if (savedInstanceState != null){
            mEditText.setText(savedInstanceState.getString(KEY_URL));
        } else {
            mEditText.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/PM5544_with_non-PAL_signals.png/320px-PM5544_with_non-PAL_signals.png");
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButton.setEnabled(false);
                new GetImageTask().execute(mEditText.getText().toString());
            }
        });
    }

    class GetImageTask extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... inputString) {
            String url = inputString[0];

            //Load the image URL into a bitmap and call the activity's image update function
            Bitmap resultBitmap = null;
            try {
                URL urlObject = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                resultBitmap = BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
                resultBitmap = null;
            }
            final Bitmap resultBmp = resultBitmap;

            ImageLoadActivity.this.runOnUiThread(new Runnable()
                {
                    public void run() {
                        updateImageView(resultBmp);
                }
            });
            return null;
        }
    }

    //Update the image view with the specified bitmap
    private void updateImageView(Bitmap resultImage){
        if (resultImage == null){
            mImageView.setImageResource(R.drawable.ic_image_frame);
        } else {
            mImageView.setImageBitmap(resultImage);
        }
        mButton.setEnabled(true);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_URL,mEditText.getText().toString());
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
