package goldteam;

import goldteam.panels.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GoldTeam {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PanelManager pm = GamePanelManager.getGamePanelManager();
        JPanel optionsPanel = new OptionsPanel();
        pm.addPanel(GamePanelManager.OPTIONS_PANEL, optionsPanel);
        
        JFrame frame = new GameWindow(pm);

    }
}
