package tile_interactive;

import entity.Entity;
import java.util.Objects;
import main.Gamepanel;

public class IT_Grass extends InteractiveTile {

   public IT_Grass(Gamepanel gp, int col, int row) {
      super(gp, col, row);
      this.gp = gp;
      worldX = gp.tileSize * col;
      worldY = gp.tileSize * row;
      down1 = setup("/Tiles_Interactive/grass");
      destructible = true;
      hitpoint = 10;
   }

   public boolean isCorrectItem(Entity entity) {
      boolean isCorrectItem = false;
      if (entity.currentWeapon.type == type_scissors) {
         isCorrectItem = true;
      }

      return isCorrectItem;
   }
}
