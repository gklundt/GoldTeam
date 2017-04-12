package goldteam.panels;


import goldteam.animators.SkyAnimation;
import goldteam.builders.FlatPlatformBuilder;
import goldteam.builders.GhostEnemyBuilder;
import goldteam.builders.HeartHudBuilder;
import goldteam.builders.HorizontalPlatformBuilder;
import goldteam.builders.MapDoorsBuilder;
import goldteam.builders.ObstaclePlatformBuilder;
import goldteam.builders.PitPlatformBuilder;
import goldteam.colliders.PlatformCollider;
import goldteam.colliders.StationaryGhostCollider;
import goldteam.domain.Animatable;
import goldteam.domain.AnimationState;
import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.platforms.SkyPlatform;
import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Mishal
 */
public class TestMapsPanel extends GamePanelBase {



    public TestMapsPanel(PanelManager panelManager) {
        super(panelManager, new GameData());

    }

    @Override
    protected void addGameObjects() {
        this.spawnPoint = new Point(1, 370);
        super.addGameObjects();


        


        this.gameObjectBuilder = new GhostEnemyBuilder(gameData);
        addGameObject(this.gameObjectProvider.build(gameObjectBuilder, new Point(10, 490)));

        this.platformBuilder = new FlatPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(0, 400), 300, 200));
        addGameObject(this.platformProvider.build(platformBuilder, new Point(400, 250), 150, 150));
        addGameObject(this.platformProvider.build(platformBuilder, new Point(400, 400), 300, 200));
        addGameObject(this.platformProvider.build(platformBuilder, new Point(650, 400), 300, 200));
        
        this.platformBuilder = new HorizontalPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(320, 300), 50, 10));
        
        this.platformBuilder = new MapDoorsBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(775, 440), 60, 10));
        
        this.platformBuilder = new PitPlatformBuilder (gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(300, 400), 120, 200));
        
        this.platformBuilder = new ObstaclePlatformBuilder (gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(440, 238), 47, 13));
        
        SkyPlatform sky = new SkyPlatform(gameData, new Point(0, 0), 500, 700);
        SkyAnimation fpa4 = new SkyAnimation(sky, gameData.getMapDimensions(), "assets/map.png");
        sky.addAnimator(AnimationState.DEFAULT, fpa4);
        fpa4.setDimensions(new Dimension(400, 250));
        addGameObject(sky);
        
        //StationaryGhostCollider sg = new StationaryGhostCollider();
        //PlatformCollider pc = new PlatformCollider();
        //addGameObject(pc);
//
//        //this.layeredPane.add(ga2, layeredPane.highestLayer());
//        //this.layeredPane.add(ppa, layeredPane.highestLayer());
//
//        CollisionDetector collisionDetector;
//        collisionDetector = new CollisionDetector(this.gameData);
//
//        collisionDetector.addCollisionListener(sg);
//
//        collisionDetector.registerCollidable(g1);
//        collisionDetector.registerCollidable(g2);
//        //collisionDetector.registerCollidable(dp);
//
//        //-----------------------------------//
//        CollisionDetector collisionDetector2;
//        collisionDetector2 = new CollisionDetector(this.gameData);
//        collisionDetector2.addCollisionListener(pc);
//
//        collisionDetector2.registerCollidable(g1);
//        collisionDetector2.registerCollidable(g2);
//        collisionDetector2.registerCollidable(flatPlatform);
//        collisionDetector2.registerCollidable(raisedPlatform);
//        collisionDetector2.registerCollidable(lavaPlatform);
//        collisionDetector2.registerCollidable(flatPlatform1);
//        //collisionDetector2.registerCollidable(dp); // Door Collision 
//
//        //MainCharacterGhost g2 = new MainCharacterGhost(gameData, new Point(60, 60));
//        // BigGhostAnimation bga2 = new BigGhostAnimation(g2, gameData.getMapDimensions(), "assets/GameGhostStripeRed.png");
//        //BigGhostAnimation bga2 = new BigGhostAnimation(g2, gameData.getRunEdgeDimensions(), "assets/GameGhostStripeRed.png");
//        //g2.setAnimator(bga2);
//        //this.layeredPane.add(bga2, 20);
//        this.gameData.setMovableCharacter(g2);
//
//        //BackgroundPanelGhost g1 = new BackgroundPanelGhost(gameData, new Point(gameData.getMapDimensions().width, gameData.getMapDimensions().height));
//        // GhostAnimation bga1 = new GhostAnimation(g1, gameData.getMapDimensions(), "assets/GameGhostStripe.png");
//        // g1.setAnimator(bga1);
//        // this.layeredPane.add(bga1, 15);
//
//        /* tm = new TestMap(gameData, new Point(0, 0));
//        TestMapAnimator tma = new TestMapAnimator(tm, gameData.getMapDimensions());
//        tm.setAnimator(tma);
//        this.layeredPane.add(tma, 5);
//        fpa1.setDimensions(new Dimension(200,150));
//         */
//
//
//        //this.layeredPane.add(bga2, layeredPane.highestLayer());
//        // this.layeredPane.add(bga1, layeredPane.highestLayer());
//        gameOverHud = new TestHudItem(gameData, new Point(10, 10));
//        gameOverHud.setWatcher(g1);
//        TestHudAnimation goa = new TestHudAnimation(gameOverHud, gameData.getVisibleDimensions());
//        gameOverHud.setAnimator(goa);
//        
//        this.layeredPane.add(goa, layeredPane.highestLayer());
//        
//        DoorCollider dr = new DoorCollider();
//        
//        CollisionDetector collisionDetector4;
//        collisionDetector4 = new CollisionDetector(this.gameData);
//        
//        collisionDetector4.addCollisionListener(dr);
//     
//        collisionDetector4.registerCollidable(g1);
//        collisionDetector4.registerCollidable(dp1);
    }

//    private void switchAnimation(Animatable anim) {
//
//        if (anim.getRemoveAnimator() != null) {
//            this.layeredPane.remove(anim.getAnimator());
//        }
//        
//        if (anim.getAnimator() != null) {
//            AnimationBase a = anim.getAnimator();
//       
//            if (a instanceof ResettableAnimation) {
//                ResettableAnimation gsa = (ResettableAnimation) a;
//                gsa.resetAnimation();
//            }
//            
//            this.layeredPane.add(anim.getAnimator(), layeredPane.highestLayer());
//        }
//    }
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
//            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
//            return;
//        }
//
//        int k = e.getKeyCode();
//        if (!check(k)) {
//            addKey(k);
//        }
//
//        g1.processKeyInput(e);
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//        int k = e.getKeyCode();
//        if (check(k)) {
//            removeKey(k);
//        }
//        g1.processKeyInput(e);
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
//}

//        addGameObject(sg);
//
//
//        CollisionDetector collisionDetector;
//        collisionDetector = new CollisionDetector(this.gameData);
//
//        collisionDetector.addCollisionListener(sg);
//
//        collisionDetector.registerCollidable(g1);
//        collisionDetector.registerCollidable(g2);
        //collisionDetector.registerCollidable(dp);

        //-----------------------------------//
//        PlatformCollider pc = new PlatformCollider();
//        CollisionDetector collisionDetector2;
//        collisionDetector2 = new CollisionDetector(this.gameData);
//        collisionDetector2.addCollisionListener(pc);
//
//        collisionDetector2.registerCollidable(g1);
//        collisionDetector2.registerCollidable(g2);
//        collisionDetector2.registerCollidable(flatPlatform);
//        collisionDetector2.registerCollidable(raisedPlatform);
//        collisionDetector2.registerCollidable(lavaPlatform);
//        collisionDetector2.registerCollidable(flatPlatform1);
        //collisionDetector2.registerCollidable(dp); // Door Collision 

        //MainCharacterGhost g2 = new MainCharacterGhost(gameData, new Point(60, 60));
        // BigGhostAnimation bga2 = new BigGhostAnimation(g2, gameData.getMapDimensions(), "assets/GameGhostStripeRed.png");
        //BigGhostAnimation bga2 = new BigGhostAnimation(g2, gameData.getRunEdgeDimensions(), "assets/GameGhostStripeRed.png");
        //g2.setAnimator(bga2);
        //this.layeredPane.add(bga2, 20);
       // this.gameData.setMovableCharacter(g2);

        //BackgroundPanelGhost g1 = new BackgroundPanelGhost(gameData, new Point(gameData.getMapDimensions().width, gameData.getMapDimensions().height));
        // GhostAnimation bga1 = new GhostAnimation(g1, gameData.getMapDimensions(), "assets/GameGhostStripe.png");
        // g1.setAnimator(bga1);
        // this.layeredPane.add(bga1, 15);

        /* tm = new TestMap(gameData, new Point(0, 0));
        TestMapAnimator tma = new TestMapAnimator(tm, gameData.getMapDimensions());
        tm.setAnimator(tma);
        this.layeredPane.add(tma, 5);
        fpa1.setDimensions(new Dimension(200,150));
         */
   

        //this.layeredPane.add(bga2, layeredPane.highestLayer());
        // this.layeredPane.add(bga1, layeredPane.highestLayer());
//        gameOverHud = new TestHudItem(gameData, new Point(10, 10));
//        gameOverHud.setWatcher(g1);
//        TestHudAnimation goa = new TestHudAnimation(gameOverHud, gameData.getVisibleDimensions());
//        gameOverHud.setAnimator(goa);
//        
//        this.layeredPane.add(goa, layeredPane.highestLayer());
//        
//        DoorCollider dr = new DoorCollider();
//        
//        CollisionDetector collisionDetector4;
//        collisionDetector4 = new CollisionDetector(this.gameData);
//        
//        collisionDetector4.addCollisionListener(dr);
//     
//        collisionDetector4.registerCollidable(g1);
//        collisionDetector4.registerCollidable(dp1);
 //   }

    private void switchAnimation(Animatable anim) {

//        if (anim.getRemoveAnimator() != null) {
//            this.layeredPane.remove(anim.getAnimator());
//        }
 
    }
}