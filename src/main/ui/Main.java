package ui;

import javax.swing.*;

// Main class to create and start the user interface
public class Main {

    // This method contains code referenced in the website:
    // Link: https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
    // EFFECTS: creates a user application
    public static void main(String[] args) {
        //new ToDoListConsoleApp();

        // runs code on event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListGUI();
            }
        });
    }
}
