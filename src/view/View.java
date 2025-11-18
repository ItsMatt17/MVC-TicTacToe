package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Model;
import model.Status;

public class View { 
    
    private final int ROWS = 3;
    private final int COLS = 3;
    private final JButton[] buttons = new JButton[ROWS * COLS];
    private final JFrame frame; 


    public View(){
        this.frame = new JFrame();
        
        initialize();

        frame.setSize(600, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initialize(){ 
        // ===== Game Panel =====
        JPanel board = new JPanel(new GridLayout(ROWS, COLS));
        
        for(int i = 0; i < ROWS * COLS; i++){ 
            JButton button = new JButton();
            button.setName(String.valueOf(i));
            board.add(button);

            buttons[i] = button;
        }
        frame.add(board);
    }

    public void reset(){
        for(JButton button : buttons){
            button.setText("");
            button.setBackground(null);
        }
    }

    public void gameWon(ArrayList<Integer> pos){
        System.out.println(pos);
        for (int i : pos) {
            buttons[i].setBackground(Color.GREEN);
        }

    }

    public void render(String board){
        for (int i = 0; i < buttons.length; i++) {
            if (board.charAt(i) == '_') continue;
            buttons[i].setFont(new Font("Arial", Font.BOLD, 45));
            buttons[i].setText(String.valueOf(board.charAt(i)));
        }
    }

    public void setListener(MouseAdapter m){ 
        for (JButton b : buttons){ 
            b.addMouseListener(m);
        }

    }


}