package view;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    private final String[] text = {"Player vs Player", "Player vs AI", "Help", "Exit"};


    //TODO: Figure out a proper method of connecting this with controller :( (action commands?)
    public Menu() {
        setLayout(new GridBagLayout());
        setSize(600, 600);
        JPanel buttons = new JPanel(new GridLayout(text.length, 1));

        for(String str : text) {
            JButton button = new JButton(str);
            button.setPreferredSize(new Dimension(200, 65));
            buttons.add(button);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(buttons, gbc);

    }


}
