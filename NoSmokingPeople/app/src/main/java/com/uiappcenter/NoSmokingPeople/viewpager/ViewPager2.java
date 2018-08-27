package com.uiappcenter.NoSmokingPeople.viewpager;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uiappcenter.NoSmokingPeople.R;

/**
 * Created by 성민 on 2017-07-23.
 */

public class ViewPager2 extends Fragment {

    public ViewPager2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) layoutInflater.inflate(R.layout.viewpager2, container, false);
        TextView textView2 = (TextView) layout.findViewById(R.id.SecondTitle);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"BMHANNA_11yrs_ttf.ttf");
        textView2.setTypeface(typeface);
        return layout;
    }
}