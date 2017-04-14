package goldteam.builders;

import goldteam.animators.ArrowChargeAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.Boostable;
import goldteam.domain.Weapon;
import goldteam.domain.GameEngine;
import goldteam.domain.HudBuilderBase;
import goldteam.hud.ArrowChargeIndicator;
import goldteam.hud.DebuffIndicator;

public class DebuffHudBuilder
        extends HudBuilderBase {

    private DebuffIndicator hudItem;

    public DebuffHudBuilder(GameEngine gameData) {
        super(gameData);

    }

    @Override
    protected void createObject() {
        this.gameObject = new DebuffIndicator(gameData, point);
        this.hudItem = (DebuffIndicator) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        ArrowChargeAnimation hha = new ArrowChargeAnimation(hudItem, gameData.getVisibleDimensions(), (Weapon) this.watchable);
        hudItem.addAnimator(AnimationState.DEFAULT, hha);
        hudItem.addAnimationTimerListener(hha);
    }

    @Override
    protected void setWatcher() {
        this.hudItem.setWatcher((Boostable) this.watchable);
    }

}
