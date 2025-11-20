package games;

import java.awt.event.MouseEvent;

import model.Model;
import utils.Utils;
import view.View;

import javax.swing.*;


public class DefaultGame extends Game {

    private final boolean ai;
    private int move = -1;
    private Timer timer;
//    private boolean aiScheduledMove = false;

    public DefaultGame(boolean ai){
        this.ai = ai;
    }


    public void send(MouseEvent e){
        if (move != -1) return;
        move = Integer.parseInt(e.getComponent().getName());
    }



    public void resetMove(){
        move = -1;
    }

    public void move(Model model, View view, int move, Timer timer, boolean replay){
        model.move(move);
        view.render(model.getGameState(), model.getPlayers());
        if(model.isTerminal()) {
            timer.stop();
            processEndstate(model, view);
        }

    }


    public void run(Model model, View view, boolean replay){
        timer = new Timer(100, e -> {
            if(model.isTerminal()) return;

            if (model.getCurrentPlayerInfo().isAI()){
//                if (aiScheduledMove) return;
//
//                aiScheduledMove = true;
//
//                timer.stop(); // EXPLAIN: Ok without this the function will continue to call itself every 20s even while sleeping, so I stop the call until it's done sleeping
//
//                Utils.sleep(500,() -> { //TODO: Calls render 50 times because its happening ever 20 millis and this waits 1000 :((( HOW DO I FIX THIS
//                        aiScheduledMove = false;
//                });

                int bestPos = model.getBestMove();
                move(model, view, bestPos, timer, replay);
                if(model.isTerminal() && replay){timer.restart(); return;}

            } else if (!model.getCurrentPlayerInfo().isAI()){
                if (move == -1) return;
                if (!model.isOpen(move)) {
                    resetMove();
                    return;
                }
                move(model, view, move, timer, replay);
                resetMove();
                if(model.isTerminal() && replay){timer.restart(); return;}
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