package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.View;
import games.Game;
import games.NoAI;
import model.Model;

import javax.swing.*;

public class Controller extends MouseAdapter { 

    private final view.View view;
    private final Model model;
    private final Game gameType;

    public Controller(View view, Model model){ 
        this.view = view;
        this.model = model;
        this.gameType = new NoAI();
    }


    @Override
    public void mouseClicked(MouseEvent e){
        System.out.println(model.isWon());
        if(model.isWon() || model.isFull()) return;

        int pos = Integer.parseInt(e.getComponent().getName());
        model.move(pos);
        view.render(model.toString());

        if (model.isWon()) {
            view.gameWon(model.getWinningPos());
            Timer t = new Timer(3500, e1 -> {
                model.reset();
                view.reset();
            });
            t.setRepeats(false);
            t.start();
            return;
        }

        if (model.isFull()) {
            Timer t = new Timer(3500, e1 -> {
                model.reset();
                view.reset();
            });
            t.setRepeats(false);
            t.start();
            return;
        }





    }

}