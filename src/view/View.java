package view;

import model.GameState;
import model.PlayerInfo;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

    public void gameWon(int[] pos){
        for (int i : pos) {
            buttons[i].setBackground(Color.GREEN);
        }
    }

    public void gameDraw(ArrayList<Integer> pos){
        for (int i : pos) {
            buttons[i].setBackground(Color.RED);
        }
    }

    public void setCurrentPlayer(char playerChar){
    }

    public void render(GameState state, PlayerInfo[] players){
        StringBuilder sb = new StringBuilder();
        String str = state.toString();
        System.out.println(str);
        for(int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (str.charAt(i) == '*') {sb.append('*'); continue;}
            char c = players[Integer.parseInt(String.valueOf(str.charAt(i)))].getMark();
            System.out.println(c);
            sb.append(c);
            }

        str = sb.toString();
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '*') continue;
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 45));
            buttons[i].setText(String.valueOf(str.charAt(i)));
        }


    }

    public void setListener(MouseAdapter m){ 
        for (JButton b : buttons){ 
            b.addMouseListener(m);
        }

    }


}