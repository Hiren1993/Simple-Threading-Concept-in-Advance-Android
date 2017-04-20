package com.example.user.simpleasynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountingTask tsk = new CountingTask();
        tsk.execute();
    }

    private class CountingTask extends AsyncTask<Void,Integer,Integer>{
        CountingTask(){}

        @Override
        protected Integer doInBackground(Void... params) {
            int i = 0;
            while (i < 100) {
                SystemClock.sleep(250);
                i++;
                if (i % 5 == 0) {
                // update UI with progress every 5%
                publishProgress(i);
                }
            }
            return i;
        }

        protected void onProgressUpdate(Integer... progress) {
            TextView tv = (TextView) findViewById(R.id.count);
            tv.setText(progress[0] + "% Complete!");
        }
        protected void onPostExecute(Integer result) {
            TextView tv = (TextView) findViewById(R.id.count);
            tv.setText("Count Complete! Counted to " + result.toString());
        }
    }
}
