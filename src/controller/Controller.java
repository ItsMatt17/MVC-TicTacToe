package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.View;
import games.Game;
import model.Model;

public class Controller extends MouseAdapter { 

    private final view.View view;
    private final Model model;
    private final Game gameType;

    public Controller(View view, Model model, Game gameType){
        this.view = view;
        this.model = model;
        this.gameType = gameType;
    }

    public void run(boolean replay){
        gameType.run(model, view, true);
    }


    @Override
    public void mouseReleased(MouseEvent e) { //TODO: THIS SHOULD NOT BE COUPLED WITH Game type UGH
        gameType.send(e);
    }

}