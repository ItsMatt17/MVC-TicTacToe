package games;

import model.Model;
import utils.Utils;
import view.View;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class AI extends Game {

    public AI(){}



    @Override
    public void execute(MouseEvent e, Model model, View view) {
        if (model.isWon() || model.isFull()) return;

        int pos = Integer.parseInt(e.getComponent().getName());
        if (!model.isOpen(pos)) return;

        if (pos != model.getBestMove()) System.out.println("DUMBASS");

        model.move(pos);
        view.render(model.toString());
        if (renderEndstate(model, view)) return;


        if(!model.isWon() && !model.isFull()){
            Utils.sleep(250, () -> {
                model.move(model.getBestMove());
                view.render(model.toString());

                renderEndstate(model, view);
            });
        }


    }
}
