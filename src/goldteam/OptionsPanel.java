package goldteam;

import goldteam.domain.PanelManager;
import goldteam.domain.ManagedPanelBase;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public final class OptionsPanel extends ManagedPanelBase {

    private BufferedImage img;

    public OptionsPanel(PanelManager panelManager) {
        super(panelManager);
        setSize(new Dimension(800, 600));
        LayoutManager mgr = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        super.setLayout(mgr);

        ArrayList<JButton> ret = TestButtons(); //
        for (JButton ret1 : ret) {
            super.add(ret1);
        }
    }

    public ArrayList<JButton> TestButtons() {

        ArrayList<JButton> ret = new ArrayList<>();
        ret.add(new JButton("Open TestGraphicsPanel"));
        ret.add(new JButton("Open Test_HUD_Panel"));
        ret.add(new JButton("Open Test Game Engine Panel"));
        ret.add(new JButton("Open TestColldiersPanel"));
        ret.add(new JButton("Open TestPanelTemplate"));
        ret.add(new JButton("Open TestCharacterPanel"));
        ret.add(new JButton("Open Switch Animation Test Panel"));
        ret.add(new JButton("Open Test Sounds Panel"));
        ret.add(new JButton("Open Test Maps Panel"));
        ret.add(new JButton("Open Game Over Stage Test Panel"));
        ret.add(new JButton("Open Death Stage Test Panel"));
<<<<<<< HEAD
=======
        ret.add(new JButton("Open Game Level Test Panel"));
>>>>>>> dev

        ret.get(0).addActionListener(l -> stupidAction(ret.get(0).getText()));
        ret.get(1).addActionListener(l -> stupidAction(ret.get(1).getText()));
        ret.get(2).addActionListener(l -> stupidAction(ret.get(2).getText()));
        ret.get(3).addActionListener(l -> stupidAction(ret.get(3).getText()));
        ret.get(4).addActionListener(l -> stupidAction(ret.get(4).getText()));
        ret.get(5).addActionListener(l -> stupidAction(ret.get(5).getText()));
        ret.get(6).addActionListener(l -> stupidAction(ret.get(6).getText()));
        ret.get(7).addActionListener(l -> stupidAction(ret.get(7).getText()));
        ret.get(9).addActionListener(l -> stupidAction(ret.get(9).getText()));
        ret.get(8).addActionListener(l -> stupidAction(ret.get(8).getText()));
        ret.get(10).addActionListener(l -> stupidAction(ret.get(10).getText()));
<<<<<<< HEAD
=======
        ret.get(11).addActionListener(l -> stupidAction(ret.get(11).getText()));
>>>>>>> dev

        return ret;
    }

    private void stupidAction(String caption) {
        if ("Open Test_HUD_Panel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_HUD_PANEL);
        }
        if ("Open TestGraphicsPanel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_GRAPHICS_PANEL);
        }
        if ("Open Test Game Engine Panel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_GAME_ENGINE_PANEL);
        }
        if ("Open TestColldiersPanel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_COLLIDERS_PANEL);
        }
        if ("Open TestPanelTemplate".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_PANEL_TEMPLATE);
        }
        if ("Open TestCharacterPanel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_CHARACTER_PANEL);
        }
        if ("Open Switch Animation Test Panel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_ANIM_SWITCH_PANEL);
        }
        if ("Open Test Sounds Panel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_SOUNDS_PANEL);
        }
        if ("Open Test Maps Panel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_MAPS_PANEL);
        }
        if ("Open Game Over Stage Test Panel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_GAME_OVER_STAGE_PANEL);
        }
        if ("Open Death Stage Test Panel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.TEST_DEATH_STAGE_PANEL);
        }
<<<<<<< HEAD
=======
        if ("Open Game Level Test Panel".equals(caption)) {
            panelManager.setActivePanel(GamePanelManager.GAME_LEVEL_TEST_PANEL);
        }
>>>>>>> dev
    }
}
