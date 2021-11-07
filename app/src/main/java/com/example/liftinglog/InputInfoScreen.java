package com.example.liftinglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InputInfoScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseDatabase ExerciseDatabase;
    DatabaseReference ExerciseReference, ExerciseReference2, ExerciseReference3;

    EditText WeightUsed, Reps;
    Button Submit_Button, To_Graphs;
    Spinner spinner;
    int orm;

    String EXERCISE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_info_screen);

        spinner = findViewById(R.id.spinnerWorkoutSelect);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.workout_selctor, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        EXERCISE = intent.getStringExtra("Exercise");
        if (EXERCISE.equals("Bench Press"))
            spinner.setSelection(0);
        else if (EXERCISE.equals("Deadlift"))
            spinner.setSelection(1);
        else if (EXERCISE.equals("Chest Press"))
            spinner.setSelection(2);

        WeightUsed = (EditText) findViewById(R.id.WeightUsed);
        Reps = (EditText) findViewById(R.id.Reps);
        Submit_Button = (Button) findViewById(R.id.Submit_Button);
        To_Graphs = (Button) findViewById(R.id.To_Graphs);

        ExerciseDatabase = FirebaseDatabase.getInstance();
        ExerciseReference = ExerciseDatabase.getReference("chartTable");
        ExerciseReference2 = ExerciseDatabase.getReference("DeadliftTable");
        ExerciseReference3 = ExerciseDatabase.getReference("ChestPressTable");

        setListeners();
    }

    private void setListeners() {
        Submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EXERCISE.equals("Bench Press"))
                {
                    String id = ExerciseReference.push().getKey();
                    long database_time = new Date().getTime();
                    int weight = new Integer(WeightUsed.getText().toString());
                    int reps = new Integer(Reps.getText().toString());
                    int database_ORM = calculate1RM(weight, reps);

                    POJO pointValues = new POJO(database_time, database_ORM);

                    ExerciseReference.child(id).setValue(pointValues);

                    Toast.makeText(InputInfoScreen.this, "Stats Successfully Uploaded!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InputInfoScreen.this, HomepageScreen.class);
                    startActivity(intent);
                }
                else if (EXERCISE.equals("Deadlift"))
                {
                    String id = ExerciseReference2.push().getKey();
                    long database_time = new Date().getTime();
                    int weight = new Integer(WeightUsed.getText().toString());
                    int reps = new Integer(Reps.getText().toString());
                    int database_ORM = calculate1RM(weight, reps);

                    POJO pointValues = new POJO(database_time, database_ORM);

                    ExerciseReference2.child(id).setValue(pointValues);

                    Toast.makeText(InputInfoScreen.this, "Stats Successfully Uploaded!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InputInfoScreen.this, HomepageScreen.class);
                    startActivity(intent);
                }
                else if (EXERCISE.equals("Chest Press"))
                {
                    String id = ExerciseReference3.push().getKey();
                    long database_time = new Date().getTime();
                    int weight = new Integer(WeightUsed.getText().toString());
                    int reps = new Integer(Reps.getText().toString());
                    int database_ORM = calculate1RM(weight, reps);

                    POJO pointValues = new POJO(database_time, database_ORM);

                    ExerciseReference3.child(id).setValue(pointValues);

                    Toast.makeText(InputInfoScreen.this, "Stats Successfully Uploaded!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InputInfoScreen.this, HomepageScreen.class);
                    startActivity(intent);
                }

            }
        });

        To_Graphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EXERCISE.equals("Bench Press"))
                {
                    Intent intent2 = new Intent(InputInfoScreen.this, BenchPressGraph.class);
                    startActivity(intent2);
                }
                else if (EXERCISE.equals("Deadlift"))
                {
                    Intent intent2 = new Intent(InputInfoScreen.this, DeadliftGraph.class);
                    startActivity(intent2);
                }
                else if (EXERCISE.equals("Chest Press"))
                {
                    Intent intent2 = new Intent(InputInfoScreen.this, ChestPressGraph.class);
                    startActivity(intent2);
                }

            }
        });
    }

    public int calculate1RM(int weightused, int repsdone){
        orm = (int) (weightused/(1.0278 - 0.0278 * repsdone));
        return orm;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        EXERCISE = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}