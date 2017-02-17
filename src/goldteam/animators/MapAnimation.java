/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.AnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;

/**
 *
 * @author Mishal
 */
public class MapAnimation extends AnimationBase {

    public MapAnimation(GameObject gameObject, Dimension preferredSize, String assetFile, int frameRate) {
        super(gameObject, preferredSize, assetFile, frameRate);
        
        super.loadImage(imgFilename, 2, 4);
    }

    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
