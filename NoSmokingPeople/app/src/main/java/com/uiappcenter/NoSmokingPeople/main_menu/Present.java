package com.uiappcenter.NoSmokingPeople.main_menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
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

public class Present extends Fragment {
    int progress;
    long startTime;
    ProgressBar progress1, progress2, progress3, progress4, progress11, progress12, progress13;
    Boolean running = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container2, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.main_present, container2, false);

        TextView textView = (TextView) layout.findViewById(R.id.presenthealth);
        TextView textView1 = (TextView) layout.findViewById(R.id.savething);
        progress1 = (ProgressBar) layout.findViewById(R.id.progressBar);
        progress2 = (ProgressBar) layout.findViewById(R.id.progressBar2);
        progress3 = (ProgressBar) layout.findViewById(R.id.progressBar3);
        progress4 = (ProgressBar) layout.findViewById(R.id.progressBar4);
        progress11 = (ProgressBar) layout.findViewById(R.id.progressBa11);
        progress12 = (ProgressBar) layout.findViewById(R.id.progressBa12);
        progress13 = (ProgressBar) layout.findViewById(R.id.progressBa13);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"BMHANNA_11yrs_ttf.ttf");
        textView.setTypeface(typeface);
        textView1.setTypeface(typeface);

        running = true;

        SharedPreferences sp = this.getActivity().getSharedPreferences("WW2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        boolean first = sp.getBoolean("WW2",false);
        if(first == false) {
            editor.putBoolean("WW2", true);
            editor.commit();

            startTime = System.currentTimeMillis();
            progress = 0;
        }
        else{
            startTime = sp.getLong("inter", 0);
            long enterTime = System.currentTimeMillis();
            long intervalTime = enterTime - startTime;

            int day3 = (int) (intervalTime/(60*60*24*1000));
            int hour3 = (int) (intervalTime%(60*60*24*1000)/(60*60*1000));
            int minutes3 = (int) ((intervalTime%(60*60*24*1000)%(60*60*1000))/(60*1000));
            int second3 = (int) ((intervalTime%(60*60*24*1000)%(60*60*1000))%(60*1000)/1000);

            progress = second3 + minutes3*60 + hour3*60*60 + day3*60*60*24;
        }

        ProgressThread progressThread = new ProgressThread();
        progressThread.start();

        return layout;

    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences spq = getActivity().getSharedPreferences("WW2", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = spq.edit();
        edit.putLong("inter", startTime);
        edit.commit();
        running = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("point", progress);
    }

    class ProgressThread extends Thread
    {
        public void run(){
            while (running = true){
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

                progress1.setProgress(progress);
                progress1.setMax(60*20);
                progress2.setProgress(progress);
                progress2.setMax(60*60*12);
                progress3.setProgress(progress);
                progress3.setMax(60*60*24*14);
                progress4.setProgress(progress);
                progress4.setMax(60*60*24*35);
                progress11.setProgress(progress);
                progress11.setMax(60*60*24);
                progress12.setProgress(progress);
                progress12.setMax(60*60*24*3);
                progress13.setProgress(progress);
                progress13.setMax(60*60*24*7);

            }
        };
    }
}