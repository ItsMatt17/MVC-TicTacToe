package model;


import java.util.ArrayList;

public class Model {

    private final int[] winBoards = {0b000000111, 0b000111000, 0b111000000, 0b100100100, 0b010010010, 0b001001001, 0b100010001, 0b001010100};
    int p1;
    int p2;
    int turn; // 0 = p1's turn vs 1 == p2's
    ArrayList<Integer> winningPos =  new ArrayList<>();
    String winner = "";

    public Model(){
        this.p1 = 0;
        this.p2 = 0;
        this.turn = 0;
    }

    public boolean isWon(){
        for (int mask : winBoards) {
            if (((mask & p1) == mask) || (mask & p2) == mask) {
                setWinningPos(mask);

                return true;
            }
        }
        return false;
    }

    public void setWinningPos(int mask){
        for (int i = 0; i < 9; i++) {
            if ((mask & (1 << i)) != 0) winningPos.add(i);
        }
    }


    public ArrayList<Integer> getWinningPos(){
        return winningPos;
    }

    public boolean isFull(){ return (getBoard() == 0b111111111); }

    public int getBoard(){
        return (this.p1 | this.p2);
    }

    public boolean isOpen(int pos){
        return ((this.getBoard() & (1 << pos)) == 0);
    }

    public int getPlayer(int pos){
        if ((this.p1 & (1 << pos)) != 0) return 0;
        else if ((this.p2 & (1 << pos)) != 0) return 1;
        return -1;
    }

    public void move(int pos){
        if (!isOpen(pos)) return;
        if (turn == 0) p1 |= 1 << pos;
        if (turn == 1) p2 |= 1 << pos;
        turn ^= 1; // Swap turns
    }
    
    @Override
    public String toString(){ 
        StringBuilder str = new StringBuilder();
        char c = 0;
        for(int i = 0; i < 9; i++){ 
            int p = this.getPlayer(i);

            if (p == -1) c = '_';
            else if (p == 0) c = 'X'; 
            else if (p == 1) c = 'O';

            str.append(c);
        }
        return str.toString();

    }
    

}