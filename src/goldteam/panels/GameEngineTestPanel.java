package goldteam.panels;

import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.DoubleVector;
import goldteam.gamedata.GameData;
import goldteam.providers.ArcherBuilder;
import goldteam.providers.ArrowBuilder;
import goldteam.providers.FlyerEnemyBuilder;
import goldteam.providers.LauncherEnemyBuilder;
import goldteam.providers.WalkerEnemyBuilder;
import java.awt.Point;
import java.util.Random;

public class GameEngineTestPanel extends GamePanelBase {

    public GameEngineTestPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {

        builder = new ArcherBuilder(this.gameData);
        this.addGameObject(provider.build(builder, new Point(400, 400)));

        Random x = new Random();
        Random y = new Random();
        for (int i = 1; i <= 18; ++i) {

            int rx = ((Double) (x.nextDouble() * 500.00)).intValue() % 500;
            int ry = ((Double) (y.nextDouble() * 500.00)).intValue() % 500;

            FlyerEnemyBuilder flyerEnemyBuilder = new FlyerEnemyBuilder(this.gameData);
            LauncherEnemyBuilder launcherEnemyBuilder = new LauncherEnemyBuilder(this.gameData, true);
            WalkerEnemyBuilder walkerEnemyBuilder = new WalkerEnemyBuilder(this.gameData);
            ArrowBuilder arrowBuilder = new ArrowBuilder(this.gameData, new DoubleVector(15d, 0d));

            switch (i % 4) {
                case 0:
                    this.addGameObject(provider.build(flyerEnemyBuilder, new Point(rx, ry)));
                    break;
                case 1:
                    this.addGameObject(provider.build(launcherEnemyBuilder, new Point(rx, ry)));
                    break;
                case 2:
                    this.addGameObject(provider.build(walkerEnemyBuilder, new Point(rx, ry)));
                    break;
                case 3:
                    this.addGameObject(provider.build(arrowBuilder, new Point(rx, ry)));
                    break;
            }
        }
    }
}
