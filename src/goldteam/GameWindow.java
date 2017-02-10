package goldteam;

import javax.swing.JFrame;
import goldteam.panels.PanelManager;
import goldteam.panels.PanelManagerListener;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * GameWindow contains all subsequent control panels. Game Window is constructed
 * with a PanelManager and implements its listener. When the Panel Manager's
 * active panel is updated, the GameWindow updates the focus element and loads a
 * new panel
 *
 * @author gordon
 */
public class GameWindow extends JFrame implements PanelManagerListener {
    
    private final PanelManager panelManager;
    
    public GameWindow(PanelManager panelManager) {
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panelManager = panelManager;
        this.panelManager.addPanelManagerListener(() -> panelManagerChanged());
        super.setLocationRelativeTo(null);
        super.setSize(new Dimension(800, 600));
        super.setContentPane(panelManager.getActivePanel());
        super.setVisible(true);
    }
    
    @Override
    public void panelManagerChanged() {
        setVisible(false);
        JPanel p = panelManager.getActivePanel();
        setContentPane(p);
        
        setVisible(true);
        p.setFocusable(true);
        p.requestFocus();
    }
    
}
