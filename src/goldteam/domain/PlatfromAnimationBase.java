/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;

/**
 *
 * @author faaez
 */
public class PlatfromAnimationBase extends AnimationBase{

    protected final String imgFilename;
    protected final GameObject gameObject;
    private final Animatable animatableGameObject;
    private Dimension platfromDimensions;
    private Color color;
    
    public PlatfromAnimationBase(GameObject gameObject, Dimension preferredSize, String assetFile){
        super();
        super.setSize(preferredSize);
        this.imgFilename = assetFile;
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        this.color = Color.BLACK;
        //this.af = new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0, 0.0);
    }
    
    public void setDimensions(Dimension platfromDimensions){
        this.platfromDimensions = platfromDimensions;
    }
    
    public Dimension getDimensions(){
        return this.platfromDimensions;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    @Override
    protected void update() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        g2d.fillRect(gameObject.positionVector.x,
                     gameObject.positionVector.y,
                     platfromDimensions.width, 
                     platfromDimensions.height
        );
    }
}
