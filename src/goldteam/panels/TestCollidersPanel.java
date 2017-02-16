/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import goldteam.characters.StationaryGhost;
import goldteam.colliders.CollisionDetector;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author fchishti
 */
public class TestCollidersPanel extends GamePanelBase {

    private StationaryGhost g1;
    private Ghost g2;
    private ArrayList<GameObject> objects;

    //private Component gp;
    public TestCollidersPanel(PanelManager panelManager) {
        super(panelManager, new GameData());

    }

    @Override
    protected void addGameObjects() {

        g1 = new StationaryGhost(gameData, new Point(200, 400));
        g2 = new Ghost(gameData, new Point(200, 400));

        CharacterAnimationBase ga1 = new GhostAnimation(g1, gameData.getVisibleDimensions(), "assets/GameGhostStripe.png");
        CharacterAnimationBase ga2 = new GhostAnimation(g2, gameData.getVisibleDimensions(), "assets/GameGhostStripe.png");

        g1.setAnimator(ga1);
        g2.setAnimator(ga2);

        this.layeredPane.add(ga1, layeredPane.highestLayer());
        this.layeredPane.add(ga2, layeredPane.highestLayer());

        CollisionDetector collisionDetector;
        collisionDetector = new CollisionDetector(this.gameData);
        collisionDetector.registerCollidable(g1);
        collisionDetector.registerCollidable(g2);
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
