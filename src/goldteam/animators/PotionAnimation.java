package goldteam.animators;

import goldteam.domain.GameObject;
import goldteam.domain.PotionAnimationBase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class PotionAnimation extends PotionAnimationBase {

    public PotionAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.setColor(Color.DARK_GRAY);
        super.loadImage(imgFilename, new AffineTransform(.5, 1, 1, .5, 1, 1));
    }
}
