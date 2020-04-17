package com.mariano.taskapp.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mariano.taskapp.model.Task;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM ` tasktable`")
    List<Task> getAll();

    @Insert(onConflict = REPLACE)
    void insertAll(List<Task> todo);

    @Insert(onConflict = REPLACE)
    void insertOne(Task todo);

    @Query("SELECT * FROM ` TaskTable` WHERE id = :taskId")
    Task getTodoById(int taskId);

    @Delete
    void delete(Task task);

}
