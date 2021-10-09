package ui;

import model.Task;
import model.ToDoList;

import java.util.Scanner;

// A user console application for to-do list
public class ToDoApp {
    private ToDoList toDoList;
    private Scanner sc;

    public ToDoApp() {
        launchApp();
    }

    // This method references code from this repo
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

            if (nextAction.equals("7")) {
                keepLooping = false;
            } else {
                actionCenter(nextAction);
            }
        }

        System.out.println("See you next time!");
    }

    //EFFECTS: prints out number of options for user input
    private void displayOptions() {
        System.out.println("Please select an option:\n"
                + "[1] Add a task\n"
                + "[2] Edit a task\n"
                + "[3] Delete a task\n"
                + "[4] Check a task\n"
                + "[5] Uncheck a task\n"
                + "[6] Clear the completed list\n"
                + "[7] Exit");
    }

    //EFFECTS: displays the current to-do list elements
    private void displayList() {
        System.out.println("Current to-do list:\n");
        System.out.println(toDoList.viewToDoList());
        System.out.println();
        System.out.println("Completed list:\n");
        System.out.println(toDoList.viewCompletedList());
        System.out.println();
        System.out.println("Tasks in progress: " + toDoList.getIncompleteTasks());
        System.out.println("Tasks completed: " + toDoList.getCompletedTasks());
        System.out.println();
    }

    //MODIFIES: this
    //EFFECTS: takes the given user input and execute methods according to number
    private void actionCenter(String action) {
        switch (action) {
            case "1":
                addATask();
                break;
            case "2":
                editATask();
                break;
            case "3":
                deleteATask();
                break;
            case "4":
                checkATask();
                break;
            case "5":
                uncheckATask();
                break;
            case "6":
                clearCompleteList();
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
    }
}
