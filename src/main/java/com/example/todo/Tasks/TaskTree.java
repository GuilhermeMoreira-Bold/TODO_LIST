package com.example.todo.Tasks;

import com.example.todo.odernation.RadixSort;
import com.example.todo.utils.DataManipulation;

import java.util.*;

public class TaskTree {
    public Task root;

    public TaskTree() {
        this.root = null;
    }

    public List<Task> getTasksByDate() {
        List<Task> taskList = new ArrayList<>();
        if (root != null) {
            taskList = getAllTasksHelper(root, taskList);
        }
        RadixSort radix = new RadixSort();
        taskList = radix.sort(taskList);

        return taskList;

    }

    private List<Task> getAllTasksHelper(Task task, List<Task> taskList) {
        if (task != null) {
            getAllTasksHelper(task.leftTask, taskList);
            taskList.add(task);
            for (Task subtask : task.getSubtasks()) {
                getAllTasksHelper(subtask, taskList);
            }
            getAllTasksHelper(task.rightTask, taskList);
        }
        return taskList;
    }

    /**
     * @param description add a task here in a String format
     */
    public void addTask(String description) {
        if (root == null) {
            root = new Task(description);
            return;
        }
        if (findTask(description) == null) {
            addTask(root, description);
        } else {
            System.out.println("Task already exist");
        }
    }

    /**
     * This method provides a way to add a subtask
     *
     * @param originalTaskDescription
     * @param subtaskDescription
     */
    public void addSubtask(String originalTaskDescription, String subtaskDescription) {
        Task parent = findTask(originalTaskDescription);
        Task subtask = new Task(subtaskDescription);
        parent.addSubTask(subtask);
    }


    /**
     * @param description
     * @return return the Task object
     */
    public Task findTask(String description) {
        return find(description);
    }

    /**
     * @param description remove with the Task description
     */
    public void removeTask(String description) {
        balance(remove(root, description), description);
    }

    private Task remove(Task root, String description) {
        if (root == null) {
            System.out.println("Value not found");
            return null;
        }

        if (description.compareTo(root.description) < 0) {
            root.leftTask = remove(root.leftTask, description);
        } else if (description.compareTo(root.description) > 0) {
            root.rightTask = remove(root.rightTask, description);
        } else {
            // Caso 1: Nó sem ou com apenas um filho
            if (root.leftTask == null) {
                return root.rightTask;
            } else if (root.rightTask == null) {
                return root.leftTask;
            }

            // Caso 2: Nó com dois filhos
            root.description = minValue(root.rightTask);
            root.rightTask = remove(root.rightTask, root.description);
        }

        return root;
    }

    private String minValue(Task task) {
        Task minValue = task;
        while (task.leftTask != null) {
            minValue = task.leftTask;
            task = task.leftTask;
        }
        return minValue.description;
    }

    private Task addTask(Task task, String description) {
        if (task == null) {
            return new Task(description);
        }

        if (description.compareTo(task.description) < 0) {
            task.leftTask = addTask(task.leftTask, description);
        } else if (description.compareTo(task.description) > 0) {
            task.rightTask = addTask(task.rightTask, description);
        } else {
            return task;
        }
        updateHeight(task);
        return balance(task, description);
    }
    private Task find(String description) {
        Task current = root;
        while (current != null && !description.equals(current.description)) {
            if (current.description.compareTo(description) > 0) {
                for (int i = 0; i < current.getSubtasks().size(); i++) {
                    if (current.getSubtask(i).description.equals(description)) {
                        return current.getSubtask(i);
                    }
                }
                current = current.leftTask;
            } else {
                for (int i = 0; i < current.getSubtasks().size(); i++) {
                    if (current.getSubtask(i).description.equals(description)) {
                        return current.getSubtask(i);
                    }
                }
                current = current.rightTask;
            }
        }
        return current;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }


    private Task rightRotate(Task y) {
        Task x = y.leftTask;
        Task T2 = x.rightTask;

        x.rightTask = y;
        y.leftTask = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Task leftRotate(Task x) {
        Task y = x.rightTask;
        Task T2 = y.leftTask;

        y.leftTask = x;
        x.rightTask = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    void updateHeight(Task task) {
        if (task != null) {
            task.height = max(height(task.leftTask), height(task.rightTask) + 1);
        }
    }

    private int getBalanceFactor(Task task) {
        if (task == null) {
            return 0;
        }
        return height(task.leftTask) - height(task.rightTask);
    }

    /**
     * Use this function to avoid nullpoint execpetion
     *
     * @param task
     * @return the Task height
     */
    private int height(Task task) {
        if (task == null) {
            return 0;
        }
        return task.height;
    }

    private Task balance(Task task, String description) {
        int balanceFactor = getBalanceFactor(task);
        if (balanceFactor > 1 && description.compareTo(task.leftTask.description) < 0) {
            return rightRotate(task);
        }
        if (balanceFactor < -1 && description.compareTo(task.rightTask.description) > 0) {
            return leftRotate(task);
        }
        if (balanceFactor > 1 && description.compareTo(task.leftTask.description) > 0) {
            task.leftTask = leftRotate(task.leftTask);
            return rightRotate(task);
        }
        if (balanceFactor < -1 && description.compareTo(task.rightTask.description) < 0) {
            task.rightTask = rightRotate(task.rightTask);
            return leftRotate(task);
        }
        return task;
    }

    public void printTree() {
        if (root != null) {
            printTree(root);
        }
    }

    private void printTree(Task task) {
        if (task != null) {
            printTree(task.leftTask);
            System.out.println("Task class" + ":" + task.getDescription() + "\nTask data: " + DataManipulation.getDataInString(task.date));
            for (Task subtask : task.getSubtasks()) {
                System.out.println(subtask + " ");
            }
            printTree(task.rightTask);
        }
    }

}