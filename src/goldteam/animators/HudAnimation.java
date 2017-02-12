/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;

/**
 *
 * @author Caleb Dunham
 */
public class HudAnimation extends CharacterAnimationBase {
    /**
     * Constructor to set up the GUI components
     *
     * @param gameObject
     * @param preferredSize
     * @param assetFile
     * @param frameRate
     */
    public HudAnimation(GameObject gameObject, Dimension preferredSize, String assetFile, int frameRate) {
        super(gameObject, preferredSize, assetFile, frameRate);
        // Setup animation
        super.loadImage(imgFilename, 2, 4);
    }

    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
