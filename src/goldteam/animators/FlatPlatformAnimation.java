package goldteam.animators;

import goldteam.domain.GameObject;
import goldteam.domain.PlatformAnimationBase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class FlatPlatformAnimation extends PlatformAnimationBase {

    public FlatPlatformAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.setColor(Color.DARK_GRAY);
        super.loadImage(imgFilename, new AffineTransform(.5, 0, 0, .5, 0, 0));
    }

}
