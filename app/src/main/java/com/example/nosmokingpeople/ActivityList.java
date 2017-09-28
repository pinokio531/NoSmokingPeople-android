package com.example.nosmokingpeople;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by 성민 on 2017-08-13.
 */

public class ActivityList extends AppCompatActivity{

    public static ArrayList<Activity> activitylist = new ArrayList<Activity>();


    public void addList(){
        activitylist.add(new Activity());
    }

}
