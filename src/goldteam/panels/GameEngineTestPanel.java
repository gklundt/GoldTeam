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

        builder = new ArcherBuilder(this.gameData, new Point(400,400));
        this.addGameObject(provider.build(builder));

        Random x = new Random();
        Random y = new Random();
        for (int i = 1; i <= 18; ++i) {

            int rx = ((Double) (x.nextDouble() * 500.00)).intValue() % 500;
            int ry = ((Double) (y.nextDouble() * 500.00)).intValue() % 500;

            CharacterAnimationBase ga1 = null;
            switch (i % 4) {
                case 0:
                    builder = new FlyerEnemyBuilder(this.gameData, new Point(rx, ry));
                    this.addGameObject(provider.build(builder));
                    break;
                case 1:
                    builder = new LauncherEnemyBuilder(this.gameData, new Point(rx, ry), true);
                    this.addGameObject(provider.build(builder));
                    break;
                case 2:
                    builder = new WalkerEnemyBuilder(this.gameData, new Point(rx, ry));
                    this.addGameObject(provider.build(builder));
                    break;
                case 3:
                    builder = new ArrowBuilder(this.gameData, new Point(rx, ry), new DoubleVector(15d, 0d));
                    this.addGameObject(provider.build(builder));
                    break;
            }
        }
    }
}
