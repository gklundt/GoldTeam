/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;

/**
 *
 * @author gordon
 */
public final class OptionsPanel extends ManagedPanel {

    public OptionsPanel(PanelManager panelManager) {
        super(panelManager);
        LayoutManager mgr = new GridBagLayout();
        super.setLayout(mgr);
        JButton[] ret = TestButtons(); //
        for (JButton ret1 : ret) {
            super.add(ret1);
        }
    }

    public JButton[] TestButtons() {
        JButton[] ret = new JButton[2];
        ret[0] = new JButton("Open TestGraphicsPanel");
        ret[1] = new JButton("Open Test_HUD_Panel");
        ret[0].addActionListener(l -> stupidAction(ret[0].getText()));
        ret[1].addActionListener(l -> stupidAction(ret[1].getText()));
        
        return ret;
    }

    private void stupidAction(String caption) {
        if ("Open Test_HUD_Panel".equals(caption))
            panelManager.setActivePanel(GamePanelManager.TEST_HUD_PANEL);
        if ("Open TestGraphicsPanel".equals(caption))
            panelManager.setActivePanel(GamePanelManager.TEST_GRAPHICS_PANEL);
    }
}
