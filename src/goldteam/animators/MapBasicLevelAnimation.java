<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
=======
>>>>>>> dev
package goldteam.animators;

import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

<<<<<<< HEAD
/**
 *
 * @author Mishal
 */
public class MapBasicLevelAnimation extends CharacterAnimationBase{
=======
public class MapBasicLevelAnimation extends CharacterAnimationBase {
>>>>>>> dev

    public MapBasicLevelAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.loadImage(imgFilename, 2, 4, new AffineTransform(1, 0, 0, 1, 0, 0));
    }

    @Override
    protected void update() {
<<<<<<< HEAD

                 ++currentFrame;    // displays next frame
               if (currentFrame >= numFrames) {
                   currentFrame = 0;
        }    }
=======
        ++currentFrame;    // displays next frame
        if (currentFrame >= numFrames) {
            currentFrame = 0;
        }
    }
>>>>>>> dev

    public void setAnimator(CharacterAnimationBase ga1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
<<<<<<< HEAD
    
=======
>>>>>>> dev
}
