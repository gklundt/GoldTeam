package goldteam.animators;

import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class BigGhostAnimation extends CharacterAnimationBase {

    public BigGhostAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.loadImage(imgFilename, 2, 4, new AffineTransform(1, 0, 0, 1, 0, 0));
    }

    @Override
    public void update() {
        ++currentFrame;    // displays next frame
        if (currentFrame >= numFrames) {
            currentFrame = 0;
        }
    }
}
