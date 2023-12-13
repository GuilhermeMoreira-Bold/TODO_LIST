package com.example.todo.Tasks;

import com.example.todo.utils.DataManipulation;

public class Test {
    public static void main(String[] args) {
        TaskTree taskTree = new TaskTree();
        taskTree.addTask("RAPAZZZZZ");
        taskTree.addTask("QUE ISSO MEU FILHO");
        taskTree.addTask("UIIIII");
        taskTree.addSubtask("QUE ISSO MEU FILHO", "CALMAA");
        taskTree.addSubtask("CALMAA", "DIMAIISSS");
        taskTree.printTree();
        taskTree.removeTask("QUE ISSO MEU FILHO");
        System.out.println("\nAfter remove");
        taskTree.printTree();
//        System.out.println(taskTree.getTasksByDate().toString());

    }
}
