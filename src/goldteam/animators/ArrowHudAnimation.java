package goldteam.animators;

import goldteam.domain.ArrowHudAnimationBase;
import goldteam.domain.DepletableWatcher;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class ArrowHudAnimation extends ArrowHudAnimationBase {

    private final DepletableWatcher gameObj;

    public ArrowHudAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, new AffineTransform(), assetFile);
        this.gameObj = (DepletableWatcher) gameObject;
        super.loadImage(assetFile, new AffineTransform(1, 0, 0, 1, 0, 0));
    }

    @Override
    protected void update() {
        this.arrowCount = this.gameObj.getWatcher().getCount();
    }

}
