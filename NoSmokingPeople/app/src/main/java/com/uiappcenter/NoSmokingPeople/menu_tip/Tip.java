package com.uiappcenter.NoSmokingPeople.menu_tip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.uiappcenter.NoSmokingPeople.R;

import java.util.Date;

/**
 * Created by 성민 on 2017-08-06.
 */

public class Tip extends Fragment {

    public int page;

    public Tip() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container2, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.main_tip, container2, false);


        ImageView imageview1 = (ImageView) layout.findViewById(R.id.article1);
        ImageView imageview2 = (ImageView) layout.findViewById(R.id.article2);


        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TipViewPagerActive.class);
                intent.putExtra("page",0);
                startActivity(intent);
            }
        });

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page=1;
                Intent intent = new Intent(getActivity(), TipViewPagerActive.class);
                intent.putExtra("page",1);
                startActivity(intent);
            }
        });

        timestamp();

        return layout;
    }
    public void timestamp() {
        long time = System.currentTimeMillis();
        Date date = new Date();
        date.setTime(time);
        SharedPreferences asd = getActivity().getSharedPreferences("WW", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = asd.edit();

        editor.putInt("nowSecond", date.getSeconds());
        editor.commit();
    }



}
