/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.CollectableAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

/**
 *
 * @author faaez
 */
public class CollectablePermanentWeaponBoostAnimation extends CollectableAnimationBase{
    
    public CollectablePermanentWeaponBoostAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.loadImage(imgFilename, new AffineTransform(.05, 0, 0, .05, 0, 0));
    }
}
