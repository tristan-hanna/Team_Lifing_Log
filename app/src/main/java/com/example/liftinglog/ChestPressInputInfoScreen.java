package com.example.liftinglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChestPressInputInfoScreen extends AppCompatActivity {

    FirebaseDatabase ExerciseDatabase;
    DatabaseReference ExerciseReference;

    EditText WeightUsed, Reps;
    Button Submit_Button, To_ChestPressGraph;
    SimpleDateFormat sdf = new SimpleDateFormat("dd:mm:yyyy");
    int orm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_press_input_info_screen);

        WeightUsed = (EditText) findViewById(R.id.WeightUsed);
        Reps = (EditText) findViewById(R.id.Reps);
        Submit_Button = (Button) findViewById(R.id.Submit_Button);
        To_ChestPressGraph = (Button) findViewById(R.id.To_ChestPressGraph);

        ExerciseDatabase = FirebaseDatabase.getInstance();
        ExerciseReference = ExerciseDatabase.getReference("ChestPressTable");

        setListeners();
    }

    private void setListeners() {
        Submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = ExerciseReference.push().getKey();
                long database_time = new Date().getTime();
                int weight = new Integer(WeightUsed.getText().toString());
                int reps = new Integer(Reps.getText().toString());
                int database_ORM = calculate1RM(weight, reps);

                POJO pointValues = new POJO(database_time, database_ORM);

                ExerciseReference.child(id).setValue(pointValues);

                Toast.makeText(ChestPressInputInfoScreen.this, "Stats Successfully Uploaded!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChestPressInputInfoScreen.this, HomepageScreen.class);
                startActivity(intent);
            }
        });

        To_ChestPressGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ChestPressInputInfoScreen.this, ChestPressGraph.class);
                startActivity(intent2);
            }
        });
    }

    public int calculate1RM(int weightused, int repsdone){
        orm = (int) (weightused/(1.0278 - 0.0278 * repsdone));
        return orm;
    }
}