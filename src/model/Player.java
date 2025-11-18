package model;

public class Player {

    private int board = 0b000000000;
    private final char playerChar;

    public Player(char c){
        this.playerChar = c;
    }

    public void setPos(int pos){
        board |= 1 << pos;
    }

    public int getBoard() { return board;}

    public char getPlayerChar() { return playerChar; }

    public boolean isSet(int pos){ return (board & (1 << pos)) != 0;}
}
