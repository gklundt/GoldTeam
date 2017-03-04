/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.GameObject;
import goldteam.domain.SkyAnimationBase;
import goldteam.platforms.SkyPlatform;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Mishal
 */
public class SkyAnimation extends SkyAnimationBase{
    
    public SkyAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.setColor(Color.DARK_GRAY);
        super.loadImage(imgFilename, new AffineTransform(1, 0, 0, 0.416, 0, 0));
    
    }
    
}