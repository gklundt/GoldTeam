/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

/**
 *
 * @author faaez
 */
public abstract class TestHudAnimationBase extends AnimationBase{
    
    protected final GameObject gameObject;
    private final Animatable animatableGameObject;
     protected int count;
    
    public TestHudAnimationBase(GameObject gameObject, Dimension preferredSize){
        super();
        super.setSize(preferredSize);
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        update();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        
        Font f = new Font("Comic Sans MS", Font.BOLD, 100);
        g2d.setFont(f);
        
        if(this.count < 5){
            Color myColour = new Color(0, 0, 0, 100);
            g2d.setColor(myColour);
            g2d.fillRect(0, 0, 10000, 10000);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Game Over", 150, 200);
        }
    }
    
}
