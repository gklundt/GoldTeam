package goldteam.providers;

import goldteam.animators.ArrowChargeAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.Weapon;
import goldteam.domain.GameEngine;
import goldteam.domain.HudBuilderBase;
import goldteam.hud.ArrowChargeIndicator;
import java.awt.Point;

public class ArrowChargeHudBuilder
        extends HudBuilderBase {

    private Weapon watcher;
    private ArrowChargeIndicator hudItem;

//        chargeBar.addAnimationTimerListener(aca);        
    public ArrowChargeHudBuilder(GameEngine gameData, Weapon weapon) {
        super(gameData);
        this.watcher = weapon;

    }

    @Override
    protected void createObject() {
        this.gameObject = new ArrowChargeIndicator(gameData, point);
        this.hudItem = (ArrowChargeIndicator) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        ArrowChargeAnimation hha = new ArrowChargeAnimation(hudItem, gameData.getVisibleDimensions(), this.watcher);
        hudItem.addAnimator(AnimationState.DEFAULT, hha);
        hudItem.addAnimationTimerListener(hha);
    }

    @Override
    protected void setWatcher() {
        this.hudItem.setWatcher(watcher);
    }

}
