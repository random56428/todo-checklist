package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the list functionality component of to-do list
public class LowerPane extends JPanel {
    private ToDoListGUI toDoListGUI;
    private JTextField textField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton checkButton;

    // Constructs the text field and buttons
    public LowerPane(ToDoListGUI toDoListGUI) {
        super(new FlowLayout());

        this.toDoListGUI = toDoListGUI;

        initTextField();
        initAddButton();
        initDeleteButton();
        initEditButton();
        initCheckButton();

        add(textField);
        add(addButton);
        add(deleteButton);
        add(editButton);
        add(checkButton);
    }

    // This method references code from the website:
    // Link: https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
    // MODIFIES: this
    // EFFECTS: initializes text field
    private void initTextField() {
        this.textField = new JTextField();
        textField.setPreferredSize(new Dimension(200,30));

        textField.getDocument().addDocumentListener(new DocumentListener() {
            // EFFECTS: calls changedUpdate when text is added
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            // EFFECTS: calls changedUpdate when text is removed
            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            // MODIFIES: this
            // EFFECTS: when text is inserted/removed/changed and text in box is not empty, enable
            // add button, otherwise disable it
            @Override
            public void changedUpdate(DocumentEvent e) {
                addButton.setEnabled(!textField.getText().equals(""));
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes add button
    private void initAddButton() {
        this.addButton = new JButton("Add");
        addButton.setEnabled(false);
        addButton.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: if text box is not empty and when add button is pressed, add string as task to
            // to-do list, reset text box, and focus back on field box, otherwise if box is empty, do nothing
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

    // MODIFIES: this
    // EFFECTS: initializes delete button
    private void initDeleteButton() {
        this.deleteButton = new JButton("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new ActionListener() {
            // EFFECTS: when delete button is pressed, checks to see if task is selected in list, then deletes
            // it in to-do list and listPane visual list
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoListGUI.deleteTask();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes edit button
    private void initEditButton() {
        this.editButton = new JButton("Edit");
        editButton.setEnabled(false);
        editButton.addActionListener(new ActionListener() {
            // EFFECTS: when edit button is pressed, calls editTask in toDoListGUI to show option pane
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoListGUI.editTask();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes check button
    private void initCheckButton() {
        this.checkButton = new JButton("Check");
        checkButton.setEnabled(false);
        checkButton.addActionListener(new ActionListener() {
            // EFFECTS: when check button is pressed, calls checkTask in toDoListGUI
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoListGUI.checkTask();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: if passed value is true - a task is selected in the list, enable delete/edit/check buttons,
    // otherwise, if false - a task is not selected in list, disable the buttons
    public void enableButtons(boolean isEnabled) {
        this.deleteButton.setEnabled(isEnabled);
        this.editButton.setEnabled(isEnabled);
        this.checkButton.setEnabled(isEnabled);
    }

}
