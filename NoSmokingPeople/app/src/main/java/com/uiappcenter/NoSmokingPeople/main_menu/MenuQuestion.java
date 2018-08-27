package com.uiappcenter.NoSmokingPeople.main_menu;

import android.bluetooth.BluetoothAdapter;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uiappcenter.NoSmokingPeople.Model.ErrorMsgData;
import com.uiappcenter.NoSmokingPeople.Model.ErrorMsgResult;
import com.uiappcenter.NoSmokingPeople.R;
import com.uiappcenter.NoSmokingPeople.retrofit.ApplicationController;
import com.uiappcenter.NoSmokingPeople.retrofit.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 성민 on 2017-08-11.
 */

public class MenuQuestion extends AppCompatActivity {

    NetworkService networkService2;
    EditText background;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_question);

        TextView title = (TextView) findViewById(R.id.Title);
        Button bt_back = (Button) findViewById(R.id.buttonback);
        Button bt_send = (Button) findViewById(R.id.sendButton);
        background = (EditText) findViewById(R.id.question);

        LinearLayout layout = (LinearLayout) findViewById(R.id.questoinback);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "BMHANNA_11yrs_ttf.ttf");
        title.setTypeface(typeface);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                networkService2 = ApplicationController.getInstance().getNetworkService();

                //디바이스 정보 ex)Samsung Galaxy s7 7.0 (SM-G930S) sdk25 -> 이런형식으로
                String deviceInfo = Build.BRAND + " " + BluetoothAdapter.getDefaultAdapter().getName() + " " + Build.VERSION.RELEASE + "(" + Build.MODEL + ") sdk" + Build.VERSION.SDK_INT;

                //학번, 메시지, 디바이스 정보, 패키지 네임 순으로
                ErrorMsgData errorMsgData = new ErrorMsgData(null, background.getText().toString(), deviceInfo, getPackageName());

                Call<ErrorMsgResult> errorMsgResultCall = networkService2.getErrorMsgResult(errorMsgData);
                errorMsgResultCall.enqueue(new Callback<ErrorMsgResult>() {

                    @Override
                    public void onResponse(Call<ErrorMsgResult> call, Response<ErrorMsgResult> response) {

                    }

                    @Override
                    public void onFailure(Call<ErrorMsgResult> call, Throwable t) {

                    }

                });


                Toast.makeText(MenuQuestion.this, "문의사항이 접수 되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                touch_hide.hideSoftInputFromWindow(background.getWindowToken(), 0);

            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
