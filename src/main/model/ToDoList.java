package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

// Represents a to-do list to add tasks to undertake and a list with completed tasks.
// Includes basic functions every to-do list has such as add, delete, edit, view, and check a task.
public class ToDoList implements Writable {
    private List<Task> toDoList;
    private List<Task> completedList;

    // constructor for to-do list
    // EFFECTS: creates an empty to-do list and empty completed list
    public ToDoList() {
        toDoList = new LinkedList<>();
        completedList = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given task to the end of the to-do list
    public void addTask(Task t) {
        toDoList.add(t);
    }

    // MODIFIES: this
    // EFFECTS: removes the first occurrence of task matching the string n from the to-do list
    //          and return true, otherwise if not found, return false
    public boolean deleteTask(String n) {
        for (Task t : toDoList) {
            if (t.getNote().equals(n)) {
                toDoList.remove(t);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: changes the task note matching the first occurrence of beforeNote to afterNote
    //          in the to-do list and return true, otherwise if not found, return false
    public boolean editTask(String beforeNote, String afterNote) {
        for (Task t : toDoList) {
            if (t.getNote().equals(beforeNote)) {
                t.setNote(afterNote);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: check the first occurrence of task matching the given string n in to-do list,
    //          remove it from the to-do list, then add it to the end of the completed list and
    //          return true, otherwise if not found, return false
    public boolean checkTask(String n) {
        for (Task t : toDoList) {
            if (t.getNote().equals(n)) {
                t.check();
                completedList.add(t);
                toDoList.remove(t);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: uncheck the first occurrence of task matching given string n in complete
    //          list, remove it from the complete list, then add it to the end of to-do list and
    //          return true, otherwise if not found, return false
    public boolean uncheckTask(String n) {
        for (Task t : completedList) {
            if (t.getNote().equals(n)) {
                t.uncheck();
                toDoList.add(t);
                completedList.remove(t);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: deletes all completed task from complete list
    public void deleteAllCompleteTask() {
        completedList.clear();
    }

    // EFFECTS: returns a string of all the task's notes currently in to-do list/completed list
    //          in format: "note A, note B, note C", etc.
    //          Returns in progress to-do list if parameter is false, and returns completed to-do list
    //          if parameter is true
    public String viewToDoList(Boolean isCompleted) {
        StringBuilder allTasks = new StringBuilder();
        List<Task> whichListToView;

        if (isCompleted) {
            whichListToView = completedList;
        } else {
            whichListToView = toDoList;
        }

        for (Task t : whichListToView) {
            allTasks.append(t.getNote() + ", ");
        }

        if (allTasks.length() > 1) {
            allTasks.setLength(allTasks.length() - 2);
        }

        return allTasks.toString();
    }

    // EFFECTS: if param is true, return the completed list, otherwise,
    //          if param is false, return the to-do list
    public List<Task> getToDoList(Boolean isCompleted) {
        if (isCompleted) {
            return this.completedList;
        } else {
            return this.toDoList;
        }
    }

    // EFFECTS: returns the number of tasks still incomplete in the to-do list
    public int getIncompleteTasks() {
        return toDoList.size();
    }

    // EFFECTS: returns the number of tasks that are completed in the to-do list
    public int getCompletedTasks() {
        return completedList.size();
    }

    // This method references code from this repository
    // Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: puts to-do list and completed list into json object and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("todolist", allTasksToJson());
        return json;
    }

    // This method references code from this repository
    // Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns tasks in this to-do list and completed list in a json array
    private JSONArray allTasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : toDoList) {
            jsonArray.put(t.toJson());
        }

        for (Task t : completedList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}