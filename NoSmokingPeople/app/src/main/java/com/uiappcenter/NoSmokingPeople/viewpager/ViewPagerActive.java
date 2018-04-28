package com.uiappcenter.NoSmokingPeople.viewpager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import android.widget.Toast;

import com.uiappcenter.NoSmokingPeople.R;
import com.uiappcenter.NoSmokingPeople.start_page.Splash;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by 성민 on 2017-07-23.
 */

public class ViewPagerActive extends FragmentActivity {

    ViewPager viewpager;


    private long pressedtime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);


        viewpager = (ViewPager) findViewById(R.id.vp);
        viewpager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        viewpager.setCurrentItem(0);

        TextView textView1 = (TextView) findViewById(R.id.Title);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"BMHANNA_11yrs_ttf.ttf");
        textView1.setTypeface(typeface);

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(viewpager);


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
                    return new ViewPager4();

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

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis()>=pressedtime + 2000){
            pressedtime = System.currentTimeMillis();
            Toast.makeText(this, " 한번 더 누르면 어플이 종료됩니다", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            super.onBackPressed();
            Intent intent = new Intent(ViewPagerActive.this, Splash.class);
            startActivity(intent);
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
