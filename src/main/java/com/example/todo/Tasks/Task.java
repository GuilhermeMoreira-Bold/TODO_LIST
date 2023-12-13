package com.example.todo.Tasks;

import com.example.todo.utils.DataManipulation;

import java.util.ArrayList;
import java.util.List;

public class Task {
    public String description;
    public long date;
    private List<Task> subtasks;
    boolean isComplete;
    public Task leftTask, rightTask;
    public int height;
    public Task(String description){
        this.description = description;
        this.subtasks = new ArrayList<>();
        leftTask = null;
        rightTask = null;
        height = 1;
        date = DataManipulation.getDataToday();
    }
    public void addSubTask(Task subtask){
        subtasks.add(subtask);
    }
    public String getDescription(){
        return description;
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }
    public Task getSubtask(int position){
        return subtasks.get(position);
    }

    @Override
    public String toString() {
        String subtaskString = " ";
        for (Task name: this.subtasks) {
            subtaskString = name.toString();
        }
        return "Task{" +
                "description='" + description + '\'' +
                  subtaskString   + DataManipulation.getDataInString(date) +
                '}';
    }

    public void setDescription(String text) {
        description = text;
    }
}
