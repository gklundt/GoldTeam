/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.providers;

import goldteam.animators.GhostAnimation;
import goldteam.characters.Walker;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class WalkerEnemyBuilder extends CharacterBuilderBase {

    private final GameEngine gameData;
    private Walker enemy;
    private final Point point;

    public WalkerEnemyBuilder(GameEngine gameData, Point point) {
        this.gameData = gameData;
        this.point = point;
    }

    @Override
    protected void createObject() {
        this.gameObject = new Walker(gameData, point);
        this.enemy = (Walker) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        GhostAnimation ghostAnimation = new GhostAnimation(enemy, this.gameData.getRunEdgeDimensions(), "assets/GameGhostStripe.png");
        enemy.setAnimator(ghostAnimation);
    }

}
