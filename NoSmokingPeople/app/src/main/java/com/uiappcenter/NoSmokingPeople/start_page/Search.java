package com.uiappcenter.NoSmokingPeople.start_page;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uiappcenter.NoSmokingPeople.R;

import static com.uiappcenter.NoSmokingPeople.R.layout.activity_search;

/**
 * Created by 성민 on 2017-07-18.
 */

public class Search extends AppCompatActivity {


    private long pressedtime = 0;
    public static Activity activityOath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_search);


        activityOath = Search.this;

        Typeface typeface = Typeface.createFromAsset(getAssets(), "BMHANNA_11yrs_ttf.ttf");

        TextView textView1 = (TextView) findViewById(R.id.Title);
        TextView textView2 = (TextView) findViewById(R.id.SecondTitle);
        Button push = (Button) findViewById(R.id.next);
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);
        push.setTypeface(typeface);



        final EditText WY = (EditText) findViewById(R.id.WriteYear);
        final EditText WA = (EditText) findViewById(R.id.WriteAmount);
        final EditText WP = (EditText) findViewById(R.id.WritePrice);
        LinearLayout layout = (LinearLayout) findViewById(R.id.CheckList);


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                touch_hide.hideSoftInputFromWindow(WY.getWindowToken(), 0);
                touch_hide.hideSoftInputFromWindow(WA.getWindowToken(), 0);
                touch_hide.hideSoftInputFromWindow(WP.getWindowToken(), 0);

            }
        });

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (WY.getText().toString().length() == 0) {
                    Toast.makeText(Search.this, "흡연 기간을 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                } else if (WA.getText().toString().length() == 0) {
                    Toast.makeText(Search.this, "흡연 개비를 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                } else if (WP.getText().toString().length() == 0) {
                    Toast.makeText(Search.this, "담배 가격을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    SharedPreferences sharedPreferences = getSharedPreferences("WW", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("WriteYear", Integer.parseInt(WY.getText().toString()));
                    editor.putInt("WriteAmount", Integer.parseInt(WA.getText().toString()));
                    editor.putInt("WritePrice", Integer.parseInt(WP.getText().toString()));
                    editor.commit();
                }

                Intent intent = new Intent(Search.this, Oath.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stop);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() >= pressedtime + 2000) {
            pressedtime = System.currentTimeMillis();
            Toast.makeText(this, " 한번 더 누르면 어플이 종료됩니다", Toast.LENGTH_SHORT).show();
            return;
        } else {
            super.onBackPressed();
            Intent intent = new Intent(Search.this, Splash.class);
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
