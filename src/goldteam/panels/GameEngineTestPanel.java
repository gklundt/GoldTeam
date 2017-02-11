package goldteam.panels;

import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.gamedata.GameData;
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
public class GameEngineTestPanel extends ManagedPanel implements KeyListener, MouseListener {

    private Component gp;

    public GameEngineTestPanel(PanelManager panelManager) {
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

        for (int i = 1; i <= 4; ++i) {
            GhostAnimation ga1 = null;
            switch (i % 4) {
                case 0:
                    ga1 = this.createNewGhost(gd, new Point(i, i), 15, "assets/GameGhostStripe.png");
                    break;
                case 1:
                    ga1 = this.createNewGhost(gd, new Point(i, i), 5, "assets/GameGhostStripeRed.png");
                    break;
                case 2:
                    ga1 = this.createNewGhost(gd, new Point(i, i), 10, "assets/GameGhostStripeOrange.png");
                    break;
                case 3:
                    ga1 = this.createNewGhost(gd, new Point(i, i), 20, "assets/GameGhostStripeGreen.png");
                    break;
            }
            lp.add(ga1, lp.highestLayer());
        }

    }

    private GhostAnimation createNewGhost(GameData gd, Point p, Integer speed, String image) {
        Ghost g1 = new Ghost(gd, p);

        GhostAnimation ga1 = new GhostAnimation(g1, gd.getVisibleDimensions(), image, 10);

        g1.setVelocityScalarDelta(Delta.create((-0.9 * g1.getVelocity().doubleValue()) + speed, ModType.FIXED));
        g1.setAnimator(ga1);

        return ga1;
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
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            undoGraphics();
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
        }

        @Override
        public void ancestorMoved(AncestorEvent event) {
            //updateListeners();
        }
    }
}
