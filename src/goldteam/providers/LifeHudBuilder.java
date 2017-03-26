package goldteam.providers;

import goldteam.animators.LifeHudAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.Depletable;
import goldteam.domain.GameEngine;
import goldteam.domain.HudBuilderBase;
import goldteam.hud.LifeHudItem;

public class LifeHudBuilder
        extends HudBuilderBase {

    private LifeHudItem hudItem;

    public LifeHudBuilder(GameEngine gameData) {
        super(gameData);

    }

    @Override
    protected void createObject() {
        this.gameObject = new LifeHudItem(gameData, point);
        this.hudItem = (LifeHudItem) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        LifeHudAnimation hha = new LifeHudAnimation(hudItem, gameData.getVisibleDimensions(), "assets/Archer_Head.png");
        hudItem.addAnimator(AnimationState.DEFAULT, hha);
    }

    @Override
    protected void setWatcher() {
        this.hudItem.setWatcher((Depletable) this.watchable);
    }

}
