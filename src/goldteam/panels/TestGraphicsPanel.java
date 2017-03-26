package goldteam.panels;

import goldteam.domain.PanelManager;
import goldteam.characters.Ghost;
import goldteam.domain.GamePanelBase;
import goldteam.gamedata.GameData;
import goldteam.providers.GhostEnemyBuilder;
import java.awt.Point;

public class TestGraphicsPanel extends GamePanelBase {

    private Ghost ghost;

    public TestGraphicsPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {

        builder = new GhostEnemyBuilder(gameData, new Point(60,60));
        addGameObject(provider.build(builder));
        
    }
}
