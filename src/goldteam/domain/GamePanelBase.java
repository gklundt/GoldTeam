/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import goldteam.gamedata.GameData;
import goldteam.GamePanelManager;
import goldteam.characters.ArcherBow;
import goldteam.colliders.CollisionDetector;
import goldteam.builders.ArcherBuilder;
import goldteam.builders.ArrowBuilder;
import goldteam.builders.DeathGameStageBuilder;
import goldteam.builders.FlyerEnemyBuilder;
import goldteam.builders.LauncherEnemyBuilder;
import goldteam.builders.OverGameStageBuilder;
import goldteam.builders.WinGameStageBuilder;
import goldteam.characters.ArcherMan;
import goldteam.characters.Flyer;
import goldteam.panels.TestCollidersPanel;
import goldteam.providers.CollectableProvider;
import goldteam.providers.GameObjectProvider;
import goldteam.providers.HudProvider;
import goldteam.providers.PlatformProvider;
import goldteam.providers.ProjectileProvider;
import java.awt.Component;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 *
 * @author gordon
 */
public abstract class GamePanelBase extends ManagedPanelBase implements AncestorListener, KeyListener, MouseListener {

    private Component glassPanel;
    private Runnable panelRunner;
    private Thread panelThread;
    private JLayeredPane layeredPane;
    private CollisionDetector collisionDetector;
    private ArrayList<KeyHandler> keyEventListeners;
    private ArrayList<ClickHandler> clickEventListeners;

    protected GameData gameData;

    protected GameObjectBuilderBase gameObjectBuilder;
    protected ProjectileBuilderBase projectileBuilder;
    protected HudBuilderBase hudBuilder;
    protected PlatformBuilderBase platformBuilder;
    protected CollectableBuilderBase collectableBuilder;

    protected GameObjectProvider gameObjectProvider;
    protected ProjectileProvider projectileProvider;
    protected HudProvider hudProvider;
    protected PlatformProvider platformProvider;
    protected CollectableProvider collectableProvider;

    private ArrowBuilder arrowBuilder;
    private LauncherEnemyBuilder launcherBuilder;
    protected Point spawnPoint;
    private ArcherBuilder archerBuilder;
    protected ArcherBow archerWeapon;

    private ShootingStrategy shootingStrategy;
    
    private ArrayList<Object> gameObjects;

    public GamePanelBase(PanelManager panelManager, GameData gameData) {
        super(panelManager);
        this.gameData = gameData;
        this.panelRunner = () -> {
            setupLayeredPanels();
            addGameObjects();
            addGameListener();
        };
        super.setDoubleBuffered(true);
        super.addAncestorListener(this);
        this.collisionDetector = new CollisionDetector(this.gameData);
        this.keyEventListeners = new ArrayList<>();
        this.clickEventListeners = new ArrayList<>();
        this.gameObjectProvider = new GameObjectProvider();
        this.projectileProvider = new ProjectileProvider();
        this.collectableProvider = new CollectableProvider();
        this.hudProvider = new HudProvider();
        this.platformProvider = new PlatformProvider();
        this.arrowBuilder = new ArrowBuilder(gameData);
        this.launcherBuilder = new LauncherEnemyBuilder(gameData);

        this.spawnPoint = new Point(400, 300);
        this.shootingStrategy = new ShootDefault();
        
        this.gameObjects = new ArrayList<>();
    }

//<editor-fold defaultstate="collapsed" desc="KeyEvents">
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int k = e.getKeyCode();
        if (!checkKey(k)) {
            addKey(k);
        }

        this.notifyKeyListeners();

        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            panelThread.interrupt();
            undoGraphics();
            panelManager.setActivePanel(GamePanelManager.START_PANEL);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (checkKey(k)) {
            removeKey(k);
        }
        this.notifyKeyListeners();
    }

    private synchronized boolean checkKey(Integer e) {
        return this.gameData.getHeldKeys().contains(e);
    }

    private synchronized void addKey(Integer e) {
        this.gameData.getHeldKeys().add(e);
    }

    private synchronized void removeKey(Integer e) {
        this.gameData.getHeldKeys().remove(e);
    }

    private void notifyKeyListeners() {
        ActionEvent e = new ActionEvent(this, 0, null);
        keyEventListeners.forEach((keyEventListener) -> {
            keyEventListener.processKeyInput();
        });
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Mouse Events">
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int k = e.getButton();
        Point p = e.getPoint();
        if (!checkMouse(k)) {
            addMouse(k, p);
        }
        this.notifyClickListeners();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int k = e.getButton();
        if (checkMouse(k)) {
            removeMouse(k);
        }
        this.notifyClickListeners();
        this.launchArrow(e.getPoint());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private synchronized boolean checkMouse(Integer e) {
        return this.gameData.getHeldMouse().containsKey(e);
    }

    private synchronized void addMouse(Integer e, Point p) {
        this.gameData.getHeldMouse().put(e, p);
    }

    private synchronized void removeMouse(Integer e) {
        this.gameData.getHeldMouse().remove(e);
    }

    private void notifyClickListeners() {
        ActionEvent e = new ActionEvent(this, 0, null);
        clickEventListeners.forEach((mouseEventListener) -> {
            mouseEventListener.processMouseInput();
        });
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Ancestor Events">
    @Override
    public void ancestorAdded(AncestorEvent event) {
        panelThread = new Thread(panelRunner);
        SwingUtilities.invokeLater(() -> panelThread.start());
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {

    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Panel Construction and Destruction">
    private void setupLayeredPanels() {
        JRootPane jrp = getRootPane();
        jrp.setDoubleBuffered(true);
        layeredPane = new JLayeredPane();

        layeredPane.setOpaque(true);
        layeredPane.setDoubleBuffered(true);
        jrp.setContentPane(layeredPane);

        layeredPane.setSize(gameData.getVisibleDimensions());
        layeredPane.setPreferredSize(gameData.getVisibleDimensions());

        glassPanel = jrp.getGlassPane();
        glassPanel.setVisible(true);
        glassPanel.requestFocus();
        glassPanel.addKeyListener(this);
        glassPanel.addMouseListener(this);

    }

    protected void addGameObjects() {
        this.archerBuilder = new ArcherBuilder(this.gameData);
        addGameObject(gameObjectProvider.build(archerBuilder, spawnPoint));
        this.archerWeapon = new ArcherBow(gameData, gameData.getMovableCharacter().PositionVector());
        addGameObject(archerWeapon);
        
        ((ArcherMan)(gameData.getMovableCharacter())).subscribe(this);

        this.archerBuilder.setArcherBow(archerWeapon);
        switchWeapon((Boostable) this.gameData.getMovableCharacter());
    }

    private void addGameListener() {
        validate();
        gameData.addGraphicsUpdateTimerListener((ActionEvent l) -> {
            layeredPane.repaint();
            Toolkit.getDefaultToolkit().sync();
        });
    }

    protected void undoGraphics() {
        glassPanel.removeKeyListener(this);
        glassPanel.removeMouseListener(this);
        glassPanel.setVisible(false);
        validate();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Special Game Listener">
    private void switchAnimation(Animatable addAnimation, AnimationBase removeAnimation) {

        if (removeAnimation != null) {
            this.layeredPane.remove(removeAnimation);
        }

        if (addAnimation.getAnimator() != null) {
            AnimationBase a = addAnimation.getAnimator();

            if (a instanceof ResettableAnimation) {
                ResettableAnimation gsa = (ResettableAnimation) a;
                gsa.resetAnimation();
            }

            int layer;
            if (addAnimation instanceof Enemy) {
                layer = 10;
            } else if (addAnimation instanceof Platform) {
                layer = 1;
            } else if (addAnimation instanceof GameStageAnimationBase) {
                layer = 30;
            } else {
                layer = 20;
            }

            this.layeredPane.add(addAnimation.getAnimator(), layer);
        }
    }

    private void launchArrow(Point mouseLocation) {
        if (gameData.getMovableCharacter() == null) {
            return;
        }
        if (this.archerWeapon.getCount() > 0) {
            this.shootingStrategy.shoot(archerWeapon, projectileProvider, arrowBuilder, archerWeapon, mouseLocation, this);
            this.archerWeapon.setCountDelta(Delta.create(-1d, ModType.FIXED));
        }
    }

    private void switchWeapon(Boostable boostable) {
        if (boostable.isPermanentBoostableWeapon()) {
            shootingStrategy = new ShootBuff();
        } else {
            if (boostable.isBoostableWeapon()) {
                shootingStrategy = new ShootBuff();
            } else {
                shootingStrategy = new ShootDefault();
            }
        }
    }

    private void checkAttackableforRemoval(Attackable attackable) {
        if (attackable.getHealthValue() == 0) {
            this.removeGameObject(attackable);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Add and Remove Game Objects">
    protected void addGameObject(Object gameObject) {

        this.gameObjects.add(gameObject);
        if (gameObject instanceof Animatable) {
            Animatable animatable = (Animatable) gameObject;
            AnimationBase animationBase = animatable.getAnimator();

            int layer;
            if (gameObject instanceof Enemy) {
                layer = 10;
            } else if (gameObject instanceof Platform) {
                layer = 1;
            } else if (gameObject instanceof GameStageAnimationBase) {
                layer = 30;
            } else {
                layer = 20;
            }

            this.layeredPane.add(animationBase, layer);
            animatable.addAnimationChangeListener(l -> switchAnimation(animatable, (AnimationBase) l.getSource()));
        }

        if (gameObject instanceof Boostable) {
            Boostable boostable = (Boostable) gameObject;
            boostable.addBoostableListener(l -> switchWeapon(boostable));
        }

        if (gameObject instanceof Attackable) {
            Attackable attackable = (Attackable) gameObject;
            attackable.addAttackableListener(l -> checkAttackableforRemoval(attackable));
        }

        if (gameObject instanceof Collidable) {
            Collidable collidable = (Collidable) gameObject;
            collisionDetector.registerCollidable(collidable);
        }

        if (gameObject instanceof ClickHandler) {
            ClickHandler clickHandler = (ClickHandler) gameObject;
            this.clickEventListeners.add(clickHandler);
        }

        if (gameObject instanceof KeyHandler) {
            KeyHandler keyHandler = (KeyHandler) gameObject;
            this.keyEventListeners.add(keyHandler);
        }

        if (gameObject instanceof CollisionListener) {
            CollisionListener collisionListener = (CollisionListener) gameObject;
            collisionDetector.addCollisionListener(collisionListener);
        }

        if (gameObject instanceof Removable) {
            Removable removable = (Removable) gameObject;
            removable.addRemovableListener(l -> removeGameObject(l.getSource()));
        }

    }

    protected void removeGameObject(Object gameObject) {

        if (gameObject instanceof Collidable) {
            Collidable collidable = (Collidable) gameObject;
            collisionDetector.removeCollidable(collidable);
        }

        if (gameObject instanceof CollisionListener) {
            CollisionListener collisionListener = (CollisionListener) gameObject;
            collisionDetector.removeCollisionListener(collisionListener);
        }

        if (gameObject instanceof Removable) {
            Removable removable = (Removable) gameObject;
            removable.removeRemovableListener(l -> removeGameObject(l.getSource()));
        }

        if (gameObject instanceof Animatable) {
            Animatable animatable = (Animatable) gameObject;
            AnimationBase animationBase = animatable.getAnimator();
            this.layeredPane.remove(animationBase);
            animatable.removeAnimationChangeListener(l -> switchAnimation(animatable, (AnimationBase) l.getSource()));
        }

        if (gameObject instanceof ClickHandler) {
            ClickHandler clickHandler = (ClickHandler) gameObject;
            this.clickEventListeners.remove(clickHandler);
        }

        if (gameObject instanceof KeyHandler) {
            KeyHandler keyHandler = (KeyHandler) gameObject;
            this.keyEventListeners.remove(keyHandler);
        }
        
        if (gameObject instanceof Flyer)
        {
            ((GameObject) gameObject).markForRemoval();
        }
    }

    public void respawn()
    {
        int lives = ((ArcherMan)(gameData.getMovableCharacter())).getLifeValue();
        if(lives == 0)
        {
            win();
            return;
        }
        for(Object o : gameObjects)
            removeGameObject(o);
        gameObjects.clear();
        addGameObjects();
        ((ArcherMan)(gameData.getMovableCharacter())).setLifeValue(lives - 1);
    }
    
    public void win()
    {
        panelThread.interrupt();
        undoGraphics();
        this.panelManager.setActivePanel(GamePanelManager.START_PANEL);
    }
    
    public void createLauncher(Point p) {
        this.addGameObject(gameObjectProvider.build(launcherBuilder, p.getLocation()));
    }
//</editor-fold>

    public void showDeath()
    {
        int lives = ((ArcherMan)(gameData.getMovableCharacter())).getLifeValue();
        if(lives != 0)
        {
            gameObjectBuilder = new DeathGameStageBuilder(gameData);
            addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(400, 400)));
        }
        else
        {
            gameObjectBuilder = new OverGameStageBuilder(this.gameData);
            this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(400, 400)));
        }
    }
    
    public void showWin()
    {
        gameObjectBuilder = new WinGameStageBuilder(gameData);
        addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(400, 400)));
    }
}
