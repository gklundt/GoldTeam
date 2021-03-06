package goldteam.animators;

import goldteam.domain.GameObject;
import goldteam.domain.SkyAnimationBase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class SkyAnimation extends SkyAnimationBase {

    public SkyAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.setColor(Color.DARK_GRAY);
        super.loadImage(imgFilename, new AffineTransform(1, 0, 0, 0.416, 0, 0));

    }

}
