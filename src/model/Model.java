package model;


import utils.Utils;

import java.util.ArrayList;

public class Model {

    private static final int[] winBoards = {0b000000111, 0b000111000, 0b111000000, 0b100100100, 0b010010010, 0b001001001, 0b100010001, 0b001010100};

    Player p1;
    Player p2;
    Player winner;
    ArrayList<Integer> winningPos =  new ArrayList<>();
    Player[] players = new Player[2]; // Easier to deal with turn based moves using this.
    int turn; // 0 = p1's turn vs 1 == p2's

    public Model(){
        this.p1 = new Player('X', false);
        this.p2 = new Player('O', true);
        this.turn = 0;

        players[0] = p1;
        players[1] = p2;
    }

    public void reset(){
        for (Player p : players){
            p.setBoard(0);
        }
        turn = 0;
        winner = null;
        winningPos.clear();
    }

    public void setTurn(int turn){ this.turn = turn; }

    public int minimax(int depth){
        Player curr = players[turn];
        if (curr.isWin()) return curr.isMaximizing() ? 10 - depth : depth - 10;
        if(isFull() || depth == 0) return 0;

        if (curr.isMaximizing()) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++){
                if (!isOpen(i)) continue;
                int oldBoard = curr.getBoard();
                int oldTurn = turn;

                move(i);
                int score = minimax(depth-1);
                max = Math.max(score, max);

                curr.setBoard(oldBoard);
                setTurn(oldTurn);
            }
            return max;
        } else {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++){
                if (!isOpen(i)) continue;
                int oldBoard = curr.getBoard();
                int oldTurn = turn;

                move(i);

                int score = minimax(depth-1);
                min = Math.min(score, min);

                curr.setBoard(oldBoard);
                setTurn(oldTurn);
            }
            return min;
        }
    }


    public int getBestMove(){
        Player p = players[turn];
        int bestScore = p.isMaximizing() ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestIdx = -1;
        for(int i = 0; i < 9; i++) {
            if (!isOpen(i)) continue;

            int oldBoard = p.getBoard();
            int oldTurn = turn;

            move(i);
            int val = minimax(9);

            p.setBoard(oldBoard);
            setTurn(oldTurn);

            if (p.isMaximizing() && val > bestScore) {
                bestScore = val;
                bestIdx = i;
            } else if (!p.isMaximizing() && val < bestScore) {
                bestScore = val;
                bestIdx = i;
            }
        }
        return bestIdx;

    }


    public boolean isWon(){
        return p1.isWin() || p2.isWin();
    }

    public Player getWinner(){
        if (winner != null) return winner;
        for (Player p : players){
            if (!p.isWin())  continue;

            winner = p;
            return p;
        }
        return null;
    }

    public void setWinningPos(){
        if (!isWon()) return;
        assert (winningPos.toArray().length == 0);

        Player p = getWinner();
        if (p == null) return;

        int m = 0;
        for (int mask : winBoards) {
            if (Utils.testMask(p.getBoard(), mask, mask)) m = mask;
        }

        for (int i = 0; i < 9; i++){
            if (Utils.isSet(m, i)) winningPos.add(i);
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
        return (!Utils.isSet(getBoard(), pos));
    }

    public Player getPlayer(int pos){
        for (Player p :  players) {
            if (p.isSet(pos)) return p;
        }
        return null;
    }

    public void move(int pos){
        if (!isOpen(pos)) return;
        players[turn].setPos(pos);
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