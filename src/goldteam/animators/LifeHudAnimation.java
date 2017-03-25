package goldteam.animators;

import goldteam.domain.DepletableWatcher;
import goldteam.domain.GameObject;
import goldteam.domain.HudAnimationBase;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class LifeHudAnimation extends HudAnimationBase {

    private final DepletableWatcher gameObj;

    public LifeHudAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        this.gameObj = (DepletableWatcher) gameObject;
        super.loadImage(imgFilename, this.gameObj.getWatcher().getCount(), new AffineTransform(1, 0, 0, 1, 0, 0));
    }

    @Override
    protected void update() {
        this.count = this.gameObj.getWatcher().getCount();
    }

}
