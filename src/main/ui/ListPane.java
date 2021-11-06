package ui;

import model.Task;
import model.ToDoList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

// Represents the list component of to-do list and completed list
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

    // EFFECTS: Constructs the component panes of the to-do list and completed list
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
    private void initToDoList() {
        defaultToDoListModel = new DefaultListModel<>();
        todoList = new JList<>(defaultToDoListModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoList.setVisibleRowCount(VISIBLE_ROWS);

        // Enable buttons when a task is selected
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
    private void initCompletedList() {
        defaultCompletedListModel = new DefaultListModel<>();
        completedList = new JList<>(defaultCompletedListModel);
        completedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        completedList.setVisibleRowCount(VISIBLE_ROWS);

        addListeners();

        completedListScrollPane = new JScrollPane(completedList);
        completedListScrollPane.setPreferredSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));
    }

    // MODIFIES: this
    // EFFECTS: adds listeners for completedList and defaultCompletedListModel
    private void addListeners() {
        // Enable uncheck button when a task is selected
        completedList.addListSelectionListener(e -> {
            if (!completedList.isSelectionEmpty()) {
                toDoListGUI.passValueIsSelectedForUncheck(true);
            } else if (completedList.isSelectionEmpty()) {
                toDoListGUI.passValueIsSelectedForUncheck(false);
            }
        });

        // Enable clear all button when completedList is not empty
        defaultCompletedListModel.addListDataListener(new ListDataListener() {
            // EFFECTS: calls contentsChanged when element is added
            @Override
            public void intervalAdded(ListDataEvent e) {
                contentsChanged(e);
            }

            // EFFECTS: calls contentsChanged when element is removed
            @Override
            public void intervalRemoved(ListDataEvent e) {
                contentsChanged(e);
            }

            // EFFECTS: if defaultCompletedListModel is not empty, pass false to ToDoListGUI to enable clear all button,
            // otherwise, pass true
            @Override
            public void contentsChanged(ListDataEvent e) {
                toDoListGUI.passValueIsCompletedListEmpty(defaultCompletedListModel.isEmpty());
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: clears defaultToDoListModel and defaultCompletedListModel
    public void clearList() {
        this.defaultToDoListModel.clear();
        this.defaultCompletedListModel.clear();
    }

    // MODIFIES: this
    // EFFECTS: clears only the defaultCompletedListModel
    public void clearCompletedList() {
        this.defaultCompletedListModel.clear();
    }

    // MODIFIES: this
    // EFFECTS: loads toDoList tasks into defaultToDoListModel and defaultCompletedListModel
    public void loadData(ToDoList toDoList) {
        List<Task> parseToStringsToDoList = toDoList.getToDoList(false);
        List<Task> parseToStringsCompletedList = toDoList.getToDoList(true);

        for (Task t : parseToStringsToDoList) {
            defaultToDoListModel.addElement(t.getNote());
        }

        for (Task t : parseToStringsCompletedList) {
            defaultCompletedListModel.addElement(t.getNote());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds string s to defaultToDoListModel
    public void addToDefaultListModel(String s) {
        defaultToDoListModel.addElement(s);
    }

    // MODIFIES: this
    // EFFECTS: if a task is selected in the to-do list, remove it and return the string. Otherwise,
    // if nothing is selected, return "".
    public String removeSelectedItem() {
        if (!todoList.isSelectionEmpty()) {
            String temp = defaultToDoListModel.get(todoList.getSelectedIndex());
            defaultToDoListModel.remove(todoList.getSelectedIndex());
            return temp;
        }
        return "";
    }

    // EFFECTS: returns true if defaultToDoListModel or defaultCompletedListModel is not empty, otherwise return false
    public boolean hasElements() {
        return !defaultToDoListModel.isEmpty() || !defaultCompletedListModel.isEmpty();
    }

    // EFFECTS: if isCompletedList is true, return current selected value in completed list,
    // otherwise, return current selected value in to-do list
    public String getCurrentSelectedTask(boolean isCompletedList) {
        if (isCompletedList) {
            return completedList.getSelectedValue();
        } else {
            return todoList.getSelectedValue();
        }
    }

    // MODIFIES: this
    // EFFECTS: updates current selected task note in to-do list to given newValue
    public void editSelectedItem(String newValue) {
        defaultToDoListModel.setElementAt(newValue, todoList.getSelectedIndex());
    }

    // MODIFIES: this
    // EFFECTS: checks selected task from to-do list and moves it to completed list
    public void checkSelectedItem() {
        String temp = defaultToDoListModel.getElementAt(todoList.getSelectedIndex());
        defaultToDoListModel.removeElementAt(todoList.getSelectedIndex());
        defaultCompletedListModel.addElement(temp);
    }

    // MODIFIES: this
    // EFFECTS: unchecks selected task from completed list and moves it to to-do list
    public void uncheckSelectedItem() {
        String temp = defaultCompletedListModel.getElementAt(completedList.getSelectedIndex());
        defaultCompletedListModel.removeElementAt(completedList.getSelectedIndex());
        defaultToDoListModel.addElement(temp);
    }


}
