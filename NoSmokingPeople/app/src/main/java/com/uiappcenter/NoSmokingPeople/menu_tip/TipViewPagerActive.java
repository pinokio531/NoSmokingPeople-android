package com.uiappcenter.NoSmokingPeople.menu_tip;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uiappcenter.NoSmokingPeople.R;

/**
 * Created by 성민 on 2017-08-19.
 */

public class TipViewPagerActive extends FragmentActivity {

    private ViewPager viewpager;
    private Tip tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        TextView title = (TextView) findViewById(R.id.Title);
        Button bt_back = (Button) findViewById(R.id.buttonback);
        Button bt_right = (Button) findViewById(R.id.right);
        Button bt_left = (Button) findViewById(R.id.left);

        Intent intent = getIntent();
        int a = intent.getExtras().getInt("page");

        viewpager = (ViewPager) findViewById(R.id.container3);
        viewpager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        viewpager.setCurrentItem(a);



        Typeface typeface = Typeface.createFromAsset(getAssets(), "BMHANNA_11yrs_ttf.ttf");
        title.setTypeface(typeface);

        bt_right.setOnClickListener(new View.OnClickListener() {
            int nowPage;
            @Override
            public void onClick(View view) {
                nowPage = viewpager.getCurrentItem();
                if(nowPage==1){
                    viewpager.setCurrentItem(0);
                }
                else{
                    viewpager.setCurrentItem(nowPage+1);
                }
            }
        });

        bt_left.setOnClickListener(new View.OnClickListener() {
            int nowPage;
            @Override
            public void onClick(View view) {
                nowPage = viewpager.getCurrentItem();
                if(nowPage==0){
                    viewpager.setCurrentItem(1);
                }
                else{
                    viewpager.setCurrentItem(nowPage-1);
                }
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TipViewPager1();

                case 1:
                    return new TipViewPager2();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}