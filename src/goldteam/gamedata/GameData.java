package goldteam.gamedata;

import goldteam.domain.Depletable;
import goldteam.domain.DepletableWatcher;
import goldteam.domain.GameEngine;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author gordon
 */
public class GameData implements GameEngine,
        DepletableWatcher {
    
    private Boolean isGameRunning;
    private Dimension mapDimensions;
    private final Dimension visibleDimensions;
    private Dimension runEdgeDimensions;
    private final ArrayList<Integer> heldKeys;
    private final ArrayList<Integer> heldMouse;
    private Depletable mainCharacter;
    
    private final Timer mapUpdateTimer;
    private final Timer graphicsUpdateTimer;
    private final Timer animationGraphicsUpdateTimer;
    
    private final Timer effectsUpdateTimer;
    private final Timer collisionTimer;
    
    public GameData() {
        
        this.effectsUpdateTimer = new Timer(1000 / 24, null);
        this.graphicsUpdateTimer = new Timer(1000 / 24, null);
        this.animationGraphicsUpdateTimer = new Timer(1000 / 10, null);
        this.mapUpdateTimer = new Timer(1000 / 24, null);
        this.collisionTimer = new Timer(1000 / 48, null);
        
        this.heldKeys = new ArrayList<>();
        this.heldMouse = new ArrayList<>();
        
        this.visibleDimensions = new Dimension(800, 600);
        this.runEdgeDimensions = new Dimension(600, 400);
        this.mapDimensions = new Dimension(2400, 1800);
        graphicsUpdateTimer.start();
        collisionTimer.start();
        animationGraphicsUpdateTimer.start();
    }
    
    @Override
    public boolean isGameRunning() {
        return this.isGameRunning;
    }
    
    @Override
    public Dimension getMapDimensions() {
        return this.mapDimensions;
    }
    
    @Override
    public Dimension getVisibleDimensions() {
        return this.visibleDimensions;
    }
    
    @Override
    public Dimension getRunEdgeDimensions() {
        return this.runEdgeDimensions;
    }
    
    @Override
    public ArrayList<Integer> getHeldKeys() {
        return this.heldKeys;
    }
    
    @Override
    public ArrayList<Integer> getHeldMouse() {
        return this.heldMouse;
    }
    
    @Override
    public void addKeysListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    
    @Override
    public void addClicksListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    
    @Override
    public void addMapUpdateTimerListener(ActionListener listener) {
        this.mapUpdateTimer.addActionListener(listener);
    }
    
    @Override
    public void addGraphicsUpdateTimerListener(ActionListener listener) {
        this.graphicsUpdateTimer.addActionListener(listener);
    }
    
    @Override
    public void addEffectsTimerListener(ActionListener listener) {
        this.effectsUpdateTimer.addActionListener(listener);
    }
    
    @Override
    public Depletable getWatcher() {
        return this.mainCharacter;
    }
    
    @Override
    public void setWatcher(Depletable target) {
        this.mainCharacter = target;
        this.mainCharacter.addDepletableListener(l -> LifeWatcher());
    }
    
    private void LifeWatcher() {
        if (this.mainCharacter.getCount() < 0) {
            this.isGameRunning = false;
        }
    }
    
    @Override
    public void addCollisionTimer(ActionListener listener) {
        this.collisionTimer.addActionListener(listener);
    }
    
    @Override
    public void removeKeysListener(ActionListener listener) {
        //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void removeClicksListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void removeMapUpdateTimerListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void removeGraphicsUpdateTimerListener(ActionListener listener) {
        this.graphicsUpdateTimer.removeActionListener(listener);
    }
    
    @Override
    public void addAnimationUpdateTimerListener(ActionListener listener) {
        this.animationGraphicsUpdateTimer.addActionListener(listener);
    }
    
    @Override
    public void removeAnimationUpdateTimerListener(ActionListener listener) {
        this.animationGraphicsUpdateTimer.removeActionListener(listener);
    }
    
    @Override
    public void removeEffectsTimerListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void removeCollisionTimer(ActionListener listener) {
        this.collisionTimer.removeActionListener(listener);
    }
    
}
