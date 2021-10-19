package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// A user console application for to-do list
public class ToDoApp {
    private static final String JSON_FILE_LOCATION = "./data/todolist.json";
    private static final String OPTION_ADD_TASK = "1";
    private static final String OPTION_EDIT_TASK = "2";
    private static final String OPTION_DELETE_TASK = "3";
    private static final String OPTION_CHECK_TASK = "4";
    private static final String OPTION_UNCHECK_TASK = "5";
    private static final String OPTION_CLEAR_COMPLETED_LIST = "6";
    private static final String OPTION_SAVE_LIST = "7";
    private static final String OPTION_LOAD_LIST = "8";
    private static final String OPTION_EXIT = "9";
    private ToDoList toDoList;
    private Scanner sc;
    private JsonReader reader;
    private JsonWriter writer;

    public ToDoApp() {
        launchApp();
    }

    // This method references code from this repository
    // Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    //MODIFIES: this
    //EFFECTS: start the application and ask for user input
    private void launchApp() {
        boolean keepLooping = true;
        String nextAction;

        init();

        while (keepLooping) {
            displayList();
            displayOptions();

            nextAction = sc.nextLine();

            if (nextAction.equals(OPTION_EXIT)) {
                keepLooping = false;
            } else {
                actionCenter(nextAction);
            }
        }

        System.out.println("See you next time!");
    }

    //EFFECTS: prints out number of options for user input
    private void displayOptions() {
        System.out.println("Please select an option:");
        System.out.println("[1] Add a task");
        System.out.println("[2] Edit a task");
        System.out.println("[3] Delete a task");
        System.out.println("[4] Check a task");
        System.out.println("[5] Uncheck a task");
        System.out.println("[6] Clear the completed list");
        System.out.println("[7] Save to-do list to file");
        System.out.println("[8] Load to-do list from file");
        System.out.println("[9] Exit");
    }

    //EFFECTS: displays the current to-do list elements
    private void displayList() {
        System.out.println("Current to-do list:\n");
        System.out.println(toDoList.viewToDoList(false));
        System.out.println();
        System.out.println("Completed list:\n");
        System.out.println(toDoList.viewToDoList(true));
        System.out.println();
        System.out.println("Tasks in progress: " + toDoList.getIncompleteTasks());
        System.out.println("Tasks completed: " + toDoList.getCompletedTasks());
        System.out.println();
    }

    //MODIFIES: this
    //EFFECTS: takes the given user input and execute methods according to number
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void actionCenter(String action) {
        switch (action) {
            case OPTION_ADD_TASK:
                addATask();
                break;
            case OPTION_EDIT_TASK:
                editATask();
                break;
            case OPTION_DELETE_TASK:
                deleteATask();
                break;
            case OPTION_CHECK_TASK:
                checkATask();
                break;
            case OPTION_UNCHECK_TASK:
                uncheckATask();
                break;
            case OPTION_CLEAR_COMPLETED_LIST:
                clearCompleteList();
                break;
            case OPTION_SAVE_LIST:
                saveToDoList();
                break;
            case OPTION_LOAD_LIST:
                loadToDoList();
                break;
            default:
                System.out.println("[INVALID] Invalid entry\n");
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: add a task to the to-do list
    private void addATask() {
        System.out.println("[ADD] Enter your task to add:");
        String userTask = sc.nextLine();
        toDoList.addTask(new Task(userTask));
        System.out.println("[SUCCESS] Successfully added task\n");
    }

    //MODIFIES: this
    //EFFECTS: edits a task in the to-do list
    private void editATask() {
        System.out.println("[EDIT] Please type the task to edit:");
        String taskToChange = sc.nextLine();
        System.out.println("[EDIT] Enter new note to change to:");
        String newNote = sc.nextLine();
        boolean result = toDoList.editTask(taskToChange, newNote);

        if (result) {
            System.out.println("[SUCCESS] Successfully edited task\n");
        } else {
            System.out.println("[FAILURE] Could not find specified task to edit\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: deletes a task from to-do list
    private void deleteATask() {
        System.out.println("[DELETE] Please type the task to delete:");
        boolean result = toDoList.deleteTask(sc.nextLine());

        if (result) {
            System.out.println("[SUCCESS] Successfully deleted task\n");
        } else {
            System.out.println("[FAILURE] Could not find specified task to delete\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: checks a task for completion and move it to the completed list
    private void checkATask() {
        System.out.println("[CHECK] Please type the task to check:");
        boolean result = toDoList.checkTask(sc.nextLine());

        if (result) {
            System.out.println("[SUCCESS] Successfully checked task\n");
        } else {
            System.out.println("[FAILURE] Could not find specified task to check\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: unchecks a task and move it back to to-do list
    private void uncheckATask() {
        System.out.println("[UNCHECK] Please type the task to uncheck:");
        boolean result = toDoList.uncheckTask(sc.nextLine());

        if (result) {
            System.out.println("[SUCCESS] Successfully unchecked task\n");
        } else {
            System.out.println("[FAILURE] Could not find specified task to uncheck\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: clears the completed list
    private void clearCompleteList() {
        toDoList.deleteAllCompleteTask();
        System.out.println("[SUCCESS] Successfully cleared the completed list!\n");
    }

    //MODIFIES: this
    //EFFECTS: initialize all local fields
    private void init() {
        this.toDoList = new ToDoList();
        this.sc = new Scanner(System.in);
        this.reader = new JsonReader(JSON_FILE_LOCATION);
        this.writer = new JsonWriter(JSON_FILE_LOCATION);
    }

    //EFFECTS: saves to-do list data onto file
    private void saveToDoList() {
        try {
            writer.open();
            writer.write(toDoList);
            writer.close();
            System.out.println("[SUCCESS] Saved to-do list to path: " + JSON_FILE_LOCATION);
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("[FAILURE] Unable to save to-do list to path: " + JSON_FILE_LOCATION);
            System.out.println();
        }
    }

    //MODIFIES: this
    //EFFECTS: loads to-do list from file
    private void loadToDoList() {
        try {
            toDoList = reader.read();
            System.out.println("[SUCCESS] Loaded to-do list from path: " + JSON_FILE_LOCATION);
            System.out.println();
        } catch (IOException e) {
            System.out.println("[FAILURE] Unable to load to-list from path: " + JSON_FILE_LOCATION);
            System.out.println();
        }
    }

}
