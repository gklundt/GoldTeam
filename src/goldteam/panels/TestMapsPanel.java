
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;




import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.gamedata.GameData;
import goldteam.maps.BasicLevelMap;
import goldteam.GoldTeam;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Mishal
 */
public class TestMapsPanel extends ManagedPanel implements KeyListener, MouseListener{
 
     private BasicLevelMap layer;
     private JLayeredPane lp;
     private Component gp;
     private Canvas canvas;
     
     
     
     
   
     
    public TestMapsPanel(PanelManager panelManager) {     
        super(panelManager);
        super.addAncestorListener(new AncestorListenerImpl());
         //layer = TestMapsPanel.FromFile("map.txt");
    }
    
    
    private void updateListeners() {
         JRootPane jrp = getRootPane();
         JLayeredPane lp = new JLayeredPane();

         GameData gd = new GameData();
         gd.addGraphicsUpdateTimerListener(l -> lp.repaint());
    
         canvas = new Canvas();
         canvas.setPreferredSize(new Dimension(800,600));
         canvas.setMaximumSize(new Dimension(800,600));
         canvas.setMinimumSize(new Dimension(800,600));
         
   
         
         
        
        
    }

    private void undoGraphics() {
        gp.removeKeyListener(this);
        gp.removeMouseListener(this);
        gp.setVisible(true);
      
        
        validate();
        
    }
   
    
   @Override
   public void paintComponent(Graphics g) {
    super.paintComponent(g);
        //layer.LoadTileSheet("assets/block_brick.png");
//      layer.PositionVector();
        //layer.DrawLayer(g);
        //g.fillRect(0, 0, 800, 600);
        g.setColor(Color.red);
        g.drawString("Game Start!", 50, 60);
        g.drawRect(10, 10, 150, 200);
        g.drawRect(30, 30, 120, 230);
        //g.clearRect(0, 0, 800, 600);
//      layer.setVelocityScalarDelta(Delta.create(-15.0d, ModType.FIXED));
      
     
   }

   

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
          if (e.getKeyChar() == KeyEvent.VK_RIGHT) {
            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
            undoGraphics();
        }if (e.getKeyChar() == KeyEvent.VK_LEFT) {
           
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       if (e.getKeyChar() == KeyEvent.VK_RIGHT){
            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
            undoGraphics();
        } if (e.getKeyChar() == KeyEvent.VK_LEFT){
            
        }
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
        private class AncestorListenerImpl implements AncestorListener {

        public AncestorListenerImpl() {
        }

        @Override
        public void ancestorAdded(AncestorEvent event) {
            updateListeners();
        }

        @Override
        public void ancestorRemoved(AncestorEvent event) {
            //updateListeners();
        }

        @Override
        public void ancestorMoved(AncestorEvent event) {
            //updateListeners();
        }
    }
}
