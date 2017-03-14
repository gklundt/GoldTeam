/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.characters;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import java.awt.Point;

/**
 *
 * @author Joshua
 */
public abstract class BaseEnemy extends GameObject implements Animatable
{
    protected AnimationBase animator;
    protected int maxSpeed;
    
    public BaseEnemy(GameEngine gamedata, Point initialPoint)
    {
        super(gamedata, initialPoint);
    }
    
    
}
