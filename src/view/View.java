package view;

import model.GameState;
import model.PlayerInfo;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.*;

public class View {
    
    private final int ROWS = 3;
    private final int COLS = 3;
    private final JButton[] buttons = new JButton[ROWS * COLS];
    private final JFrame frame;
    private final JPanel game;
    private final JPanel menu;

    public View(){
        this.frame = new JFrame();

        menu = initializeMenuPanel();
        game = initializeGamePanel();

        frame.setSize(600, 600);
        frame.add(game);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel initializeGamePanel(){
        JPanel board = new JPanel(new GridLayout(ROWS, COLS));
        for(int i = 0; i < ROWS * COLS; i++){ 
            JButton button = new JButton();
            button.setName(String.valueOf(i));
            board.add(button);

            buttons[i] = button;
        }
        return board;
    }

    private void removeMenu(){
        menu.setVisible(false);
        frame.add(game);
    }

    private JPanel initializeMenuPanel(){
       return new Menu();
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
            buttons[i].setBackground(Color.YELLOW);
        }
    }

    public void setCurrentPlayer(char playerChar){
    }

    public void render(GameState state, PlayerInfo[] players){
        StringBuilder sb = new StringBuilder();
        String str = state.toString();
        System.out.println(str);
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '*') {sb.append('*'); continue;}
            char c = players[Integer.parseInt(String.valueOf(str.charAt(i)))].getMark();
            sb.append(c);
            }

        str = sb.toString();
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '*') continue;
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 45));
            buttons[i].setText(String.valueOf(str.charAt(i)));
        }


    }

    public void setGameListener(MouseAdapter m){
        for (JButton b : buttons){ 
            b.addMouseListener(m);
        }

    }


}