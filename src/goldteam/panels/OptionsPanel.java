/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
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
        super.add(TestButton());
    }

    public JButton TestButton() {
        JButton ret = new JButton("something");
        ret.addActionListener(l -> stupidAction());
        return ret;
    }

    private void stupidAction() {
        panelManager.setActivePanel(GamePanelManager.TEST_GRAPHICS_PANEL);
    }
}
