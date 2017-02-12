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
 * @author gordon
 */
public class GhostAnimation extends CharacterAnimationBase {

    /**
     * Constructor to set up the GUI components
     *
     * @param gameObject
     * @param preferredSize
     * @param assetFile
     * @param frameRate
     */
    public GhostAnimation(GameObject gameObject, Dimension preferredSize, String assetFile, int frameRate) {
        super(gameObject, preferredSize, assetFile, frameRate);
        // Setup animation
        super.loadImage(imgFilename, 2, 4);
    }

    /**
     * Update the position based on update routine in GameData
     */
    @Override
    public void update() {

        ++currentFrame;    // displays next frame
        if (currentFrame >= numFrames) {
            currentFrame = 0;
        }
    }

}
