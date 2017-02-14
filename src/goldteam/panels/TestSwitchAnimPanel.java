/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.animators.BigGhostAnimation;
import goldteam.animators.GhostAnimation;
import goldteam.characters.MoodyGhost;
import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author fchishti
 */
public class TestSwitchAnimPanel extends GamePanelBase {

    
    private MoodyGhost ghost;
    
    /**
     * Create a new panel manager
     *
     * @param panelManager
     */
    public TestSwitchAnimPanel(PanelManager panelManager) {
        super(panelManager, new GameData());

    }

    /**
     * Create a game object Create an animator for the game object Add the
     * animator to the this.layeredPane
     *
     */
    @Override
    protected void addGameObjects() {
        
        ghost = new MoodyGhost(this.gameData, new Point(60, 60));

        AnimationBase defaultGhostAnimation = new GhostAnimation(ghost, this.gameData.getVisibleDimensions(), "assets/GameGhostStripeRed.png");
        AnimationBase slideGhostAnimation = new GhostAnimation(ghost, this.gameData.getVisibleDimensions(), "assets/GameGhostStripeOrange.png");
        AnimationBase runGhostAnimation = new BigGhostAnimation(ghost, this.gameData.getVisibleDimensions(), "assets/GameGhostStripeGreen.png");

        ghost.setAnimator(defaultGhostAnimation);
        ghost.addAnimator(AnimationState.WALKING_LEFT, slideGhostAnimation);
        ghost.addAnimator(AnimationState.JUMPING_LEFT, runGhostAnimation);

        AnimationBase t = ghost.getAnimator();
        this.layeredPane.add(t);
        ghost.addAnimationChangeListener(l -> SwitchGhostListener(l));

    }

    private void SwitchGhostListener(ActionEvent event) {
        Animatable obj = (Animatable) event.getSource();
        this.layeredPane.remove(obj.getRemoveAnimator());
        this.layeredPane.add(obj.getAnimator());
    }

    /**
     * Add events Base class implements AncestorListener, KeyListener,
     * MouseListener So you can override any key or mouse event for test
     * purposes By default Escape takes you back to the game options panel
     *
     */

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

        ghost.processKeyInput(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int k = e.getKeyCode();
        if (check(k)) {
            removeKey(k);
        }
        ghost.processKeyInput(e);
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
