/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;

/**
 *
 * @author fchishti
 */
public class TestCollidersPanel extends GamePanelBase {

    public TestCollidersPanel(PanelManager panelManager) {
        super(panelManager, new GameData());

    }

    @Override
    protected void addGameObjects() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
