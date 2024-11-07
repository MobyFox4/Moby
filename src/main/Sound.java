package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;

public class Sound {
   Clip clip;
   URL[] soundURL = new URL[30];
   FloatControl fc;
   int volumeScale = 3;
   float volume;

   public Sound() {
      this.soundURL[0] = this.getClass().getResource("/res/Sound/GhostAttack.wav");
      this.soundURL[1] = this.getClass().getResource("/res/Sound/Pickup.wav");
      this.soundURL[2] = this.getClass().getResource("/res/Sound/PlayerAttack.wav");
      this.soundURL[3] = this.getClass().getResource("/res/Sound/UnlockDoor.wav");
      this.soundURL[4] = this.getClass().getResource("/res/Sound/press.wav");
      this.soundURL[5] = this.getClass().getResource("/res/Sound/hit.wav");
      this.soundURL[6] = this.getClass().getResource("/res/Sound/drink.wav");
      this.soundURL[7] = this.getClass().getResource("/res/Sound/ready.wav");
      this.soundURL[8] = this.getClass().getResource("/res/Sound/select.wav");
      this.soundURL[9] = this.getClass().getResource("/res/Sound/Hit_wood.wav");
      this.soundURL[10] = this.getClass().getResource("/res/Sound/gameover.wav");
      this.soundURL[11] = this.getClass().getResource("/res/Sound/grow.wav");
      this.soundURL[12] = this.getClass().getResource("/res/Sound/footstep1.wav");
      this.soundURL[13] = this.getClass().getResource("/res/Sound/footstep2.wav");
   }

   public void setFile(int i) {
      try {
         AudioInputStream ais = AudioSystem.getAudioInputStream(this.soundURL[i]);
         clip = AudioSystem.getClip();
         clip.open(ais);
         fc = (FloatControl)this.clip.getControl(Type.MASTER_GAIN);
         this.checkVolume();
      } catch (Exception e) {
          e.printStackTrace();
          clip = null;
      }

   }

   public void play() {
      this.clip.start();
   }

   public void loop() {
      this.clip.loop(-1);
   }

   public void stop() {
      this.clip.stop();
   }

   public void checkVolume() {
      switch(this.volumeScale) {
      case 0:
         this.volume = -80.0F;
         break;
      case 1:
         this.volume = -20.0F;
         break;
      case 2:
         this.volume = -12.0F;
         break;
      case 3:
         this.volume = -5.0F;
         break;
      case 4:
         this.volume = 1.0F;
         break;
      case 5:
         this.volume = 6.0F;
      }

      this.fc.setValue(this.volume);
   }
}
