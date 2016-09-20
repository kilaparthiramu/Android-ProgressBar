package com.example.rkilaparthi.progresssbar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    Button b1;
    ProgressBar p;
    TextView t;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1	=	(Button) findViewById(R.id.button_start);
        p = 	(ProgressBar) findViewById(R.id.progress);
        t= (TextView) findViewById(R.id.textview_download);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                b1.setEnabled(false);
                new ShowDialogAsyncTask().execute();
            }
        });
    }


    private class ShowDialogAsyncTask extends AsyncTask<Void, Integer, Void>{

        int progress_status;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Toast.makeText(MainActivity.this,
                    "Invoke onPreExecute()", Toast.LENGTH_SHORT).show();

            progress_status	=	0;
            t.setText("downloading 0%");

        }

        @Override
        protected Void doInBackground(Void... params) {

            while(progress_status<100){

                progress_status += 2;

                publishProgress(progress_status);
                SystemClock.sleep(300);

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            p.setProgress(values[0]);
            t.setText("downloading " +values[0]+"%");

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Toast.makeText(MainActivity.this,
                    "Invoke onPostExecute()", Toast.LENGTH_SHORT).show();

            t.setText("download complete");
            b1.setEnabled(true);
        }

    }
}
