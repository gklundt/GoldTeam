/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Caleb Dunham
 */
public abstract class ChargeAnimationBase extends AnimationBase {

    protected AffineTransform af;
    protected GameObject gameObject;
    private final Animatable animatableGameObject;
    protected double chargeAmount;
    protected Point archerPosition;
    private final double growthFactor;
    private GradientPaint chargePaint;
    private int xPoints[];
    private int yPoints[];
    private Point mousePosition;
    
    public ChargeAnimationBase(GameObject gameObject, Dimension preferredSize, AffineTransform af) {
        super();
        super.setSize(preferredSize);
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        this.af = new AffineTransform();
        this.growthFactor = Math.tan(45.0);
        xPoints = new int[3];
        yPoints = new int[3];
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (archerPosition == null) {
            return;
        }
        /*
            ~Charge Indicator Layout~
               p1(xPoints[0],yPoints[0])-> 0
                                          / \
                                         /   \
            p2(xPoints[1],yPoints[1])-> 0 --- 0 <-p3(xPoints[2],yPoints[2])
        
            Ideally, this triangle will update to always "point" at the mouse cursor. 
        */
        xPoints[0] = archerPosition.x;
        yPoints[0] = archerPosition.y - 25;
        xPoints[1] = (int) (xPoints[0] - chargeAmount*growthFactor); 
        yPoints[1] = (int) (yPoints[0] + chargeAmount*3);
        xPoints[2] = (int) (xPoints[0] + chargeAmount*growthFactor);
        yPoints[2] = (int) (yPoints[0] + chargeAmount*3);
        
        this.chargePaint = new GradientPaint(xPoints[0], yPoints[0], Color.RED,             //From p1...
                              (xPoints[1]+xPoints[2])/2, yPoints[1], Color.YELLOW, true);   //...to p2
        g2d.setPaint(chargePaint);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
