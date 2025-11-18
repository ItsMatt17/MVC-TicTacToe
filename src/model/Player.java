package model;

import utils.Utils;

public class Player {


    private static final int[] winBoards = {0b000000111, 0b000111000, 0b111000000, 0b100100100, 0b010010010, 0b001001001, 0b100010001, 0b001010100};

    private int board = 0b000000000;
    private final char playerChar;
    private final boolean maximizing;

    public Player(char c, boolean maximizing){
        this.playerChar = c;
        this.maximizing = maximizing;
    }

    public Player(char c, boolean maximizing, int board){
        this.playerChar = c;
        this.maximizing = maximizing;
    }

    public boolean isMaximizing() {return this.maximizing;}

    public void setPos(int pos){
        board |= (1 << pos);
    }

    public int getBoard() { return board;}

    public char getPlayerChar() { return playerChar; }

    public boolean isSet(int pos){ return Utils.isSet(board, pos); }

    public void setBoard(int board){this.board = board; }

    public boolean isWin(){
        for (int mask : winBoards){
                if (Utils.testMask(board, mask, mask)) return true;
        }
        return false;
    }


}
