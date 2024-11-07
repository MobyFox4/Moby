package main;

import entity.Entity;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import main.Gamepanel;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class Gamepanel extends JPanel implements Runnable {
   final int originalTileSize = 16; // 16x16
   final int scale = 3;
   public final int tileSize = originalTileSize * scale; // 48x48
   public final int maxScreenCol = 20;
   public final int maxScreenRow = 12;
   public final int screenWidth = tileSize * maxScreenCol;
   public final int screenHeight = tileSize * maxScreenRow;
   public final int maxWorldCol = 50;
   public final int maxWorldRow = 50;
   public final int maxMap = 10;
   public int currentMap = 0;
   int screenWidth2 = screenWidth;
   int screenHeight2 = screenHeight;
   BufferedImage tempScreen;
   Graphics2D g2;
   public boolean fullScreenOn = false;
   int FPS = 60;
   TileManager tileM = new TileManager(this);
   public KeyHandler keyH = new KeyHandler(this);
   Sound sound = new Sound();
   public CollisionChecker cChecker = new CollisionChecker(this);
   public AssetSetter aSetter = new AssetSetter(this);
   public UI ui = new UI(this);
   Config config = new Config(this);
   Thread gameThread;
   public Player player = new Player(this, this.keyH);
   public Entity[][] obj = new Entity[maxMap][20];;
   public Entity[][] npc = new Entity[maxMap][1];
   public Entity[][] ghost = new Entity[maxMap][100];
   public InteractiveTile[][] iTile = new InteractiveTile[maxMap][100];
   ArrayList<Entity> entityList = new ArrayList();;
   public int gameState;
   public final int titleState = 0;
   public final int playState = 1;
   public final int pauseState = 2;
   public final int dialogueState = 3;
   public final int inventorystate = 4;
   public final int gameOverState = 5;
   public final int passState = 6;
   public final int winState = 7;

   public Gamepanel() {
      this.setPreferredSize(new Dimension(960, 576));
      this.setBackground(Color.black);
      this.setDoubleBuffered(true);
      this.addKeyListener(this.keyH);
      this.setFocusable(true);
   }

   public void setupGame() {
      this.aSetter.setObject();
      this.aSetter.setNPC();
      this.aSetter.setGhost();
      this.aSetter.setInteractiveTile();
      this.gameState = 0;
      this.player.setDefaultValues();
      this.player.setItems();
      this.tempScreen = new BufferedImage(960, 576, 2);
      this.g2 = (Graphics2D)this.tempScreen.getGraphics();
      if (this.fullScreenOn) {
         this.setFullScreen();
      }

   }

   public void setFullScreen() {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gd = ge.getDefaultScreenDevice();
      gd.setFullScreenWindow(Main.screen);
      this.screenWidth2 = Main.screen.getWidth();
      this.screenHeight2 = Main.screen.getHeight();
   }

   public void startGameThread() {
      this.gameThread = new Thread(this);
      this.gameThread.start();
   }

   public void run() {
      double drawInterval = (double)(1000000000 / this.FPS);
      double delta = 0.0D;
      long lastTime = System.nanoTime();
      long timer = 0L;
      int drawCount = 0;

      while(this.gameThread != null) {
         long currentTime = System.nanoTime();
         delta += (double)(currentTime - lastTime) / drawInterval;
         timer += currentTime - lastTime;
         lastTime = currentTime;
         if (delta >= 1.0D) {
            this.update();
            this.drawToTempscreen();
            this.drawToScreen();
            --delta;
            ++drawCount;
         }

         if (timer >= 1000000000L) {
            System.out.println("FPS:" + drawCount);
            drawCount = 0;
            timer = 0L;
         }
      }

   }

   public void update() {
      if (gameState == playState) {
         player.update();

         int i;
         for(i = 0; i < this.npc[this.currentMap].length; ++i) {
            if (this.npc[this.currentMap][i] != null) {
               this.npc[this.currentMap][i].update();
            }
         }

         for(i = 0; i < this.ghost[this.currentMap].length; ++i) {
            if (this.ghost[this.currentMap][i] != null) {
               this.ghost[this.currentMap][i].update();
            }
         }
      }

      if (this.gameState == 2) {
      }

   }

   public void drawToTempscreen() {
      this.g2.setColor(Color.BLACK);
      this.g2.fillRect(0, 0, 960, 576);
      long drawStart = 0L;
      if (this.keyH.checkDrawTime) {
         drawStart = System.nanoTime();
      }

      if (this.gameState == 0) {
         this.ui.draw(this.g2);
      } else {
         this.tileM.draw(this.g2);

         int i;
         for(i = 0; i < this.iTile[1].length; ++i) {
            if (this.iTile[this.currentMap][i] != null) {
               this.iTile[this.currentMap][i].draw(this.g2);
            }
         }

         this.entityList.add(this.player);

         for(i = 0; i < this.npc[1].length; ++i) {
            if (this.npc[this.currentMap][i] != null) {
               this.entityList.add(this.npc[this.currentMap][i]);
            }
         }

         for(i = 0; i < this.obj[1].length; ++i) {
            if (this.obj[this.currentMap][i] != null) {
               this.entityList.add(this.obj[this.currentMap][i]);
            }
         }

         for(i = 0; i < this.ghost[1].length; ++i) {
            if (this.ghost[this.currentMap][i] != null) {
               this.entityList.add(this.ghost[this.currentMap][i]);
            }
         }

         for(i = 0; i < this.iTile[1].length; ++i) {
            if (this.iTile[this.currentMap][i] != null) {
               this.iTile[this.currentMap][i].update();
            }
         }

         //Collections.sort(this.entityList, new 1(this));

         for(i = 0; i < this.entityList.size(); ++i) {
            ((Entity)this.entityList.get(i)).draw(this.g2);
         }

         this.entityList.clear();
         this.player.draw(this.g2);
         this.ui.draw(this.g2);
      }

      if (this.keyH.checkDrawTime) {
         long drawend = System.nanoTime();
         long passed = drawend - drawStart;
         this.g2.setColor(Color.white);
         this.g2.drawString("Draw Time: " + passed, 10, 400);
         System.out.println("Draw Time: " + passed);
      }

   }

   public void drawToScreen() {
      Graphics g = this.getGraphics();
      g.drawImage(this.tempScreen, 0, 0, this.screenWidth2, this.screenHeight2, (ImageObserver)null);
      g.dispose();
   }

   public void playMusic(int i) {
      this.sound.setFile(i);
      this.sound.play();
      this.sound.loop();
   }

   public void stopMusic() {
      this.sound.stop();
   }

   public void playSE(int i) {
      this.sound.setFile(i);
      this.sound.play();
   }
}
