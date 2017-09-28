package com.example.nosmokingpeople;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.icu.text.SelectFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * Created by 성민 on 2017-08-06.
 */

public class Diary extends Fragment implements View.OnClickListener{

    public RadioButton one;
    public RadioButton two;
    public RadioButton three;
    public RadioButton four;
    public RadioButton five;

    private TextView tvDate1;
    private TextView tvDate2;
    private GridAdapter gridAdapter;
    private ArrayList<String> dayList;
    private GridView gridView;
    private Calendar mCal;

    public Diary() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container2, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.activity_calendar, container2, false);

        tvDate1 = (TextView) layout.findViewById(R.id.tv_date1);
        tvDate2 = (TextView) layout.findViewById(R.id.tv_date2);
        gridView = (GridView) layout.findViewById(R.id.gridView);

        long now = System.currentTimeMillis();
        final Date date = new Date(now);

        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        tvDate1.setText(curMonthFormat.format(date));
        tvDate2.setText(curYearFormat.format(date));

        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");

        mCal = Calendar.getInstance();

        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date))-1,1);

        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);

        for(int i =1; i < dayNum; i++){
            dayList.add(" ");
        }
        setCalendarDate(mCal.get(Calendar.MONTH)+1);
        gridAdapter = new GridAdapter(getActivity().getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                show();
            }
        });

        return layout;
    }

    public void show() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.diary_alert,null);
        one = (RadioButton) view.findViewById(R.id.OnePoint);
        two = (RadioButton) view.findViewById(R.id.TwoPoint);
        three = (RadioButton) view.findViewById(R.id.ThreePoint);
        four = (RadioButton) view.findViewById(R.id.FourPoint);
        five = (RadioButton) view.findViewById(R.id.FivePoint);
        Button bt_confirm = (Button) view.findViewById(R.id.confirm);
        Button bt_close = (Button) view.findViewById(R.id.close);

        TextView title = (TextView) view.findViewById(R.id.title);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"BMHANNA_11yrs_ttf.ttf");
        title.setTypeface(typeface);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(view);

        final AlertDialog dialog = builder.create();

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });

        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(dialog.getWindow().getAttributes());
        params.width = 800;
        params.height= WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();

        Window window = dialog.getWindow();
        window.setAttributes(params);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.OnePoint:
                one.isChecked();
                break;

            case R.id.TwoPoint:
                two.isChecked();
                break;

            case R.id.ThreePoint:
                three.isChecked();
                break;

            case R.id.FourPoint:
                four.isChecked();
                break;

            case R.id.FivePoint:
                five.isChecked();
                break;


        }
    }

    private class GridAdapter extends BaseAdapter {
        private List<String> list;
        private LayoutInflater inflater;

        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        private class ViewHolder {
            TextView tvItemGridView;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GridAdapter.ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new GridAdapter.ViewHolder();

                holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);

                convertView.setTag(holder);
            } else {
                holder = (GridAdapter.ViewHolder) convertView.getTag();
            }

            holder.tvItemGridView.setText("" + getItem(position));

            mCal = Calendar.getInstance();

            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            if(sToday.equals(getItem(position))){
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.colorWhite));
                holder.tvItemGridView.setBackground(getResources().getDrawable(R.drawable.calendar_click));
            }
            return convertView;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCalendarDate(int month){
        mCal.set(Calendar.MONTH, month-1);

        for(int i =0; i<mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            dayList.add(""+(i+1));
        }
    }
}
