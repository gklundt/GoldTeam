<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
=======
>>>>>>> dev
package goldteam.animators;

import goldteam.domain.CollectableAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

<<<<<<< HEAD
/**
 *
 * @author faaez
 */
public class CollectableArrowAnimation extends CollectableAnimationBase{
    
=======
public class CollectableArrowAnimation extends CollectableAnimationBase {

>>>>>>> dev
    public CollectableArrowAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.loadImage(imgFilename, new AffineTransform(.25, 0, 0, .25, 0, 0));
    }
}
