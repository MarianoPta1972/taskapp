package com.mariano.taskapp.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM ` TaskTable` WHERE status = :taskType" )
    List<Task> getTaskByType(String taskType);

    @Delete
    void delete(Task task);

    @Update
    void update (Task task);


}
