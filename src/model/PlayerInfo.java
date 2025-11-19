package model;

public class PlayerInfo {

    private char mark;
    private boolean isAI;

    public PlayerInfo(char mark, boolean isAI){
        this.mark = mark;
        this.isAI = isAI;
    }
    public char getMark() { return mark;}
    public boolean isAI() { return isAI; }

}
