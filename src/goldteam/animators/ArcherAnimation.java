/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.characters.ArcherMan;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Caleb Dunham
 */
@SuppressWarnings("serial")
public class ArcherAnimation extends CharacterAnimationBase {

    private final ArcherMan archer;
    
    public ArcherAnimation(GameObject gameObject, Dimension preferredSize, String assetFile, int spriteCount) {
        super(gameObject, preferredSize, assetFile);
        this.archer = (ArcherMan) gameObject;
        // Setup animation
        super.loadImage(imgFilename, 1, spriteCount, new AffineTransform(1, 0, 0, 1, 0, 0));
    }

    @Override
    protected void update() {
            
        //When the archer dies, the animator stops on the last frame.
        if((archer.animator==archer.animators.get(AnimationState.DYING_RIGHT) 
                || archer.animator==archer.animators.get(AnimationState.DYING_LEFT)) && currentFrame==numFrames-1)
            return;
        
        //When the archer draws an arrow, the animator animates until the second to last frame.
        //When the mouse button is released, pauseAnimation is set to false and the animator continues to animate.
        if(archer.pauseAnimation && currentFrame == numFrames-2)
            return;
        
        //Otherwise just update animation by incrementing currentFrame.
        if (++currentFrame >= numFrames)
            currentFrame = 0;
        
    }
}
