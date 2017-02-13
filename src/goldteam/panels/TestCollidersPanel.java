/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.animators.GhostAnimation;
import goldteam.characters.StationaryGhost;
import goldteam.domain.Delta;
import goldteam.domain.GamePanelBase;
import goldteam.domain.ModType;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;

/**
 *
 * @author fchishti
 */
public class TestCollidersPanel extends GamePanelBase {

    private StationaryGhost g1;

    public TestCollidersPanel(PanelManager panelManager) {
        super(panelManager, new GameData());

    }

    @Override
    protected void addGameObjects() {
        g1 = new StationaryGhost(gameData, new Point(200, 400));
        GhostAnimation ga1 = new GhostAnimation(g1, gameData.getVisibleDimensions(), "assets/GameGhostStripe.png");
        g1.setAnimator(ga1);
        this.layeredPane.add(ga1, layeredPane.highestLayer());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            panelThread.interrupt();
            undoGraphics();
            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
            return;
        }

        int k = e.getKeyCode();
        if (!check(k)) {
            addKey(k);
        }

        g1.processKeyInput(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int k = e.getKeyCode();
        if (check(k)) {
            removeKey(k);
        }
        g1.processKeyInput(e);
    }

    private synchronized boolean check(Integer e) {
        return this.gameData.getHeldKeys().contains(e);
    }

    private synchronized void addKey(Integer e) {
        this.gameData.getHeldKeys().add(e);
    }

    private synchronized void removeKey(Integer e) {
        this.gameData.getHeldKeys().remove(e);
    }

}
