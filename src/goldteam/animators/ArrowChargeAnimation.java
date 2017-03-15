/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.characters.ArcherMan;
import goldteam.domain.Archer;
import goldteam.domain.AttackableWatcher;
import goldteam.domain.ChargeAnimationBase;
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
public class ArrowChargeAnimation extends ChargeAnimationBase {

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

    public ArrowChargeAnimation(GameObject gameObject) {
        super(gameObject, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0, 0.0));
        this.gameObj = (AttackableWatcher) gameObject;
        
    }

    @Override
    protected void update() {
        this.chargeAmount = (int) this.gameObj.getWatcher().getChargeValue();
    }

}
