package games;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import model.Model;
import utils.Utils;
import view.View;

public abstract class Game {

    public abstract void execute(MouseEvent e, Model model, View view);
    public abstract void send(MouseEvent e);
    public abstract void run(Model model, View view, boolean replay);

    public void processEndstate(Model model, View view) {
        assert(model.isTerminal());
        model.update();
        model.save();
        if (model.isWon()) {renderWin(model, view); return;}
        if (model.isFull()) {renderDraw(model, view); return;}

    }

    private void renderWin(Model model, View view){
        view.gameWon(model.getWinningIndexes());

        Utils.sleep(2500, () -> {
            view.reset();
            model.reset();
        });
    }

    private void renderDraw(Model model, View view){
        view.gameDraw(new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)));
        Utils.sleep(2500, () -> {
            view.reset();
            model.reset();
        });
    }


}