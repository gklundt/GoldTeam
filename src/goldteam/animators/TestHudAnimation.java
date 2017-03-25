package goldteam.animators;

import goldteam.domain.AttackableWatcher;
import goldteam.domain.GameObject;
import goldteam.domain.TestHudAnimationBase;
import java.awt.Dimension;

public class TestHudAnimation extends TestHudAnimationBase {

    private final AttackableWatcher gameObj;

    public TestHudAnimation(GameObject gameObject, Dimension preferredSize) {
        super(gameObject, preferredSize);
        this.gameObj = (AttackableWatcher) gameObject;
    }

    @Override
    protected void update() {
        this.count = this.gameObj.getWatcher().getHealthValue();
    }

}
