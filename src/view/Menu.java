package view;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    public Menu() {
        setLayout(new GridBagLayout());
        JPanel buttons = new JPanel(new GridLayout(4, 1));

        buttons.add(new JButton("Player vs Player"));
        buttons.add(new JButton("Player vs AI"));
        buttons.add(new JButton("Help"));
        buttons.add(new JButton("Exit"));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(buttons, gbc);

    }




}
