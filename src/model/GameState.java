package model;

import utils.Utils;

public class GameState {

    private static final int[] winBoards = {0b000000111, 0b000111000, 0b111000000, 0b100100100, 0b010010010, 0b001001001, 0b100010001, 0b001010100};


    private final int p1;
    private final int p2;
    private final int turn;
    private final int[] players = new int[2];

    public GameState(int p1, int p2, int turn){
        this.p1 = p1;
        this.p2 = p2;
        this.turn = turn;
        players[0] = p1;
        players[1] = p2;
    }

    public GameState move(int pos) {
        if (!isOpen(pos)) return null;

        int newBoard = setBit(players[turn], pos);
        if (turn == 0) return new GameState(newBoard, p2, 1);
        if (turn == 1) return new GameState(p1, newBoard, 0);
        System.out.println("Something went wrong in GameState.move()");
        return null;
    }

    public int getWinner(){
        assert(isWon());
        if (isBoardWin(p1)) return 0;
        if (isBoardWin(p2)) return 1;
        return -1;
    }

    private int setBit(int board, int pos){
        return Utils.setPos(board, pos);
    }

    public boolean isTerminal(){
        return (isFull() || isWon());
    }

    public boolean isOpen(int pos){
        return (!Utils.isSet(getBoard(), pos));
    }

    public boolean isFull(){ return (getBoard() == 0b111111111); }


    private int getBoard(){
        return (p1 | p2);
    }

    public boolean isWon() {
        for(int mask : winBoards) {
            if ((p1 & mask) == mask) return true;
            if ((p2 & mask) == mask) return true;
        }
        return false;
    }


    public int[] getWinningIndexes(){
        assert(isWon());
        assert(getWinMask() != 0);

        int mask = getWinMask();
        int[] arr = new int[3];

        int count = 0;
        for(int i = 0; i < 9; i++) {
            if(!Utils.isSet(mask, i)) continue;
            arr[count] = i;
            count++;
        }
        return arr;
    }

    public int getWinMask(){
        assert (isWon());

        for(int mask : winBoards) {
            if(Utils.testMask(players[0], mask, mask)) return mask;
            if(Utils.testMask(players[1], mask, mask)) return mask;
        }
        return 0;
    }

    public int getPlayer(int pos){
        if(isOpen(pos)) return -1;
        for (int i = 0; i < players.length; i++) {
            if(Utils.isSet(players[i], pos)) return i;
        }
        return -1;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            if(isOpen(i)) {sb.append("*"); continue;}
            sb.append(getPlayer(i));
        }
        return sb.toString();
    }



    // ========== MINI-MAX HELPER FUNCTIONS ==========

    public static boolean isBoardWin(int board){
        for (int mask : winBoards) {
            if ((board & mask) == mask ) return true;
        }
        return false;
    }
    private static boolean isFull(int board) {return board == 0b111111111;}

    public static boolean isOpen(int board, int pos){
        return (!Utils.isSet(board, pos));
    }
    private static int getBoard(int p1, int p2){ return (p1 | p2);}

    private static int negamax(int currPlayer, int oppPlayer, int depth, int maxDepth) {
        if(isBoardWin(currPlayer)) return 10 - depth;
        if(isBoardWin(oppPlayer)) return -10 + depth;
        if(depth == maxDepth || isFull(currPlayer | oppPlayer)) return 0;

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < 9; i++) {
            if(!isOpen(currPlayer | oppPlayer, i)) continue;

            int move = Utils.setPos(currPlayer, i);
            int score = -negamax(oppPlayer, move, depth + 1, maxDepth);

            max = Math.max(score, max);
        }
        return max;
    }

    // NOTE: 0 is MY TURN | 1 IS BOT's TURN
    public int getBestMove(){
        int bestScore = Integer.MIN_VALUE;
        int bestIndex = -1;
        int curr = players[turn];
        int nextPlayer = players[turn ^ 1];
        for (int i = 0; i < 9; i++) {
            if(!isOpen(getBoard(curr, nextPlayer), i)) continue;

            int move = Utils.setPos(curr, i);
            int score = -negamax(nextPlayer, move, 0, 9);

            if (score >  bestScore) {
                bestScore = score;
                bestIndex = i;
            }
        }
        return bestIndex;
    }

}
