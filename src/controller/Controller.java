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

    public void run(){

    }


    @Override
    public void mouseReleased(MouseEvent e) {
        gameType.execute(e, model, view);
    }

}