package games;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import model.Model;
import utils.Utils;
import view.View;

import javax.swing.*;


public class NoAI extends Game {


    public NoAI(){}

    
    @Override
    public void execute(MouseEvent e, Model model, View view){
        if(model.isWon() || model.isFull()) return;

        int pos = Integer.parseInt(e.getComponent().getName());
        model.move(pos);
        view.render(model.toString());

        renderEndstate(model, view);

    }


    

    
}