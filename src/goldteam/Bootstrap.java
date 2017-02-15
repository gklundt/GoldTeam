package goldteam;

import goldteam.panels.GameEngineTestPanel;
import goldteam.domain.PanelManager;
import goldteam.panels.TestCharacterPanel;
import goldteam.panels.TestCollidersPanel;
import goldteam.panels.TestGraphicsPanel;
import goldteam.panels.TestPanelTemplate;
import goldteam.panels.Test_HUD_Panel;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Bootstrap Container. This bootstrap is a container for pre-assembled
 * interfaces. We will use containers to construct objects needed for further
 * use. This container resolver should prevent the need to create objects inside
 * a class. Ideally, dependencies should be resolved from a container and
 * injected into a constructor.
 *
 * Consider this the "composition root" of the application.
 *
 * @author gordon
 */
class Bootstrap {

    public void initialize() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration gc = device.getDefaultConfiguration();

        PanelManager pm = GamePanelManager.getGamePanelManager();
        addOptionsPanel(pm);

        /* Add additional test/game panels here. */
        addTestGraphicsPanel(pm);
        addTest_HUD_Panel(pm);
        addTestGameEnginePanel(pm);
        addTestCollidersPanel(pm);
        addTestPanelTemplate(pm);
        addTestCharacterPanel(pm);

        /* Register game window */
        GameWindow gw = new GameWindow(gc, pm);
        registerInstance("PanelManager", pm);
        registerInstance("GameWindow", gw);
    }

    /**
     * Add a new block for each test panel below.
     *
     * Add create method to list above
     *
     * Add a button in goldteam.OptionsPanel.java
     *
     * Create a graphics panel that extends goldteam.domain.GamePanelBase in
     * goldteam.panels
     *
     *
     * See goldteam.panels.TestPanelTemplate for a template/example
     *
     * Add a constant for your gamepanel name in goldteam.GamePanelManager.java
     *
     *
     */
    private void addTestGraphicsPanel(PanelManager pm) {
        JPanel testPanel = new TestGraphicsPanel(pm);
        pm.addPanel(GamePanelManager.TEST_GRAPHICS_PANEL, testPanel);
    }

    private void addTest_HUD_Panel(PanelManager pm) {
        JPanel testPanel = new Test_HUD_Panel(pm);
        pm.addPanel(GamePanelManager.TEST_HUD_PANEL, testPanel);
    }

    private void addTestGameEnginePanel(PanelManager pm) {
        JPanel testPanel = new GameEngineTestPanel(pm);
        pm.addPanel(GamePanelManager.TEST_GAME_ENGINE_PANEL, testPanel);
    }

    private void addTestCollidersPanel(PanelManager pm) {
        JPanel testPanel = new TestCollidersPanel(pm);
        pm.addPanel(GamePanelManager.TEST_COLLIDERS_PANEL, testPanel);
    }

    private void addTestPanelTemplate(PanelManager pm) {
        JPanel testPanel = new TestPanelTemplate(pm);
        pm.addPanel(GamePanelManager.TEST_PANEL_TEMPLATE, testPanel);
    }
    
    private void addTestCharacterPanel(PanelManager pm) {
        JPanel testPanel = new TestCharacterPanel(pm);
        pm.addPanel(GamePanelManager.TEST_CHARACTER_PANEL, testPanel);
    }
    
    /* Do not modify below */
    private final HashMap<String, Object> resolver = new HashMap<>();

    public <T> T resolve(String obj) {
        for (Map.Entry<String, Object> entry : resolver.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key.equals(obj)) {
                return (T) value;
            }
        }
        return null;
    }

    public void registerInstance(String instanceName, Object obj) {
        resolver.put(instanceName, obj);
    }

    private void addOptionsPanel(PanelManager pm) {
        JPanel optionsPanel = new OptionsPanel(pm);
        pm.addPanel(GamePanelManager.OPTIONS_PANEL, optionsPanel);
    }

}
