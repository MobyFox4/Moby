package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.Gamepanel;
import main.UtilityTool;

public class TileManager {
   Gamepanel gp;
   public Tile[] tile;
   public int[][][] mapTileNum;

   public TileManager(Gamepanel gp) {
      this.gp = gp;
      tile = new Tile[20];
      mapTileNum = new int[10][50][50];
      getTileImage();
      loadMap("/res/Map/Worldmap00.txt", 0);
      loadMap("/res/Map/Worldmap01.txt", 1);
   }

   public void getTileImage() {
      setup(0, "000", true);
      setup(1, "001", true);
      setup(2, "002", false);
      setup(3, "003", false);
      setup(4, "004", false);
      setup(5, "005", true);
      setup(6, "006", true);
      setup(7, "007", true);
      setup(8, "008", true);
      setup(9, "009", true);
      setup(10, "010", true);
      setup(11, "011", false);
      setup(12, "012", true);
      setup(13, "013", false);
      setup(14, "014", false);
      setup(15, "015", true);
      setup(16, "016", true);
      setup(17, "017", true);
   }

   public void setup(int index, String imageName, boolean collision) {
      UtilityTool uTool = new UtilityTool();

      try {
         tile[index] = new Tile();
         tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/" + imageName + ".png"));
         tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
         tile[index].collision = collision;
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public void loadMap(String filePath, int map) {
      try {
         InputStream is = getClass().getResourceAsStream(filePath);
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         int col = 0;
         int row = 0;

         while(true) {
            if (col >= 50) {
               break;
            }

            if (row >= 50) {
               break;
            }

            String line = br.readLine();

            while(true) {
               if (col >= 50) {
                  if (col == 50) {
                     col = 0;
                     ++row;
                  }
                  break;
               }

               String[] numbers = line.split(" ");
               int num = Integer.parseInt(numbers[col]);
               mapTileNum[map][col][row] = num;
               ++col;
            }
         }

         br.close();
      } catch (Exception e) {
      }

   }

    public void draw(Graphics2D g2) {
      int worldCol = 0;
      int worldRow = 0;

      while(true) {
         if (worldCol >= 50) {
            break;
         }

         if (worldRow >= 50) {
            break;
         }

         int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
         int worldX = worldCol * gp.tileSize;
         int worldY = worldRow * gp.tileSize;
         int screenX = worldX - gp.player.worldX + gp.player.screenX;
         int screenY = worldY - gp.player.worldY + gp.player.screenY;
         if (worldX + gp.tileSize * 2 > gp.player.worldX - gp.player.screenX) {
            if (worldX - gp.tileSize * 2 < gp.player.worldX + gp.player.screenX) {
               if (worldY + gp.tileSize * 2 > gp.player.worldY - gp.player.screenY) {
                  if (worldY - gp.tileSize * 2 < gp.player.worldY + gp.player.screenY) {
                     g2.drawImage(tile[tileNum].image, screenX, screenY, (ImageObserver)null);
                  }
               }
            }
         }

         ++worldCol;
         if (worldCol == 50) {
            worldCol = 0;
            ++worldRow;
         }
      }

   }
}
