package com.example.liftinglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomepageScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_screen);

        //Initializes buttons on homepages to take users to different screens
        ImageButton currentworkout = (ImageButton) findViewById(R.id.CurrentWorkoutButton);
        currentworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageScreen.this, LogCurrentWorkoutScreen.class );
                startActivity(intent);
            }
        });

        ImageButton specificexercise = (ImageButton) findViewById(R.id.SpecificExerciseButton);
        specificexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(HomepageScreen.this, SpecificExerciseScreen.class );
                startActivity(intent2);
            }
        });

        ImageButton overallprogress = (ImageButton) findViewById(R.id.ProgressButton);
        overallprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(HomepageScreen.this, OverallProgressScreen.class );
                startActivity(intent3);
            }
        });

        Button signout = (Button) findViewById(R.id.SignOutButton);
        signout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(HomepageScreen.this, LoginScreen.class);
                startActivity(intent4);
            }
        });
    }
}