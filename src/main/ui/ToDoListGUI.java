package ui;

import model.Task;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter
//This class represents the main window of the to-do list application
public class ToDoListGUI extends JFrame {
    private ToDoList toDoList;
    private ListPane listPane;
    private LowerPane lowerPane;

    //Constructs the to-do list gui
    public ToDoListGUI() {
        super("To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //setMinimumSize(new Dimension(500,300));
        setLocationByPlatform(true);

        initList();
        initLowerPane();

        pack();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: initialize the list pane for to-do list
    public void initList() {
        this.toDoList = new ToDoList();
        this.listPane = new ListPane();
        add(listPane, BorderLayout.NORTH);
    }

    //MODIFIES: this
    //EFFECTS: initialize the bottom portion panel with functionality
    public void initLowerPane() {
        this.lowerPane = new LowerPane(this);
        add(lowerPane, BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS: adds task to to-do list
    public void addTask(String s) {
        this.toDoList.addTask(new Task(s));
        listPane.addToDefaultListModel(s);
    }

    //MODIFIES: this
    //EFFECTS: invokes a method from listPane; if an empty string is given as result, do nothing.
    //Otherwise, if a string is deleted as a result, remove associated Task from toDoList
    public void deleteTask() {
        String result = listPane.removeSelectedItem();
        if (!result.equals("")) {
            toDoList.deleteTask(result);
        }
    }
}
