/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.Collectables.Arrows;
import goldteam.Collectables.Health;
import goldteam.Collectables.Shields;
import goldteam.GamePanelManager;
import goldteam.animators.ArrowHudAnimation;
import goldteam.animators.CollectableArrowAnimation;
import goldteam.animators.CollectableHealthAnimation;
import goldteam.animators.CollectableShieldAnimation;
import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import goldteam.characters.StationaryGhost;
import goldteam.colliders.CollisionDetector;
import goldteam.colliders.StationaryGhostCollider;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import goldteam.platforms.FlatPlatform;
import goldteam.platforms.LavaPlatform;
import goldteam.animators.FlatPlatformAnimation;
import goldteam.animators.HeartHudAnimation;
import goldteam.animators.ShieldHudAnimation;
import goldteam.animators.LavaPlatformAnimation;
import goldteam.colliders.CollectablesCollider;
import goldteam.hud.ShieldHudItem;
import goldteam.colliders.PlatformCollider;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionPlane;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.domain.Platform;
import goldteam.hud.ArrowHudItem;
import goldteam.hud.HeartHudItem;
import java.awt.Dimension;
import java.util.Iterator;

/**
 *
 * @author fchishti
 */
public class TestCollidersPanel extends GamePanelBase {

    private StationaryGhost g1;
    private Ghost g2;
    private ArrayList<GameObject> objects;
    private FlatPlatform flatPlatform,raisedPlatform, flatPlatform1;
    private LavaPlatform lavaPlatform;
    private ArrayList<Platform> platforms;
    private Arrows arrow;
    private Health health;
    private Shields shield;
    private HeartHudItem hearts;
    private ShieldHudItem shields;
    private ArrowHudItem hudArrow;
    private final CollisionDetector collisionDetector;
    private final CollisionDetector collisionDetector2;
    private final PlatformCollider pc;

    //private Component gp;
    public TestCollidersPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
        objects = new ArrayList<>();
        collisionDetector = new CollisionDetector(this.gameData);
        collisionDetector2 = new CollisionDetector(this.gameData);
        pc = new PlatformCollider();
        platforms = new ArrayList<>();
    }

    @Override
    protected void addGameObjects() {
        
        objects = new ArrayList<>();
        
        g1 = new StationaryGhost(gameData, new Point(200, 400));
        g2 = new Ghost(gameData, new Point(200, 400));
        objects.add(g1);
        objects.add(g2);
        
        CharacterAnimationBase ga1 = new GhostAnimation(g1, gameData.getVisibleDimensions(), "assets/GameGhostStripe.png");
        CharacterAnimationBase ga2 = new GhostAnimation(g2, gameData.getVisibleDimensions(), "assets/GameGhostStripeRed.png");
        
        flatPlatform = new FlatPlatform(gameData, new Point(0, 412),300,150);
        FlatPlatformAnimation fpa = new FlatPlatformAnimation(flatPlatform, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
        fpa.setDimensions(new Dimension(300,150));
        platforms.add(flatPlatform);
        
        raisedPlatform = new FlatPlatform(gameData, new Point(fpa.getDimensions().width, 300),100,300);
        FlatPlatformAnimation rpa = new FlatPlatformAnimation(raisedPlatform, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
        rpa.setDimensions(new Dimension(100,300));
        platforms.add(raisedPlatform);
        
        lavaPlatform = new LavaPlatform(gameData, new Point(fpa.getDimensions().width + rpa.getDimensions().width, 412), 200, 150);
        LavaPlatformAnimation lpa = new LavaPlatformAnimation(lavaPlatform, gameData.getVisibleDimensions(), "assets/lavaTile1.jpg");
        lpa.setDimensions(new Dimension(200,150));
        platforms.add(lavaPlatform);
        
        flatPlatform1 = new FlatPlatform(gameData, new Point(fpa.getDimensions().width + rpa.getDimensions().width + lpa.getDimensions().width, 412),200,150);
        FlatPlatformAnimation fpa1 = new FlatPlatformAnimation(flatPlatform1, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
        fpa1.setDimensions(new Dimension(200,150));
        platforms.add(flatPlatform1);
        
        arrow = new Arrows(gameData, new Point(20, 388));
        CollectableArrowAnimation aa = new CollectableArrowAnimation(arrow, gameData.getVisibleDimensions(), "assets/crate.png");
        
        health = new Health(gameData, new Point(80, 390));
        CollectableHealthAnimation ha = new CollectableHealthAnimation(health, gameData.getVisibleDimensions(), "assets/heart.png");
        
        shield = new Shields(gameData, new Point(130, 390));
        CollectableShieldAnimation sa = new CollectableShieldAnimation(shield, gameData.getVisibleDimensions(), "assets/shield.png");
        
        hearts = new HeartHudItem(gameData, new Point(10, 10));
        hearts.setWatcher(g1);
        HeartHudAnimation hha = new HeartHudAnimation(hearts, gameData.getVisibleDimensions(), "assets/heart.png");
        hearts.setAnimator(hha);

        shields = new ShieldHudItem(gameData, new Point(10, 30));
        shields.setWatcher(g1);
        ShieldHudAnimation sha = new ShieldHudAnimation(shields, gameData.getVisibleDimensions(), "assets/shield.png");
        shields.setAnimator(sha);
        
        hudArrow = new ArrowHudItem(gameData, new Point(10, 60));
        hudArrow.setWatcher(g1);
        ArrowHudAnimation aha = new ArrowHudAnimation(hudArrow, gameData.getVisibleDimensions(), "assets/Arrow_HUD_Item.png");
        hudArrow.setAnimator(aha);
        
        g1.setAnimator(ga1);
        g2.setAnimator(ga2);
        
        flatPlatform.setAnimator(fpa);
        raisedPlatform.setAnimator(rpa);
        lavaPlatform.setAnimator(lpa);
        flatPlatform1.setAnimator(fpa1);
        
        arrow.setAnimator(aa);
        health.setAnimator(ha);
        shield.setAnimator(sa);
        
        //raisedPlatform.setCollider(raisedPlatform, CollisionPlane.LEFT);
        
        this.layeredPane.add(fpa, layeredPane.highestLayer());
        this.layeredPane.add(lpa, layeredPane.highestLayer());
        this.layeredPane.add(rpa, layeredPane.highestLayer());
        this.layeredPane.add(fpa1, layeredPane.highestLayer());
        this.layeredPane.add(ga1, layeredPane.highestLayer());
        this.layeredPane.add(ga2, layeredPane.highestLayer());
        
        this.layeredPane.add(aa, layeredPane.highestLayer());
        this.layeredPane.add(ha, layeredPane.highestLayer());
        this.layeredPane.add(sa, layeredPane.highestLayer());
        
        this.layeredPane.add(hha, this.layeredPane.highestLayer());
        this.layeredPane.add(sha, this.layeredPane.highestLayer());  
        this.layeredPane.add(aha, this.layeredPane.highestLayer());  

        StationaryGhostCollider sg = new StationaryGhostCollider();  
        
        CollisionDetector collisionDetector;
        collisionDetector = new CollisionDetector(this.gameData);
        
        collisionDetector.addCollisionListener(sg);  
        
        collisionDetector.registerCollidable(g1);
        collisionDetector.registerCollidable(g2);
        
        //-----------------------------------//
        
        PlatformCollider pc = new PlatformCollider();
        CollisionDetector collisionDetector2;
        collisionDetector2 = new CollisionDetector(this.gameData);
        collisionDetector2.addCollisionListener(pc);
        
        
        collisionDetector2.addCollisionListener(pc);
        collisionDetector2.registerCollidable(g1);
        collisionDetector2.registerCollidable(g2);
        collisionDetector2.registerCollidable(flatPlatform);
        collisionDetector2.registerCollidable(raisedPlatform);
        collisionDetector2.registerCollidable(lavaPlatform);
        collisionDetector2.registerCollidable(flatPlatform1);

        //-------------------------------------//
        CollectablesCollider cd = new CollectablesCollider();
        
        CollisionDetector collisionDetector3;
        
        collisionDetector3 = new CollisionDetector(this.gameData);
        
        collisionDetector3.addCollisionListener(cd);
        
        collisionDetector3.registerCollidable(g1);
        collisionDetector3.registerCollidable(arrow);
        collisionDetector3.registerCollidable(health);
        collisionDetector3.registerCollidable(shield);
        
        g1.setHealthDelta(Delta.create(-1.0, ModType.FIXED)); //Purposely started wounded to demonstrate collectable items.
        g1.setShieldDelta(Delta.create(-1.0, ModType.FIXED)); //Purposely started wounded to demonstrate collectable items.
        g1.setArrowDelta(Delta.create(-1.0, ModType.FIXED));
        
        System.out.println(g1.getHealthValue());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            panelThread.interrupt();
            undoGraphics();
            for(GameObject go : objects)
                collisionDetector.removeCollidable((Collidable) go);
            for (Platform p : platforms)
                collisionDetector2.removeCollidable((Collidable) p);
            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
            return;
        }

        int k = e.getKeyCode();
        if (!check(k)) {
            addKey(k);
        }

        g1.processKeyInput(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int k = e.getKeyCode();
        if (check(k)) {
            removeKey(k);
        }
        g1.processKeyInput(e);
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
