package com.example.nosmokingpeople;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 성민 on 2017-08-11.
 */

public class MenuOath extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_oath);

        TextView title = (TextView) findViewById(R.id.Title);
        TextView title2 = (TextView) findViewById(R.id.SecondTitle);
        TextView oathname = (TextView) findViewById(R.id.name);
        TextView oathdate = (TextView) findViewById(R.id.DatePicker);
        Button bt_back = (Button) findViewById(R.id.buttonback);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "BMHANNA_11yrs_ttf.ttf");
        title.setTypeface(typeface);
        title2.setTypeface(typeface);

        Intent intent = getIntent();
        String oathname2 = intent.getStringExtra("oathname");
        String startdate2 = intent.getStringExtra("startdate");

        oathname.setText(oathname2);
        oathdate.setText(startdate2);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}