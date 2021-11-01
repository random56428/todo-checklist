package ui;

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
        toDoList = new ToDoList();
        listPane = new ListPane();
        add(listPane, BorderLayout.NORTH);
    }

    //MODIFIES: this
    //EFFECTS: initialize the bottom portion panel with functionality
    public void initLowerPane() {
        lowerPane = new LowerPane();
        add(lowerPane, BorderLayout.SOUTH);
    }
}
