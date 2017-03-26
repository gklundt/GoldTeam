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

        builder = new DeathGameStageBuilder(this.gameData);
        this.addGameObject(provider.build(builder, new Point(400, 400)));

    }
}
