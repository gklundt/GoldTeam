/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import goldteam.gamedata.GameData;
import goldteam.GamePanelManager;
import goldteam.colliders.CollisionDetector;
import goldteam.providers.GameObjectProvider;
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
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 *
 * @author gordon
 */
public abstract class GamePanelBase extends ManagedPanelBase implements AncestorListener, KeyListener, MouseListener {

    protected final GameData gameData;
    private Component glassPanel;
    private final Runnable panelRunner;
    private Thread panelThread;
    private JLayeredPane layeredPane;
    private final CollisionDetector collisionDetector;
    private final ArrayList<KeyHandler> keyEventListeners;
    private final ArrayList<ClickHandler> clickEventListeners;
    protected GameObjectBuilderBase builder;
    protected final GameObjectProvider provider;

    public GamePanelBase(PanelManager panelManager, GameData gameData) {
        super(panelManager);
        this.gameData = gameData;
        this.panelRunner = () -> {
            setupLayerdPanels();
            addGameObjects();
            addGameListener();
        };
        super.setDoubleBuffered(true);
        super.addAncestorListener(this);
        this.collisionDetector = new CollisionDetector(this.gameData);
        this.keyEventListeners = new ArrayList<>();
        this.clickEventListeners = new ArrayList<>();
        this.provider = new GameObjectProvider();

    }

    protected abstract void addGameObjects();

    protected void undoGraphics() {
        glassPanel.removeKeyListener(this);
        glassPanel.removeMouseListener(this);
        glassPanel.setVisible(false);
        validate();
    }

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
            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
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
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
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

    private synchronized boolean checkMouse(Integer e) {
        return this.gameData.getHeldMouse().containsKey(e);
    }

    private synchronized void addMouse(Integer e, Point p) {
        this.gameData.getHeldMouse().put(e, p);
    }

    private synchronized void removeMouse(Integer e) {
        this.gameData.getHeldMouse().remove(e);
    }

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

    private void setupLayerdPanels() {
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

    private void addGameListener() {
        validate();
        gameData.addGraphicsUpdateTimerListener((ActionEvent l) -> {
            layeredPane.repaint();
            Toolkit.getDefaultToolkit().sync();
        });
    }

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

            this.layeredPane.add(addAnimation.getAnimator(), layeredPane.highestLayer());
        }
    }

    protected void addGameObject(GameObject gameObject) {

        if (gameObject instanceof Animatable) {
            Animatable animatable = (Animatable) gameObject;
            AnimationBase animationBase = animatable.getAnimator();
            this.layeredPane.add(animationBase, layeredPane.highestLayer());
            animatable.addAnimationChangeListener(l -> switchAnimation(animatable, (AnimationBase)l.getSource()));
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
    }

    private void notifyKeyListeners() {
        ActionEvent e = new ActionEvent(this, 0, null);
        for (KeyHandler keyEventListener : keyEventListeners) {
            keyEventListener.processKeyInput();
        }
    }

    private void notifyClickListeners() {
        ActionEvent e = new ActionEvent(this, 0, null);
        for (ClickHandler mouseEventListener : clickEventListeners) {
            mouseEventListener.processMouseInput();
        }
    }

}
