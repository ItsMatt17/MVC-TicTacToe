package games;

import model.Model;
import view.View;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class AI implements Game {

    public AI(){}

    @Override
    public void execute(MouseEvent e, Model model, View view) {
        if (model.isWon() || model.isFull()) return;

        int pos = Integer.parseInt(e.getComponent().getName());
        if (!model.isOpen(pos)) return;


        model.move(pos);
        view.render(model.toString());
        System.out.println(pos);


        if(!model.isWon() && !model.isFull()){
            model.move(model.getBestMove());
            view.render(model.toString());
        }

        if (model.isWon()) {
            model.setWinningPos();
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
            view.gameDraw(new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)));
            Timer t = new Timer(3500, e1 -> {
                model.reset();
                view.reset();
            });
            t.setRepeats(false);
            t.start();
            return;
        }

        // ===== AI MOVE =====


    }
}
