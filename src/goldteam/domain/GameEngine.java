package goldteam.domain;

import java.awt.Dimension;
import java.awt.event.ActionListener;

public interface GameEngine {

    public boolean isGameRunning();

    public Dimension getMapDimensions();

    public Dimension getVisibleDimensions();

    public Dimension getRunEdgeDimensions();

    public int[] getHeldKeys();

    public int[] getHeldMouse();

    public void addKeysListener(ActionListener listener);
    public void removeKeysListener(ActionListener listener);
    
    public void addClicksListener(ActionListener listener);
    public void removeClicksListener(ActionListener listener);
    
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
    

}
