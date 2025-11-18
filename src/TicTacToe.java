
import controller.Controller;
import games.AI;
import games.Game;
import games.NoAI;
import model.Model;
import view.View;

import java.util.HashMap;
import java.util.Map;


public class TicTacToe { 

    static Map<Integer, Game> games = Map.of(
            0, new AI(),
            1, new NoAI()
    );

    public static void main(String[] args){ 
        int game = Integer.parseInt(args[0]);

        Model m = new Model();
        View v = new View();
        Controller c = new Controller(v, m, games.getOrDefault(game, new NoAI()));
        v.setListener(c);
    

    }   








}