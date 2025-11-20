
import controller.Controller;
import games.Game;
import games.DefaultGame;
import model.Model;
import view.View;

import java.util.Map;


public class TicTacToe { 

    public static void main(String[] args){

        Model m = new Model();
        View v = new View();
        Controller c = new Controller(v, m, new DefaultGame(true));
        v.setListener(c);
    

    }   








}