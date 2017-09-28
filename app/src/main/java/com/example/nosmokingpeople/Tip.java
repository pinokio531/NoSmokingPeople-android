package com.example.nosmokingpeople;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static com.example.nosmokingpeople.R.id.container3;

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
        ImageView imageview3 = (ImageView) layout.findViewById(R.id.article3);
        ImageView imageview4 = (ImageView) layout.findViewById(R.id.article4);


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

        imageview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TipViewPagerActive.class);
                intent.putExtra("page",2);
                startActivity(intent);
            }
        });

        imageview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TipViewPagerActive.class);
                intent.putExtra("page",3);
                startActivity(intent);
            }
        });

        return layout;
    }

}
