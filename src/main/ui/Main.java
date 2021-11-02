package ui;

import javax.swing.*;

// Main class to create and start the user interface
public class Main {

    //EFFECTS: creates a user application
    public static void main(String[] args) {
        //new ToDoListConsoleApp();

        //runs code on event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListGUI();
            }
        });
    }
}
