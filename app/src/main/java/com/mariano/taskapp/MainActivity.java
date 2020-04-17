package com.mariano.taskapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mariano.taskapp.DB.TaskDataBaseDao;
import com.mariano.taskapp.fragments.BlankFragment;
import com.mariano.taskapp.fragments.NewTaskFragment;

public class MainActivity extends AppCompatActivity {

    Button newTaskBtn;
    Button todoBtn;

    public TaskDataBaseDao db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newTaskBtn = findViewById(R.id.newTaskBtn);
        todoBtn = findViewById(R.id.todoBtn);


        db = Room.databaseBuilder(getApplicationContext(), TaskDataBaseDao.class,"taskDataBase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();

        newTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTaskFragment fragment1 = new NewTaskFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1, "todo")
                        .addToBackStack("fragment").commit();
            }
        });


        todoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlankFragment frag1 = new BlankFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container, frag1, "todo")
                        .addToBackStack("fragment").commit();

            }
        });

    }




}
