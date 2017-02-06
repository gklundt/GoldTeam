/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam;

import goldteam.panels.GamePanelManager;
import javax.swing.JFrame;
import goldteam.panels.PanelManager;
import goldteam.panels.PanelManagerListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author gordon
 */
public class GameWindow extends JFrame implements PanelManagerListener {

    private final PanelManager panelManager;

    public GameWindow(PanelManager panelManager) {
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panelManager = panelManager;
        this.panelManager.addPanelManagerListener(() -> panelManagerChanged());
        super.setSize(800, 600);
        //super.pack();
        super.setLocationRelativeTo(null);
        super.setContentPane(panelManager.getActivePanel());
        super.setVisible(true);

    }

    @Override
    public void panelManagerChanged() {
        setVisible(false);
        JPanel p = panelManager.getActivePanel();
        setContentPane(p);
        setVisible(true);
        p.setFocusable(true);
        p.requestFocus();
        p.setVisible(true);
    }

}
