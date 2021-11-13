package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

// Representation of the splash/welcome screen before loading the to-do list application
public class SplashScreen {
    private static final String IMAGE_LOCATION = "./data/Amiya.gif"; //Credits to artist "Totouri" for gif
    private static final int BORDER_SEP = 50;
    private static final int DIALOG_WIDTH = 350;
    private static final int DIALOG_HEIGHT = 350;
    private JDialog dialog;
    private JProgressBar progressBar;
    private JLabel image;
    private JLabel title;
    private ToDoListGUI toDoListGUI;

    // EFFECTS: Constructs the splash screen
    public SplashScreen(ToDoListGUI toDoListGUI) {
        this.toDoListGUI = toDoListGUI;
        progressBar = new JProgressBar(0, 5000);
        dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.getRootPane().setBorder(new EmptyBorder(BORDER_SEP, BORDER_SEP, BORDER_SEP, BORDER_SEP));
        dialog.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        dialog.setUndecorated(true);

        image = new JLabel(new ImageIcon(IMAGE_LOCATION));

        title = new JLabel("To-Do List", SwingUtilities.CENTER);
        title.setFont(new Font("Calibri", Font.BOLD, 40));

        dialog.add(title, BorderLayout.NORTH);
        dialog.add(image, BorderLayout.CENTER);
        dialog.add(progressBar, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);

        Worker worker = new Worker();
        worker.execute();
    }

    // This method references code from the following website links:
    // https://www.youtube.com/watch?v=X5Q-Mecu_64
    // https://stackoverflow.com/questions/29290178/gui-has-to-wait-until-splashscreen-finishes-executing
    // Represents a swing worker thread so thread.sleep does not block EDT (GUI becomes unresponsive)
    public class Worker extends SwingWorker<Void, Integer> {

        // EFFECTS: while i < max bound of progressBar, increase i by incrementAmount while having a delay; if
        // progressBar is 1/4 full, increase the speed to fill by incrementAmount squared. Add progress to progressBar
        // by calling publish(i).
        // Returns null when while loop terminates
        // If Thread.sleep throws an InterruptedException, catch it, print stack trace and continue.
        @Override
        protected Void doInBackground() {
            final int incrementAmount = 7;
            int i = progressBar.getMinimum();
            int max = progressBar.getMaximum();

            while (i < max) {
                try {
                    Thread.sleep(8);
                } catch (Exception e) {
                    //if an exception was caught, keep going
                    e.printStackTrace();
                }
                if (i < max / 4) {
                    i += incrementAmount;
                } else {
                    i += incrementAmount * incrementAmount;
                }

                publish(i);
            }

            return null;
        }

        // MODIFIES: this
        // EFFECTS: sets the progress bar to the last element in the given list of integers
        @Override
        protected void process(List<Integer> chunks) {
            progressBar.setValue(chunks.get(chunks.size() - 1));
        }

        // MODIFIES: this
        // EFFECTS: when progress bar is finished loading, set splash screen to not visible, dispose it and set
        // toDoListGUI visible
        @Override
        protected void done() {
            dialog.setVisible(false);
            dialog.dispose();
            toDoListGUI.setVisible(true);
        }
    }

}
