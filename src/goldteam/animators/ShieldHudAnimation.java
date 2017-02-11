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
public class ShieldHudAnimation extends HudAnimationBase {
     /**
     * Constructor to set up the GUI components
     *
     * @param gameObject
     * @param preferredSize
     * @param assetFile
     */
    private AttackableWatcher gameObj;
    
    public ShieldHudAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        this.gameObj = (AttackableWatcher) gameObject;
        // Setup animation
        super.loadImage(imgFilename, this.gameObj.getWatcher().getShieldValue());
    }

    @Override
    protected void update() {
        this.count = this.gameObj.getWatcher().getShieldValue();
    }
}
