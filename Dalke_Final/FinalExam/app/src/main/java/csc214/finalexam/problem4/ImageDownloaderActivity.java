////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Final Project
////////////////////////////////////////

package csc214.finalexam.problem4;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import csc214.finalexam.R;

////////////////////////////////////////
// Image Downloader Activity
////////////////////////////////////////

public class ImageDownloaderActivity extends AppCompatActivity {
    private static final String TAG = "ImgDler";

    private static final String IMG_PATH = "http://i.imgur.com/kZOMq.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_downloader);
    }

    public void downloadPressed(View view) {
        new GetImageTask().execute(IMG_PATH);
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

            ImageDownloaderActivity.this.runOnUiThread(new Runnable()
            {
                public void run() {
                    downloadFinished(resultBmp);
                }
            });
            return null;
        }
    }

    public void downloadFinished(Bitmap result){
        ImageView imageView = (ImageView)findViewById(R.id.iv_image);
        imageView.setImageBitmap(result);
    }

    // use this method to scale the image
    private Bitmap getScaledBitmap(String path) {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        return getScaledBitmap(path, size.x, size.y);
    }

    private static Bitmap getScaledBitmap(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        Log.d(TAG, "requested width=" + width + "," + "requested height=" + height);
        Log.d(TAG, "srcWidth=" + srcWidth + "," + "srcHeight=" + srcHeight);

        int sampleSize = 1;
        if(srcHeight > height || srcWidth > width ) {
            if(srcWidth > srcHeight) {
                sampleSize = Math.round(srcHeight / height);
            }
            else {
                sampleSize = Math.round(srcWidth / width);
            }
        }

        Log.d(TAG, "sampleSize=" + sampleSize);

        BitmapFactory.Options scaledOptions = new BitmapFactory.Options();
        scaledOptions.inSampleSize = sampleSize;

        //return BitmapFactory.decodeFile(path, scaledOptions);
        return BitmapFactory.decodeFile(path);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
