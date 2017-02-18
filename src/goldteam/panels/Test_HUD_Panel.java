package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.animators.BigGhostAnimation;
import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.animators.GhostAnimation;
import goldteam.animators.HeartHudAnimation;
import goldteam.animators.ShieldHudAnimation;
import goldteam.characters.Ghost;
import goldteam.colliders.CollisionDetector;
import goldteam.colliders.GhostCollider;
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
    private Ghost[] bigGhost;
    private HeartHudItem hearts;
    private ShieldHudItem shields;
    private final CollisionDetector collisionDetector;

    public Test_HUD_Panel(PanelManager panelManager) {
        super(panelManager, new GameData());
        collisionDetector = new CollisionDetector(this.gameData);
    }

    @Override
    protected void addGameObjects() {
        GhostCollider gc = new GhostCollider();
        collisionDetector.addCollisionListener(gc);
        
        ghost = new Ghost(gameData, new Point(60, 60));
        GhostAnimation ghostAnimation = new GhostAnimation(ghost, gameData.getVisibleDimensions(), "assets/GameGhostStripe.png");
        ghost.setAnimator(ghostAnimation);
        ghost.setVelocityScalarDelta(Delta.create(0.0d, ModType.FIXED));
        collisionDetector.registerCollidable(ghost);
        
        bigGhost = new Ghost[5];
        BigGhostAnimation[] bigGhostAnimation = new BigGhostAnimation[5];
        for(int i = 0; i<5; i++) {
            bigGhost[i] = new Ghost(gameData, new Point(600, 600));
            bigGhost[i].setHealthDelta(Delta.create(-20.0, ModType.FIXED));
            bigGhost[i].setShieldDelta(Delta.create(-20.0, ModType.FIXED));
            bigGhostAnimation[i] = new BigGhostAnimation(bigGhost[i], gameData.getVisibleDimensions(), "assets/GameGhostStripeRed.png");
            bigGhost[i].setAnimator(bigGhostAnimation[i]);
            bigGhost[i].setVelocityScalarDelta(Delta.create(0.0d, ModType.FIXED));
            this.layeredPane.add(bigGhostAnimation[i], this.layeredPane.highestLayer());
            collisionDetector.registerCollidable(bigGhost[i]);
        }
        
        hearts = new HeartHudItem(gameData, new Point(10, 10));
        hearts.setWatcher(ghost);
        HeartHudAnimation hha = new HeartHudAnimation(hearts, gameData.getVisibleDimensions(), "assets/heart.png");
        hearts.setAnimator(hha);

        shields = new ShieldHudItem(gameData, new Point(10, 30));
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
