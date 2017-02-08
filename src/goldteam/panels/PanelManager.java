/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import javax.swing.JPanel;

/**
 *
 * @author gordon
 */
public interface PanelManager {

    void addPanel(String name, JPanel panel);

    JPanel getPanel(String name);

    void addPanelManagerListener(PanelManagerListener listener);

    void removePanelManagerListener(PanelManagerListener listener);

    JPanel getActivePanel();

    void setActivePanel(String panel);

    void notifyListeners();
}
