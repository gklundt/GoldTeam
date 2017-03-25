
package goldteam.providers;

import goldteam.animators.GameStageAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.GameEngine;
import goldteam.domain.GameStageBuilderBase;
import goldteam.hud.GameStageItem;
import java.awt.Point;

public class DeathGameStageBuilder extends GameStageBuilderBase {

    private GameStageItem DeathGameStage;

    public DeathGameStageBuilder(GameEngine gameData, Point point) {
        super(gameData, point);
    }

    @Override
    protected void createObject() {
        this.gameObject = new GameStageItem(this.gameData, new Point());
        this.DeathGameStage = (GameStageItem) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        GameStageAnimation gsa = new GameStageAnimation(this.gameObject, this.gameData.getVisibleDimensions(), "assets/death.png");
        this.DeathGameStage.addAnimator(AnimationState.DEFAULT, gsa);
    }
    
}
