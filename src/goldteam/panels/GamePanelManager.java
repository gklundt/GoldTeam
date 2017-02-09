/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *jw34ebjwejkgrwkgnerg
 * @author gordon
 */
public class GamePanelManager implements PanelManager {

    public static final String OPTIONS_PANEL = "OPTIONS_PANEL";
    public static final String TEST_GRAPHICS_PANEL = "TEST_GRAPHICS_PANEL";
    public static final String TEST_HUD_PANEL = "TEST_HUD_PANEL";

    private final ArrayList<PanelManagerListener> listeners;
    private final HashMap<String, JPanel> panels;
    private String activePanel;

    private static GamePanelManager instance;

    private GamePanelManager() {
        this.panels = new HashMap<>();
        this.listeners = new ArrayList<>();
    }

    public static GamePanelManager getGamePanelManager() {
        if (instance == null) {
            instance = new GamePanelManager();
        }
        return instance;
    }

    @Override
    public void addPanel(String name, JPanel panel) {
        this.panels.put(name, panel);
        if (this.panels.size() == 1) {
            this.activePanel = name;
        }
    }

    @Override
    public JPanel getPanel(String name) {
        return this.panels.get(name);
    }

    @Override
    public void addPanelManagerListener(PanelManagerListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removePanelManagerListener(PanelManagerListener listener) {
        this.listeners.remove(listener);
    }

    @Override
    public void notifyListeners() {
        for (PanelManagerListener lsn : this.listeners) {
            lsn.panelManagerChanged();
        }
    }

    @Override
    public JPanel getActivePanel() {
        return this.getPanel(this.activePanel);
    }

    @Override
    public void setActivePanel(String panel) {
        for (String p : this.panels.keySet()) {
            if (p.equals(panel)) {
                this.activePanel = p;
                this.notifyListeners();
                break;
            }
        }
    }
}
