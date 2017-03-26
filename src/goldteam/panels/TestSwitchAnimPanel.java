package goldteam.panels;

import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.providers.MoodyGhostEnemyBuilder;
import java.awt.Point;

public class TestSwitchAnimPanel extends GamePanelBase {

    public TestSwitchAnimPanel(PanelManager panelManager) {
        super(panelManager, new GameData());

    }

    @Override
    protected void addGameObjects() {
        gameObjectBuilder = new MoodyGhostEnemyBuilder(gameData);
        addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(60, 60)));
    }
}
