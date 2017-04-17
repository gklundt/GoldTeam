
package goldteam.builders;

import goldteam.animators.GameStageAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.GameEngine;
import goldteam.domain.GameStageBuilderBase;
import goldteam.hud.GameStageItem;
import java.awt.Point;

public class WinGameStageBuilder extends GameStageBuilderBase {

    private GameStageItem DeathGameStage;

    public WinGameStageBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new GameStageItem(this.gameData, new Point());
        this.DeathGameStage = (GameStageItem) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        GameStageAnimation gsa = new GameStageAnimation(this.gameObject, this.gameData.getVisibleDimensions(), "assets/levelup.png");
        this.DeathGameStage.addAnimator(AnimationState.DEFAULT, gsa);
    }
    
}
