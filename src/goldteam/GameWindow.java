/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam;

import javax.swing.JFrame;
import goldteam.panels.PanelManager;
import goldteam.panels.PanelManagerListener;

/**
 *
 * @author gordon
 */
public class GameWindow extends JFrame implements PanelManagerListener {

    private final PanelManager panelManager;
    
    public GameWindow(PanelManager panelManager) {
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setContentPane(panelManager.getActivePanel());
        super.pack();
        super.setLocationRelativeTo(null);
        super.setVisible(true);

        this.panelManager = panelManager;
        this.panelManager.addPanelManagerListener(() -> panelManagerChanged());
    }

    @Override
    public void panelManagerChanged() {
        super.setContentPane(panelManager.getActivePanel());
    }
}
