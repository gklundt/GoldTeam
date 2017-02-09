package goldteam;

import goldteam.panels.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Bootstrap Container.
 * This bootstrap is a container for pre-assembled interfaces.  We will use
 * containers to construct objects needed for further use.  This container
 * resolver should prevent the need to create objects inside a class.  Ideally,
 * dependencies should be resolved from a container and injected into a 
 * constructor.
 * 
 * Consider this the "composition root" of the application.
 * @author gordon
 */
class Bootstrap {

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

    public void initialize() {

        PanelManager pm = GamePanelManager.getGamePanelManager();
        addOptionsPanel(pm);
        addTestGraphicsPanel(pm);
        addTest_HUD_Panel(pm);
        addTestGameEnginePanel(pm);
        GameWindow gw = new GameWindow(pm);

        registerInstance("PanelManager", pm);
        registerInstance("GameWindow", gw);

    }

    private void addTestGraphicsPanel(PanelManager pm) {
        JPanel test = new TestGraphicsPanel(pm);
        pm.addPanel(GamePanelManager.TEST_GRAPHICS_PANEL, test);
    }

    private void addTest_HUD_Panel(PanelManager pm) {
        JPanel test = new Test_HUD_Panel(pm);
        pm.addPanel(GamePanelManager.TEST_HUD_PANEL, test);
    }
    
    private void addOptionsPanel(PanelManager pm) {
        JPanel optionsPanel = new OptionsPanel(pm);
        pm.addPanel(GamePanelManager.OPTIONS_PANEL, optionsPanel);
        
    }

    private void addTestGameEnginePanel(PanelManager pm) {
        JPanel testPanel = new GameEngineTestPanel(pm);
        pm.addPanel(GamePanelManager.TEST_GAME_ENGINE_PANEL, testPanel);
    }
}
