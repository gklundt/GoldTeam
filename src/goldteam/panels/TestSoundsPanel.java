package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.animators.BigGhostAnimation;
import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import goldteam.colliders.CollisionDetector;
import goldteam.colliders.GhostCollider;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.Delta;
import goldteam.domain.GamePanelBase;
import goldteam.domain.GameSounds;
import goldteam.domain.ModType;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 *
 * @author Rajiv
 */
public class TestSoundsPanel extends GamePanelBase {
    
    private final CollisionDetector collisionDetector;
    
    public TestSoundsPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
        collisionDetector = new CollisionDetector(this.gameData);
    }
    
    @Override
    protected void addGameObjects() {
        GameSounds.sounds[17].play();
        Random x = new Random();
        Random y = new Random();
        for (int i = 1; i <= 6; ++i) {

            int rx = ((Double) (x.nextDouble() * 500.00)).intValue() % 500;
            int ry = ((Double) (y.nextDouble() * 500.00)).intValue() % 500;
            
            CharacterAnimationBase ga1 = null;
            switch (i % 4) {
                case 0:
                    ga1 = this.createNewGhost(gameData, new Point(rx, ry), 15, "assets/GameGhostStripe.png", i);
                    break;
                case 1:
                    ga1 = this.createNewGhost(gameData, new Point(rx, ry), 5, "assets/GameGhostStripeRed.png", i);
                    break;
                case 2:
                    ga1 = this.createNewGhost(gameData, new Point(rx, ry), 10, "assets/GameGhostStripeOrange.png", i);
                    break;
                case 3:
                    ga1 = this.createNewGhost(gameData, new Point(rx, ry), 20, "assets/GameGhostStripeGreen.png", i);
                    break;
            }
            this.layeredPane.add(ga1, layeredPane.highestLayer());
            GhostCollider gc = new GhostCollider();
            collisionDetector.addCollisionListener(gc);
        }
    }
        
    protected CharacterAnimationBase createNewGhost(GameData gd, Point p, Integer speed, String image, int bigOrSmall) {
        Ghost g1 = new Ghost(gd, p);
        CharacterAnimationBase ga1;
        if (bigOrSmall % 3 == 0) {
             ga1 = new BigGhostAnimation(g1, gd.getVisibleDimensions(), image);
        } else {
             ga1 = new GhostAnimation(g1, gd.getVisibleDimensions(), image);
        }
        g1.setVelocityScalarDelta(Delta.create((-0.9 * g1.getVelocity().doubleValue()) + speed, ModType.FIXED));
        g1.setAnimator(ga1);
        collisionDetector.registerCollidable(g1);
        return ga1;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ESCAPE:
                undoGraphics();
                panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
                break;
            case KeyEvent.VK_1:
                GameSounds.sounds[0].play();
                break;
            case KeyEvent.VK_2:
                GameSounds.sounds[1].play();
                break;
            case KeyEvent.VK_3:
                GameSounds.sounds[2].play();
                break;
            case KeyEvent.VK_4:
                GameSounds.sounds[3].play();
                break;
            case KeyEvent.VK_5:
               GameSounds.sounds[4].play();
               break;
            case KeyEvent.VK_6:
                GameSounds.sounds[5].play();
                break;
            case KeyEvent.VK_7:
                GameSounds.sounds[6].play();
                break;
            case KeyEvent.VK_8:
                GameSounds.sounds[11].play();
                break;
            case KeyEvent.VK_9:
                GameSounds.sounds[12].play();
                break;
            case KeyEvent.VK_0:
                GameSounds.sounds[13].play();
                break;
            default:
                break;
        }
        int k = e.getKeyCode();
        if (!check(k)) {
            addKey(k);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int k = e.getKeyCode();
        if (check(k)) {
            removeKey(k);
        }
    }
    
    private synchronized boolean check(Integer e) {
        return this.gameData.getHeldKeys().contains(e);
    }

    private synchronized void addKey(Integer e) {
        this.gameData.getHeldKeys().add(e);
    }

    private synchronized void removeKey(Integer e) {
        this.gameData.getHeldKeys().remove(e);
    }
}
