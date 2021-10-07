package model;

import java.util.LinkedList;
import java.util.List;

// Represents a to-do list to add tasks to undertake and a list with completed tasks.
// Includes basic functions every to-do list has such as add, delete, edit, view, and check a task.
public class ToDoList {
    private List<Task> toDoList;
    private List<Task> completedList;

    //constructor for to-do list
    //EFFECTS: creates an empty to-do list and empty completed list
    public ToDoList() {
        toDoList = new LinkedList<>();
        completedList = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds given task to the end of the to-do list
    public void addTask(Task t) {
        toDoList.add(t);
    }

    //MODIFIES: this
    //EFFECTS: removes the first occurrence of task matching the string n from the to-do list,
    //         otherwise if not found, do nothing
    public void deleteTask(String n) {
        for (Task t : toDoList) {
            if (t.getNote().equals(n)) {
                toDoList.remove(t);
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: changes the task note matching the first occurrence of beforeNote to afterNote
    //         in the to-do list, otherwise if not found, do nothing
    public void editTask(String beforeNote, String afterNote) {
        for (Task t : toDoList) {
            if (t.getNote().equals(beforeNote)) {
                t.setNote(afterNote);
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: check the first occurrence of task matching the given string n in to-do list,
    //         remove it from the to-do list, and add it to the end of the completed list,
    //         otherwise if not found, do nothing
    public void checkTask(String n) {
        for (Task t : toDoList) {
            if (t.getNote().equals(n)) {
                t.check();
                completedList.add(t);
                toDoList.remove(t);
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: uncheck the first occurrence of task matching given string n in complete
    //         list, remove it from the complete list, and add it to the end of to-do list,
    //         otherwise if not found, do nothing
    public void uncheckTask(String n) {
        for (Task t : completedList) {
            if (t.getNote().equals(n)) {
                t.uncheck();
                toDoList.add(t);
                completedList.remove(t);
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: deletes all completed task from complete list
    public void deleteAllCompleteTask() {
        completedList.clear();
    }

    //EFFECTS: returns a string of all the task's notes currently in to-do list
    //         in format: "note A, note B, note C", etc.
    public String viewToDoList() {
        String allTasks = "";

        for (Task t : toDoList) {
            allTasks += t.getNote() + ", ";
        }

        if (allTasks.length() > 1) {
            return allTasks.substring(0, allTasks.length() - 2);
        } else {
            return allTasks;
        }
    }

    //EFFECTS: returns the number of tasks still incomplete in the to-do list
    public int getIncompleteTasks() {
        return toDoList.size();
    }

    //EFFECTS: returns the number of tasks that are completed in the to-do list
    public int getCompletedTasks() {
        return completedList.size();
    }

}
