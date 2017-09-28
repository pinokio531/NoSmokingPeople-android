package com.example.nosmokingpeople;

        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.Build;
        import android.support.annotation.NonNull;
        import android.support.annotation.RequiresApi;
        import android.support.design.internal.BottomNavigationItemView;
        import android.support.design.internal.BottomNavigationMenuView;
        import android.support.design.widget.BottomNavigationView;
        import android.support.design.widget.NavigationView;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.text.Layout;
        import android.text.SpannableString;
        import android.text.style.AlignmentSpan;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.ToggleButton;

        import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity{


    private long pressedtime=0;

    private ActivityList list;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById (R.id.bottomnavigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Button bt1 = (Button) findViewById(R.id.oath);
        Button bt2 = (Button) findViewById(R.id.viewpager);
        Button bt3 = (Button) findViewById(R.id.ifHaveQuestion);
        Button bt4 = (Button) findViewById(R.id.setting);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        View header = navigationView.getHeaderView(0);
        TextView name = (TextView) header.findViewById(R.id.nameView);


        Intent intent = getIntent();
        final String oathname = intent.getStringExtra("oathname");
        final String startdate = intent.getStringExtra("startdate");

        name.setText(oathname);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuOath.class);
                intent.putExtra("oathname",oathname);
                intent.putExtra("startdate",startdate);
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

        list = new ActivityList();
        list.addList();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        callFragment(0);

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
                        callFragment(0);
                        return true;

                    case R.id.present:
                        callFragment(1);
                        return true;

                    case R.id.diary:
                        callFragment(2);
                        return true;

                    case R.id.tip:
                        callFragment(3);
                        return true;
                }
                return false;
            }
        });

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
                Intent intent = new Intent(MainActivity.this, Splash.class);
                startActivity(intent);
                moveTaskToBack(true);
                list.finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void callFragment(int num){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch(num){
            case 0:
                Home home = new Home();
                transaction.replace(R.id.container2, home);
                transaction.commit();
                break;

            case 1:
                Present present = new Present();
                transaction.replace(R.id.container2, present);
                transaction.commit();
                break;

            case 2:
                Diary diary = new Diary();
                transaction.replace(R.id.container2, diary);
                transaction.commit();
                break;

            case 3:
                Tip tip = new Tip();
                transaction.replace(R.id.container2, tip);
                transaction.commit();
                break;
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
