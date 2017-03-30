package goldteam.animators;

import goldteam.characters.ArcherMan;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class ArcherAnimationDrawing extends CharacterAnimationBase {

    private final ArcherMan archer;
    
    public ArcherAnimationDrawing(GameObject gameObject, Dimension preferredSize, String assetFile, int spriteCount) {
        super(gameObject, preferredSize, assetFile);
        this.archer = (ArcherMan) gameObject;
        super.loadImage(imgFilename, 1, spriteCount, new AffineTransform(1, 0, 0, 1, 0, 0));
    }

    @Override
    protected void update() {
        
        //When the archer draws an arrow, the animator animates until the second to last frame.
        //When the mouse button is released, pauseAnimation is set to false and the animator continues to animate.
        if(this.archer.mousePressed() && currentFrame == numFrames-2)
            return;
        
        ++currentFrame;    // displays next frame
        if (currentFrame >= numFrames) {
            currentFrame = 0;
        }
    }
}
