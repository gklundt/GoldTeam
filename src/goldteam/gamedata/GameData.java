package goldteam.gamedata;

import goldteam.animators.ArcherAnimation;
import goldteam.animators.ArrowAnimation;
import goldteam.characters.ArcherMan;
import goldteam.characters.Arrow;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.Depletable;
import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.GamePanelBase;
import goldteam.domain.MapLocationConverter;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author gordon
 */
public class GameData implements GameEngine,
        MapLocationConverter {

    private Boolean isGameRunning;
    private final Dimension mapDimensions;
    private final Dimension visibleDimensions;
    //  private final Dimension runEdgeDimensions;
    private final ArrayList<Integer> heldKeys;
    private final ArrayList<Integer> heldMouse;
    private Depletable depletableCharacter;
    private GameObject centralGameObject;

    private final Timer mapUpdateTimer;
    private final Timer graphicsUpdateTimer;
    private final Timer animationGraphicsUpdateTimer;

    private final Timer effectsUpdateTimer;
    private final Timer collisionTimer;
    private final CoordConv mapLocationConverter;
    private Point mapLocation;
    private ArcherMan archerMan;

    public GameData() {

        this.effectsUpdateTimer = new Timer(1000 / 24, null);
        this.graphicsUpdateTimer = new Timer(1000 / 24, null);
        this.animationGraphicsUpdateTimer = new Timer(1000 / 10, null);
        this.mapUpdateTimer = new Timer(1000 / 24, null);
        this.collisionTimer = new Timer(1000 / 48, null);

        this.heldKeys = new ArrayList<>();
        this.heldMouse = new ArrayList<>();

        this.visibleDimensions = new Dimension(800, 600);
//        this.runEdgeDimensions = new Dimension(600, 400);
        this.mapDimensions = new Dimension(1500, 900);

        this.mapLocationConverter = new CoordConv(mapDimensions, visibleDimensions);
        graphicsUpdateTimer.start();
        collisionTimer.start();
        animationGraphicsUpdateTimer.start();
        this.mapLocation = new Point();
        archerMan = new ArcherMan(this, new Point(400, 400));
    }
    
    public ArcherMan getArcherMan() {
        return archerMan;
    }

    @Override
    public boolean isGameRunning() {
        return this.isGameRunning;
    }

    @Override
    public Dimension getMapDimensions() {
        return this.mapDimensions;
    }

    @Override
    public Dimension getVisibleDimensions() {
        return this.visibleDimensions;
    }

    @Override
    public Dimension getRunEdgeDimensions() {
        return this.visibleDimensions;
    }

    @Override
    public ArrayList<Integer> getHeldKeys() {
        return this.heldKeys;
    }

    @Override
    public ArrayList<Integer> getHeldMouse() {
        return this.heldMouse;
    }

    @Override
    public void addKeysListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void addClicksListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void addMapUpdateTimerListener(ActionListener listener) {
        this.mapUpdateTimer.addActionListener(listener);
    }

    @Override
    public void addGraphicsUpdateTimerListener(ActionListener listener) {
        this.graphicsUpdateTimer.addActionListener(listener);
    }

    @Override
    public void addEffectsTimerListener(ActionListener listener) {
        this.effectsUpdateTimer.addActionListener(listener);
    }

    @Override
    public Depletable getDepletableCharacter() {
        return this.depletableCharacter;
    }

    @Override
    public void setDepletableCharacter(Depletable target) {
        this.depletableCharacter = target;
        this.depletableCharacter.addDepletableListener((ActionEvent l) -> {
            if (GameData.this.depletableCharacter.getCount() < 0) {
                GameData.this.isGameRunning = false;
            }
        });
    }

    @Override
    public GameObject getMovableCharacter() {
        return this.centralGameObject;
    }

    @Override
    public void setMovableCharacter(GameObject target) {
        this.centralGameObject = target;
    }

    @Override
    public void addCollisionTimer(ActionListener listener) {
        this.collisionTimer.addActionListener(listener);
    }

    @Override
    public void removeKeysListener(ActionListener listener) {
        //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeClicksListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeMapUpdateTimerListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeGraphicsUpdateTimerListener(ActionListener listener) {
        this.graphicsUpdateTimer.removeActionListener(listener);
    }

    @Override
    public void addAnimationUpdateTimerListener(ActionListener listener) {
        this.animationGraphicsUpdateTimer.addActionListener(listener);
    }

    @Override
    public void removeAnimationUpdateTimerListener(ActionListener listener) {
        this.animationGraphicsUpdateTimer.removeActionListener(listener);
    }

    @Override
    public void removeEffectsTimerListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCollisionTimer(ActionListener listener) {
        this.collisionTimer.removeActionListener(listener);
    }

    @Override
    public void getMapLocation(Point visableLocation, Point refLocation) {
        this.mapLocationConverter.getMapLocation(visableLocation, refLocation);
    }

    @Override
    public void getVisibleLocation(Point mapLocation, Point refLocation) {
        this.mapLocationConverter.getVisibleLocation(mapLocation, refLocation);
    }

    @Override
    public Point getMapLocation() {
        Point vis = this.centralGameObject.PositionVector();
        this.getMapLocation(vis, mapLocation);
        mapLocation.x = (0 - mapLocation.x) + vis.x;
        mapLocation.y = (0 - mapLocation.y) + vis.y;
        return mapLocation;
    }
    
    public void createBomb(Point p) {
        Arrow arrow = new Arrow(this, (Point)(p.clone()), new DoubleVector(0.0, 5.0));
        CharacterAnimationBase ga1;
        ga1 = new ArrowAnimation(arrow, getVisibleDimensions(), "assets/GameGhostStripe.png");
        arrow.setAnimator(ga1);
   }

}
