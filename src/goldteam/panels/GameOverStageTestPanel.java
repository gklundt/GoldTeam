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
        builder = new OverGameStageBuilder(this.gameData);
        this.addGameObject(provider.build(builder, new Point(400, 400)));
    }
}
