package games;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import model.Model;
import utils.Utils;
import view.View;

public abstract class Game {

    public abstract void execute(MouseEvent e, Model model, View view);

    public boolean renderEndstate(Model model, View view) {
        if (!model.isWon() && !model.isFull()) return false;
        if (model.isWon()) {
            model.setWinningPos();
            view.gameWon(model.getWinningPos());

            Utils.sleep(2500, () -> {
                view.reset();
                model.reset();
            });
            System.out.println("hi");
            return true;
        }

        view.gameDraw(new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)));
        Utils.sleep(2500, () -> {
            view.reset();
            model.reset();
        });
        return true;
    }



}