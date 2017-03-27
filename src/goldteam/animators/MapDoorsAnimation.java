package goldteam.animators;

import goldteam.domain.GameObject;
import goldteam.domain.MapDoorsAnimationBase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class MapDoorsAnimation extends MapDoorsAnimationBase {

    public MapDoorsAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.setColor(Color.DARK_GRAY);
        super.loadImage(imgFilename, new AffineTransform(1, 0, 0, 1, 0, 0));
    }
}
