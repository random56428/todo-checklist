package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the list functionality component of to-do list
public class LowerPane extends JPanel {
    private ToDoListGUI toDoListGUI;
    private JTextField textField;
    private JButton addButton;
    private JButton deleteButton;

    //Constructs the text field and buttons
    public LowerPane(ToDoListGUI toDoListGUI) {
        super(new FlowLayout());

        this.toDoListGUI = toDoListGUI;

        initTextField();
        initAddButton();
        initDeleteButton();

        add(textField);
        add(addButton);
        add(deleteButton);
    }

    //MODIFIES: this
    //EFFECTS: initializes text field
    public void initTextField() {
        this.textField = new JTextField();
        textField.setPreferredSize(new Dimension(200,30));

        textField.getDocument().addDocumentListener(new DocumentListener() {
            //EFFECTS: calls changedUpdate when text is added
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            //EFFECTS: calls changedUpdate when text is removed
            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            //MODIFIES: this
            //EFFECTS: when text is inserted/removed/changed and text in box is not empty, enable
            //add button, otherwise disable it
            @Override
            public void changedUpdate(DocumentEvent e) {
                addButton.setEnabled(!textField.getText().equals(""));
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: initializes add button
    public void initAddButton() {
        this.addButton = new JButton("Add");
        addButton.setEnabled(false);
        addButton.addActionListener(new ActionListener() {
            //MODIFIES: this
            //EFFECTS: if text box is not empty and when add button is pressed, add string as task to
            //to-do list, reset text box, and focus back on field box, otherwise if box is empty, do nothing
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("")) {
                    toDoListGUI.addTask(textField.getText());
                    textField.setText("");
                    textField.requestFocusInWindow();
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: initializes delete button
    public void initDeleteButton() {
        this.deleteButton = new JButton("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new ActionListener() {
            //EFFECTS: when delete button is pressed, checks to see if task is selected in list, then deletes
            //it in to-do list and listPane visual list
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoListGUI.deleteTask();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: if passed value is true - a task is selected in the list, enable delete button,
    //otherwise, if false - a task is not selected in list, disable delete button
    public void enableDeleteButton(boolean isEnabled) {
        this.deleteButton.setEnabled(isEnabled);
    }

}
