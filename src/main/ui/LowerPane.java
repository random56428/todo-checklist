package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the list functionality component of to-do list
public class LowerPane extends JPanel {
    ToDoListGUI toDoListGUI;

    //Constructs the text field and buttons
    public LowerPane(ToDoListGUI toDoListGUI) {
        super(new FlowLayout());

        this.toDoListGUI = toDoListGUI;

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200,30));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("")) {
                    toDoListGUI.addTask(textField.getText());
                    textField.setText("");
                    textField.requestFocusInWindow();
                }
            }
        });

        JButton deleteButton = new JButton("Delete");

        add(textField);
        add(addButton);
        add(deleteButton);
    }

}
