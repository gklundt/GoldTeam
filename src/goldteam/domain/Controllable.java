package goldteam.domain;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Controllable {

    public void processKeyInput(KeyEvent keyEvent);

    public void processMouseInput(MouseEvent mouseEvent);

}