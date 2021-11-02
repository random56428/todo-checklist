package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter
//This class represents the main window of the to-do list application
public class ToDoListGUI extends JFrame {
    private static final String JSON_FILE_LOCATION = "./data/todolist.json";
    private JsonReader reader;
    private JsonWriter writer;
    private ToDoList toDoList;
    private ListPane listPane;
    private LowerPane lowerPane;

    //Constructs the to-do list gui
    public ToDoListGUI() {
        super("To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(500, 350));
        setLocationByPlatform(true);

        initMenu();
        initList();
        initLowerPane();

        pack();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: initialize the menu dropdown
    public void initMenu() {
        final int MENU_ITEM_GAP = 20;
        this.reader = new JsonReader(JSON_FILE_LOCATION);
        this.writer = new JsonWriter(JSON_FILE_LOCATION);
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem exit = new JMenuItem("Exit");
        save.setIconTextGap(MENU_ITEM_GAP);
        load.setIconTextGap(MENU_ITEM_GAP);
        exit.setIconTextGap(MENU_ITEM_GAP);
        //todo: Add Mnemonics and save/load icons to JMenuItems
        file.add(save);
        file.add(load);
        file.addSeparator();
        file.add(exit);
        menuBar.add(file);
        add(menuBar, BorderLayout.NORTH);

        saveJMenuItem(save);
        loadJMenuItem(load);
        exitJMenuItem(exit);
    }

    //MODIFIES: this
    //EFFECTS: initialize the list pane for to-do list
    public void initList() {
        this.toDoList = new ToDoList();
        this.listPane = new ListPane(this);
        add(listPane, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: initialize the bottom portion panel with functionality
    public void initLowerPane() {
        this.lowerPane = new LowerPane(this);
        add(lowerPane, BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS: saves to-do list into JSON file
    public void saveJMenuItem(JMenuItem save) {
        save.addActionListener(new ActionListener() {
            //MODIFIES: this
            //EFFECTS: when save button is pressed, saves current to-do list onto JSON file and signals to user
            //that to-do list was successfully saved.
            //Otherwise, throws FileNotFoundException if path/file not found or file can not be saved, signals to user
            //that to-do list was unable to be saved.
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.open();
                    writer.write(toDoList);
                    writer.close();

                    //todo: Add JOptionPane here & delete line below (Need interface/abstract class for popup here?)
                    System.out.println("[SUCCESS] Saved to-do list to path: " + JSON_FILE_LOCATION);
                } catch (FileNotFoundException exception) {

                    //todo: Add JOptionPane here & delete line below
                    System.out.println("[FAILURE] Unable to save to-do list to path: " + JSON_FILE_LOCATION);
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: loads to-do list from JSON file and display contents on visual list
    public void loadJMenuItem(JMenuItem load) {
        load.addActionListener(new ActionListener() {
            //MODIFIES: this
            // EFFECTS: when load button is pressed, prompts user whether to overwrite current to-do list content,
            //          if yes - clears current list and load to-do list from JSON file and signals to user
            //          that toDoList was successfully loaded.
            // Otherwise, throws FileNotFoundException if path/file not found or file can not be read, signals to user
            // that toDoList was unable to be loaded.
            //          if no - do nothing
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //todo: Add JOptionPane to ask if the user wants to overwrite current to-do list with loaded data
                    toDoList = reader.read();
                    listPane.clearList();
                    listPane.loadData(toDoList);

                    //todo: Add JOptionPane here & delete line below
                    System.out.println("[SUCCESS] Loaded to-do list from path: " + JSON_FILE_LOCATION);
                } catch (IOException exception) {

                    //todo: Add JOptionPane here & delete line below
                    System.out.println("[FAILURE] Unable to load to-list from path: " + JSON_FILE_LOCATION);
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: button to exit the application
    public void exitJMenuItem(JMenuItem exit) {
        exit.addActionListener(new ActionListener() {
            //MODIFIES: this
            //EFFECTS: when exit button is pressed, terminates the entire application
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds task to to-do list, calls method on listPane to add task to visual list
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

    //EFFECTS: passes the boolean invoked by listPane to lowerPane of whether a task is selected in list
    public void passValueIsSelected(boolean isSelected) {
        lowerPane.enableDeleteButton(isSelected);
    }

}
