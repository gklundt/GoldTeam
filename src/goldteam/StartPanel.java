package goldteam;

import goldteam.domain.PanelManager;
import goldteam.domain.ManagedPanelBase;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public final class StartPanel extends ManagedPanelBase {

    public StartPanel(PanelManager panelManager) {
        super(panelManager);
        setSize(new Dimension(800, 600));
        GridBagLayout mgr = new GridBagLayout();
        super.setLayout(mgr);

        JPanel buttonPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(buttonLayout);
        
        ArrayList<JButton> ret = TestButtons(); //
        for (JButton ret1 : ret) {
            buttonPanel.add(ret1);
        }

        super.add(buttonPanel);
        

        
    }

    public ArrayList<JButton> TestButtons() {

        ArrayList<JButton> ret = new ArrayList<>();
        ret.add(new JButton("Start Game"));
        ret.add(new JButton("Test Panels"));

        ret.get(0).addActionListener(l -> stupidAction(ret.get(0).getText()));
        ret.get(1).addActionListener(l -> stupidAction(ret.get(1).getText()));

        return ret;
    }

    private void stupidAction(String caption) {
        if ("Start Game".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_COLLIDERS_PANEL);
        }
        if ("Test Panels".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
        }
    }
}
