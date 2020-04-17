package com.mariano.taskapp.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mariano.taskapp.model.Task;

@Database(entities = {Task.class}, version = 1,exportSchema = false)
public abstract class TaskDataBaseDao extends RoomDatabase{
    public abstract TaskDao taskDao();
}
