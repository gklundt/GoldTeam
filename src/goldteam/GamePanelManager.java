package goldteam;

import goldteam.domain.PanelManager;
import goldteam.domain.PanelManagerListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static final String TEST_GAME_OVER_STAGE_PANEL = "TEST_GAME_OVER_STAGE_PANEL";
    public static final String TEST_DEATH_STAGE_PANEL = "TEST_DEATH_STAGE_PANEL";
    public static final String GAME_LEVEL_TEST_PANEL = "GAME_LEVEL_TEST_PANEL";

    /* Do not edit below */
    private final ArrayList<PanelManagerListener> listeners;
    private final HashMap<String, String> panels;
    private JPanel activePanel;

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
    public void addPanel(String name, String panel) {
        //String pName = panel.getClass().getName();
        this.panels.put(name, panel);
        if(this.activePanel == null){
            this.activePanel = this.createPanel(panel);
        }
    }

    @Override
    public JPanel getPanel(String name) {
        return this.createPanel(this.panels.get(name));
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
        return this.activePanel;
    }

    @Override
    public void setActivePanel(String panel) {

        this.activePanel = null;
        
        for (String p : this.panels.keySet()) {
            if (p.equals(panel)) {
                this.activePanel = this.createPanel(this.panels.get(p));
                this.activePanel.requestFocus();
                this.notifyListeners();
                break;
            }
        }
    }

    private JPanel createPanel(String className) {

        //String className = "goldteam.panels.GameEngineTestPanel";
        // Create a reflected class from name
        Class<JPanel> tempPanel = null;
        try {
            tempPanel = (Class<JPanel>) Class.forName(className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GamePanelManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Generate a constructor from the constructors defined in our class
        Constructor<JPanel> ctor = null;
        try {
            ctor = tempPanel.getDeclaredConstructor(PanelManager.class);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(GamePanelManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Attempt to create the instance of our class using the ctor.
        JPanel ret = null;
        try {
            ret = ctor.newInstance(this);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GamePanelManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

}
