package goldteam.panels;

import goldteam.animators.GhostAnimation;
import goldteam.animators.HudAnimation;
import goldteam.characters.Ghost;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.gamedata.GameData;
import goldteam.hud.BasicHudItem;
import goldteam.hud.HeartHudItem;
import goldteam.hud.ShieldHudItem;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 *
 * @author gordon
 */
public class Test_HUD_Panel extends ManagedPanel implements KeyListener, MouseListener {

    private Component gp;
    private Ghost ghost;
    private GhostAnimation ghostAnime;
    private HeartHudItem hearts;
    private ShieldHudItem shields;

    public Test_HUD_Panel(PanelManager panelManager) {
        super(panelManager);
        super.addAncestorListener(new AncestorListenerImpl());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void updateListeners() {
        JRootPane jrp = getRootPane();
        JLayeredPane lp = new JLayeredPane();

        GameData gd = new GameData();
        gd.addGraphicsUpdateTimerListener(l -> lp.repaint());

        lp.setSize(gd.getVisibleDimensions());
        lp.setOpaque(true);
        lp.setPreferredSize(gd.getVisibleDimensions());
        jrp.setContentPane(lp);

        gp = jrp.getGlassPane();
        gp.setVisible(true);
        gp.requestFocus();
        gp.addKeyListener(this);
        gp.addMouseListener(this);

        validate();

        Ghost g1 = new Ghost(gd, new Point(60, 60));
        g1.setVelocityScalarDelta(Delta.create(-15.0d, ModType.FIXED));
        GhostAnimation ga1 = new GhostAnimation(g1, gd.getVisibleDimensions(), "assets/GameGhostStripe.png", 10);
        g1.setAnimator(ga1);

        Ghost g2 = new Ghost(gd, new Point(90, 90));
        GhostAnimation ga2 = new GhostAnimation(g2, gd.getVisibleDimensions(), "assets/GameGhostStripe.png", 10);
        g2.setAnimator(ga2);

        lp.add(ga1, lp.highestLayer() + 1);
        lp.add(ga2, lp.highestLayer() + 1);
        ghost = new Ghost(gd, new Point(60,60));
        gd.setWatcher(ghost);
        hearts = new HeartHudItem(gd, new Point(60,60));
        shields = new ShieldHudItem(gd,new Point(60,60));
        hearts.setWatcher(ghost);
        shields.setWatcher(ghost);
        ghostAnime = new GhostAnimation(ghost, gd.getVisibleDimensions(), "assets/GameGhostStripe.png", 10);
        ghost.setAnimator(ghostAnime);
        lp.add(ghostAnime, lp.highestLayer() + 1);
    }

    private void undoGraphics() {
        gp.removeKeyListener(this);
        gp.removeMouseListener(this);
        gp.setVisible(false);
        validate();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ESCAPE:
                undoGraphics();
                panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
                break;
            case KeyEvent.VK_1:
                hearts.Update(-1);
                break;
            case KeyEvent.VK_2:
                shields.Update(-1);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private class AncestorListenerImpl implements AncestorListener {

        public AncestorListenerImpl() {
        }

        @Override
        public void ancestorAdded(AncestorEvent event) {
            updateListeners();
        }

        @Override
        public void ancestorRemoved(AncestorEvent event) {

        }

        @Override
        public void ancestorMoved(AncestorEvent event) {
            //updateListeners();
        }
    }
}
