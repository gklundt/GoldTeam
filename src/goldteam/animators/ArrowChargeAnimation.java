/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.characters.ArcherMan;
import goldteam.domain.Archer;
import goldteam.domain.AttackableWatcher;
import goldteam.domain.GameObject;
import goldteam.domain.HudAnimationBase;
import goldteam.hud.ArrowChargeIndicator;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Caleb Dunham
 */
public class ArrowChargeAnimation extends HudAnimationBase {

    /**
     * Constructor to set up the GUI components
     *
     * @param gameObject -> ArrowChargeIndicator
     * @param preferredSize
     * @param assetFile
     * @param frameRate
     */

    private AttackableWatcher gameObj;
    private BufferedImage img;
    private final ArcherMan archer;

    public ArrowChargeAnimation(GameObject gameObject, ArcherMan archer, Dimension preferredSize) {
        super(gameObject, preferredSize);
        this.gameObj = (AttackableWatcher) gameObject;
        this.archer = archer;
        // Setup animation
        img = new BufferedImage(preferredSize.width/4, preferredSize.height/4, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.drawRect(100, 100, 50, 50);
        Graphics2D g2d = (Graphics2D) g; 
        g2d.draw(new Rectangle2D.Double(100, 100,
                               50,
                               50));
        super.loadImage(img, 1);
    }

    @Override
    protected void update() {
        this.count = (int) this.gameObj.getWatcher().getChargeValue();
    }

}
