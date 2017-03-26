/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.providers;

import goldteam.characters.ArcherMan;
import goldteam.domain.CharacterBuilderBase;
import goldteam.domain.GameEngine;
import java.awt.Point;

public class BombBuilder extends CharacterBuilderBase {


    public BombBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new ArcherMan(gameData, point);
    }

    @Override
    protected void addAnimations() {

    }

}
