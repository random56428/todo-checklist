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
// This class represents the main window of the to-do list application
public class ToDoListGUI extends JFrame {
    private static final String JSON_FILE_LOCATION = "./data/todolist.json";
    private JsonReader reader;
    private JsonWriter writer;
    private ToDoList toDoList;
    private ListPane listPane;
    private LowerPane lowerPane;

    //EFFECTS: Constructs the to-do list gui
    public ToDoListGUI() {
        super("To-Do List");
        new SplashScreen(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initMenu();
        initList();
        initLowerPane();

        pack();
        setMinimumSize(this.getSize());
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: initialize the menu dropdown
    private void initMenu() {
        final int MENU_ITEM_GAP = 20;
        this.reader = new JsonReader(JSON_FILE_LOCATION);
        this.writer = new JsonWriter(JSON_FILE_LOCATION);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu view = new JMenu("View");
        JMenu zoom = new JMenu("Zoom");
        JMenuItem save = new JMenuItem("Save", 'S');
        JMenuItem load = new JMenuItem("Load", 'L');
        JMenuItem exit = new JMenuItem("Exit", 'X');
        JMenuItem zoomIn = new JMenuItem("Zoom In");
        JMenuItem zoomOut = new JMenuItem("Zoom Out");
        JMenuItem defaultZoom = new JMenuItem("Restore Default Zoom");

        setJMenuItemGaps(MENU_ITEM_GAP, zoom, save, load, exit, zoomIn, zoomOut, defaultZoom);

        initFileJMenu(file, save, load, exit);
        initViewJMenu(view, zoom, zoomIn, zoomOut, defaultZoom);

        menuBar.add(file);
        menuBar.add(view);
        add(menuBar, BorderLayout.NORTH);

        addFunctionalitySaveJMenuItem(save);
        addFunctionalityLoadJMenuItem(load);
        addFunctionalityExitJMenuItem(exit);
        addFunctionalityZoomJMenuItem(zoomIn, zoomOut, defaultZoom);
    }

    // MODIFIES: menuItemGap, zoom, save, load, exit, zoomIn, zoomOut, defaultZoom
    // EFFECTS: Sets icon gap or menu lengths of each JMenuItem by menuItemGap amount
    private void setJMenuItemGaps(int menuItemGap, JMenu zoom, JMenuItem save, JMenuItem load, JMenuItem exit,
                                  JMenuItem zoomIn, JMenuItem zoomOut, JMenuItem defaultZoom) {
        save.setIconTextGap(menuItemGap);
        load.setIconTextGap(menuItemGap);
        exit.setIconTextGap(menuItemGap);
        zoom.setIconTextGap(menuItemGap);
        zoomIn.setIconTextGap(menuItemGap + 5);
        zoomOut.setIconTextGap(menuItemGap + 5);
        defaultZoom.setIconTextGap(menuItemGap + 5);
    }

    // MODIFIES: view, zoom, zoomIn, zoomOut, defaultZoom
    // EFFECTS: Adds zoom in/out/restore zoom buttons to zoom submenu, then adds it to view menu bar
    private void initViewJMenu(JMenu view, JMenu zoom, JMenuItem zoomIn, JMenuItem zoomOut, JMenuItem defaultZoom) {
        view.setMnemonic('V');
        zoom.add(zoomIn);
        zoom.add(zoomOut);
        zoom.add(defaultZoom);
        view.add(zoom);
    }

    // MODIFIES: file, save, load, exit
    // EFFECTS: Adds save/load/exit buttons to file menu bar
    private void initFileJMenu(JMenu file, JMenuItem save, JMenuItem load, JMenuItem exit) {
        file.setMnemonic('F');
        file.add(save);
        file.add(load);
        file.addSeparator();
        file.add(exit);
    }

    // MODIFIES: this
    // EFFECTS: initialize the list pane for to-do list
    private void initList() {
        this.toDoList = new ToDoList();
        this.listPane = new ListPane(this);
        add(listPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initialize the bottom portion panel with functionality
    private void initLowerPane() {
        this.lowerPane = new LowerPane(this);
        add(lowerPane, BorderLayout.SOUTH);
    }

    // MODIFIES: this, save
    // EFFECTS: saves to-do list into JSON file
    public void addFunctionalitySaveJMenuItem(JMenuItem save) {
        save.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: when save button is pressed, saves current to-do list onto JSON file
            // Otherwise, throws FileNotFoundException if path/file not found or file can not be saved, signals to user
            // that to-do list was unable to be saved.
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.open();
                    writer.write(toDoList);
                    writer.close();
                } catch (FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(ToDoListGUI.this,
                            "Unable to save to-do list to path: " + JSON_FILE_LOCATION, "To-Do List Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // MODIFIES: this, load
    // EFFECTS: loads to-do list from JSON file and display contents on visual list
    public void addFunctionalityLoadJMenuItem(JMenuItem load) {
        load.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: when load button is pressed,
            // if there is already data in the list, prompt the user whether to overwrite current to-do list content,
            //          if yes - clears current list and load to-do list from JSON file
            //          if no - do nothing.
            // If there is no data already in the list, load the data from JSON file
            // Otherwise, throws FileNotFoundException if path/file not found or file can not be read, signals to user
            //          that toDoList was unable to be loaded.
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (listPane.hasElements()) {
                        int result = JOptionPane.showConfirmDialog(ToDoListGUI.this,
                                "There is already data in the list. Are you sure you want to replace it?",
                                "Load File", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if (result == JOptionPane.YES_OPTION) {
                            loadDataToListPane();
                        }
                    } else {
                        loadDataToListPane();
                    }
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(ToDoListGUI.this,
                            "Unable to load to-list from path: " + JSON_FILE_LOCATION, "To-Do List Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: reads to-do list (includes completed list) from JSON file, clears visual list, then loads data
    // into the visual list in listPane
    // throws IOException if an error occurs when trying to read from the file
    private void loadDataToListPane() throws IOException {
        toDoList = reader.read();
        listPane.clearList();
        listPane.loadData(toDoList);
    }

    // MODIFIES: this, exit
    // EFFECTS: button to exit the application
    public void addFunctionalityExitJMenuItem(JMenuItem exit) {
        exit.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: when exit button is pressed, terminates the entire application
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // MODIFIES: zoomIn, zoomOut, defaultZoom
    // EFFECTS: adds functionality to zoomIn and zoomOut buttons - increases/decreases zoom on lists in listPane
    private void addFunctionalityZoomJMenuItem(JMenuItem zoomIn, JMenuItem zoomOut, JMenuItem defaultZoom) {
        // MODIFIES: this
        // EFFECTS: when zoomIn menu button is pressed, call listPane method to zoom in
        zoomIn.addActionListener(e -> listPane.zoom(true));

        // MODIFIES: this
        // EFFECTS: when zoomOut menu button is pressed, call listPane method to zoom out
        zoomOut.addActionListener(e -> listPane.zoom(false));

        // MODIFIES: this
        // EFFECTS: when defaultZoom menu button is pressed, call listPane method to reset default zoom
        defaultZoom.addActionListener(e -> listPane.restoreZoom());
    }

    // MODIFIES: this
    // EFFECTS: adds task to to-do list, calls method on listPane to add task to visual list
    public void addTask(String s) {
        this.toDoList.addTask(new Task(s));
        listPane.addToDefaultListModel(s);
    }

    // MODIFIES: this
    // EFFECTS: invokes a method from listPane; if an empty string is given as result, do nothing.
    // Otherwise, if a string is deleted as a result, remove associated Task from toDoList
    public void deleteTask() {
        String result = listPane.removeSelectedItem();
        if (!result.equals("")) {
            toDoList.deleteTask(result);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to edit a task to specified text in dialog
    public void editTask() {
        String selectedValue = listPane.getCurrentSelectedTask(false);
        String newValue = (String) JOptionPane.showInputDialog(ToDoListGUI.this,
                "Edit your task note to:", "Edit", JOptionPane.PLAIN_MESSAGE, null,
                null, selectedValue);
        if (newValue != null && !selectedValue.equals(newValue)) {
            toDoList.editTask(selectedValue, newValue);
            listPane.editSelectedItem(newValue);
        }
    }

    // MODIFIES: this
    // EFFECTS: checks the selected task in listPane and check it as completed
    public void checkTask() {
        String selectedValue = listPane.getCurrentSelectedTask(false);
        toDoList.checkTask(selectedValue);
        listPane.checkSelectedItem();
    }

    // MODIFIES: this
    // EFFECTS: unchecks the selected task in listPane and uncheck it, move it back from completed list to to-do list
    public void uncheckTask() {
        String selectedValue = listPane.getCurrentSelectedTask(true);
        toDoList.uncheckTask(selectedValue);
        listPane.uncheckSelectedItem();
    }

    // MODIFIES: this
    // EFFECTS: clears entire completed list in ToDoList and in visual list
    public void clearAllCompletedTasks() {
        toDoList.deleteAllCompleteTask();
        listPane.clearCompletedList();
    }

    // EFFECTS: passes the boolean invoked by listPane to lowerPane of whether a task is selected in to-do list
    public void passValueIsSelected(boolean isSelected) {
        lowerPane.enableButtons(isSelected);
    }

    // EFFECTS: passes the boolean invoked by listPane to lowerPane of whether a task is selected in completed list
    public void passValueIsSelectedForUncheck(boolean isSelected) {
        lowerPane.enableUncheckButton(isSelected);
    }

    // EFFECTS: passes the boolean invoked by listPane to lowerPane of whether completed list is empty or not
    public void passValueIsCompletedListEmpty(boolean isEmpty) {
        lowerPane.enableClearAllButton(isEmpty);
    }

}
