package goldteam.panels;

import goldteam.animators.GhostAnimation;
import goldteam.animators.HeartHudAnimation;
import goldteam.animators.ShieldHudAnimation;
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
    private Ghost g1;
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

        g1 = new Ghost(gd, new Point(60, 60));
        g1.setVelocityScalarDelta(Delta.create(-15.0d, ModType.FIXED));
        GhostAnimation ga1 = new GhostAnimation(g1, gd.getVisibleDimensions(), "assets/GameGhostStripe.png", 10);
        g1.setAnimator(ga1);
        
        hearts = new HeartHudItem(gd, new Point(100, 100));
        hearts.setWatcher(g1);
        HeartHudAnimation hha = new HeartHudAnimation(hearts, gd.getVisibleDimensions(), "assets/heart.png");
        hearts.setAnimator(hha);
        
        shields = new ShieldHudItem(gd, new Point(200, 200));
        shields.setWatcher(g1);
        ShieldHudAnimation sha = new ShieldHudAnimation(shields, gd.getVisibleDimensions(), "assets/shield.png");
        shields.setAnimator(sha);
        
        lp.add(ga1, lp.highestLayer());
        lp.add(hha, lp.highestLayer());
        lp.add(sha, lp.highestLayer());
        
        validate();
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
                g1.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
                break;
            case KeyEvent.VK_2:
                g1.setShieldDelta(Delta.create(-1.0, ModType.FIXED));
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
