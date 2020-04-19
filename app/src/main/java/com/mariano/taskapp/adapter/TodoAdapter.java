package com.mariano.taskapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.mariano.taskapp.DB.TaskDataBaseDao;
import com.mariano.taskapp.MainActivity;
import com.mariano.taskapp.R;
import com.mariano.taskapp.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TaskViewHolder> {


    private Context context;

    public TaskDataBaseDao db;
    private List<Task> list = new ArrayList<>();
    public String typeData;

    public TodoAdapter(Context context, TaskDataBaseDao db, String typeData) {
        this.context = context;
        this.db = db;
        this.typeData = typeData;
    }

    public List<Task> getList() {
           list = db.taskDao().getAll();
           return list;
    }
    public List<Task> getListByType() {
        list = db.taskDao().getTaskByType(typeData);
        return list;
    }

    public void setList(List<Task> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_layout, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskViewHolder holder, final int position) {
            List<Task> lista = getListByType();
            holder.taskName.setText(lista.get(position).getTitle());
            holder.taskDescription.setText(lista.get(position).getDescription());
            holder.taskType.setText(lista.get(position).getType());
            switch (lista.get(position).getStatus()) {
                case "TODO":
                    holder.todo.setChecked(true);
                    break;
                case "COMPLETED":
                    holder.completed.setChecked(true);
                    break;
                default:
                    holder.inProgress.setChecked(true);
                    break;
            }

            holder.statusName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    Task task;
                    task = getListByType().get(position);
                    switch (checkedId) {
                        case R.id.todo_status:
                            task.setStatus("TODO");
                            break;
                        case R.id.completed_status:
                            task.setStatus("COMPLETED");
                            break;
                        default:
                            task.setStatus("IN_PROGRESS");
                            break;
                    }
                    db.taskDao().update(task);
                    return;
                }
            });
        }

    @Override
    public int getItemCount() {
        return getListByType().size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskName;
        TextView taskDescription;
        RadioGroup statusName;
        RadioButton todo;
        RadioButton completed;
        RadioButton inProgress;
        TextView taskType;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.todo_title);
            taskDescription =itemView.findViewById(R.id.taskDesc);
            taskType = itemView.findViewById(R.id.task_type);
            statusName = itemView.findViewById(R.id.status_indicator);
            todo = itemView.findViewById(R.id.todo_status);
            completed = itemView.findViewById(R.id.completed_status);
            inProgress = itemView.findViewById(R.id.in_progress_status);

        }
    }


}
