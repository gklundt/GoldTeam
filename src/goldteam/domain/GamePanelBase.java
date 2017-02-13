/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import goldteam.gamedata.GameData;
import goldteam.GamePanelManager;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    protected Component glassPanel;
    protected final Runnable panelRunner;
    protected Thread panelThread;
    protected JLayeredPane layeredPane;

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
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            panelThread.interrupt();
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

}
