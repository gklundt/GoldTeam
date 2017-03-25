package goldteam.providers;

import goldteam.animators.HeartHudAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.Attackable;
import goldteam.domain.GameEngine;
import goldteam.domain.HudBuilderBase;
import goldteam.hud.HeartHudItem;
import java.awt.Point;


public class HeartHudBuilder
        extends HudBuilderBase {

    private Attackable watcher;
    private HeartHudItem hudItem;

    
    public HeartHudBuilder(GameEngine gameData, Point point, Attackable attackable) {
        super(gameData, point);
        this.watcher = attackable;
    }

    @Override
    protected void createObject() {
        this.gameObject = new HeartHudItem(gameData, point);
        this.hudItem = (HeartHudItem) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        HeartHudAnimation hha = new HeartHudAnimation(hudItem, gameData.getVisibleDimensions(), "assets/heart.png");
        hudItem.addAnimator(AnimationState.DEFAULT, hha);
    }

    @Override
    protected void setWatcher() {
        this.hudItem.setWatcher(watcher);
    }

}
