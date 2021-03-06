/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.builders.FlyerEnemyBuilder;
import goldteam.builders.LauncherEnemyBuilder;
import goldteam.builders.WalkerEnemyBuilder;
import java.awt.Point;

/**
 *
 * @author fchishti
 */
public class GameLevelTestPanel extends GamePanelBase {


    /**
     * Create a new panel manager
     *
     * @param panelManager
     */
    public GameLevelTestPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    /**
     * Create a game object Create an animator for the game object Add the
     * animator to the this.layeredPane
     *
     */
    @Override
    protected void addGameObjects() {

        super.addGameObjects();

        gameObjectBuilder = new FlyerEnemyBuilder(gameData, this);
        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(400, 100)));

        gameObjectBuilder = new WalkerEnemyBuilder(gameData);
        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(200, 400)));

        gameObjectBuilder = new LauncherEnemyBuilder(gameData);
        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(750, 350)));
    }
}
