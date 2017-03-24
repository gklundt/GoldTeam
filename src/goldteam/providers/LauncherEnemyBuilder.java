/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.providers;

import goldteam.animators.GhostAnimation;
import goldteam.characters.Launcher;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class LauncherEnemyBuilder extends CharacterBuilderBase {

    private final GameEngine gameData;
    private Launcher enemy;
    private final Point point;
    private final Boolean left;

    public LauncherEnemyBuilder(GameEngine gameData, Point point, Boolean left) {
        this.gameData = gameData;
        this.point = point;
        this.left = left;
    }

    @Override
    protected void createObject() {
        this.gameObject = new Launcher(gameData, point, left);
        this.enemy = (Launcher) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        GhostAnimation ghostAnimation = new GhostAnimation(enemy, this.gameData.getRunEdgeDimensions(), "assets/GameGhostStripe.png");
        enemy.setAnimator(ghostAnimation);
    }

}
