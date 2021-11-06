package com.example.liftinglog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OverallProgressScreen extends AppCompatActivity {

    GraphView Bench_Press_Progress_GraphView;
    GraphView Deadlift_Progress_GraphView;
    GraphView Chest_Press_Progress_GraphView;
    LineGraphSeries bench_press_series;
    LineGraphSeries deadlift_series;
    LineGraphSeries chest_press_series;

    Calendar sdf = Calendar.getInstance();
    String workout_date = DateFormat.getDateInstance().format(sdf.getTime());

    FirebaseDatabase ExerciseDatabase;
    DatabaseReference ExerciseReference, ExerciseReference2, ExerciseReference3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_progress_screen);

        Bench_Press_Progress_GraphView = (GraphView) findViewById(R.id.Bench_Press_Progress_GraphView);
        Deadlift_Progress_GraphView = (GraphView) findViewById(R.id.Deadlift_Progress_GraphView);
        Chest_Press_Progress_GraphView = (GraphView) findViewById(R.id.Chest_Press_Progress_GraphView);

        bench_press_series = new LineGraphSeries();
        deadlift_series = new LineGraphSeries();
        chest_press_series = new LineGraphSeries();
        bench_press_series.setTitle("Bench Press Progress");
        bench_press_series.setColor(Color.GREEN);
        deadlift_series.setTitle("Deadlift Progress");
        deadlift_series.setColor(Color.RED);
        chest_press_series.setTitle("Chest Press Progress");
        chest_press_series.setColor(Color.BLUE);

        Bench_Press_Progress_GraphView.addSeries(bench_press_series);
        Deadlift_Progress_GraphView.addSeries(deadlift_series);
        Chest_Press_Progress_GraphView.addSeries(chest_press_series);

        ExerciseDatabase = FirebaseDatabase.getInstance();
        ExerciseReference = ExerciseDatabase.getReference("chartTable");
        ExerciseReference2 = ExerciseDatabase.getReference("DeadliftTable");
        ExerciseReference3 = ExerciseDatabase.getReference("ChestPressTable");

        Bench_Press_Progress_GraphView.getLegendRenderer().setVisible(true);
        Bench_Press_Progress_GraphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        Bench_Press_Progress_GraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
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

        Deadlift_Progress_GraphView.getLegendRenderer().setVisible(true);
        Deadlift_Progress_GraphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        Deadlift_Progress_GraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
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

        Chest_Press_Progress_GraphView.getLegendRenderer().setVisible(true);
        Chest_Press_Progress_GraphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        Chest_Press_Progress_GraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
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

                for (DataSnapshot myDataSnapshot : snapshot.getChildren()) {
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

        ExerciseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                int index = 0;

                for (DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                    POJO pointValues = myDataSnapshot.getValue(POJO.class);
                    dp[index] = new DataPoint(pointValues.getXvalue(), pointValues.getYvalue());
                    index++;
                }
                deadlift_series.resetData(dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        ExerciseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                int index = 0;

                for (DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                    POJO pointValues = myDataSnapshot.getValue(POJO.class);
                    dp[index] = new DataPoint(pointValues.getXvalue(), pointValues.getYvalue());
                    index++;
                }
                chest_press_series.resetData(dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}