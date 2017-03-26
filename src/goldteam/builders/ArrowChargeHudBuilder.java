package goldteam.builders;

import goldteam.animators.ArrowChargeAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.Weapon;
import goldteam.domain.GameEngine;
import goldteam.domain.HudBuilderBase;
import goldteam.hud.ArrowChargeIndicator;

public class ArrowChargeHudBuilder
        extends HudBuilderBase {

    private ArrowChargeIndicator hudItem;

    public ArrowChargeHudBuilder(GameEngine gameData) {
        super(gameData);

    }

    @Override
    protected void createObject() {
        this.gameObject = new ArrowChargeIndicator(gameData, point);
        this.hudItem = (ArrowChargeIndicator) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        ArrowChargeAnimation hha = new ArrowChargeAnimation(hudItem, gameData.getVisibleDimensions(), (Weapon) this.watchable);
        hudItem.addAnimator(AnimationState.DEFAULT, hha);
        hudItem.addAnimationTimerListener(hha);
    }

    @Override
    protected void setWatcher() {
        this.hudItem.setWatcher((Weapon) this.watchable);
    }

}
