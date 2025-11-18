package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import games.AI;
import view.View;
import games.Game;
import model.Model;

import javax.swing.*;

public class Controller extends MouseAdapter { 

    private final view.View view;
    private final Model model;
    private final Game gameType;

    public Controller(View view, Model model, Game gameType){
        this.view = view;
        this.model = model;
        this.gameType = gameType;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        gameType.execute(e, model, view);
    }

}