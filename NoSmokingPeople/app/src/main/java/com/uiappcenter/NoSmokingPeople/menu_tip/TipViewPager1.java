package com.uiappcenter.NoSmokingPeople.menu_tip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.uiappcenter.NoSmokingPeople.R;

/**
 * Created by 성민 on 2017-08-18.
 */

public class TipViewPager1 extends Fragment{

    public TipViewPager1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container3, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) layoutInflater.inflate(R.layout.tip1, container3, false);
        return layout;
    }
}
