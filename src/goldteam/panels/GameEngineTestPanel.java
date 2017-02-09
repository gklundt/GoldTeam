package goldteam.panels;

import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import goldteam.gamedata.GameData;
import java.awt.Graphics;
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
public class GameEngineTestPanel extends ManagedPanel implements KeyListener, MouseListener {

    public GameEngineTestPanel(PanelManager panelManager) {
        super(panelManager);
        super.addAncestorListener(new AncestorListenerImpl());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void updateListeners() {
        validate();
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
