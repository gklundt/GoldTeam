/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.animators.GameStageAnimation;
import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.hud.GameStageItem;
import java.awt.Point;

/**
 *
 * @author fchishti
 */
public class GameOverStageTestPanel extends GamePanelBase {

    /**
     * Create a new panel manager
     *
     * @param panelManager
     */
    public GameOverStageTestPanel(PanelManager panelManager) {
        super(panelManager, new GameData());

    }

    /**
     * Create a game object Create an animator for the game object Add the
     * animator to the this.layeredPane
     *
     */
    @Override
    protected void addGameObjects() {
        GameStageItem gsi = new GameStageItem(this.gameData, new Point());
        GameStageAnimation gsa = new GameStageAnimation(gsi, this.gameData.getVisibleDimensions(), "assets/gameover.png");
        this.layeredPane.add(gsa);
    }

    /**
     * Add events Base class implements AncestorListener, KeyListener,
     * MouseListener So you can override any key or mouse event for test
     * purposes By default Escape takes you back to the game options panel
     *
     */
}
