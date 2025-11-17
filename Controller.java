
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller extends MouseAdapter { 

    private final View view; 
    private final Model model;
    private final Gametype gt; 

    public Controller(View view, Model model){ 
        this.view = view;
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e){ 
        // System.out.println(e.getComponent().getName()); // Spot is already taken | make view shake?        
        int pos = Integer.parseInt(e.getComponent().getName());
        model.applyMove(pos);    
        view.render(model);
    }

}