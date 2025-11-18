package model;

public class Player {

    private final int[] winBoards = {0b000000111, 0b000111000, 0b111000000, 0b100100100, 0b010010010, 0b001001001, 0b100010001, 0b001010100};
    private int board = 0b000000000;
    private final char boardChar;

    public Player(char c){
        this.boardChar = c;
    }

    private void setPos(int pos){
        board |= 1 << pos;
    }

    public boolean isWon() {
        for (int mask : winBoards) {
            if (mask == board) return true;
        }
        return false;
    }

    // Assuming the position is already free
    public void play(int pos){
        setPos(pos);
    }

    // minimax this
    public void play(){
    }

    public void minimax(){

    }






}
