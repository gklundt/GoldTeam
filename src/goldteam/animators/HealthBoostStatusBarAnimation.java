/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.BoostStatusBarAnimationBase;
import goldteam.domain.BoostableWatcher;
import goldteam.domain.Delta;
import goldteam.domain.GameObject;
import goldteam.domain.ModType;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

/**
 *
 * @author faaez
 */
public class HealthBoostStatusBarAnimation extends BoostStatusBarAnimationBase {

    private BoostableWatcher gameObj;

    public HealthBoostStatusBarAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.loadImage(assetFile, new AffineTransform(.25, 0, 0, .25, 0, 0));
        this.gameObj = (BoostableWatcher) gameObject;
    }

    @Override
    protected void update() {

        try {
            if (this.gameObj.getWatcher().isBoostableHealth()) {
                super.draw = true;
                super.setProgress(Delta.create(-1.0, ModType.FIXED));
            }

            if (super.getProgress() <= 0) {
                super.draw = false;
                this.gameObj.getWatcher().setBoostableHealth(false);
                super.resetProgress();
            }
        } catch (Exception e) {
        }
    }

}
