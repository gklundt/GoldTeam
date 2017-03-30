
package goldteam.animators;

import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Caleb Dunham
 */
public class ArcherAnimationHurting extends CharacterAnimationBase {
    
    public ArcherAnimationHurting(GameObject gameObject, Dimension preferredSize, String assetFile, int spriteCount) {
        super(gameObject, preferredSize, assetFile);
        super.loadImage(imgFilename, 1, spriteCount, new AffineTransform(1, 0, 0, 1, 0, 0));
    }

    @Override
    protected void update() {
        ++currentFrame;    // displays next frame
        if (currentFrame >= numFrames) {
            currentFrame = 0;
        }
    }
}
