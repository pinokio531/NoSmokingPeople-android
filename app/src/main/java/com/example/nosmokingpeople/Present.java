package com.example.nosmokingpeople;

import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ScrollingView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by 성민 on 2017-08-06.
 */

public class Present extends Fragment {

    public Present() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container2, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.main_present, container2, false);

        TextView textView = (TextView) layout.findViewById(R.id.presenthealth);
        TextView textView1 = (TextView) layout.findViewById(R.id.savething);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"BMHANNA_11yrs_ttf.ttf");
        textView.setTypeface(typeface);
        textView1.setTypeface(typeface);

        return layout;
    }

}
