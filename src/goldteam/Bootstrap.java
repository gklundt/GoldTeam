/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam;

import goldteam.panels.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
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

        GameWindow gw = new GameWindow(pm);

        registerInstance("PanelManager", pm);
        registerInstance("GameWindow", gw);

    }

    private void addTestGraphicsPanel(PanelManager pm) {
        JPanel test = new TestGraphicsPanel(pm);
        pm.addPanel(GamePanelManager.TEST_GRAPHICS_PANEL, test);
    }

    private void addOptionsPanel(PanelManager pm) {
        JPanel optionsPanel = new OptionsPanel(pm);
        pm.addPanel(GamePanelManager.OPTIONS_PANEL, optionsPanel);
        
    }

}
