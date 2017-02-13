/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import goldteam.characters.StationaryGhost;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.gamedata.GameData;
import java.awt.Component;
import java.awt.GraphicsConfiguration;
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
 * @author fchishti
 */
public class TestCollidersPanel extends ManagedPanel implements KeyListener, MouseListener{

    private Component gp;
    private StationaryGhost g1;
    
    public TestCollidersPanel(PanelManager panelManager) {
        super(panelManager);
        super.addAncestorListener(new AncestorListenerImpl());
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

        g1 = new StationaryGhost(gd, new Point(20, 400));
        g1.setVelocityScalarDelta(Delta.create(-15.0d, ModType.FIXED));
        GhostAnimation ga1 = new GhostAnimation(g1, gd.getVisibleDimensions(), "assets/GameGhostStripe.png", 10);
        g1.setAnimator(ga1);
        lp.add(ga1, lp.highestLayer());
    }
    
    private void undoGraphics() {
        gp.removeKeyListener(this);
        gp.removeMouseListener(this);
        gp.setVisible(false);
        validate();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
            undoGraphics();
        }
        g1.processKeyInput(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
