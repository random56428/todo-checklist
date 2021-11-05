package ui;

import model.Task;
import model.ToDoList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

// Represents the list component of to-do list
public class ListPane extends JPanel {
    private static final int VISIBLE_ROWS = 10;
    private static final int BORDER_MARGIN = 10;
    private static final int LIST_WIDTH = 300;
    private static final int LIST_HEIGHT = 200;
    private ToDoListGUI toDoListGUI;
    private DefaultListModel<String> defaultToDoListModel;
    private DefaultListModel<String> defaultCompletedListModel;
    private JList<String> todoList;
    private JList<String> completedList;
    private JScrollPane todoListScrollPane;
    private JScrollPane completedListScrollPane;

    // Constructs the component panes of the to-do list
    public ListPane(ToDoListGUI toDoListGUI) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.toDoListGUI = toDoListGUI;

        JPanel todoListJPanel = new JPanel();
        JPanel completedListJPanel = new JPanel();

        // The following line of code is referenced from the website:
        // Link: https://stackoverflow.com/questions/25227777/swing-create-a-compoundborder-with-3-borders
        todoListJPanel.setBorder(new CompoundBorder(
                new CompoundBorder(
                        new EmptyBorder(BORDER_MARGIN, BORDER_MARGIN, BORDER_MARGIN, BORDER_MARGIN),
                        new TitledBorder(new EtchedBorder(), "To-Do List")),
                new EmptyBorder(BORDER_MARGIN, BORDER_MARGIN, BORDER_MARGIN, BORDER_MARGIN)));

        // The following line of code is referenced from the website:
        // Link: https://stackoverflow.com/questions/25227777/swing-create-a-compoundborder-with-3-borders
        completedListJPanel.setBorder(new CompoundBorder(
                new CompoundBorder(
                        new EmptyBorder(BORDER_MARGIN, BORDER_MARGIN, BORDER_MARGIN, BORDER_MARGIN),
                        new TitledBorder(new EtchedBorder(), "Completed List")),
                new EmptyBorder(BORDER_MARGIN, BORDER_MARGIN, BORDER_MARGIN, BORDER_MARGIN)));

        initToDoList();
        initCompletedList();

        todoListJPanel.add(todoListScrollPane);
        completedListJPanel.add(completedListScrollPane);

        add(todoListJPanel);
        add(completedListJPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes the to-do list
    public void initToDoList() {
        defaultToDoListModel = new DefaultListModel<>();
        todoList = new JList<>(defaultToDoListModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoList.setVisibleRowCount(VISIBLE_ROWS);

        // Enable the delete button when a task is selected
        todoList.addListSelectionListener(new ListSelectionListener() {
            // EFFECTS: if a task is selected, pass true value to lowerPane to enable button, otherwise if
            // task is not selected, pass false to lowerPane to disable button
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!todoList.isSelectionEmpty()) {
                    toDoListGUI.passValueIsSelected(true);
                } else if (todoList.isSelectionEmpty()) {
                    toDoListGUI.passValueIsSelected(false);
                }
            }
        });

        todoListScrollPane = new JScrollPane(todoList);
        todoListScrollPane.setPreferredSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));
    }

    // MODIFIES: this
    // EFFECTS: initializes the completed list
    public void initCompletedList() {
        defaultCompletedListModel = new DefaultListModel<>();
        completedList = new JList<>();
        completedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        completedList.setVisibleRowCount(VISIBLE_ROWS);

        completedListScrollPane = new JScrollPane(completedList);
        completedListScrollPane.setPreferredSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));
    }

    // MODIFIES: this
    // EFFECTS: clears defaultListModel
    public void clearList() {
        this.defaultToDoListModel.clear();
    }

    // MODIFIES: this
    // EFFECTS: loads toDoList tasks onto defaultListModel
    public void loadData(ToDoList toDoList) {
        List<Task> parseToStrings = toDoList.getToDoList(false);

        for (Task t : parseToStrings) {
            defaultToDoListModel.addElement(t.getNote());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds string s to defaultListModel
    public void addToDefaultListModel(String s) {
        defaultToDoListModel.addElement(s);
    }

    // MODIFIES: this
    // EFFECTS: if a task is selected in the list, remove it and return the string. Otherwise,
    // if nothing is selected, return "".
    public String removeSelectedItem() {
        if (!todoList.isSelectionEmpty()) {
            String temp = defaultToDoListModel.get(todoList.getSelectedIndex());
            defaultToDoListModel.remove(todoList.getSelectedIndex());
            return temp;
        }
        return "";
    }

    // EFFECTS: returns true if defaultListModel is not empty, otherwise return false
    public boolean hasElements() {
        return !defaultToDoListModel.isEmpty();
    }

    // EFFECTS: returns currently selected value
    public String getCurrentSelectedTask() {
        return todoList.getSelectedValue();
    }

    // MODIFIES: this
    // EFFECTS: updates current selected task note to given newValue
    public void editSelectedItem(String newValue) {
        defaultToDoListModel.setElementAt(newValue, todoList.getSelectedIndex());
    }

}
