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
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoListGUI.deleteTask();
            }
        });
    }

}
