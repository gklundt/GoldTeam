package goldteam.domain;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public interface GameEngine {

    public boolean isGameRunning();
    public Dimension getMapDimensions();
    public Dimension getVisibleDimensions();

    public ArrayList<Integer> getHeldKeys();
    public HashMap<Integer, Point> getHeldMouse();
    
    public void addMapUpdateTimerListener(ActionListener listener);
    public void removeMapUpdateTimerListener(ActionListener listener);
    public void addGraphicsUpdateTimerListener(ActionListener listener);
    public void removeGraphicsUpdateTimerListener(ActionListener listener);
    public void addAnimationUpdateTimerListener(ActionListener listener);
    public void removeAnimationUpdateTimerListener(ActionListener listener);
    public void addEffectsTimerListener(ActionListener listener);
    public void removeEffectsTimerListener(ActionListener listener);
    public void addCollisionTimer(ActionListener listener);
    public void removeCollisionTimer(ActionListener listener);

    public Attackable getAttackableCharacter();
    public void setAttackableCharacter(Attackable target);
    public Depletable getDepletableCharacter();
    public void setDepletableCharacter(Depletable target);
    public GameObject getMovableCharacter();
    public void setMovableCharacter(GameObject target);

    public Point getMapLocation();
    

}
