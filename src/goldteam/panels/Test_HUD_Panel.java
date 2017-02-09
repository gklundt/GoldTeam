package goldteam.panels;

import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JRootPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 *
 * @author gordon
 */
public class Test_HUD_Panel extends ManagedPanel implements KeyListener, MouseListener {

    public Test_HUD_Panel(PanelManager panelManager) {
        super(panelManager);
        super.addAncestorListener(new AncestorListenerImpl());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        GraphicsConfiguration gf = getGraphicsConfiguration();
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.black);
//        g2d.fill(gf.getBounds());
//        g2d.draw(gf.getBounds());
    }

    private void updateListeners() {
        addKeyListener(this);
        addMouseListener(this);
        GraphicsConfiguration gf = getGraphicsConfiguration();
        JRootPane jrp = getRootPane();

        Ghost g1 = new Ghost();
        Ghost g2 = new Ghost();
        
        GhostAnimation ga1 = new GhostAnimation(g1, jrp.getSize(), "assets/GameGhostStripe.png", 10);
        GhostAnimation ga2 = new GhostAnimation(g2, jrp.getSize(), "assets/GameGhostStripe.png", 10);
        g1.setAnimator(ga1);
        g2.setAnimator(ga2);

        add(g1.getAnimator());
        add(g2.getAnimator());
        validate();
        ga1.setVisible(true);
        ga2.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
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
            //updateListeners();
        }

        @Override
        public void ancestorMoved(AncestorEvent event) {
            //updateListeners();
        }
    }
}
