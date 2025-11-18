package model;

public class Player {

    private int board = 0b000000000;
    private final char boardChar;

    public Player(char c){
        this.boardChar = c;
    }

    public void setPos(int pos){
        board |= 1 << pos;
    }

    public int getBoard() { return board;}
}
