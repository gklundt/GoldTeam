/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.animators.GhostAnimation;
import goldteam.animators.HeartHudAnimation;
import goldteam.animators.ShieldHudAnimation;
import goldteam.characters.Ghost;
import goldteam.domain.Delta;
import goldteam.domain.GamePanelBase;
import goldteam.domain.ModType;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.hud.HeartHudItem;
import goldteam.hud.ShieldHudItem;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author Rajiv
 */
public class TestSoundsPanel extends GamePanelBase {
    
    private Ghost ghostFace;
    private HeartHudItem health;
    private ShieldHudItem strength;
    
    public TestSoundsPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    /**
     * Create a game object
     * Create an animator for the game object
     * Add the animator to the this.layeredPane
     **/
    @Override
    protected void addGameObjects() {
        ghostFace = new Ghost(gameData, new Point(60, 60));
        GhostAnimation rga = new GhostAnimation(ghostFace, gameData.getVisibleDimensions(), "assets/GameGhostStripeRed.png");
        ghostFace.setAnimator(rga);
        ghostFace.setVelocityScalarDelta(Delta.create(0.0d, ModType.FIXED));

        health = new HeartHudItem(gameData, new Point(10, 10));
        health.setWatcher(ghostFace);
        HeartHudAnimation ha = new HeartHudAnimation(health, gameData.getVisibleDimensions(), "assets/heart.png");
        health.setAnimator(ha);

        strength = new ShieldHudItem(gameData, new Point(10, 30));
        strength.setWatcher(ghostFace);
        ShieldHudAnimation sa = new ShieldHudAnimation(strength, gameData.getVisibleDimensions(), "assets/shield.png");
        strength.setAnimator(sa);

        this.layeredPane.add(rga, this.layeredPane.highestLayer() + 1);
        this.layeredPane.add(ha, this.layeredPane.highestLayer() + 1);
        this.layeredPane.add(sa, this.layeredPane.highestLayer() + 1);      
    }
    
    /**
     * Add events
     * Base class implements AncestorListener, KeyListener, MouseListener
     * So you can override any key or mouse event for test purposes
     * By default Escape takes you back to the game options panel
     * @param e
     **/
     @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ESCAPE:
                undoGraphics();
                panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
                break;
            case KeyEvent.VK_1:
                ghostFace.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
                break;
            case KeyEvent.VK_2:
                ghostFace.setShieldDelta(Delta.create(-1.0, ModType.FIXED));
                break;
            default:
                break;
        }
    }
}
