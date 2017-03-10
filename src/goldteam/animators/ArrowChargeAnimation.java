/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.AttackableWatcher;
import goldteam.domain.GameObject;
import goldteam.domain.HudAnimationBase;
import goldteam.hud.ArrowChargeIndicator;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

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

    public ArrowChargeAnimation(GameObject gameObject, Dimension preferredSize) {
        super(gameObject, preferredSize);
        this.gameObj = (AttackableWatcher) gameObject;
        // Setup animation
        super.drawLine(this.gameObj.getWatcher().getChargeValue(), new AffineTransform(.25, 0, 0, .25, 0, 0));
    }

    @Override
    protected void update() {
        this.count = (int) this.gameObj.getWatcher().getChargeValue();
    }

}
