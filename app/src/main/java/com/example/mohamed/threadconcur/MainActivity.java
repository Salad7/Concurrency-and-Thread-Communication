package com.example.mohamed.threadconcur;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    Handler handler;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Downloading...");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


        new Thread(new DoWork()).start();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

            switch (message.what){
                case DoWork.STATUS_START:
                    progressDialog.show();
                    break;
                case DoWork.STATUS_STEP:
                progressDialog.setProgress((Integer)message.obj);
                    break;
                case DoWork.STATUS_DONE:

                    break;
                default:
                    break;

                }

                return false;
            }
        });

        //t.run();

    }


    public class DoWork implements Runnable{

        final static int STATUS_START = 0x00;
        final static int STATUS_STEP = 0x01;
        final static int STATUS_DONE = 0x02;


        @Override
        public void run() {
            Message msg = new Message();
            msg.what= STATUS_START;
            handler.sendMessage(msg);
                for(int i = 0; i < 100; i++){
                    for(int x = 0; x < 100000000; x++){
                        msg = new Message();
                        msg.what= STATUS_STEP;
                        msg.obj = i;
                        handler.sendMessage(msg);
                    }
                }
            msg = new Message();
            msg.what= STATUS_DONE;
            handler.sendMessage(msg);
            }
        }
}
