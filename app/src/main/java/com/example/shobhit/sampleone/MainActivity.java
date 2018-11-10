package com.example.shobhit.sampleone;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {
    final String TAG = "MAINACTIVITY";
    AlertDialog.Builder builder;
    Button downloadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadFile = (Button) findViewById(R.id.downloadFile);
        builder = new AlertDialog.Builder(this);
        builder.setMessage("File is Moved!").setTitle("SampleOne");

        builder.setMessage("File is Moved!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();

                    }
                });



        downloadFile.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                new DownloadFilesTask().execute("");
            }
        });


    }


    private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivity.this, "",
                    "Moving. Please wait...", true);
            //copy the file content in bytes
        }

        protected Long doInBackground(String... urls) {
            InputStream inStream = null;
            OutputStream outStream = null;
            long totalSize=0;
            try {
                File dir = new File(Environment.getExternalStorageDirectory() + "/Download/EducationalVideos/");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File bfile = new File(dir.getAbsolutePath());
                inStream = getAssets().open("video.zip");
                outStream = new FileOutputStream(bfile.getAbsolutePath() + "/video.zip");
                byte[] buffer = new byte[1024];
                int length = 0;



                while ((length = inStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                    totalSize += length;
                }

                inStream.close();
                outStream.close();
                dialog.dismiss();




            } catch (IOException e) {
                e.printStackTrace();
            }

            return totalSize;
        }



        protected void onPostExecute(Long result) {


            AlertDialog alert = builder.create();
            alert.setTitle("SampleOne");
            alert.show();
        }
    }





}
