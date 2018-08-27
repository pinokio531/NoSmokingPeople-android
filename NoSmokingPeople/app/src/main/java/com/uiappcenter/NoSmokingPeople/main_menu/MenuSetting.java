package com.uiappcenter.NoSmokingPeople.main_menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.uiappcenter.NoSmokingPeople.R;

/**
 * Created by 성민 on 2017-08-11.
 */

public class MenuSetting extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_setting);

        TextView title = (TextView) findViewById(R.id.Title);
        Button bt_back = (Button) findViewById(R.id.buttonback);
        Button bt_reset = (Button) findViewById(R.id.resetButton);
        Button vers = (Button) findViewById(R.id.version);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "BMHANNA_11yrs_ttf.ttf");
        title.setTypeface(typeface);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        bt_reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                show();
            }
        });

        vers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuSetting.this, ApplicationInformation.class);
                startActivity(intent);
            }
        });
    }

    public void show(){
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.diary_alert,null);

        Button bt_confirm = (Button) view.findViewById(R.id.confirm);
        Button bt_close = (Button) view.findViewById(R.id.close);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view);

        final AlertDialog dialog = builder.create();

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(dialog.getWindow().getAttributes());
        params.width = 1000;
        params.height= WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();

        Window window = dialog.getWindow();
        window.setAttributes(params);

        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences reset1 = getSharedPreferences("isFirst", MODE_PRIVATE);
                SharedPreferences reset2 = getSharedPreferences("WW", MODE_PRIVATE);
                SharedPreferences reset3 = getSharedPreferences("WW2", MODE_PRIVATE);
                SharedPreferences reset4 = getSharedPreferences("WW", MODE_PRIVATE);
                SharedPreferences reset5 = getSharedPreferences("shared", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = reset1.edit();
                SharedPreferences.Editor editor2 = reset2.edit();
                SharedPreferences.Editor editor3 = reset3.edit();
                SharedPreferences.Editor editor4 = reset4.edit();
                SharedPreferences.Editor editor5 = reset5.edit();
                editor1.putBoolean("isFirst",false);
                editor2.putBoolean("WW",false);
                editor3.putBoolean("WW2",false);
                editor4.putInt("WriteYear", 0);
                editor5.putString("nameOFuser", null);
                editor1.commit();
                editor2.commit();
                editor3.commit();
                editor4.commit();
                editor5.commit();

                MainActivity mainActivity = (MainActivity) MainActivity.activity;

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                mainActivity.finish();
                finish();
            }
        });


    }
}
