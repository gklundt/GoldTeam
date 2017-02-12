/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 *
 * @author gordon
 */
public final class OptionsPanel extends ManagedPanel {

    public OptionsPanel(PanelManager panelManager) {
        super(panelManager);
        setSize(new Dimension(800,600));
        LayoutManager mgr = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        super.setLayout(mgr);
        JButton[] ret = TestButtons(); //
        for (JButton ret1 : ret) {
            super.add(ret1);
        }
    }

    public JButton[] TestButtons() {
        JButton[] ret = new JButton[4];
        ret[0] = new JButton("Open TestGraphicsPanel");
        ret[1] = new JButton("Open Test_HUD_Panel");
        ret[2] = new JButton("Open Test Game Engine Panel");
        ret[3] = new JButton("Open TestColldiersPanel");
        
        ret[0].addActionListener(l -> stupidAction(ret[0].getText()));
        ret[1].addActionListener(l -> stupidAction(ret[1].getText()));
        ret[2].addActionListener(l -> stupidAction(ret[2].getText()));
        ret[3].addActionListener(l -> stupidAction(ret[3].getText()));
        
        return ret;
    }

    private void stupidAction(String caption) {
        if ("Open Test_HUD_Panel".equals(caption))
            panelManager.setActivePanel(GamePanelManager.TEST_HUD_PANEL);
        if ("Open TestGraphicsPanel".equals(caption))
            panelManager.setActivePanel(GamePanelManager.TEST_GRAPHICS_PANEL);
        if ("Open Test Game Engine Panel".equals(caption))
            panelManager.setActivePanel(GamePanelManager.TEST_GAME_ENGINE_PANEL);
        if ("Open TestColldiersPanel".equals(caption))
            panelManager.setActivePanel(GamePanelManager.TEST_COLLIDERS_PANEL);
    }
}
