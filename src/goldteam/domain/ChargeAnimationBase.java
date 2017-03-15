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
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Caleb Dunham
 */
public abstract class ChargeAnimationBase extends AnimationBase {

    protected final AffineTransform af;
    protected GameObject gameObject;
    private final Animatable animatableGameObject;
    protected int chargeAmount;
    protected Point positionVector;

    public ChargeAnimationBase(GameObject gameObject, Dimension preferredSize, AffineTransform af) {
        super();
        super.setSize(preferredSize);
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        this.af = new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0, 0.0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (positionVector == null) {
            return;
        }

        int x = positionVector.x;
        int y = positionVector.y;
        int width = 25;
        int height = chargeAmount;

        g2d.drawRect(x, y, width, height);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
