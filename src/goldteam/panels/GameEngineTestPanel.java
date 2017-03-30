package goldteam.panels;

import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.gamedata.GameData;
import goldteam.builders.FlyerEnemyBuilder;
import goldteam.builders.LauncherEnemyBuilder;
import goldteam.builders.WalkerEnemyBuilder;
import java.awt.Point;
import java.util.Random;

public class GameEngineTestPanel extends GamePanelBase {

    public GameEngineTestPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {
        super.addGameObjects();

        Random x = new Random();
        Random y = new Random();
        for (int i = 1; i <= 18; ++i) {

            int rx = ((Double) (x.nextDouble() * 500.00)).intValue() % 500;
            int ry = ((Double) (y.nextDouble() * 500.00)).intValue() % 500;

            FlyerEnemyBuilder flyerEnemyBuilder = new FlyerEnemyBuilder(this.gameData);
            LauncherEnemyBuilder launcherEnemyBuilder = new LauncherEnemyBuilder(this.gameData, true);
            WalkerEnemyBuilder walkerEnemyBuilder = new WalkerEnemyBuilder(this.gameData);

            switch (i % 3) {
                case 0:
                    this.addGameObject(gameObjectProvider.build(flyerEnemyBuilder, new Point(rx, ry)));
                    break;
                case 1:
                    this.addGameObject(gameObjectProvider.build(launcherEnemyBuilder, new Point(rx, ry)));
                    break;
                case 2:
                    this.addGameObject(gameObjectProvider.build(walkerEnemyBuilder, new Point(rx, ry)));
            }
        }
    }
}
