package games;

import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import model.Model;
import view.View;

import javax.swing.*;


public class DefaultGame extends Game {

    private final boolean ai;
    private int move = -1;
    private Timer timer;
    public DefaultGame(boolean ai){
        this.ai = ai;
    }


    public void send(MouseEvent e){
        if (move != -1) return;
        move = Integer.parseInt(e.getComponent().getName());
    }



    public void processMove(){
        move = -1;
    }



    public void run(Model model, View view, boolean replay){
        timer = new Timer(20, e -> {
            if (model.getCurrentPlayerInfo().isAI()){
                int bestPos = model.getBestMove();
                model.move(bestPos);
                view.render(model.getGameState(), model.getPlayers());
                if(model.isTerminal()) {
                    processEndstate(model, view);

                    timer.restart();

                    return;
                }
            }

            if(move == -1) return;
            if(!model.isOpen(move)) { processMove(); return; }

            model.move(move);
            processMove();
            view.render(model.getGameState(), model.getPlayers());
            if(model.isTerminal()) {
                processEndstate(model, view);
                timer.restart();
            }

        });
        timer.setRepeats(true);
        timer.start();



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



    }
    
}