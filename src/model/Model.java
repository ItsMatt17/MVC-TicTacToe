package model;


import utils.Utils;

import java.util.ArrayList;

public class Model {
    PlayerInfo[] players = new PlayerInfo[2]; // Easier to deal with turn based moves using this.
    private Repository repository = Repository.getInstance();
    private GameState state = new GameState(0, 0, 0);

    public Model(){
      players[0] = new PlayerInfo('X', true);
      players[1] = new PlayerInfo('O', false);

    }

    public int[] getWinningIndexes(){
        if(!isWon()) return null;
        return state.getWinningIndexes();
    }

    public void save(){repository.save();}
    public void update() {repository.update(this);}

    public PlayerInfo getCurrentPlayerInfo() {return players[getCurrentPlayerIndex()];}
    public int getCurrentPlayerIndex() {return state.getCurrentPlayer();}
    public PlayerInfo getWinner(){return players[state.getWinner()];} // TODO: Implement this in a safer way
    public PlayerInfo[] getPlayers(){ return players;}
    public GameState getGameState() {return state;}
    public boolean isTerminal() {return state.isTerminal();}
    public boolean isOpen(int pos){ return state.isOpen(pos);}
    public boolean isWon(){ return state.isWon();}
    public boolean isFull(){ return state.isFull();}
    public int getBestMove() { return state.getBestMove();}
    public boolean move(int pos){
        if(!isOpen(pos)) return false;
        if(isWon())  return false;

        state = state.move(pos);
        return true;
    }

    public void reset(){
        state = new GameState(0, 0, 0);
    }

}