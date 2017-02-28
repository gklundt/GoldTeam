package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.animators.BigGhostAnimation;
import goldteam.animators.GhostAnimation;
import goldteam.animators.TestMapAnimator;
import goldteam.characters.BackgroundPanelGhost;
import goldteam.characters.Ghost;
import goldteam.characters.MainCharacterGhost;
import goldteam.domain.Delta;
import goldteam.domain.GameObject;
import goldteam.domain.GamePanelBase;
import goldteam.domain.ModType;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.maps.TestMap;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author Mishal
 */
public class TestMapsPanel extends GamePanelBase {

    private TestMap tm;

    public TestMapsPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {

        MainCharacterGhost g2 = new MainCharacterGhost(gameData, new Point(60, 60));
        BigGhostAnimation bga2 = new BigGhostAnimation(g2, gameData.getRunEdgeDimensions(), "assets/GameGhostStripeRed.png");
        g2.setAnimator(bga2);
        this.layeredPane.add(bga2, 20);

        this.gameData.setMovableCharacter(g2);

        BackgroundPanelGhost g1 = new BackgroundPanelGhost(gameData, new Point(gameData.getMapDimensions().width/2, gameData.getMapDimensions().height/2));
        GhostAnimation bga1 = new GhostAnimation(g1, gameData.getMapDimensions(), "assets/GameGhostStripe.png");
        g1.setAnimator(bga1);
        this.layeredPane.add(bga1, 15);

        tm = new TestMap(gameData, new Point(0, 0));
        TestMapAnimator tma = new TestMapAnimator(tm, gameData.getMapDimensions());
        tm.setAnimator(tma);
        this.layeredPane.add(tma, 5);
    }
}
