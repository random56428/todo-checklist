package ui;

import javax.swing.*;
import java.awt.*;

//Represents the list functionality component of to-do list
public class LowerPane extends JPanel {

    //Constructs the text field and buttons
    public LowerPane() {
        super(new FlowLayout());

        JTextField textField = new JTextField("Hello");
        textField.setPreferredSize(new Dimension(200,30));

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");

        add(textField);
        add(addButton);
        add(deleteButton);
    }

}
