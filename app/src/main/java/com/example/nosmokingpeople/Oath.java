package com.example.nosmokingpeople;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;

/**
 * Created by 성민 on 2017-07-20.
 */


@RequiresApi(api = Build.VERSION_CODES.N)
public class Oath extends AppCompatActivity {

    long time = System.currentTimeMillis();//현재시각 구하기
    Date d = new Date();//현재시각 가져오기
    SimpleDateFormat Today = new SimpleDateFormat("yyyy년" + " " + "MM월" +  " " +"dd일");//날짜 표현 방식
    String data = Today.format(d);//출력할 날짜 가져오기
    TextView calendar;

    int mYear;
    int mMonth;
    int mDay;

    static final int DATEDIALOG = 1;

    private ActivityList list;
    private EditText Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oath);

        list = new ActivityList();
        list.addList();

        Typeface typeface = Typeface.createFromAsset(getAssets(),"BMHANNA_11yrs_ttf.ttf");

        TextView textView1 = (TextView) findViewById(R.id.Title);
        TextView textView2 = (TextView) findViewById(R.id.SecondTitle);
        LinearLayout layout = (LinearLayout) findViewById(R.id.Oath);
        Button push = (Button) findViewById(R.id.next);

        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);
        push.setTypeface(typeface);

        Name = (EditText) findViewById(R.id.name);

        Intent intent = getIntent();
        final String period = intent.getStringExtra("period");
        final String amount = intent.getStringExtra("amount");
        final String price = intent.getStringExtra("price");

        calendar = (TextView) findViewById(R.id.DatePicker);

        calendar.setText(data);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                showDialog(DATEDIALOG);

            }
        });


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                touch_hide.hideSoftInputFromWindow(Name.getWindowToken(),0);

            }
        });

        push.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(Name.getText().toString().length() == 0)
                {
                    Toast.makeText(Oath.this,"이름을 입력해주세요",Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent= new Intent(Oath.this, ViewPagerActive.class);
                intent.putExtra("oathname",Name.getText().toString());
                intent.putExtra("startdate",calendar.getText().toString());
                intent.putExtra("period",period);
                intent.putExtra("amount",amount);
                intent.putExtra("price",price);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stop);
            }
        });
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            mYear = year;
            mMonth = month;
            mDay = day;

            calendar.setText(mYear + "년" + " " + (mMonth + 1) + "월" + " " + mDay + "일");
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATEDIALOG:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }
}