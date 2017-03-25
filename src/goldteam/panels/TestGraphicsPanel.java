package goldteam.panels;

import goldteam.domain.PanelManager;
import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import goldteam.domain.GamePanelBase;
import goldteam.gamedata.GameData;
import java.awt.Point;

public class TestGraphicsPanel extends GamePanelBase {

    private Ghost ghost;

    public TestGraphicsPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {
//        
//        
//        
//        ghost = new Ghost(this.gameData, new Point(60, 60));
//        GhostAnimation ghostAnimation = new GhostAnimation(ghost, this.gameData.getRunEdgeDimensions(), "assets/GameGhostStripe.png");
//        ghost.setAnimator(ghostAnimation);
//        this.layeredPane.add(ghostAnimation);
    }
}
