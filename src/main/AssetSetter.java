package main;

import entity.D_Ghost;
import entity.Entity;
import ghost.GHOST_BadGhost;
import java.util.Objects;
import object.OBJ_Axe;
import object.OBJ_Desk;
import object.OBJ_ExitDoor;
import object.OBJ_GrassScissors;
import object.OBJ_GreenDoor;
import object.OBJ_GreenKey;
import object.OBJ_Healthpotion;
import object.OBJ_Ring;
import object.OBJ_Tree;
import object.OBJ_Water;
import object.OBJ_YellowDoor;
import object.OBJ_YellowKey;
import tile_interactive.IT_Grass;
import tile_interactive.IT_Wood1;

public class AssetSetter {
   Gamepanel gp;

   public AssetSetter(Gamepanel gp) {
       this.gp = gp;
   }

   public void setObject() {
      int mapNum = 0;
      int i = 0;
      gp.obj[mapNum][i] = new OBJ_Axe(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*3;
      gp.obj[mapNum][i].worldY = gp.tileSize*47;
      i++;
      
      gp.obj[mapNum][i] = new OBJ_GrassScissors(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*19;
      gp.obj[mapNum][i].worldY = gp.tileSize*21;
      i++;

      gp.obj[mapNum][i] = new OBJ_Healthpotion(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*34;
      gp.obj[mapNum][i].worldY = gp.tileSize*11;
      i++;

      gp.obj[mapNum][i] = new OBJ_Water(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*2;
      gp.obj[mapNum][i].worldY = gp.tileSize*34;
      i++;

      gp.obj[mapNum][i] = new OBJ_Tree(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*8;
      gp.obj[mapNum][i].worldY = gp.tileSize*47;
      i++;

      gp.obj[mapNum][i] = new OBJ_Desk(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*38;
      gp.obj[mapNum][i].worldY = gp.tileSize*1;
      i++;

      gp.obj[mapNum][i] = new OBJ_ExitDoor(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*46;
      gp.obj[mapNum][i].worldY = gp.tileSize*0;
      i++;

      gp.obj[mapNum][i] = new OBJ_GreenDoor(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*6;
      gp.obj[mapNum][i].worldY = gp.tileSize*42;
      i++;

      gp.obj[mapNum][i] = new OBJ_YellowDoor(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*41;
      gp.obj[mapNum][i].worldY = gp.tileSize*32;
      i++;

      gp.obj[mapNum][i] = new OBJ_YellowDoor(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*41;
      gp.obj[mapNum][i].worldY = gp.tileSize*36;
      i++;

      gp.obj[mapNum][i] = new OBJ_GreenKey(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*34;
      gp.obj[mapNum][i].worldY = gp.tileSize*10;
      i++;

      gp.obj[mapNum][i] = new OBJ_YellowKey(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*39;
      gp.obj[mapNum][i].worldY = gp.tileSize*21;
      i++;

      
      mapNum = 1;
      i = 0;
      
      gp.obj[mapNum][i] = new OBJ_Axe(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*3;
      gp.obj[mapNum][i].worldY = gp.tileSize*47;
      i++;

      gp.obj[mapNum][i] = new OBJ_GrassScissors(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*19;
      gp.obj[mapNum][i].worldY = gp.tileSize*21;
      i++;

      gp.obj[mapNum][i] = new OBJ_Healthpotion(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*48;
      gp.obj[mapNum][i].worldY = gp.tileSize*1;
      i++;

      gp.obj[mapNum][i] = new OBJ_Desk(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*38;
      gp.obj[mapNum][i].worldY = gp.tileSize*1;
      i++;

      gp.obj[mapNum][i] = new OBJ_Ring(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*1;
      gp.obj[mapNum][i].worldY = gp.tileSize*48;
      i++;

      gp.obj[mapNum][i] = new OBJ_ExitDoor(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*0;
      gp.obj[mapNum][i].worldY = gp.tileSize*30;
      i++;

      gp.obj[mapNum][i] = new OBJ_GreenDoor(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*6;
      gp.obj[mapNum][i].worldY = gp.tileSize*42;
      i++;

      gp.obj[mapNum][i] = new OBJ_YellowDoor(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*41;
      gp.obj[mapNum][i].worldY = gp.tileSize*36;
      i++;

      gp.obj[mapNum][i] = new OBJ_GreenKey(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*18;
      gp.obj[mapNum][i].worldY = gp.tileSize*3;
      i++;

      gp.obj[mapNum][i] = new OBJ_YellowKey(gp);
      gp.obj[mapNum][i].worldX = gp.tileSize*39;
      gp.obj[mapNum][i].worldY = gp.tileSize*21;
      i++;

   }

   public void setNPC() {
      int mapNum = 0;
      int i = 0;
      
      gp.npc[mapNum][i] = new D_Ghost(gp);
      gp.npc[mapNum][i].worldX = gp.tileSize*34;
      gp.npc[mapNum][i].worldY = gp.tileSize*9;
   }

   public void setGhost() {
      int mapNum = 0;
      int i = 0;
      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*46;
      gp.ghost[mapNum][i].worldY = gp.tileSize*14;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*46;
      gp.ghost[mapNum][i].worldY = gp.tileSize*3;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*45;
      gp.ghost[mapNum][i].worldY = gp.tileSize*26;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*47;
      gp.ghost[mapNum][i].worldY = gp.tileSize*37;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*26;
      gp.ghost[mapNum][i].worldY = gp.tileSize*42;
      i++;
      
      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*41;
      gp.ghost[mapNum][i].worldY = gp.tileSize*46;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*26;
      gp.ghost[mapNum][i].worldY = gp.tileSize*46;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*19;
      gp.ghost[mapNum][i].worldY = gp.tileSize*44;
      i++;
      
      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*11;
      gp.ghost[mapNum][i].worldY = gp.tileSize*44;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*3;
      gp.ghost[mapNum][i].worldY = gp.tileSize*42;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*3;
      gp.ghost[mapNum][i].worldY = gp.tileSize*45;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*2;
      gp.ghost[mapNum][i].worldY = gp.tileSize*44;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*14;
      gp.ghost[mapNum][i].worldY = gp.tileSize*46;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*25;
      gp.ghost[mapNum][i].worldY = gp.tileSize*25;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*5;
      gp.ghost[mapNum][i].worldY = gp.tileSize*25;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*38;
      gp.ghost[mapNum][i].worldY = gp.tileSize*3;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*38;
      gp.ghost[mapNum][i].worldY = gp.tileSize*4;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*39;
      gp.ghost[mapNum][i].worldY = gp.tileSize*4;
      i++;

      mapNum = 1;
      i = 0;
      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*10;
      gp.ghost[mapNum][i].worldY = gp.tileSize*14;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*2;
      gp.ghost[mapNum][i].worldY = gp.tileSize*14;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*29;
      gp.ghost[mapNum][i].worldY = gp.tileSize*14;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*31;
      gp.ghost[mapNum][i].worldY = gp.tileSize*14;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*5;
      gp.ghost[mapNum][i].worldY = gp.tileSize*47;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*2;
      gp.ghost[mapNum][i].worldY = gp.tileSize*39;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*14;
      gp.ghost[mapNum][i].worldY = gp.tileSize*37;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*47;
      gp.ghost[mapNum][i].worldY = gp.tileSize*37;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*45;
      gp.ghost[mapNum][i].worldY = gp.tileSize*43;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*26;
      gp.ghost[mapNum][i].worldY = gp.tileSize*42;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*11;
      gp.ghost[mapNum][i].worldY = gp.tileSize*42;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*41;
      gp.ghost[mapNum][i].worldY = gp.tileSize*46;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*3;
      gp.ghost[mapNum][i].worldY = gp.tileSize*42;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*4;
      gp.ghost[mapNum][i].worldY = gp.tileSize*45;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*25;
      gp.ghost[mapNum][i].worldY = gp.tileSize*25;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*5;
      gp.ghost[mapNum][i].worldY = gp.tileSize*25;
      i++;
      
      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*38;
      gp.ghost[mapNum][i].worldY = gp.tileSize*3;
      i++;

      gp.ghost[mapNum][i] = new GHOST_BadGhost(gp);
      gp.ghost[mapNum][i].worldX = gp.tileSize*38;
      gp.ghost[mapNum][i].worldY = gp.tileSize*4;
      i++;

   }

   public void setInteractiveTile() {
      int mapNum = 0;
      int i = 0;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 35, 1);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 35, 2);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 35, 3);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 35, 4);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 35, 5);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 39, 29);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 39, 30);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 39, 31);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 39, 37);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 39, 38);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 39, 39);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 27, 29);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 27, 30);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 27, 31);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 12, 37);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 12, 38);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 12, 39);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 1, 32);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 2, 32);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 3, 32);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 1, 36);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 2, 36);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 3, 36);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 16, 29);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 16, 30);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 16, 31);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 16, 37);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 16, 38);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 16, 39);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 8, 37);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 8, 38);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 8, 39);i++;
      gp.iTile[mapNum][i] = new IT_Grass(gp, 26, 44);i++;
      gp.iTile[mapNum][i] = new IT_Grass(gp, 13, 48);i++;
      gp.iTile[mapNum][i] = new IT_Grass(gp, 7, 46);i++;
      gp.iTile[mapNum][i] = new IT_Grass(gp, 8, 46);i++;
      gp.iTile[mapNum][i] = new IT_Grass(gp, 9, 46);i++;
      gp.iTile[mapNum][i] = new IT_Grass(gp, 7, 47);i++;
      gp.iTile[mapNum][i] = new IT_Grass(gp, 9, 47);i++;
      gp.iTile[mapNum][i] = new IT_Grass(gp, 7, 48);i++;
      gp.iTile[mapNum][i] = new IT_Grass(gp, 8, 48);i++;
      gp.iTile[mapNum][i] = new IT_Grass(gp, 9, 48);i++;
      
      mapNum = 1;
      i = 0;
      
      gp.iTile[mapNum][i] = new IT_Grass(gp, 18, 42);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 37, 37);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 38, 38);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 39, 39);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 31, 37);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 31, 38);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 31, 39);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 6, 39);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 7, 39);i++;
      gp.iTile[mapNum][i] = new IT_Wood1(gp, 8, 39);i++;
   }
}
