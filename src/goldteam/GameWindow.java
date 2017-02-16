package goldteam;

import javax.swing.JFrame;
import goldteam.domain.PanelManager;
import goldteam.domain.PanelManagerListener;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import javax.swing.JPanel;

/**
 * 
 * GameWindow contains all subsequent control panels. 
 * 
 * Game Window is constructed with a PanelManager and 
 * implements its listener. 
 * 
 * When the Panel Manager's active panel is updated, the GameWindow 
 * updates the focus element and loads a new panel
 * 
 **/
public final class GameWindow extends JFrame implements PanelManagerListener {

    private final PanelManager panelManager;

    public GameWindow(GraphicsConfiguration gc, PanelManager panelManager) {
        super(gc);
        this.panelManager = panelManager;
        this.panelManager.addPanelManagerListener(() -> panelManagerChanged());

        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(new Dimension(800, 600));
        super.setLocationRelativeTo(null);
        super.setContentPane(panelManager.getActivePanel());
        super.setVisible(true);
    }

    @Override
    public void panelManagerChanged() {
        setVisible(false);
        JPanel p = panelManager.getActivePanel();
        setContentPane(p);
        super.setVisible(true);
    }
}
