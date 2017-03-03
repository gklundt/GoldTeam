package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.animators.BigGhostAnimation;
import goldteam.animators.FlatPlatformAnimation;
import goldteam.animators.GhostAnimation;
import goldteam.animators.LavaPlatformAnimation;
import goldteam.animators.MapDoorsAnimation;
import goldteam.animators.PotionAnimation;
import goldteam.animators.SkyAnimation;
import goldteam.animators.TestMapAnimator;
import goldteam.characters.BackgroundPanelGhost;
import goldteam.characters.Ghost;
import goldteam.characters.MainCharacterGhost;
import goldteam.characters.StationaryGhost;
import goldteam.colliders.CollisionDetector;
import goldteam.colliders.PlatformCollider;
import goldteam.colliders.StationaryGhostCollider;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.Delta;
import goldteam.domain.GameObject;
import goldteam.domain.GamePanelBase;
import goldteam.domain.ModType;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.maps.TestMap;
import goldteam.platforms.DoorsPlatform;
import goldteam.platforms.FlatPlatform;
import goldteam.platforms.LavaPlatform;
import goldteam.platforms.PotionsPlatform;
import goldteam.platforms.SkyPlatform;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author Mishal
 */
public class TestMapsPanel extends GamePanelBase {
    
    private StationaryGhost g1;
    private Ghost g2;
    private TestMap tm;
    private DoorsPlatform dp, dp1;
    private FlatPlatform flatPlatform,raisedPlatform, flatPlatform1;
    private LavaPlatform lavaPlatform;
    private SkyPlatform sky;
    private PotionsPlatform pp;

    public TestMapsPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
        
    }

    @Override
    protected void addGameObjects() {
        
        g1 = new StationaryGhost(gameData, new Point(1, 490));
        g1 = new StationaryGhost(gameData, new Point(1, 490));
        g2 = new Ghost(gameData, new Point(1, 490));
        
        CharacterAnimationBase ga1 = new GhostAnimation(g1, gameData.getMapDimensions(), "assets/GameGhostStripe.png");
        //CharacterAnimationBase ga2 = new GhostAnimation(g2, gameData.getMapDimensions(), "assets/GameGhostStripeRed.png");
        
        sky = new SkyPlatform(gameData, new Point(0, 0),100,200);
        SkyAnimation fpa4 = new SkyAnimation(sky, gameData.getMapDimensions(), "assets/sky1.png");
        fpa4.setDimensions(new Dimension(200,150)); 
        
        flatPlatform = new FlatPlatform(gameData, new Point(0, 500),300,150);
        FlatPlatformAnimation fpa = new FlatPlatformAnimation(flatPlatform, gameData.getMapDimensions(), "assets/platformTile.jpg");
        fpa.setDimensions(new Dimension(300,150));
        
        raisedPlatform = new FlatPlatform(gameData, new Point(400 , 350),100,300);
        FlatPlatformAnimation rpa = new FlatPlatformAnimation(raisedPlatform, gameData.getMapDimensions(), "assets/platformTile.jpg");
        rpa.setDimensions(new Dimension(100,150));
        
        pp = new PotionsPlatform(gameData, new Point(300, 30), 100, 300);
        PotionAnimation ppa = new PotionAnimation(pp, gameData.getMapDimensions(), "assets/grass_block.png");
        ppa.setDimensions(new Dimension(200,150));
        
        
        lavaPlatform = new LavaPlatform(gameData, new Point(412 + rpa.getDimensions().width, 500), 200, 150);
        LavaPlatformAnimation lpa = new LavaPlatformAnimation(lavaPlatform, gameData.getMapDimensions(), "assets/lavaTile.png");
        lpa.setDimensions(new Dimension(200,150));
        
        flatPlatform1 = new FlatPlatform(gameData, new Point(448 + rpa.getDimensions().width + lpa.getDimensions().width, 500),200,150);
        FlatPlatformAnimation fpa1 = new FlatPlatformAnimation(flatPlatform1, gameData.getMapDimensions(), "assets/platformTile.jpg");
        fpa1.setDimensions(new Dimension(200,150));
        
        flatPlatform1 = new FlatPlatform(gameData, new Point(704 + rpa.getDimensions().width + lpa.getDimensions().width, 500),200,150);
        FlatPlatformAnimation fpa2 = new FlatPlatformAnimation(flatPlatform1, gameData.getMapDimensions(), "assets/platformTile.jpg");
        fpa1.setDimensions(new Dimension(200,150));
        
        flatPlatform1 = new FlatPlatform(gameData, new Point(960 + rpa.getDimensions().width + lpa.getDimensions().width, 500),200,150);
        FlatPlatformAnimation fpa3 = new FlatPlatformAnimation(flatPlatform1, gameData.getMapDimensions(), "assets/platformTile.jpg");
        fpa1.setDimensions(new Dimension(200,150));
         
      
        
        
        g1.setAnimator(ga1);
     //   g2.setAnimator(ga2);
        
        flatPlatform.setAnimator(fpa);
        raisedPlatform.setAnimator(rpa);
        lavaPlatform.setAnimator(lpa);
        flatPlatform1.setAnimator(fpa1);
        flatPlatform1.setAnimator(fpa2);
        flatPlatform1.setAnimator(fpa3);
        sky.setAnimator(fpa4);
        pp.setAnimator(ppa);
        
        //raisedPlatform.setCollider(raisedPlatform, CollisionPlane.LEFT);
        
        this.layeredPane.add(fpa, layeredPane.highestLayer());
        this.layeredPane.add(lpa, layeredPane.highestLayer());
        this.layeredPane.add(rpa, layeredPane.highestLayer());
        this.layeredPane.add(fpa4, 20);
        this.layeredPane.add(fpa3, layeredPane.highestLayer());
        this.layeredPane.add(fpa2, layeredPane.highestLayer());   
        this.layeredPane.add(fpa1, layeredPane.highestLayer());     
        this.layeredPane.add(ga1, layeredPane.highestLayer());
        //this.layeredPane.add(ga2, layeredPane.highestLayer());
        this.layeredPane.add(ppa, layeredPane.highestLayer());

        StationaryGhostCollider sg = new StationaryGhostCollider();
        
        CollisionDetector collisionDetector;
        collisionDetector = new CollisionDetector(this.gameData);
        
        collisionDetector.addCollisionListener(sg);
        
        collisionDetector.registerCollidable(g1);
        collisionDetector.registerCollidable(g2);
        //collisionDetector.registerCollidable(dp);
        
        //-----------------------------------//
        
        PlatformCollider pc = new PlatformCollider();
        CollisionDetector collisionDetector2;
        collisionDetector2 = new CollisionDetector(this.gameData);
        collisionDetector2.addCollisionListener(pc);
        
        collisionDetector2.registerCollidable(g1);
        collisionDetector2.registerCollidable(g2);
        collisionDetector2.registerCollidable(flatPlatform);
        collisionDetector2.registerCollidable(raisedPlatform);
        collisionDetector2.registerCollidable(lavaPlatform);
        collisionDetector2.registerCollidable(flatPlatform1);
        //collisionDetector2.registerCollidable(dp); // Door Collision 

        //MainCharacterGhost g2 = new MainCharacterGhost(gameData, new Point(60, 60));
       // BigGhostAnimation bga2 = new BigGhostAnimation(g2, gameData.getMapDimensions(), "assets/GameGhostStripeRed.png");
        //BigGhostAnimation bga2 = new BigGhostAnimation(g2, gameData.getRunEdgeDimensions(), "assets/GameGhostStripeRed.png");
        //g2.setAnimator(bga2);
        //this.layeredPane.add(bga2, 20);

        this.gameData.setMovableCharacter(g2);

        //BackgroundPanelGhost g1 = new BackgroundPanelGhost(gameData, new Point(gameData.getMapDimensions().width, gameData.getMapDimensions().height));
       // GhostAnimation bga1 = new GhostAnimation(g1, gameData.getMapDimensions(), "assets/GameGhostStripe.png");
       // g1.setAnimator(bga1);
       // this.layeredPane.add(bga1, 15);

       /* tm = new TestMap(gameData, new Point(0, 0));
        TestMapAnimator tma = new TestMapAnimator(tm, gameData.getMapDimensions());
        tm.setAnimator(tma);
        this.layeredPane.add(tma, 5);
       */
       //starting door
        dp = new DoorsPlatform(gameData, new Point(0, 438),10,10);
        MapDoorsAnimation mpa = new MapDoorsAnimation(dp, gameData.getMapDimensions(), "assets/S_door.png");
        mpa.setDimensions(new Dimension(50,50));
        this.layeredPane.add(mpa, 20);
        dp.setAnimator(mpa);
        
        //ending door 
        dp1 = new DoorsPlatform(gameData, new Point(1280, 438),10,10);
        MapDoorsAnimation mp1 = new MapDoorsAnimation(dp1, gameData.getMapDimensions(), "assets/S_door.png");
        mp1.setDimensions(new Dimension(50,50));
        this.layeredPane.add(mp1,layeredPane.highestLayer());
        dp1.setAnimator(mp1);
        
    
        //this.layeredPane.add(bga2, layeredPane.highestLayer());
       // this.layeredPane.add(bga1, layeredPane.highestLayer());
        this.layeredPane.add(mpa, layeredPane.highestLayer());
        
    }
     @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            panelThread.interrupt();
            undoGraphics();
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
