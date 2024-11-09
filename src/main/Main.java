package main;

import javax.swing.JFrame;

public class Main {
   public static JFrame screen;

   public static void main(String[] args) {
      screen = new JFrame();
      screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      screen.setResizable(false);
      screen.setTitle("6th Floor");
      Gamepanel gamePanel = new Gamepanel();
      screen.add(gamePanel);
      
      if (gamePanel.fullScreenOn) {
         screen.setUndecorated(true);
      }

      screen.pack();
      screen.setLocationRelativeTo(null);
      screen.setVisible(true);
      gamePanel.setupGame();
      gamePanel.startGameThread();
   }
}
