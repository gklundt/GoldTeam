/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.domain.DoubleVector;
import goldteam.domain.GameObjectBuilderBase;
import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.providers.ArcherBuilder;
import goldteam.providers.ArrowBuilder;
import goldteam.providers.FlyerEnemyBuilder;
import goldteam.providers.GameObjectProvider;
import goldteam.providers.LauncherEnemyBuilder;
import goldteam.providers.WalkerEnemyBuilder;
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

        builder = new ArcherBuilder(this.gameData, new Point(400, 400));
        this.addGameObject(provider.build(builder));

        builder = new FlyerEnemyBuilder(gameData, new Point(400, 100));
        this.addGameObject(provider.build(builder));

        builder = new WalkerEnemyBuilder(gameData, new Point(200, 400));
        this.addGameObject(provider.build(builder));

        builder = new LauncherEnemyBuilder(gameData, new Point(750, 350), true);
        this.addGameObject(provider.build(builder));
        
        builder = new ArrowBuilder(gameData, new Point(0,0), new DoubleVector(10d,1d));
        this.addGameObject(provider.build(builder));
    }
}
