package com.uiappcenter.NoSmokingPeople.main_menu;

        import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

        import com.uiappcenter.NoSmokingPeople.R;
import com.uiappcenter.NoSmokingPeople.menu_tip.Tip;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity{


    private long pressedtime=0;


    public static Activity activity;
    private MenuItem indicator;
    ViewPager view;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);

        activity = MainActivity.this;


        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById (R.id.bottomnavigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Button bt1 = (Button) findViewById(R.id.oath);
        Button bt2 = (Button) findViewById(R.id.viewpager);
        Button bt3 = (Button) findViewById(R.id.ifHaveQuestion);
        Button bt4 = (Button) findViewById(R.id.setting);
        view = (ViewPager) findViewById(R.id.view);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        View header = navigationView.getHeaderView(0);
        TextView name = (TextView) header.findViewById(R.id.nameView);

        SharedPreferences qwe = getSharedPreferences("shared", MODE_PRIVATE);
        String oathname = qwe.getString("nameOFuser", " ");
        name.setText(oathname);


        view.setAdapter(new pageAdapter(getSupportFragmentManager()));
        view.setOffscreenPageLimit(4);
        view.setCurrentItem(0);
        view.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(indicator != null){
                    indicator.setChecked(false);
                }
                indicator = bottomNavigationView.getMenu().getItem(position);
                indicator.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuOath.class);
                startActivity(intent);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.MainAndDrawer);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuExplain.class);
                startActivity(intent);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.MainAndDrawer);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuQuestion.class);
                startActivity(intent);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.MainAndDrawer);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuSetting.class);
                startActivity(intent);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.MainAndDrawer);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        TextView textView = (TextView) findViewById(R.id.Title);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "BMHANNA_11yrs_ttf.ttf");
        textView.setTypeface(typeface);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final pageAdapter pa = new pageAdapter(getSupportFragmentManager());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.MainAndDrawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home :
                        view.setCurrentItem(0);
                        return true;

                    case R.id.present:
                        view.setCurrentItem(1);
                        return true;

                    case R.id.diary:
                        view.setCurrentItem(2);
                        return true;

                    case R.id.tip:
                        view.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });
    }

    class pageAdapter extends FragmentPagerAdapter {
        public pageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch(position){
                case 0:
                    transaction.addToBackStack(null).commit();
                    return new Home();

                case 1:
                    return new Present();

                case 2:
                    return new Diary();

                case 3:
                    return new Tip();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.MainAndDrawer);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() >= pressedtime + 2000) {
                pressedtime = System.currentTimeMillis();
                Toast.makeText(this, " 한번 더 누르면 어플이 종료됩니다", Toast.LENGTH_SHORT).show();
                return;
            } else {
                super.onBackPressed();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                finish();
            }
        }
    }

    public static class BottomNavigationViewHelper {
        public static void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    item.setShiftingMode(false);
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }
}
