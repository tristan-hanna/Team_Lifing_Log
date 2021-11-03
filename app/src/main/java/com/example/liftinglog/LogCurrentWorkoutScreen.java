package com.example.liftinglog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class LogCurrentWorkoutScreen extends AppCompatActivity {

    ViewPager mViewPager;
    int[] images = {R.drawable.bench_press, R.drawable.deadlift, R.drawable.chest_press};
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_current_workout_screen);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPagerAdapter = new ViewPagerAdapter(LogCurrentWorkoutScreen.this, images);
        mViewPager.setAdapter(mViewPagerAdapter);
    }
}