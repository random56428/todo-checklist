package ui;

import model.Task;

import javax.swing.*;
import java.awt.*;

//Represents the list component of to-do list
public class ListPane extends JPanel {
    private static final int VISIBLE_ROWS = 10;
    private static final int LIST_WIDTH = 300;
    private static final int LIST_HEIGHT = 200;
    DefaultListModel<String> defaultListModel;
    private JList<String> todoList;

    //Constructs the component panes of the to-do list
    public ListPane() {
        defaultListModel = new DefaultListModel<>();
        todoList = new JList<>(defaultListModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoList.setVisibleRowCount(VISIBLE_ROWS);

        JScrollPane scrollPane = new JScrollPane(todoList);
        scrollPane.setPreferredSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));

        add(scrollPane);
    }

    //MODIFIES: this
    //EFFECTS: adds string s to defaultListModel
    public void addToDefaultListModel(String s) {
        defaultListModel.addElement(s);
    }

    //MODIFIES: this
    //EFFECTS: if a task is selected in the list, remove it and return the string. Otherwise,
    //if nothing is selected, return "".
    public String removeSelectedItem() {
        if (!todoList.isSelectionEmpty()) {
            String temp = defaultListModel.get(todoList.getSelectedIndex());
            defaultListModel.remove(todoList.getSelectedIndex());
            return temp;
        }
        return "";
    }

}
