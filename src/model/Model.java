package model;


import java.util.ArrayList;

public class Model {

    private final int[] winBoards = {0b000000111, 0b000111000, 0b111000000, 0b100100100, 0b010010010, 0b001001001, 0b100010001, 0b001010100};
    Player p1;
    Player p2;
    int turn; // 0 = p1's turn vs 1 == p2's
    ArrayList<Integer> winningPos =  new ArrayList<>();
    String winner = "";

    public Model(){
        this.p1 = new Player('X');
        this.p2 = new Player('O');
        this.turn = 0;
    }

    public void reset(){
        turn = 0;
        winner = "";
        winningPos.clear();
    }

    public boolean isWon(){
        for (int mask : winBoards) {
            if (((p1.getBoard() & mask) == mask) || ((p2.getBoard() & mask) == mask)){
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
        return (p1.getBoard() | p2.getBoard());
    }

    public boolean isOpen(int pos){
        return ((this.getBoard() & (1 << pos)) == 0);
    }

    public Player getPlayer(int pos){
        if (p1.isSet(pos)) return p1;
        else if ((p2.getBoard() & (1 << pos)) != 0) return p2;
        return null;
    }

    public void move(int pos){
        if (!isOpen(pos)) return;
        if (turn == 0) p1.setPos(pos);
        if (turn == 1) p2.setPos(pos);
        turn ^= 1; // Swap turns
    }

    
    @Override
    public String toString(){ 
        StringBuilder str = new StringBuilder();
        char c = 0;
        for(int i = 0; i < 9; i++){ 
            Player p = this.getPlayer(i);
            c = p != null ? p.getPlayerChar() : '_';
            str.append(c);
        }
        return str.toString();
    }
    

}