package com.anirban.myapp.assignment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends Activity {

    private final static String TAG = "ThreadingAsyncTask";
    //private ImageView mImageView;
    private ProgressBar mProgressBar,mProg,mprog3,mprog4;

    private int mDelay = 500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProg= (ProgressBar) findViewById(R.id.progressBar2);
        mprog3=(ProgressBar)findViewById(R.id.progressBar3);
        mprog4=(ProgressBar)findViewById(R.id.progressBar4);
        final Button button = (Button) findViewById(R.id.loadButton);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                new LoadIconTask().execute(R.drawable.painter);
            }
        });


    }

    class LoadIconTask extends AsyncTask<Integer,Integer,Bitmap> {


        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
            mProg.setVisibility(ProgressBar.VISIBLE);
            mprog3.setVisibility(ProgressBar.VISIBLE);
            mprog4.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Integer...resid) {
            Bitmap tmp = BitmapFactory.decodeResource(getResources(), resid[0]);
            // simulating long-running operation
            for (int i = 1; i < 11; i++) {
                sleep();
                publishProgress(i * 10);
            }
            return tmp;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
            mProg.setProgress(values[0]);
            mprog3.setProgress(values[0]);
            mprog4.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap r1) {
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            mProg.setVisibility(ProgressBar.INVISIBLE);
            mprog3.setVisibility(ProgressBar.INVISIBLE);
            mprog4.setVisibility(ProgressBar.INVISIBLE);

        }

        private void sleep() {
            try {
                Thread.sleep(mDelay);
            } catch (InterruptedException e) {
                Log.e(TAG, e.toString());
            }
        }
    }
}