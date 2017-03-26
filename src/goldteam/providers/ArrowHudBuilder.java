package goldteam.providers;

import goldteam.animators.ArrowHudAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.Depletable;
import goldteam.domain.GameEngine;
import goldteam.domain.HudBuilderBase;
import goldteam.hud.ArrowHudItem;

public class ArrowHudBuilder
        extends HudBuilderBase {

    private ArrowHudItem hudItem;

    public ArrowHudBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new ArrowHudItem(gameData, point);
        this.hudItem = (ArrowHudItem) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        ArrowHudAnimation hha = new ArrowHudAnimation(hudItem, gameData.getVisibleDimensions(), "assets/Arrow_HUD_Item.png");
        hudItem.addAnimator(AnimationState.DEFAULT, hha);
    }

    @Override
    protected void setWatcher() {
        this.hudItem.setWatcher((Depletable) this.watchable);
    }

}
