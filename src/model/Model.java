package model;


public class Model { 
    

    private final int[] winBoards = {0b000000111, 0b000111000, 0b111000000, 0b100100100, 0b010010010, 0b001001001, 0b100010001, 0b001010100};
    int p1;
    int p2;
    int turn; // 0 = p1's turn vs 1 == p2's

    public Model(){
        this.p1 = 0;
        this.p2 = 0;
        this.turn = 0;
        
    }

    private int isWon(){ 
        for (int mask : winBoards) {
            if ((p1 & mask) == mask) return 0; 
            if ((p2 & mask) == mask) return 1; 
        }
        return -1;
    }

    private boolean isFull(){ 
        return (getBoard() == 0b111111111);

    }

    private int getBoard(){ 
        return (this.p1 | this.p2);
    }

    private boolean isOpen(int pos){ 
        return ((this.getBoard() & (1 << pos)) == 0);
    }

    private void swapTurn(){ 
        if (this.turn == 0) this.turn = 1;
        else this.turn = 0;
    }

    private int getPlayer(int pos){ 
        if ((this.p1 & (1 << pos)) != 0) return 0;
        else if ((this.p2 & (1 << pos)) != 0) return 1;
        
        return -1;
    }

    public boolean applyMove(int pos){ 
        if (!isOpen(pos)) return false;

        if (turn == 0) p1 |= 1 << pos;
        if (turn == 1) p2 |= 1 << pos;    
    
        this.swapTurn();
        return true;
    }
    
    @Override
    public String toString(){ 
        String str = "";
        char c = 0;
        for(int i = 0; i < 9; i++){ 
            int p = this.getPlayer(i);


            if (p == -1) c = '_';
            else if (p == 0) c = 'X'; 
            else if (p == 1) c = 'O';

            str += c;
        }
        return str;

    }
    

}