package goldteam.providers;

import goldteam.animators.ShieldHudAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.Attackable;
import goldteam.domain.GameEngine;
import goldteam.domain.HudBuilderBase;
import goldteam.hud.ShieldHudItem;

public class ShieldHudBuilder
        extends HudBuilderBase {

    private ShieldHudItem hudItem;

    public ShieldHudBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new ShieldHudItem(gameData, point);
        this.hudItem = (ShieldHudItem) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        ShieldHudAnimation hha = new ShieldHudAnimation(hudItem, gameData.getVisibleDimensions(), "assets/shield.png");
        hudItem.addAnimator(AnimationState.DEFAULT, hha);
    }

    @Override
    protected void setWatcher() {
        this.hudItem.setWatcher((Attackable) this.watchable);
    }

}
