/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.providers.GameObjectProvider;

/**
 *
 * @author fchishti
 */
public class GameLevelTestPanel extends GamePanelBase {

    /**
     * Create a new panel manager
     * @param panelManager
     */
    public GameLevelTestPanel(PanelManager panelManager) {
        super(panelManager, new GameData());

    }

    /**
     * Create a game object
     * Create an animator for the game object
     * Add the animator to the this.layeredPane
     **/
    @Override
    protected void addGameObjects() {

        GameObjectProvider provider = new GameObjectProvider();
        
        
        //layeredPane.add(provider.build(builder));
        
    }
    
    /**
     * Add events
     * Base class implements AncestorListener, KeyListener, MouseListener
     * So you can override any key or mouse event for test purposes
     * By default Escape takes you back to the game options panel
     **/
    
    
}
