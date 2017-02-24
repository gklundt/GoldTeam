/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

/**
 *
 * @author gordon
 */
public class BigGhostAnimation extends CharacterAnimationBase {

    /**
     * Constructor to set up the GUI components
     *
     * @param gameObject
     * @param preferredSize
     * @param assetFile
     */
    public BigGhostAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        // Setup animation
        super.loadImage(imgFilename, 2, 4, new AffineTransform(1, 0, 0, 1, 0, 0));
    }
    
    public BigGhostAnimation(GameObject gameObject, Dimension preferredSize, String assetFile, int dummy) {
        super(gameObject, preferredSize, assetFile);
        // Setup animation
        super.loadImage(imgFilename, 1, 8, new AffineTransform(1, 0, 0, 1, 0, 0));
    }
    
    public BigGhostAnimation(GameObject gameObject, Dimension preferredSize, String assetFile, int dummy, int dumber) {
        super(gameObject, preferredSize, assetFile);
        // Setup animation
        super.loadImage(imgFilename, 1, 4, new AffineTransform(1, 0, 0, 1, 0, 0));
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
