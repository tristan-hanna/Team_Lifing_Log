package com.example.liftinglog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class ChestPressGraph extends AppCompatActivity {

    GraphView Chest_Press_GraphView;
    LineGraphSeries chest_press_series;
    Button share_button;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    FirebaseDatabase ExerciseDatabase;
    DatabaseReference ExerciseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_press_graph);

        share_button = (Button) findViewById(R.id.Share_Button);

        Chest_Press_GraphView = (GraphView) findViewById(R.id.Chest_Press_GraphView);
        chest_press_series = new LineGraphSeries();
        Chest_Press_GraphView.addSeries(chest_press_series);

        ExerciseDatabase = FirebaseDatabase.getInstance();
        ExerciseReference = ExerciseDatabase.getReference("ChestPressTable");

        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "Your body here";
                String shareSub = "Your subject here";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share Using:"));
            }
        });

        Chest_Press_GraphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        Chest_Press_GraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {
                    return sdf.format(new Date((long)value));
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
                chest_press_series.resetData(dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}