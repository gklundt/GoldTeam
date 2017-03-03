
package goldteam.domain;

import java.applet.Applet;
import java.applet.AudioClip;

public class GameSounds {

    public static final GameSounds[] sounds = {
        new GameSounds("assets/Sounds/ArrowShoot.wav"),
        new GameSounds("assets/Sounds/ArrowsShooting.wav"),
        new GameSounds("assets/Sounds/ImpactMeat.wav"),
        new GameSounds("assets/Sounds/ImpactStone.wav"),
        new GameSounds("assets/Sounds/ImpactWood.wav"),
        new GameSounds("assets/Sounds/pain1.wav"),
        new GameSounds("assets/Sounds/pain2.wav"),
        new GameSounds("assets/Sounds/pain3.wav"),
        new GameSounds("assets/Sounds/pain4.wav"),
        new GameSounds("assets/Sounds/pain5.wav"),
        new GameSounds("assets/Sounds/pain6.wav"),
        new GameSounds("assets/Sounds/scream.wav"),
        new GameSounds("assets/Sounds/swosh-01.wav"),
        new GameSounds("assets/Sounds/swosh-02.wav"),
        new GameSounds("assets/Sounds/swosh-03.wav"),
        new GameSounds("assets/Sounds/swosh-04.wav"),
        new GameSounds("assets/Sounds/swosh-05.wav"),
        new GameSounds("assets/Sounds/background_music.wav")
    };
    private AudioClip clip;
    
    public GameSounds(String name){
       try
       {
          clip = Applet.newAudioClip(getClass().getClassLoader().getResource(name));
       }catch (Throwable e){
          e.printStackTrace();
       }
    }
   
    public void play(){
       try{
            new Thread(){
                  @Override
                  public void run(){
                     clip.play();
                  }
            }.start();
       }catch(Throwable e){
          e.printStackTrace();
       }
    }
}
