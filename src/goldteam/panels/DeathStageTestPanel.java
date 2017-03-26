package goldteam.panels;

import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.providers.DeathGameStageBuilder;
import java.awt.Point;

public class DeathStageTestPanel extends GamePanelBase {

    public DeathStageTestPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {

        gameObjectBuilder = new DeathGameStageBuilder(this.gameData);
        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(400, 400)));

        super.addGameObjects();
    }
}
