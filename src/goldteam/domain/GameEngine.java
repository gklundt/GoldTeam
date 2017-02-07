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

    public void addClicksListener(ActionListener listener);
    
    public void updateMap();

    public void addMapUpdateTimerListener(ActionListener listener);

    public void updateGraphics();

    public void addGraphicsUpdateTimerListener(ActionListener listener);

    public void updateEffects();

    public void addEffectsTimerListener(ActionListener listener);
    

}
