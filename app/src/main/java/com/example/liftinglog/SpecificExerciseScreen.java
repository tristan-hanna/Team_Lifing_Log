package com.example.liftinglog;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.internal.NavigationMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class SpecificExerciseScreen extends AppCompatActivity {

    ViewPager mViewPager;
    int[] images = {R.drawable.bench_press, R.drawable.deadlift, R.drawable.chest_press};
    ViewPagerAdapter2 mViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_exercise_screen);

        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dail);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {

            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {

                FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dail);
                fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
                    @Override
                    public boolean onMenuItemSelected(MenuItem menuItem) {
                        Intent intent = new Intent(SpecificExerciseScreen.this, LogCurrentWorkoutScreen.class );
                        startActivity(intent);
                        return true;}
                });

                return true;
            }
        });

//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        mViewPagerAdapter = new ViewPagerAdapter2(SpecificExerciseScreen.this, images);
//        mViewPager.setAdapter(mViewPagerAdapter);


    }

}

