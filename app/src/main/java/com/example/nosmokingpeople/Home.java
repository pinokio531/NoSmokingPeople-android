package com.example.nosmokingpeople;

import android.content.Intent;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 성민 on 2017-08-06.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class Home extends Fragment {
    int Hday;
    int Hhour;
    int Hminutes;
    int Hsecond;
    double price;
    double amount;

    private final Handler handler = new Handler();
    private TextView Time_first;

    public Home() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container2, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.main_home, container2, false);
        TextView textView = (TextView) layout.findViewById(R.id.noSmokingGoal);
        TextView textView2 = (TextView) layout.findViewById(R.id.dayGoal);
        TextView content1 = (TextView) layout.findViewById(R.id.text1);
        TextView content2 = (TextView) layout.findViewById(R.id.text2);
        TextView content3 = (TextView) layout.findViewById(R.id.text3);
        TextView content4 = (TextView) layout.findViewById(R.id.text4);
        final ProgressBar progressBar = (ProgressBar) layout.findViewById(R.id.progressBar);


        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "BMHANNA_11yrs_ttf.ttf");
        textView.setTypeface(typeface);
        textView2.setTypeface(typeface);
        content1.setTypeface(typeface);
        content2.setTypeface(typeface);
        content3.setTypeface(typeface);
        content4.setTypeface(typeface);

        TextView Time_second = (TextView) layout.findViewById(R.id.time2);
        TextView SavePrice = (TextView) layout.findViewById(R.id.saveprice);
        TextView Amount = (TextView) layout.findViewById(R.id.noSmokingAmount);

        Intent intent = getActivity().getIntent();
        final String period2 = intent.getStringExtra("period");
        final String amount2 = intent.getStringExtra("amount");
        final String price2 = intent.getStringExtra("price");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    progressBar.setProgress(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();


        price = 0000.0;
        amount = 0000.0;

        SavePrice.setText(price + " " + "원");
        Amount.setText(amount + " " + "개비");

        timestart();

        return layout;
    }

    public void timestart() {

        Time_first = (TextView) getActivity().findViewById(R.id.time1);

        Hday = 0;
        Hhour = 0;
        Hminutes = 0;
        Hsecond = 0;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                    }
                };
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);
    }

    protected void update(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Time_first.setText("멋쟁이");
            }
        };

        handler.post(runnable);
    }

}
