package com.example.liftinglog;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BenchPressGraph extends AppCompatActivity {

    GraphView Bench_Press_GraphView;
    LineGraphSeries bench_press_series;
    Calendar sdf = Calendar.getInstance();
    String workout_date = DateFormat.getDateInstance().format(sdf.getTime());

    FirebaseDatabase ExerciseDatabase;
    DatabaseReference ExerciseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bench_press_graph);

        Bench_Press_GraphView = (GraphView) findViewById(R.id.Bench_Press_GraphView);
        bench_press_series = new LineGraphSeries();
        Bench_Press_GraphView.addSeries(bench_press_series);

        ExerciseDatabase = FirebaseDatabase.getInstance();
        ExerciseReference = ExerciseDatabase.getReference("chartTable");

        Bench_Press_GraphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        Bench_Press_GraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {
                    return workout_date;
                }
                else{
                    return super.formatLabel(value, isValueX);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        ExerciseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                int index = 0;

                for (DataSnapshot myDataSnapshot : snapshot.getChildren())
                {
                    POJO pointValues = myDataSnapshot.getValue(POJO.class);
                    dp[index] = new DataPoint(pointValues.getXvalue(), pointValues.getYvalue());
                    index++;
                }
                bench_press_series.resetData(dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}