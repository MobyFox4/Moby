package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
   Gamepanel gp;

   public Config(Gamepanel gp) {
      this.gp = gp;
   }

   public void saveConfig() throws IOException {
      try {
         BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
         if (gp.fullScreenOn) {
            bw.write("On");
         }

         if (!gp.fullScreenOn) {
            bw.write("Off");
         }

         bw.newLine();
         bw.write(String.valueOf(gp.sound.volumeScale));
         bw.newLine();
         bw.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public void loadConfig() {
      try {
         BufferedReader br = new BufferedReader(new FileReader("config.txt"));
         String s = br.readLine();
         if (s.equals("On")) {
            gp.fullScreenOn = true;
         }

         if (s.equals("Off")) {
            gp.fullScreenOn = false;
         }

         s = br.readLine();
         gp.sound.volumeScale = Integer.parseInt(s);
         br.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
