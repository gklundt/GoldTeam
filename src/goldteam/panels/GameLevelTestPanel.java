/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.domain.DoubleVector;
import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.providers.ArcherBuilder;
import goldteam.providers.ArrowBuilder;
import goldteam.providers.FlyerEnemyBuilder;
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

        builder = new ArcherBuilder(this.gameData);
        this.addGameObject(provider.build(builder, new Point(400, 400)));

        builder = new FlyerEnemyBuilder(gameData);
        this.addGameObject(provider.build(builder, new Point(400, 100)));

        builder = new WalkerEnemyBuilder(gameData);
        this.addGameObject(provider.build(builder, new Point(200, 400)));

        builder = new LauncherEnemyBuilder(gameData, true);
        this.addGameObject(provider.build(builder, new Point(750, 350)));
        
        builder = new ArrowBuilder(gameData, new DoubleVector(10d,1d));
        this.addGameObject(provider.build(builder, new Point(0,0)));
    }
}
