package com.mariano.taskapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mariano.taskapp.DB.TaskDataBaseDao;
import com.mariano.taskapp.MainActivity;
import com.mariano.taskapp.R;
import com.mariano.taskapp.model.Task;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class NewTaskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText title,description;
    RadioGroup statusGroup, typeGroup;
    Button newTaskBtn;
    String status, type;




    public NewTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewTaskFragment newInstance(String param1, String param2) {
        NewTaskFragment fragment = new NewTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.taskTitle);
        description =view.findViewById(R.id.taskDesc);
        newTaskBtn = view.findViewById(R.id.enterNewTaskBtn);
        statusGroup = view.findViewById(R.id.status_group);
        typeGroup =view.findViewById(R.id.type_group);

        statusGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.todo_status:
                        status = "TODO";
                        break;
                    case R.id.in_progress_status:
                        status = "IN_PROGRESS";
                        break;
                    case R.id.completed_status:
                        status = "COMPLETED";
                        break;
                }
            }
        });

        typeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.familyType:
                        type = "FAMILY";
                        break;
                    case R.id.workType:
                        type = "WORK";
                        break;
                    case R.id.personalType:
                        type = "PERSONAL";
                        break;
                }
                }

        });

        newTaskBtn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
              if (getActivity() != null) {
                  if (getActivity() instanceof MainActivity) {

                      Task task = new Task();
                      List<Task> list;
                      list = ((MainActivity) getActivity()).db.taskDao().getAll();
                      int lastItem =list.size();
                      lastItem++;
                      //Toast.makeText(getActivity(), "Activity not null! and Last Item is "+lastItem,
                      //        Toast.LENGTH_LONG).show();
                      task.setDescription(description.getText().toString());
                      task.setTitle(title.getText().toString());
                      task.setStatus(status);
                      task.setId(lastItem);
                      task.setType(type);

                     ((MainActivity) getActivity()).db.taskDao().insertOne(task);
                     getFragmentManager().popBackStack();
                  }
                } else {
                    Toast.makeText(getActivity(), "activity null",
                            Toast.LENGTH_LONG).show();
                }
             }
        });

    }


}
