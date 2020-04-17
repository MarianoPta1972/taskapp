package com.mariano.taskapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TaskViewHolder> {


    private Context context;

    public TaskDataBaseDao db;
    private List<Task> list = new ArrayList<>();
    public TodoAdapter(Context context) {
        this.context = context;
    }

    public List<Task> getList() {
           list = db.taskDao().getAll();
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
    public void onBindViewHolder(@NonNull final TaskViewHolder holder, int position) {
        List<Task> lista = getList();
        final Task todo = list.get(position);
        holder.taskName.setText(lista.get(position).getTitle());
        // holder.taskName.setText(todo.getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskName;
        TextView taskDescription;
        TextView statusName;
        Switch taskType;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.todo_title);
            taskDescription =itemView.findViewById(R.id.taskDesc);

        }
    }


}
