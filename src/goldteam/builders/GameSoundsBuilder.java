/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.domain.GameEngine;
import goldteam.domain.GameSounds;
import goldteam.domain.SoundBuilderBase;

/**
 *
 * @author Caleb Dunham
 */
public class GameSoundsBuilder extends SoundBuilderBase {
    
    public GameSounds gameSounds;

    public GameSoundsBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setWatcher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void play() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
