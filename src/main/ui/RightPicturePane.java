package ui;

import javax.swing.*;
import java.awt.*;

// This class represents a panel to display a picture
public class RightPicturePane extends JPanel {
    private static final String IMAGE_LOCATION = "./data/UBC.png";

    // This method references code from the website:
    // Link: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
    // EFFECTS: constructs panel on the right to display a picture
    public RightPicturePane() {

        // Create icon image from image in given path
        ImageIcon picture = new ImageIcon(IMAGE_LOCATION);

        // Automatically resizes icon image based on list and insert into a JLabel
        JLabel pictureContainer = new JLabel(new ImageIcon(picture.getImage().getScaledInstance(
                (int) ((picture.getIconWidth() / 3) * (ListPane.LIST_WIDTH / 400.0)),
                (int) ((picture.getIconHeight() / 3) * (ListPane.LIST_HEIGHT / 400.0)), Image.SCALE_DEFAULT)));

        // Automatically resizes this when list resizes
        pictureContainer.setPreferredSize(new Dimension(ListPane.LIST_WIDTH, ListPane.LIST_HEIGHT));
        add(pictureContainer);
    }
}
