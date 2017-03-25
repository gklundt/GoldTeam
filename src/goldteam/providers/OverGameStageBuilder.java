
package goldteam.providers;

import goldteam.animators.GameStageAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.GameEngine;
import goldteam.domain.GameStageBuilderBase;
import goldteam.hud.GameStageItem;
import java.awt.Point;

public class OverGameStageBuilder extends GameStageBuilderBase {

    private GameStageItem GameOverStage;

    public OverGameStageBuilder(GameEngine gameData, Point point) {
        super(gameData, point);
    }

    @Override
    protected void createObject() {
        this.gameObject = new GameStageItem(this.gameData, new Point());
        this.GameOverStage = (GameStageItem) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        GameStageAnimation gsa = new GameStageAnimation(this.gameObject, this.gameData.getVisibleDimensions(), "assets/gameover.png");
        this.GameOverStage.addAnimator(AnimationState.DEFAULT, gsa);
    }
    
}
