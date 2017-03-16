/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.GameObject;
import goldteam.domain.PlatfromAnimationBase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

/**
 *
 * @author faaez
 */
public class FlatPlatformAnimation extends PlatfromAnimationBase{
    
    public FlatPlatformAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.setColor(Color.DARK_GRAY);
        super.loadImage(imgFilename, new AffineTransform(.5, 0, 0, .5, 0, 0));
    }
    
    
}
