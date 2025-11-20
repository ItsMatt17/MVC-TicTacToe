package games;

import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import model.Model;
import view.View;


public class DefaultGame extends Game {

    private final boolean ai;
    public DefaultGame(boolean ai){
        this.ai = ai;
    }

    
    @Override
    public void execute(MouseEvent e, Model model, View view){
        System.out.println(
                "Is Won: " + model.isWon() + "\n" +
                "Is Full : " + model.isFull() + "\n");
        if(model.isWon() || model.isFull()) return;

        int pos = Integer.parseInt(e.getComponent().getName());
        if(!model.isOpen(pos)) return;

        model.move(pos);
        view.render(model.getGameState(), model.getPlayers());
        if(model.isTerminal()) processEndstate(model, view);

        if (!ai) return;

        int bestPos = model.getBestMove();
        model.move(bestPos);
        view.render(model.getGameState(), model.getPlayers());
        if(model.isTerminal()) processEndstate(model, view);

    }
    
}