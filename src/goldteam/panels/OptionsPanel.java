/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author gordon
 */
public class OptionsPanel extends JPanel {

    public OptionsPanel() {
        LayoutManager mgr = new GridLayout(1,1);
        super.setLayout(mgr);
        super.add(TestPanel());
        //super.setPreferredSize(new Dimension(800, 600));
        

    }

    private JButton TestPanel() {
        
        JButton ret = new JButton("something");
        ret.addActionListener((e) -> mork());
        return ret;
    }

    private void mork() {

    }

}
