package goldteam.gamedata;

import goldteam.domain.Depletable;
import goldteam.domain.DepletableWatcher;
import goldteam.domain.GameEngine;
import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Action;
import javax.swing.Timer;

/**
 *
 * @author gordon
 */
public class GameData implements GameEngine,
        DepletableWatcher {

    private final Integer FRAME_RATE;
    private Boolean isGameRunning;
    private Dimension mapDimensions;
    private final Dimension visibleDimensions;
    private Dimension runEdgeDimensions;
    private int[] heldKeys;
    private int[] heldMouse;
    private Depletable mainCharacter;

    private final Timer mapUpdateTimer;
    private final Timer graphicsUpdateTimer;
    private final Timer effectsUpdateTimer;

    public GameData() {
        this.FRAME_RATE = 24;
        this.effectsUpdateTimer = new Timer(1000 / FRAME_RATE, null);
        this.graphicsUpdateTimer = new Timer(1000 / FRAME_RATE, null);
        this.mapUpdateTimer = new Timer(1000 / FRAME_RATE, null);
        this.visibleDimensions = new Dimension(800, 600);
        graphicsUpdateTimer.start();
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
    public int[] getHeldKeys() {
        return this.heldKeys;
    }

    @Override
    public int[] getHeldMouse() {
        return this.heldMouse;
    }

    @Override
    public void addKeysListener(ActionListener listener) {

    }

    @Override
    public void addClicksListener(ActionListener listener) {

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

}
