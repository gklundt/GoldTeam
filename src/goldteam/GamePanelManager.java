package goldteam;

import goldteam.domain.PanelManager;
import goldteam.domain.PanelManagerListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

public class GamePanelManager implements PanelManager {

    /* Constants to locate panel managers */
    public static final String OPTIONS_PANEL = "OPTIONS_PANEL";
    public static final String TEST_GRAPHICS_PANEL = "TEST_GRAPHICS_PANEL";
    public static final String TEST_HUD_PANEL = "TEST_HUD_PANEL";
    public static final String TEST_GAME_ENGINE_PANEL = "TEST_GAME_ENGINE_PANEL";
    public static final String TEST_COLLIDERS_PANEL = "TEST_COLLIDERS_PANEL";
    public static final String TEST_PANEL_TEMPLATE = "TEST_PANEL_TEMPLATE";
    public static final String TEST_CHARACTER_PANEL = "TEST_CHARACTER_PANEL";
    public static final String TEST_ANIM_SWITCH_PANEL = "TEST_ANIM_SWITCH_PANEL";
    public static final String TEST_SOUNDS_PANEL = "TEST_SOUNDS_PANEL";
    public static final String TEST_MAPS_PANEL = "TEST_MAPS_PANEL";

    /* Do not edit below */
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
                this.panels.get(this.activePanel).requestFocus();
                this.notifyListeners();
                break;
            }
        }
    }
}
