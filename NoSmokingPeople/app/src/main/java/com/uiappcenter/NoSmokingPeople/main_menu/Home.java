package com.uiappcenter.NoSmokingPeople.main_menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.uiappcenter.NoSmokingPeople.R;

/**
 * Created by 성민 on 2017-08-06.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class Home extends Fragment {
    int day, second, minutes, hour;
    private int totalLifeSave;
    private int m2, h2, d2, i = 0;
    private int wa_2, wp_2;
    private float wa_3, wp_3;
    private float sl, sm, sa = 0;
    private TextView NoSmoking;
    private TextView SaveLife;
    private TextView SavePrice;
    private TextView Amount;
    private ProgressBar pb;
    int a[] = {1, 3, 7, 14, 21, 100, 200, 365, 730};
    int progress;
    TextView textView2;

    Boolean running = true;
    long firstTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container2, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.main_home, container2, false);
        TextView textView = (TextView) layout.findViewById(R.id.noSmokingGoal);
        textView2 = (TextView) layout.findViewById(R.id.dayGoal);
        TextView content1 = (TextView) layout.findViewById(R.id.text1);
        TextView content2 = (TextView) layout.findViewById(R.id.text2);
        TextView content3 = (TextView) layout.findViewById(R.id.text3);
        TextView content4 = (TextView) layout.findViewById(R.id.text4);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "BMHANNA_11yrs_ttf.ttf");
        textView.setTypeface(typeface);
        textView2.setTypeface(typeface);
        content1.setTypeface(typeface);
        content2.setTypeface(typeface);
        content3.setTypeface(typeface);
        content4.setTypeface(typeface);

        running = true;

        NoSmoking = (TextView) layout.findViewById(R.id.time1);
        SaveLife = (TextView) layout.findViewById(R.id.time2);
        SavePrice = (TextView) layout.findViewById(R.id.saveprice);
        Amount = (TextView) layout.findViewById(R.id.noSmokingAmount);
        pb= (ProgressBar) layout.findViewById(R.id.progressBar);

        SharedPreferences sp = this.getActivity().getSharedPreferences("WW", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        wp_2 = sp.getInt("WritePrice", 0);
        wa_2 = sp.getInt("WriteAmount", 0);
        Float wp_33 = sp.getFloat("wp", 0);
        Float wa_33 = sp.getFloat("wa", 0);

        boolean first = sp.getBoolean("WW",false);
        if(first == false){
            editor.putBoolean("WW", true).commit();

            firstTime = System.currentTimeMillis();

            progress = 0;

        }
        else
        {
            firstTime = sp.getLong("startTime", 0);
            long enter = System.currentTimeMillis();
            long interval2 = enter - firstTime;
            int day2 = (int) (interval2/(60*60*24*1000));
            int hour2 = (int) (interval2%(60*60*24*1000)/(60*60*1000));
            int minutes2 = (int) ((interval2%(60*60*24*1000)%(60*60*1000))/(60*1000));
            int second2 = (int) ((interval2%(60*60*24*1000)%(60*60*1000))%(60*1000)/1000);

            progress = second2 + minutes2*60 + hour2*60*60 + day2*60*60*24;

            totalLifeSave = (int) (0.297*second2 + 0.297*60*minutes2 + 0.297*60*60*hour2 + 0.297*60*60*24*day2);

            d2 = totalLifeSave/(60*60*24);
            h2 = totalLifeSave%(60*60*24)/(60*60);
            m2 = totalLifeSave%(60*60*24)%(60*60)/60;
            sl = totalLifeSave%(60*60*24)%(60*60)%60;

            sm = (float) second2*wp_33 + (float) minutes2*wp_33*60 +(float) hour2*wp_33*60*60 + (float) (day2*wp_33*60*60*24);

            sa = (float) second2*wa_33 + (float) minutes2*wa_33*60 +(float) hour2*wa_33*60*60 + (float) (day2*wa_33*60*60*24);
        }

        Thread_1 thread_1 = new Thread_1();
        Thread_2_Progress progressBar = new Thread_2_Progress();
        thread_1.start();
        progressBar.start();

        return layout;
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences spq = this.getActivity().getSharedPreferences("WW", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = spq.edit();
        edit.putLong("startTime", firstTime);
        edit.putFloat("wp", wp_3);
        edit.putFloat("wa", wa_3);
        edit.commit();
        running = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("a", day);
        outState.putInt("b", hour);
        outState.putInt("c", minutes);
        outState.putInt("d", second);
        outState.putInt("e", progress);
    }

    class Thread_1 extends Thread {
        public void run(){
        while (running = true){
            try{
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
                Thread.sleep(1000);
            }catch(Exception e){

            }
        }
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            updateContent_Goal();
            updateContent_nonSomkingTime();
            updateContent_delayDeathTime();
            updateContent_saveMoney();
            updateContent_saveAmount();
        }
    };
}

    private void updateContent_Goal() {
        if(day==0){textView2.setText("1일");}
        else if(day>=1 && day<3) {textView2.setText("3일");}
        else if(day>=3 && day<7) {textView2.setText("7일");}
        else if(day>=7 && day<14) {textView2.setText("14일");}
        else if(day>=14 && day<21) {textView2.setText("21일");}
        else if(day>=21 && day<100) {textView2.setText("100일");}
        else if(day>=100 && day<200) {textView2.setText("200일");}
        else if(day>=200 && day<365) {textView2.setText("365일");}
        else if(day>=365 && day<730) {textView2.setText("730일");}
        else if(day>=730) {textView2.setText("1000일");}
    }

    private void updateContent_nonSomkingTime() {

        long update = System.currentTimeMillis();
        long interval = update - firstTime;

        day = (int) (interval/(60*60*24*1000));
        hour = (int) (interval%(60*60*24*1000)/(60*60*1000));
        minutes = (int) ((interval%(60*60*24*1000)%(60*60*1000))/(60*1000));
        second = (int) ((interval%(60*60*24*1000)%(60*60*1000))%(60*1000)/1000);

        NoSmoking.setText(day + "일" + " " + hour + "시간" + " " + minutes + "분" + " " + second + "초");
    }

    private void updateContent_delayDeathTime() {

        sl += 0.297;

        if (sl > 59){
            ++m2;
            sl = sl - 59;
        } else if (m2 > 59) {
            ++h2;
            m2 = 0;
        } else if (h2 > 23) {
            ++d2;
            h2 = 0;
        }

        SaveLife.setText(d2 + "일" + " " + h2 + "시간" + " " + m2 + "분" + " " + Math.round(sl) + "초");
    }

    private void updateContent_saveMoney() {

        wp_3 = wp_2 / 20;
        sm = wp_3 * sa;

        SavePrice.setText(Math.round(sm * 10.0) / 10.0 + "원");
    }

    private void updateContent_saveAmount() {

        wa_3 = wa_2 / (float) 86400;
        sa += wa_3;

        Amount.setText(Math.round(sa * 10.0) / 10.0 + "개비");
    }

    class Thread_2_Progress extends Thread
    {
        public void run(){
            while (true){
                try{
                    Message msg = phandler.obtainMessage();
                    phandler.sendMessage(msg);
                    Thread.sleep(1000);
                }catch(Exception e){

                }
            }
        }

        android.os.Handler phandler = new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                ++progress;

                pb.setProgress(progress);
                if(day==0){pb.setMax(60*60*24*1);}
                else if(day>=1 && day<3) {pb.setMax(60*60*24*3);}
                else if(day>=3 && day<7) {pb.setMax(60*60*24*7);}
                else if(day>=7 && day<14) {pb.setMax(60*60*24*14);}
                else if(day>=14 && day<21) {pb.setMax(60*60*24*21);}
                else if(day>=21 && day<100) {pb.setMax(60*60*24*100);}
                else if(day>=100 && day<200) {pb.setMax(60*60*24*200);}
                else if(day>=200 && day<365) {pb.setMax(60*60*24*365);}
                else if(day>=365 && day<730) {pb.setMax(60*60*24*730);}
                else if(day>=730) {pb.setMax(60*60*24*1000);}
            }
        };
    }
}

