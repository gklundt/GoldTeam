package goldteam.animators;

import goldteam.domain.DepletableWatcher;
import goldteam.domain.GameObject;
import goldteam.domain.GameStageAnimationBase;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class GameStageAnimation extends GameStageAnimationBase {

    private DepletableWatcher gameObj;
    private float increment;

    public GameStageAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        this.gameObj = (DepletableWatcher) gameObject;

        super.loadImage(imgFilename, new AffineTransform(1, 0, 0, 1, 0, 0));
        this.increment = 0.01f;
    }

    @Override
    protected void update() {
        if (this.alpha + this.increment >= 0.9) {
            this.alpha = 1.0f;
            return;
        }
        this.alpha += this.increment;
        this.increment += .05f;
    }

    @Override
    public void resetAnimation() {
        this.alpha = 1.0f;
    }

}
