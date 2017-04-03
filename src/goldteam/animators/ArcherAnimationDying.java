package goldteam.animators;

import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class ArcherAnimationDying extends CharacterAnimationBase {

    public ArcherAnimationDying(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.loadImage(imgFilename, 1, 9, new AffineTransform(1, 0, 0, 1, 0, 0));
    }

    @Override
    protected void update() {
        ++currentFrame;    // displays next frame
        if (currentFrame >= numFrames) {
            currentFrame = 0;
        }
    }
}
