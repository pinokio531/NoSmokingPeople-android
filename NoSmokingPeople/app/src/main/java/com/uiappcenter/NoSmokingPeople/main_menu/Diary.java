package com.uiappcenter.NoSmokingPeople.main_menu;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.uiappcenter.NoSmokingPeople.R;

/**
 * Created by 성민 on 2017-08-06.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class Diary extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container2, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.activity_calendar, container2, false);
        return layout;
    }
}
