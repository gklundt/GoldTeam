package goldteam.animators;

import goldteam.domain.CollectableAnimationBase;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class CollectableArrowAnimation extends CollectableAnimationBase {

    public CollectableArrowAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.loadImage(imgFilename, new AffineTransform(.25, 0, 0, .25, 0, 0));
    }
}
