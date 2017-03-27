package goldteam.builders;

import goldteam.animators.GhostAnimation;
import goldteam.characters.StationaryGhost;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;

public class StationaryGhostBuilder extends CharacterBuilderBase {

    private StationaryGhost enemy;

    public StationaryGhostBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new StationaryGhost(gameData, point);
        this.enemy = (StationaryGhost) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        GhostAnimation ghostAnimation = new GhostAnimation(enemy, this.gameData.getMapDimensions(), "assets/GameGhostStripe.png");
        enemy.addAnimator(AnimationState.DEFAULT,ghostAnimation);
    }

}
