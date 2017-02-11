/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.Attackable;
import goldteam.domain.AttackableWatcher;
import goldteam.domain.GameObject;
import goldteam.domain.HudAnimationBase;
import java.awt.Dimension;

/**
 *
 * @author Caleb Dunham
 */
public class HeartHudAnimation extends HudAnimationBase {
    /**
     * Constructor to set up the GUI components
     *
     * @param gameObject -> HeartHudItem
     * @param preferredSize
     * @param assetFile
     * @param frameRate
     */
    
    private AttackableWatcher gameObj;
    public HeartHudAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        this.gameObj = (AttackableWatcher) gameObject;
        // Setup animation
        super.loadImage(imgFilename, this.gameObj.getWatcher().getHealthValue());
    }

    @Override
    protected void update() {
        this.count = this.gameObj.getWatcher().getHealthValue();
    }
    
}
