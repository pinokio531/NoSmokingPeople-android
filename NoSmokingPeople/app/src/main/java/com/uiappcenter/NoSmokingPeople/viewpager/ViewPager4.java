package com.uiappcenter.NoSmokingPeople.viewpager;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uiappcenter.NoSmokingPeople.R;
import com.uiappcenter.NoSmokingPeople.main_menu.MainActivity;

/**
 * Created by 성민 on 2017-07-23.
 */

public class ViewPager4 extends Fragment {

    public ViewPager4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) layoutInflater.inflate(R.layout.viewpager4, container, false);
        TextView textView2 = (TextView) layout.findViewById(R.id.SecondTitle);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"BMHANNA_11yrs_ttf.ttf");
        textView2.setTypeface(typeface);

        Button start = (Button) layout.findViewById(R.id.bt_start);

        Intent intent = getActivity().getIntent();
        final String oathname = intent.getStringExtra("oathname");
        final String startdate = intent.getStringExtra("startdate");
        final String period = intent.getStringExtra("period");
        final String amount = intent.getStringExtra("amount");
        final String price = intent.getStringExtra("price");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtra("oathname",oathname);
                intent.putExtra("startdate",startdate);
                intent.putExtra("period",period);
                intent.putExtra("amount",amount);
                intent.putExtra("price",price);
                getActivity().finish();
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.stop);
            }
        });


        return layout;
    }
}