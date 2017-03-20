/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import goldteam.animators.GameStageAnimation;
import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameObject;
import goldteam.domain.GameObjectBuilderBase;
import goldteam.domain.GamePanelBase;
import goldteam.domain.GameStageAnimationBase;
import goldteam.domain.PanelManager;
import goldteam.domain.ResettableAnimation;
import goldteam.gamedata.GameData;
import goldteam.providers.ArcherBuilder;
import goldteam.providers.FlyerEnemyBuilder;
import goldteam.providers.GameObjectProvider;
import goldteam.providers.LauncherEnemyBuilder;
import goldteam.providers.WalkerEnemyBuilder;
import java.awt.Point;

/**
 *
 * @author fchishti
 */
public class GameLevelTestPanel extends GamePanelBase {

    /**
     * Create a new panel manager
     *
     * @param panelManager
     */
    public GameLevelTestPanel(PanelManager panelManager) {
        super(panelManager, new GameData());

    }

    /**
     * Create a game object Create an animator for the game object Add the
     * animator to the this.layeredPane
     *
     */
    @Override
    protected void addGameObjects() {

        GameObjectProvider provider = new GameObjectProvider();
        GameObjectBuilderBase builder;

        builder = new ArcherBuilder(this.gameData, new Point(400, 400));
        this.addGameObject(provider.build(builder));

        builder = new FlyerEnemyBuilder(gameData, new Point(400, 100));
        this.addGameObject(provider.build(builder));

        builder = new WalkerEnemyBuilder(gameData, new Point(200, 400));
        this.addGameObject(provider.build(builder));

        builder = new LauncherEnemyBuilder(gameData, new Point(750, 350), true);
        this.addGameObject(provider.build(builder));
    }

    /**
     * Add events Base class implements AncestorListener, KeyListener,
     * MouseListener So you can override any key or mouse event for test
     * purposes By default Escape takes you back to the game options panel
     *
     */
    /* Candidates to add to panel base class */
    private void switchAnimation(Animatable anim) {

        if (anim.getRemoveAnimator() != null) {
            this.layeredPane.remove(anim.getAnimator());
        }

        if (anim.getAnimator() != null) {
            AnimationBase a = anim.getAnimator();

            if (a instanceof ResettableAnimation) {
                ResettableAnimation gsa = (ResettableAnimation) a;
                gsa.resetAnimation();
            }

            this.layeredPane.add(anim.getAnimator(), layeredPane.highestLayer());
        }
    }

    private void addGameObject(GameObject gameObject) {

        if (gameObject instanceof Animatable) {
            Animatable animatable = (Animatable) gameObject;
            AnimationBase animationBase = animatable.getAnimator();
            this.layeredPane.add(animationBase, layeredPane.highestLayer());
            animatable.addAnimationChangeListener(l -> switchAnimation(animatable));
        }
    }

}
