
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.maps.BasicLevelMap;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.JLayeredPane;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mishal
 */
public class TestMapsPanel extends GamePanelBase  {

    private BasicLevelMap layer;

    

    public TestMapsPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {
     
        BasicLevelMap blm;
       // canvas = new Canvas();
       // canvas.setPreferredSize(new Dimension(800, 600));
       // canvas.setMaximumSize(new Dimension(800, 600));
       // canvas.setMinimumSize(new Dimension(800, 600));
   /*     int[][] i = new int[26][14];
        try {
            BasicLevelMap blm = new BasicLevelMap(gameData, new Point(), i);
        } catch (IOException ex) {
            Logger.getLogger(TestMapsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       */

    }
  
    public void DrawPanel(){
    
    layer = BasicLevelMap.FromFile("/assets/map.txt");
    
}
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        layer.DrawLayer(g);
  
    }

}
