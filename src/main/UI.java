package main;

import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class UI {
   Gamepanel gp;
   Graphics2D g2;
   Font arial_40;
   Font arial_80B;
   Font Itim_25;
   Font Itim_40;
   public boolean messageOn = false;
   public String message = "";
   int messageCounter = 0;
   public boolean gameFinished = false;
   public String currentDialogue = "";
   public int commandNum = 0;
   String potionStatus = "";
   public int slotCol = 0;
   public int slotRow = 0;
   int subState = 0;
   public double time;
   DecimalFormat dFormat = new DecimalFormat("#0");
   BufferedImage bgimg;
   BufferedImage potionImage;

   public UI(Gamepanel gp) {
      this.gp = gp;
      time = 900;
      arial_40 = new Font("Arial", 0, 40);
      arial_80B = new Font("Arial", 1, 80);
      Itim_25 = new Font("Tahoma", 0, 25);
      Itim_40 = new Font("Tahoma", 0, 40);

      try {
         bgimg = ImageIO.read(getClass().getResourceAsStream("/res/background/bed.png"));
         potionImage = ImageIO.read(getClass().getResourceAsStream("/res/object/Healthpotion.png"));
      } catch (IOException e) {
            e.printStackTrace();
      }

   }

   public void showMessage(String text) {
      message = text;
      messageOn = true;
   }

   public void draw(Graphics2D g2) {
      this.g2 = g2;
      g2.setFont(arial_40);
      g2.setColor(Color.white);
      int var10000;
      if (gameFinished) {
         String text = "You Exit!";
         int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
         int x = 960 / 2 - textLength / 2;
         var10000 = 576 / 2;
         int y = var10000 - 48 * 1;
         g2.drawString(text, x, y);
         g2.setFont(arial_80B);
         g2.setColor(Color.white);
         text = "Congratulations!";
         textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
         x = gp.screenWidth / 2 - textLength / 2;
         var10000 = 576 / 2;
         y = var10000 + 48 * 2;
         g2.drawString(text, x, y);
         gp.gameThread = null;
      } else {
         
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

         if (gp.gameState == gp.playState) {
            drawHPBar();
            g2.setFont(Itim_40);
            g2.setColor(Color.white);
            time -= 0.016666666666666666D;
            String text = "Time:" + dFormat.format(Math.floor(time / 60.0D)) + ":" + dFormat.format(time % 60.0D);
            
            g2.drawString(text, gp.tileSize * 16 - 15, 35);
            drawHealthPotionStatus(g2);
            if (messageOn) {
               g2.setFont(g2.getFont().deriveFont(20.0F));
               text = message;
               
               int textX = gp.tileSize / 2;
               
               g2.drawString(text, textX, gp.tileSize * 7);
               ++messageCounter;
               if (messageCounter > 180) {
                  messageCounter = 0;
                  messageOn = false;
               }
            }
         }
         
         if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
         }
         
         if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
         }
         
         if (gp.gameState == gp.inventorystate) {
            drawCharacterScreen();
            drawInventory();
         }
         
         if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
         }

         if (gp.gameState == gp.passState) {
            drawPassScreen();
         }
         
         if (gp.gameState == gp.winState) {
            drawWinScreen();
         }
      }

   }

   public void drawWinScreen() {
      g2.setColor(new Color(0, 0, 0, 150));
   
      g2.fillRect(0, 0, 960, 576);
      g2.setFont(g2.getFont().deriveFont(1, 110.0F));
      String text = "You Win";
      g2.setColor(Color.black);
      int x = getXforCenteredText(text);
      
      int y = gp.tileSize * 4;
      g2.drawString(text, x, y);
      g2.setColor(Color.white);
      g2.drawString(text, x - 4, y - 4);
      g2.setFont(g2.getFont().deriveFont(50.0F));
      text = "Time Remaining : " + dFormat.format(Math.floor(time / 60.0D)) + ":" + dFormat.format(time % 60.0D);
      x = getXforCenteredText(text);
      
      y += gp.tileSize * 2;
      g2.drawString(text, x, y);
      text = "Your Score : " + dFormat.format(Math.floor(time * 10.0D));
      x = getXforCenteredText(text);
      
      y += gp.tileSize * 2;
      g2.drawString(text, x, y);
      text = "Restart";
      x = getXforCenteredText(text);
      
      y += gp.tileSize * 2;
      g2.drawString(text, x, y);
      if (commandNum == 0) {
         g2.drawString(">", x - 40, y);
      }

      text = "Quit";
      x = getXforCenteredText(text);
      y += 55;
      g2.drawString(text, x, y);
      if (commandNum == 1) {
         g2.drawString(">", x - 40, y);
      }

   }

   public void drawPassScreen() {
      g2.setColor(new Color(0, 0, 0, 150));
      
      g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
      g2.setFont(g2.getFont().deriveFont(1, 110.0F));
      String text = "You Exited";
      g2.setColor(Color.black);
      int x = getXforCenteredText(text);
      
      int y = gp.tileSize * 4;
      g2.drawString(text, x, y);
      g2.setColor(Color.white);
      g2.drawString(text, x - 4, y - 4);
      g2.setFont(g2.getFont().deriveFont(50.0F));
      text = "Time Remaining : " + dFormat.format(Math.floor(time / 60.0D)) + ":" + dFormat.format(time % 60.0D);
      x = getXforCenteredText(text);
      
      y += gp.tileSize * 2;
      g2.drawString(text, x, y);
      text = "Next Level";
      x = getXforCenteredText(text);
      
      y += gp.tileSize * 2;
      g2.drawString(text, x, y);
      if (commandNum == 0) {
         g2.drawString(">", x - 40, y);
      }

      text = "Quit";
      x = getXforCenteredText(text);
      y += 55;
      g2.drawString(text, x, y);
      if (commandNum == 1) {
         g2.drawString(">", x - 40, y);
      }

   }

   public void drawGameOverScreen() {
      g2.setColor(new Color(0, 0, 0, 150));

      g2.fillRect(0, 0, 960, 576);
      g2.setFont(g2.getFont().deriveFont(1, 110.0F));
      String text = "Game Over";
      g2.setColor(Color.black);
      int x = getXforCenteredText(text);
      
      int y = gp.tileSize * 4;
      g2.drawString(text, x, y);
      g2.setColor(Color.white);
      g2.drawString(text, x - 4, y - 4);
      g2.setFont(g2.getFont().deriveFont(50.0F));
      text = "Restart";
      x = getXforCenteredText(text);
      
      y += gp.tileSize * 4;
      g2.drawString(text, x, y);
      if (commandNum == 0) {
         g2.drawString(">", x - 40, y);
      }

      text = "Quit";
      x = getXforCenteredText(text);
      y += 55;
      g2.drawString(text, x, y);
      if (commandNum == 1) {
         g2.drawString(">", x - 40, y);
      }

   }

   public void drawTitleScreen() {
      if (bgimg != null) {
         g2.drawImage(bgimg, 0, 0, gp.screenWidth, gp.screenHeight, null);
      }

      g2.setFont(Itim_25);
      g2.setFont(g2.getFont().deriveFont(1, 80.0F));
      String text = "6 th FLOOR";
      
      int x = gp.tileSize;
      
      int y = gp.tileSize * 2 + 20;
      g2.setColor(Color.white);
      g2.drawString(text, x, y);
      g2.setFont(g2.getFont().deriveFont(1, 30.0F));
      text = "START";
      
      x = gp.tileSize + 10;
      
      y += gp.tileSize + 10;
      g2.drawString(text, x, y);
      if (commandNum == 0) {  
         g2.drawString(">", x - gp.tileSize / 2 - 5, y - 5);
      }

      text = "EXIT";
      
      x = gp.tileSize + 10;
      
      y += gp.tileSize;
      g2.drawString(text, x, y);
      if (commandNum == 1) {
        g2.drawString(">", x - 48 / 2 - 5, y - 5);
      }

   }

   public void drawPauseScreen() {
      g2.setColor(Color.white);
      g2.setFont(g2.getFont().deriveFont(32.0F));
      int frameX = 0;
      int frameY = 0;
      
      int frameWidth = gp.tileSize * 20;
      
      int frameHeight = gp.tileSize * 12;
      drawSubWindow(frameX, frameY, frameWidth, frameHeight);
      switch(subState) {
      case 0:
         pause_top(frameX, frameY);
         break;
      case 1:
         pause_fullScreenNotification(frameX, frameY);
         break;
      case 2:
         pause_endGameConfirmation(frameX, frameY);
      }

      gp.keyH.enterPressed = false;
   }

   public void pause_top(int frameX, int frameY) {
      String text = "Game Menu";
      int textX = getXforCenteredText(text);
      
      int textY = frameY + gp.tileSize * 2;
      g2.drawString(text, textX, textY);
      text = "Full Screen";
      
      textX -= gp.tileSize * 3;
      
      textY += gp.tileSize * 2;
      g2.drawString(text, textX, textY);
      if (commandNum == 0) {
         g2.drawString(">", textX - 30, textY);
         if (gp.keyH.enterPressed) {
            if (!gp.fullScreenOn) {
               gp.fullScreenOn = true;
            } else if (gp.fullScreenOn) {
               gp.fullScreenOn = false;
            }

            subState = 1;
         }
      }

      
      textY += gp.tileSize;
      g2.drawString("Sound Effect", textX, textY);
      if (commandNum == 1) {
         g2.drawString(">", textX - 30, textY);
      }

      
      textY += gp.tileSize;
      g2.drawString("Exit", textX, textY);
      if (commandNum == 2) {
         g2.drawString(">", textX - 30, textY);
         if (gp.keyH.enterPressed) {
            subState = 2;
            commandNum = 0;
         }
      }

      
      textY += gp.tileSize * 3;
      g2.drawString("Back", textX, textY);
      if (commandNum == 3) {
         g2.drawString(">", textX - 30, textY);
         if (gp.keyH.enterPressed) {
            gp.gameState = 1;
            commandNum = 0;
         }
      }
      
      textX = getXforCenteredText(text) + gp.tileSize * 5;
      
      textY = frameY + gp.tileSize * 3 + 24;
      g2.setStroke(new BasicStroke(3.0F));
      g2.drawRect(textX, textY, 24, 24);
      if (gp.fullScreenOn) {
         g2.fillRect(textX, textY, 24, 24);
      }

      textX -= 95;
      
      textY += 48;
      g2.drawRect(textX, textY, 120, 24);
      int volumeWidth = 24 * gp.sound.volumeScale;
      g2.fillRect(textX, textY, volumeWidth, 24);

      try {
         gp.config.saveConfig();
      } catch (IOException e) {
         Logger.getLogger(UI.class.getName()).log(Level.SEVERE, (String)null, e);
      }

   }

   public void pause_endGameConfirmation(int frameX, int frameY) {
      
      int textX = frameX + gp.tileSize * 4;
      int textY = frameY + gp.tileSize * 3;
      
      currentDialogue = "Quit the game and return to the title screen?";
      g2.drawString(currentDialogue, textX, textY);
      String text = "Yes";
      textX = getXforCenteredText(text);
      
      textY += gp.tileSize * 3;
      g2.drawString(text, textX, textY);
      if (commandNum == 0) {
         g2.drawString(">", textX - 30, textY);
         if (gp.keyH.enterPressed) {
            subState = 0;  
            gp.gameState = 0;
         }
      }

      text = "No";
      textX = getXforCenteredText(text);
      
      textY += gp.tileSize;
      g2.drawString(text, textX, textY);
      if (commandNum == 1) {
         g2.drawString(">", textX - 30, textY);
         if (gp.keyH.enterPressed) {
            subState = 0;
            commandNum = 0;
         }
      }

      if (gp.ui.commandNum < 0) {
         gp.ui.commandNum = 1;
      }

      if (gp.ui.commandNum > 1) {
         gp.ui.commandNum = 0;
      }

   }

   public void pause_fullScreenNotification(int frameX, int frameY) {
      currentDialogue = "The change will take effect after restarting the game.";
      int textX = getXforCenteredText(currentDialogue);
      
      int textY = frameY + gp.tileSize * 3;
      g2.drawString(currentDialogue, textX, textY);
      String text = "Back";
      textX = getXforCenteredText(text);
      
      textY = frameY + gp.tileSize * 9;
      g2.drawString(text, textX, textY);
      if (commandNum == 0) {
         g2.drawString(">", textX - 30, textY);
         if (gp.keyH.enterPressed) {
            subState = 0;
         }
      }

      commandNum = 0;
   }

   public void drawDialogueScreen() {
      
      int x = gp.tileSize * 2;
      int y = gp.tileSize / 2;
      int width = gp.screenWidth - gp.tileSize * 4;
      int height = gp.tileSize * 4;
      drawSubWindow(x, y, width, height);
      g2.setFont(Itim_25);
      g2.setFont(g2.getFont().deriveFont(0, 32.0F));
      
      x += gp.tileSize;
      y += gp.tileSize;
      
      String[] var5 = currentDialogue.split("\n");
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         String line = var5[var7];
         g2.drawString(line, x, y);
         y += 40;
      }

   }

   public void drawCharacterScreen() {
      
      int frameX = gp.tileSize * 2;
      
      int frameY = gp.tileSize;
      
      int frameWidth = gp.tileSize * 6 + 30;
      
      int frameHeight = gp.tileSize * 4;
      drawSubWindow(frameX, frameY, frameWidth, frameHeight);
      g2.setColor(Color.white);
      g2.setFont(g2.getFont().deriveFont(32.0F));
      int textX = frameX + 20;
      
      int textY = frameY + gp.tileSize;
      g2.drawString("Health", textX, textY);
      textY += 40;
      g2.drawString("Damage", textX, textY);
      textY += 70;
      g2.drawString("Weapon", textX, textY);
      int tailX = frameX + frameWidth - 30;
      
      textY = frameY + gp.tileSize;
      String value = String.valueOf(gp.player.hitpoint + "/" + gp.player.maxHitPoint);
      textX = getXforAlignToRightText(value, tailX);
      g2.drawString(value, textX, textY);
      textY += 40;
      value = String.valueOf(gp.player.attack);
      textX = getXforAlignToRightText(value, tailX);
      g2.drawString(value, textX, textY);
      textY += 40;
      
      g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY, (ImageObserver)null);
   }

   public void drawInventory() {
      
      int frameX = gp.tileSize * 12;
      
      int frameY = gp.tileSize;
      
      int frameWidth = gp.tileSize * 6;
      
      int frameHeight = gp.tileSize * 5;
      drawSubWindow(frameX, frameY, frameWidth, frameHeight);
      int slotXstart = frameX + 20;
      int slotYstart = frameY + 20;
      int slotX = slotXstart;
      int slotY = slotYstart;
      
      int slotSize = gp.tileSize + 3;

      int i;
      for(i = 0; i < gp.player.inventory.size(); ++i) {
         if (gp.player.inventory.get(i) == gp.player.currentWeapon) {
            g2.setColor(new Color(240, 190, 90));
            g2.fillRoundRect(slotX, slotY, 48, 48, 10, 10);
         }

         g2.drawImage((gp.player.inventory.get(i)).down1, slotX, slotY, null);
         slotX += slotSize;
         if (i == 4 || i == 9 || i == 14) {
            slotX = slotXstart;
            slotY += slotSize;
         }
      }

      i = slotXstart + slotSize * slotCol;
      int cursorY = slotYstart + slotSize * slotRow;
      
      int cursorWidth = gp.tileSize;
      
      int cursorHeight = gp.tileSize;
      g2.setColor(Color.white);
      g2.setStroke(new BasicStroke(3.0F));
      g2.drawRoundRect(i, cursorY, cursorWidth, cursorHeight, 10, 10);
      int dFrameY = frameY + frameHeight;
      
      int dFrameHeight = gp.tileSize * 3;
      int textX = frameX + 20;
      
      int textY = dFrameY + gp.tileSize;
      g2.setFont(g2.getFont().deriveFont(28.0F));
      int itemIndex = getItemIndexOnSlot();
      if (itemIndex < gp.player.inventory.size()) {
         drawSubWindow(frameX, dFrameY, frameWidth, dFrameHeight);
         String[] var21 = ((Entity)gp.player.inventory.get(itemIndex)).description.split("\n");
         int var22 = var21.length;

         for(int var23 = 0; var23 < var22; ++var23) {
            String line = var21[var23];
            g2.drawString(line, textX, textY);
            textY += 32;
         }
      }

   }

   public int getItemIndexOnSlot() {
      int itemIndex = slotCol + slotRow * 5;
      return itemIndex;
   }

   public void drawSubWindow(int x, int y, int width, int height) {
      Color c = new Color(0, 0, 0, 175);
      g2.setColor(c);
      g2.fillRoundRect(x, y, width, height, 35, 35);
      c = new Color(255, 255, 255);
      g2.setColor(c);
      g2.setStroke(new BasicStroke(5.0F));
      g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
   }

   public void drawHPBar() {
      int barWidth = 200;
      int barHeight = 25;
      int x = 10;
      int y = 10;
      g2.setColor(Color.gray);
      g2.fillRect(x, y, barWidth, barHeight);
      double hpPercentage = (double)gp.player.hitpoint / (double)gp.player.maxHitPoint;
      int currentBarWidth = (int)((double)barWidth * hpPercentage);
      g2.setColor(Color.RED);
      g2.fillRect(x, y, currentBarWidth, barHeight);
      g2.setColor(Color.WHITE);
      g2.setFont(Itim_25);
      g2.drawString("HP: " + gp.player.hitpoint + "/" + gp.player.maxHitPoint, x + 5, y + 20);
   }

   public void drawHealthPotionStatus(Graphics2D g2) {
      int x = 20;
      
      int y = gp.screenHeight - 100;
      if (gp.player.hasHealthpotion && gp.player.canUsePotion) {
         potionStatus = "READY";
      } else if (gp.player.hasHealthpotion) {
         potionStatus = String.format("%.2f", gp.player.potionCooldownTime);
      }

      g2.setColor(Color.WHITE);
      g2.setFont(arial_40);
      g2.drawString(potionStatus, x + 60, y + 38);
      if (gp.player.hasHealthpotion) {
         g2.drawImage(potionImage, x + 10, y, (ImageObserver)null);
      }

   }

   public int getXforCenteredText(String text) {
      int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
      
      int x = gp.screenWidth / 2 - length / 2;
      return x;
   }

   public int getXforAlignToRightText(String text, int tailX) {
      int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
      int x = tailX - length;
      return x;
   }
}
