package goldteam.animators;

import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class DebuffHudAnimation extends CharacterAnimationBase {

    public DebuffHudAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.loadImage(imgFilename, 1, 4, new AffineTransform(.5, 0, 0, .5, 0, 0));
    }

    @Override
    public void update() {
        ++currentFrame;    // displays next frame
        if (currentFrame >= numFrames) {
            currentFrame = 0;
        }
    }
}
