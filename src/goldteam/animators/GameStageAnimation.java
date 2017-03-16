/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.DepletableWatcher;
import goldteam.domain.GameObject;
import goldteam.domain.GameStageAnimationBase;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Caleb Dunham
 */
public class GameStageAnimation extends GameStageAnimationBase {

    /**
     * Constructor to set up the GUI components
     *
     * @param gameObject -> HeartHudItem
     * @param preferredSize
     * @param assetFile
     * @param frameRate
     */
    private DepletableWatcher gameObj;
    private float s;

    public GameStageAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        this.gameObj = (DepletableWatcher) gameObject;
        // Setup animation
        super.loadImage(imgFilename, new AffineTransform(1, 0, 0, 1, 0, 0));
        this.s = 0.01f;

    }

    @Override
    protected void update() {
        if (this.alpha + this.s >= 0.9) {
            this.alpha = 1.0f;
            return;
        }
        this.alpha += this.s;
        this.s += .05f;
    }

    @Override
    public void resetAnimation() {
        this.alpha = 0.0f;
    }

}
