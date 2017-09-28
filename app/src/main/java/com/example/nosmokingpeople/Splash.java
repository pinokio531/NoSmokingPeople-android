package com.example.nosmokingpeople;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by 성민 on 2017-07-18.
 */

public class Splash extends Activity{

    private ActivityList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_splash);
        WaitingTime();

        list = new ActivityList();
        list.addList();

        TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView1 = (ImageView) findViewById(R.id.icon);
        ImageView imageView2 = (ImageView) findViewById(R.id.character);


    }

    private void WaitingTime() {
        Thread SplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int wait = 0;
                    while (wait < 1000) {
                        sleep(100);
                        wait += 100;
                    }
                    Intent intent = new Intent(Splash.this, Search.class);
                    startActivity(intent);
                    Splash.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    Splash.this.finish();
                }
            }

        };
        SplashThread.start();


    }
}
