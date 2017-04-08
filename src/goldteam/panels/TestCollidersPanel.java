/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

//import goldteam.collectables.Arrows;
//import goldteam.Collectables.Health;
//import goldteam.collectables.Shields;
//import goldteam.collectables.Arrows;
import goldteam.GamePanelManager;
import goldteam.animators.ArcherAnimationStanding;
import goldteam.animators.ArrowHudAnimation;
import goldteam.animators.CollectableArrowAnimation;
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
import goldteam.animators.FlatPlatformAnimation;
import goldteam.animators.HeartHudAnimation;
import goldteam.animators.ShieldHudAnimation;
import goldteam.characters.ArcherMan;
import goldteam.characters.Arrow;
import goldteam.colliders.ArrowCollider;
import goldteam.colliders.ArrowCollectablesCollider;
import goldteam.colliders.PlatformCollider;
import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.Platform;
import java.awt.Dimension;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.colliders.PlatformCollider;
import goldteam.domain.Collidable;
import goldteam.domain.DoubleVector;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.domain.VectorMath;
import goldteam.hud.ArrowHudItem;
import goldteam.hud.HeartHudItem;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import goldteam.hud.ShieldHudItem;
import java.util.Iterator;


/**
 *
 * @author fchishti
 */
public class TestCollidersPanel extends GamePanelBase {

    private StationaryGhost g1;
    private Ghost g2;
    private ArrayList<GameObject> objects;
    private CollisionDetector collisionDetector;
    private CollisionDetector collisionDetector2;
    private CollisionDetector collisionDetector3;
    private CollisionDetector collisionDetector4;
    private PlatformCollider pc;
    private FlatPlatform flatPlatform, raisedPlatform, flatPlatform1, lavaPlatform;
    private ArrayList<Platform> platforms;
    //private Arrows arrow;
//    private Health health;
//    private Shields shield;
    private HeartHudItem hearts;
    private ShieldHudItem shields;
    private ArrowHudItem hudArrow;

    private ArcherMan ar;
    private int charge;

    private Arrow characterArrow;

    //private Component gp;
    public TestCollidersPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
        objects = new ArrayList<>();
        collisionDetector = new CollisionDetector(this.gameData);
        collisionDetector2 = new CollisionDetector(this.gameData);
        pc = new PlatformCollider();
        platforms = new ArrayList<>();
        charge = 0;
    }

    @Override
    protected void addGameObjects() {
        super.addGameObjects();

        objects = new ArrayList<>();

        g1 = new StationaryGhost(gameData, new Point(200, 400));
        g2 = new Ghost(gameData, new Point(200, 400));
        objects.add(g1);
        objects.add(g2);

        CharacterAnimationBase ga1 = new GhostAnimation(g1, gameData.getVisibleDimensions(), "assets/GameGhostStripe.png");
        CharacterAnimationBase ga2 = new GhostAnimation(g2, gameData.getVisibleDimensions(), "assets/GameGhostStripeRed.png");

        ar = new ArcherMan(gameData, new Point(200, 380));
//        CharacterAnimationBase archerDefaultRight = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Right.png", charge, charge);
//        CharacterAnimationBase archerDefaultLeft = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Left.png", charge, charge);
//        CharacterAnimationBase archerWalkingRight = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Right.png", charge);
//        CharacterAnimationBase archerWalkingLeft = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Left.png", charge);
//        CharacterAnimationBase archerDrawingRight = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Right.png", charge);
//        CharacterAnimationBase archerDrawingLeft = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Left.png", charge);
//        ar.setAnimator(archerDefaultRight);
//        ar.addAnimator(AnimationState.DEFAULT_RIGHT, archerDefaultRight);
//        ar.addAnimator(AnimationState.DEFAULT_LEFT, archerDefaultLeft);
//        ar.addAnimator(AnimationState.WALKING_RIGHT, archerWalkingRight);
//        ar.addAnimator(AnimationState.WALKING_LEFT, archerWalkingLeft);
//        ar.addAnimator(AnimationState.SHOOTING_RIGHT, archerDrawingRight);
//        ar.addAnimator(AnimationState.SHOOTING_LEFT, archerDrawingLeft);
//        AnimationBase t = ar.getAnimator();
//        this.layeredPane.add(t, layeredPane.highestLayer());
//        ar.addAnimationChangeListener(l -> SwitchArcherListener(l));
        
//        flatPlatform = new FlatPlatform(gameData, new Point(0, 412), 300, 150);
//        FlatPlatformAnimation fpa = new FlatPlatformAnimation(flatPlatform, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
//        fpa.setDimensions(new Dimension(300, 150));
//        platforms.add(flatPlatform);
//
//        raisedPlatform = new FlatPlatform(gameData, new Point(fpa.getDimensions().width, 300), 100, 300);
//        FlatPlatformAnimation rpa = new FlatPlatformAnimation(raisedPlatform, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
//        rpa.setDimensions(new Dimension(100, 300));
//        platforms.add(raisedPlatform);
//        
//        lavaPlatform = new FlatPlatform(gameData, new Point(fpa.getDimensions().width + rpa.getDimensions().width, 412), 200, 150);
//        FlatPlatformAnimation lpa = new FlatPlatformAnimation(lavaPlatform, gameData.getVisibleDimensions(), "assets/lavaTile1.jpg");
//        lpa.setDimensions(new Dimension(200,150));
//        platforms.add(lavaPlatform);
//
//        flatPlatform1 = new FlatPlatform(gameData, new Point(fpa.getDimensions().width + rpa.getDimensions().width + lpa.getDimensions().width, 412), 200, 150);
//        FlatPlatformAnimation fpa1 = new FlatPlatformAnimation(flatPlatform1, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
//        fpa1.setDimensions(new Dimension(200, 150));
//        platforms.add(flatPlatform1);
//
//        arrow = new Arrows(gameData, new Point(20, 388));
//        CollectableArrowAnimation aa = new CollectableArrowAnimation(arrow, gameData.getVisibleDimensions(), "assets/crate.png");

//        objects = new ArrayList<>();
//
//        g1 = new StationaryGhost(gameData, new Point(200, 400));
//        g2 = new Ghost(gameData, new Point(200, 400));
//        objects.add(g1);
//        objects.add(g2);
//
//        CharacterAnimationBase ga1 = new GhostAnimation(g1, gameData.getVisibleDimensions(), "assets/GameGhostStripe.png");
//        CharacterAnimationBase ga2 = new GhostAnimation(g2, gameData.getVisibleDimensions(), "assets/GameGhostStripeRed.png");
//
//        ar = new ArcherMan(gameData, new Point(200, 380));
//        CharacterAnimationBase archerDefaultRight = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Right.png", charge, charge);
//        CharacterAnimationBase archerDefaultLeft = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Left.png", charge, charge);
//        CharacterAnimationBase archerWalkingRight = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Right.png", charge);
//        CharacterAnimationBase archerWalkingLeft = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Left.png", charge);
//        CharacterAnimationBase archerDrawingRight = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Right.png", charge);
//        CharacterAnimationBase archerDrawingLeft = new ArcherAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Left.png", charge);
//        ar.setAnimator(archerDefaultRight);
//        ar.addAnimator(AnimationState.DEFAULT_RIGHT, archerDefaultRight);
//        ar.addAnimator(AnimationState.DEFAULT_LEFT, archerDefaultLeft);
//        ar.addAnimator(AnimationState.WALKING_RIGHT, archerWalkingRight);
//        ar.addAnimator(AnimationState.WALKING_LEFT, archerWalkingLeft);
//        ar.addAnimator(AnimationState.SHOOTING_RIGHT, archerDrawingRight);
//        ar.addAnimator(AnimationState.SHOOTING_LEFT, archerDrawingLeft);
//        AnimationBase t = ar.getAnimator();
//        this.layeredPane.add(t, layeredPane.highestLayer());
//        ar.addAnimationChangeListener(l -> SwitchArcherListener(l));

//        
//        flatPlatform = new FlatPlatform(gameData, new Point(0, 412), 300, 150);
//        FlatPlatformAnimation fpa = new FlatPlatformAnimation(flatPlatform, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
//        fpa.setDimensions(new Dimension(300, 150));
//        platforms.add(flatPlatform);
//
//        raisedPlatform = new FlatPlatform(gameData, new Point(fpa.getDimensions().width, 300), 100, 300);
//        FlatPlatformAnimation rpa = new FlatPlatformAnimation(raisedPlatform, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
//        rpa.setDimensions(new Dimension(100, 300));
//        platforms.add(raisedPlatform);
//        
//        lavaPlatform = new FlatPlatform(gameData, new Point(fpa.getDimensions().width + rpa.getDimensions().width, 412), 200, 150);
//        FlatPlatformAnimation lpa = new FlatPlatformAnimation(lavaPlatform, gameData.getVisibleDimensions(), "assets/lavaTile1.jpg");
//        lpa.setDimensions(new Dimension(200,150));
//        platforms.add(lavaPlatform);
//
//        flatPlatform1 = new FlatPlatform(gameData, new Point(fpa.getDimensions().width + rpa.getDimensions().width + lpa.getDimensions().width, 412), 200, 150);
//        FlatPlatformAnimation fpa1 = new FlatPlatformAnimation(flatPlatform1, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
//        fpa1.setDimensions(new Dimension(200, 150));
//        platforms.add(flatPlatform1);
//
//        arrow = new Arrows(gameData, new Point(20, 388));
//        CollectableArrowAnimation aa = new CollectableArrowAnimation(arrow, gameData.getVisibleDimensions(), "assets/crate.png");
////        
////        flatPlatform = new FlatPlatform(gameData, new Point(0, 412), 300, 150);
////        FlatPlatformAnimation fpa = new FlatPlatformAnimation(flatPlatform, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
////        fpa.setDimensions(new Dimension(300, 150));
////        platforms.add(flatPlatform);
////
////        raisedPlatform = new FlatPlatform(gameData, new Point(fpa.getDimensions().width, 300), 100, 300);
////        FlatPlatformAnimation rpa = new FlatPlatformAnimation(raisedPlatform, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
////        rpa.setDimensions(new Dimension(100, 300));
////        platforms.add(raisedPlatform);
////
////        lavaPlatform = new LavaPlatform(gameData, new Point(fpa.getDimensions().width + rpa.getDimensions().width, 412), 200, 150);
////        LavaPlatformAnimation lpa = new LavaPlatformAnimation(lavaPlatform, gameData.getVisibleDimensions(), "assets/lavaTile1.jpg");
////        lpa.setDimensions(new Dimension(200, 150));
////        platforms.add(lavaPlatform);
////
////        flatPlatform1 = new FlatPlatform(gameData, new Point(fpa.getDimensions().width + rpa.getDimensions().width + lpa.getDimensions().width, 412), 200, 150);
////        FlatPlatformAnimation fpa1 = new FlatPlatformAnimation(flatPlatform1, gameData.getVisibleDimensions(), "assets/platformTile.jpg");
////        fpa1.setDimensions(new Dimension(200, 150));
////        platforms.add(flatPlatform1);
////
////        arrow = new Arrows(gameData, new Point(20, 388));
////        CollectableArrowAnimation aa = new CollectableArrowAnimation(arrow, gameData.getVisibleDimensions(), "assets/crate.png");
//////        
//////        health = new Health(gameData, new Point(80, 390));
//////        CollectableHealthAnimation ha = new CollectableHealthAnimation(health, gameData.getVisibleDimensions(), "assets/heart.png");
//////        
//////        shield = new Shields(gameData, new Point(130, 390));
//////        CollectableShieldAnimation sa = new CollectableShieldAnimation(shield, gameData.getVisibleDimensions(), "assets/shield.png");
////
////        hearts = new HeartHudItem(gameData, new Point(10, 10));
////        hearts.setWatcher(ar);
////        HeartHudAnimation hha = new HeartHudAnimation(hearts, gameData.getVisibleDimensions(), "assets/heart.png");
////        hearts.setAnimator(hha);
////
////        shields = new ShieldHudItem(gameData, new Point(10, 30));
////        shields.setWatcher(ar);
////        ShieldHudAnimation sha = new ShieldHudAnimation(shields, gameData.getVisibleDimensions(), "assets/shield.png");
////        shields.setAnimator(sha);
////
////        hudArrow = new ArrowHudItem(gameData, new Point(10, 60));
////        hudArrow.setWatcher(ar);
////        ArrowHudAnimation aha = new ArrowHudAnimation(hudArrow, gameData.getVisibleDimensions(), "assets/Arrow_HUD_Item.png");
////        hudArrow.setAnimator(aha);
////
////        g1.setAnimator(ga1);
////        g2.setAnimator(ga2);
////
////        flatPlatform.setAnimator(fpa);
////        raisedPlatform.setAnimator(rpa);
////        lavaPlatform.setAnimator(lpa);
////        flatPlatform1.setAnimator(fpa1);
////
////        arrow.setAnimator(aa);
//////        health.setAnimator(ha);
//////        shield.setAnimator(sa);
//////        
////        //raisedPlatform.setCollider(raisedPlatform, CollisionPlane.LEFT);
////
////        this.layeredPane.add(fpa, layeredPane.highestLayer());
////        this.layeredPane.add(lpa, layeredPane.highestLayer());
////        this.layeredPane.add(rpa, layeredPane.highestLayer());
////        this.layeredPane.add(fpa1, layeredPane.highestLayer());
////        this.layeredPane.add(ga1, layeredPane.highestLayer());
////        this.layeredPane.add(ga2, layeredPane.highestLayer());
////
////        this.layeredPane.add(aa, layeredPane.highestLayer());
//////        this.layeredPane.add(ha, layeredPane.highestLayer());
//////        this.layeredPane.add(sa, layeredPane.highestLayer());
//////        
////        this.layeredPane.add(hha, this.layeredPane.highestLayer());
////        this.layeredPane.add(sha, this.layeredPane.highestLayer());
////        this.layeredPane.add(aha, this.layeredPane.highestLayer());
////
////        
////        //----------------------------------------//
////
////        StationaryGhostCollider sg = new StationaryGhostCollider();
////
////        collisionDetector = new CollisionDetector(this.gameData);
////
////        collisionDetector.addCollisionListener(sg);
////
////        collisionDetector.registerCollidable(g1);
////        collisionDetector.registerCollidable(g2);
////
////        //-----------------------------------//
////        PlatformCollider pc = new PlatformCollider();
////        //CollisionDetector collisionDetector2;
////        collisionDetector2 = new CollisionDetector(this.gameData);
////        collisionDetector2.addCollisionListener(pc);
////
////        collisionDetector2.addCollisionListener(pc);
////        collisionDetector2.registerCollidable(g1);
////        collisionDetector2.registerCollidable(g2);
////        for (Platform p : platforms) {
////            collisionDetector2.registerCollidable((Collidable) p);
////        }
////
////        //---------------------------------------------//
////        //-------------------------------------//
////        ArrowCollectablesCollider cd = new ArrowCollectablesCollider();
////
////        collisionDetector3 = new CollisionDetector(this.gameData);
////
////        collisionDetector3.addCollisionListener(cd);
////
////        collisionDetector3.registerCollidable(ar);
////        collisionDetector3.registerCollidable(arrow);
//////        collisionDetector3.registerCollidable(health);
//////        collisionDetector3.registerCollidable(shield);
////
////        ar.setHealthDelta(Delta.create(-1.0, ModType.FIXED)); //Purposely started wounded to demonstrate collectable items.
////        ar.setShieldDelta(Delta.create(-1.0, ModType.FIXED)); //Purposely started wounded to demonstrate collectable items.
////        ar.setArrowDelta(Delta.create(-1.0, ModType.FIXED));
    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
//            panelThread.interrupt();
//            undoGraphics();
//            for (GameObject go : objects) {
//                collisionDetector.removeCollidable((Collidable) go);
//            }
//            for (Platform p : platforms) {
//                collisionDetector2.removeCollidable((Collidable) p);
//            }
//            for (GameObject p : objects) {
//                collisionDetector3.removeCollidable((Collidable) p);
//            }
//            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
//            return;
//        }
//
//        int k = e.getKeyCode();
//        if (!check(k)) {
//            addKey(k);
//        }
//
//        //g1.processKeyInput(e);
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_A:
//                ar.setLeft(true);
//                ar.removeAnimator = ar.animator;
//                ar.animator = ar.animators.get(AnimationState.WALKING_LEFT);
//                ar.notifyAnimationChangeListeners();
//                break;
//            case KeyEvent.VK_D:
//                ar.setRight(true);
//                ar.removeAnimator = ar.animator;
//                ar.animator = ar.animators.get(AnimationState.WALKING_RIGHT);
//                ar.notifyAnimationChangeListeners();
//                break;
//            case KeyEvent.VK_SPACE:
//                ar.setJump(true);
//                break;
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//        int k = e.getKeyCode();
//        if (check(k)) {
//            removeKey(k);
//        }
//        //g1.processKeyInput(e);
//
//        ar.removeAnimator = ar.animator;
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_A:
//                ar.setLeft(false);
//                ar.animator = ar.animators.get(AnimationState.DEFAULT_LEFT);
//                break;
//            case KeyEvent.VK_D:
//                ar.setRight(false);
//                ar.animator = ar.animators.get(AnimationState.DEFAULT_RIGHT);
//                break;
//            case KeyEvent.VK_SPACE:
//                ar.setJump(false);
//                //ar.animator = ar.animators.get(AnimationState.DEFAULT_JUMP);
//                break;
//        }
//        ar.notifyAnimationChangeListeners();
//    }
//
//    private synchronized boolean check(Integer e) {
//        return this.gameData.getHeldKeys().contains(e);
//    }
//
//    private synchronized void addKey(Integer e) {
//        this.gameData.getHeldKeys().add(e);
//    }
//
//    private synchronized void removeKey(Integer e) {
//        this.gameData.getHeldKeys().remove(e);
//    }
//
//    private void SwitchArcherListener(ActionEvent event) {
//        Animatable obj = (Animatable) event.getSource();
//        this.layeredPane.remove(obj.getRemoveAnimator());
//        this.layeredPane.add(obj.getAnimator());
//    }
//
//    protected CharacterAnimationBase createNewArrow(GameData gd, Point p, DoubleVector speed, String image) {
//        characterArrow = new Arrow(gd, (Point) (p.clone()), speed);
//        CharacterAnimationBase ga1;
//        ga1 = new ArcherAnimationStanding(characterArrow, gd.getVisibleDimensions(), image);
//        arrow.setAnimator(ga1);
//        return ga1;
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        super.mousePressed(e);
//        ar.setMousePressed(true);
//        ar.removeAnimator = ar.animator;
//
//        if (e.getX() > ar.PositionVector().x) {
//            ar.animator = ar.animators.get(AnimationState.SHOOTING_RIGHT);
//        } else {
//            ar.animator = ar.animators.get(AnimationState.SHOOTING_LEFT);
//        }
//        ar.notifyAnimationChangeListeners();
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        ar.removeAnimator = ar.animator;
//        if (ar.animator == ar.animators.get(AnimationState.SHOOTING_RIGHT)) {
//            ar.animator = ar.animators.get(AnimationState.DEFAULT_RIGHT);
//        } else if (ar.animator == ar.animators.get(AnimationState.SHOOTING_LEFT)) {
//            ar.animator = ar.animators.get(AnimationState.DEFAULT_LEFT);
//        }
//        ar.notifyAnimationChangeListeners();
//        if (ar.canShootArrow()) {
//            CharacterAnimationBase arrow = null;
//            DoubleVector velocity = VectorMath.getVelocityVector(new DoubleVector(e.getX() - ar.PositionVector().getX(), e.getY() - ar.PositionVector().getY()), 15 + ar.getMouseCharge() * 3);
//            //velocity = new DoubleVector(velocity.x + ar.getVelocityVector().x, velocity.y + ar.getVelocityVector().y); //Player Momentum transfers to arrow
//            if (ar.animator == ar.animators.get(AnimationState.DEFAULT_RIGHT)) {
//                arrow = this.createNewArrow(gameData, ar.PositionVector(), velocity, "assets/Archer/Arrow_Shot_Right.png");
//            } else {
//                arrow = this.createNewArrow(gameData, ar.PositionVector(), velocity, "assets/Archer/Arrow_Shot_Left.png");
//            }
//            this.layeredPane.add(arrow, layeredPane.highestLayer());
//
//            ar.shootArrow();
//            ar.setMousePressed(false);
//
//            ArrowCollider ad = new ArrowCollider();
//
//            collisionDetector4 = new CollisionDetector(this.gameData);
//
//            collisionDetector4.addCollisionListener(ad);
//
//            collisionDetector4.registerCollidable(g1);
//            collisionDetector4.registerCollidable(g2);
//            collisionDetector4.registerCollidable(characterArrow);
//        } else {
//            //Something? Idk
//        }
//    }
}
