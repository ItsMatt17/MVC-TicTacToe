package games;

import java.awt.event.MouseEvent;
import model.Model;
import view.View;



public class NoAI implements Game { 


    public NoAI(){}
    
    @Override
    public void execute(MouseEvent e, Model model, View view){ 
        int pos = Integer.parseInt(e.getComponent().getName());
        model.applyMove(pos);    
        view.render(model);
    }
    

    
}