package games;

import java.awt.event.MouseEvent;
import model.Model;
import view.View;

public interface Game {
    public void execute(MouseEvent e, Model model, View view);

}