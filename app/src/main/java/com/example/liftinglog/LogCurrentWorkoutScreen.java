package com.example.liftinglog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class LogCurrentWorkoutScreen extends AppCompatActivity {

    ViewPager mViewPager;
    int[] images = {R.drawable.edited_bench_press, R.drawable.edited_deadlift, R.drawable.edited_chest_press};
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_current_workout_screen);

        //Initializes view pager using view pager adapter
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPagerAdapter = new ViewPagerAdapter(LogCurrentWorkoutScreen.this, images);
        mViewPager.setAdapter(mViewPagerAdapter);
    }
}