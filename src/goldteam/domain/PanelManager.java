package goldteam.domain;

import javax.swing.JPanel;

public interface PanelManager {

    void addPanel(String name, String panel);

    JPanel getPanel(String name);

    void addPanelManagerListener(PanelManagerListener listener);

    void removePanelManagerListener(PanelManagerListener listener);

    JPanel getActivePanel();

    void setActivePanel(String panel);

    void notifyListeners();
}
