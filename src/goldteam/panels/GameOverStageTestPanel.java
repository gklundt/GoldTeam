package goldteam.panels;

import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.providers.OverGameStageBuilder;
import java.awt.Point;

public class GameOverStageTestPanel extends GamePanelBase {

    public GameOverStageTestPanel(PanelManager panelManager) {
        super(panelManager, new GameData());

    }

    @Override
    protected void addGameObjects() {
        gameObjectBuilder = new OverGameStageBuilder(this.gameData);
        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(400, 400)));
    }
}
