package com.example.nosmokingpeople;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by 성민 on 2017-08-11.
 */

public class MenuExplain extends AppCompatActivity {

    ViewPager viewpager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_explain);

        TextView title = (TextView) findViewById(R.id.Title);
        TextView title2 = (TextView) findViewById(R.id.SecondTitle);
        Button bt_back = (Button) findViewById(R.id.buttonback);

        viewpager = (ViewPager) findViewById(R.id.vp);
        viewpager.setAdapter(new MenuExplain.pagerAdapter(getSupportFragmentManager()));
        viewpager.setCurrentItem(0);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "BMHANNA_11yrs_ttf.ttf");
        title.setTypeface(typeface);

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(viewpager);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position){
            switch (position){
                case 0:
                    return new ViewPager1();

                case 1:
                    return new ViewPager2();

                case 2:
                    return new ViewPager3();

                case 3:
                    return new MenuViewPager4();

                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 4;
        }
    }
}
