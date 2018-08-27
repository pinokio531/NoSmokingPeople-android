package com.uiappcenter.NoSmokingPeople.main_menu;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uiappcenter.NoSmokingPeople.R;

/**
 * Created by 성민 on 2018-03-06.
 */

public class ApplicationInformation extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_appinformation);

        Button bt_back = (Button) findViewById(R.id.buttonback);
        TextView title = (TextView) findViewById(R.id.Title);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "BMHANNA_11yrs_ttf.ttf");
        title.setTypeface(typeface);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
