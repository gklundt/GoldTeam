
package goldteam.domain;

import java.applet.Applet;
import java.applet.AudioClip;

public class GameSounds {

    public static final GameSounds[] sounds = {
        new GameSounds("assets/Sounds/ArrowShoot.wav"),//--------[0]
        new GameSounds("assets/Sounds/ArrowsShooting.wav"),    //[1]
        new GameSounds("assets/Sounds/ImpactMeat.wav"),//--------[2]
        new GameSounds("assets/Sounds/ImpactStone.wav"),       //[3]
        new GameSounds("assets/Sounds/ImpactWood.wav"),//--------[4]
        new GameSounds("assets/Sounds/pain1.wav"),             //[5]
        new GameSounds("assets/Sounds/pain2.wav"),//-------------[6]
        new GameSounds("assets/Sounds/pain3.wav"),             //[7]
        new GameSounds("assets/Sounds/pain4.wav"),//-------------[8]
        new GameSounds("assets/Sounds/pain5.wav"),             //[9]
        new GameSounds("assets/Sounds/pain6.wav"),//-------------[10]
        new GameSounds("assets/Sounds/scream.wav"),            //[11]
        new GameSounds("assets/Sounds/swosh-01.wav"),//----------[12]
        new GameSounds("assets/Sounds/swosh-02.wav"),          //[13]
        new GameSounds("assets/Sounds/swosh-03.wav"),//----------[14]
        new GameSounds("assets/Sounds/swosh-04.wav"),          //[15]
        new GameSounds("assets/Sounds/swosh-05.wav"),//----------[16]
        new GameSounds("assets/Sounds/background_music.wav")   //[17]
    };
    
    private AudioClip clip;
    private Thread soundThread;
    
    public GameSounds(){}
    
    public GameSounds(String name){
       try
       {
          clip = Applet.newAudioClip(getClass().getClassLoader().getResource(name));
       }catch (Throwable e){
          e.printStackTrace();
       }
    }
   
    public void play(){
        clip.play();
//       try{
//            soundThread = new Thread(){
//                  @Override
//                  public void run(){
//                     clip.play();
//                  }
//            };
//            soundThread.start();
//       }catch(Throwable e){
//          e.printStackTrace();
//       }
    }
    
    public void stop(){
        clip.stop();
    }
}
