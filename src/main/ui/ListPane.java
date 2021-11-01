package ui;

import model.Task;

import javax.swing.*;
import java.awt.*;

//Represents the list component of to-do list
public class ListPane extends JPanel {
    private static final int VISIBLE_ROWS = 10;
    private static final int LIST_WIDTH = 300;
    private static final int LIST_HEIGHT = 200;
    private JList<String> todoList;

    //Constructs the component panes of the to-do list
    public ListPane() {

        String[] listOfStrings = new String[]{"Hello", "Hello09123890uigrberbiogurbegioergrieognrieorgeongreoin1840cc"};

        todoList = new JList<>(listOfStrings);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoList.setVisibleRowCount(VISIBLE_ROWS);

        JScrollPane scrollPane = new JScrollPane(todoList);
        scrollPane.setPreferredSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));

        add(scrollPane);
    }

}
