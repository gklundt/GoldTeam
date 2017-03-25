package goldteam.animators;

import goldteam.domain.AttackableWatcher;
import goldteam.domain.GameObject;
import goldteam.domain.HudAnimationBase;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class HeartHudAnimation extends HudAnimationBase {

    private AttackableWatcher gameObj;

    public HeartHudAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        this.gameObj = (AttackableWatcher) gameObject;
        super.loadImage(imgFilename, this.gameObj.getWatcher().getHealthValue(), new AffineTransform(.25, 0, 0, .25, 0, 0));
    }

    @Override
    protected void update() {
        this.count = this.gameObj.getWatcher().getHealthValue();
    }

}
