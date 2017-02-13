package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.animators.GhostAnimation;
import goldteam.animators.HeartHudAnimation;
import goldteam.animators.ShieldHudAnimation;
import goldteam.characters.Ghost;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.gamedata.GameData;
import goldteam.hud.HeartHudItem;
import goldteam.hud.ShieldHudItem;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Test_HUD_Panel extends GamePanelBase {

    private Ghost ghost;
    private HeartHudItem hearts;
    private ShieldHudItem shields;

    public Test_HUD_Panel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {

        ghost = new Ghost(gameData, new Point(60, 60));
        GhostAnimation ghostAnimation = new GhostAnimation(ghost, gameData.getVisibleDimensions(), "assets/GameGhostStripe.png");
        ghost.setAnimator(ghostAnimation);
        ghost.setVelocityScalarDelta(Delta.create(0.0d, ModType.FIXED));

        hearts = new HeartHudItem(gameData, new Point(100, 100));
        hearts.setWatcher(ghost);
        HeartHudAnimation hha = new HeartHudAnimation(hearts, gameData.getVisibleDimensions(), "assets/heart.png");
        hearts.setAnimator(hha);

        shields = new ShieldHudItem(gameData, new Point(200, 200));
        shields.setWatcher(ghost);
        ShieldHudAnimation sha = new ShieldHudAnimation(shields, gameData.getVisibleDimensions(), "assets/shield.png");
        shields.setAnimator(sha);

        this.layeredPane.add(ghostAnimation, this.layeredPane.highestLayer());
        this.layeredPane.add(hha, this.layeredPane.highestLayer());
        this.layeredPane.add(sha, this.layeredPane.highestLayer());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ESCAPE:
                undoGraphics();
                panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
                break;
            case KeyEvent.VK_1:
                ghost.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
                break;
            case KeyEvent.VK_2:
                ghost.setShieldDelta(Delta.create(-1.0, ModType.FIXED));
                break;
            default:
                break;
        }
    }
}
