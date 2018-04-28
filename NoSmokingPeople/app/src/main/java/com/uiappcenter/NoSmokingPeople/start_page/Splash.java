package com.uiappcenter.NoSmokingPeople.start_page;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.uiappcenter.NoSmokingPeople.R;
import com.uiappcenter.NoSmokingPeople.main_menu.MainActivity;


/**
 * Created by 성민 on 2017-07-18.
 */

public class Splash extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_splash);

        TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView1 = (ImageView) findViewById(R.id.icon);
        ImageView imageView2 = (ImageView) findViewById(R.id.character);

        SharedPreferences qq = getSharedPreferences("WW", MODE_PRIVATE);
        SharedPreferences qq2 = getSharedPreferences("shared", MODE_PRIVATE);
        int certain = qq.getInt("WriteYear",0);
        String userName = qq2.getString("nameOFuser", null);

        if(certain == 0 || userName ==null){
            SharedPreferences.Editor editor = qq.edit();
            editor.putBoolean("isFirst",true);
            editor.commit();
            WaitingTime();
        }
        else{
            WaitingTime1();
        }
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
                    finish();
                } catch (InterruptedException e) {

                }
            }

        };
        SplashThread.start();
    }

    private void WaitingTime1(){
        Thread SplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int wait = 0;
                    while (wait < 1000) {
                        sleep(100);
                        wait += 100;
                    }
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {

                }
            }

        };
        SplashThread.start();
    }
}
