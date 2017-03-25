package goldteam.animators;

import goldteam.domain.GameObject;
import goldteam.domain.PlatformAnimationBase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class LavaPlatformAnimation extends PlatformAnimationBase {

    public LavaPlatformAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.setColor(Color.ORANGE);
        super.loadImage(imgFilename, new AffineTransform(.5, 10, 10, .5, 10, 10));
    }

    @Override
    public void update() {

        for (int i = 0; i < 3; i++) {
            //super.setColor(new Color(255,rand(222,0),0));
        }
    }

    private int rand(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

}
