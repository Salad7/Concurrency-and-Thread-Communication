package com.example.mohamed.threadconcur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t = new Thread(new DoWork());

        t.run();

    }


    public class DoWork implements Runnable{

        @Override
        public void run() {
                for(int i = 0; i < 100; i++){

                }
            }
        }
}
