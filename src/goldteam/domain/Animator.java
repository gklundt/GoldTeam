/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Graphics;

/**
 *
 * @author gordon
 */
public interface Animator {
    public void PaintNextImage(AnimationState state, Graphics graphics);
}
