/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Caleb Dunham
 */
public abstract class ChargeAnimationBase extends AnimationBase{
    
    protected final AffineTransform af;
    protected GameObject gameObject;
    private final Animatable animatableGameObject;
    protected int chargeAmount;
    protected Point positionVector;

    public ChargeAnimationBase(GameObject gameObject, AffineTransform af) {
        super();
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        this.af = new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0, 0.0);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.draw(new Rectangle2D.Double(
                positionVector.x, 
                positionVector.y, 
                25/*rectwidth*/, 
                chargeAmount/*recheight*/));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
