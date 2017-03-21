/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.ArrowHudAnimationBase;
import goldteam.domain.AttackableWatcher;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Caleb Dunham
 */
public class ArrowHudAnimation extends ArrowHudAnimationBase {

    /**
     * Constructor to set up the GUI components
     *
     * @param gameObject -> HeartHudItem
     * @param preferredSize
     * @param assetFile
     * @param frameRate
     */

    private AttackableWatcher gameObj;

    public ArrowHudAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, new AffineTransform(), assetFile);
        this.gameObj = (AttackableWatcher) gameObject;
        // Setup animation
        super.loadImage(assetFile, new AffineTransform(1, 0, 0, 1, 0, 0));
    }

    @Override
    protected void update() {
        this.arrowCount = this.gameObj.getWatcher().getArrowCount();
    }

}
