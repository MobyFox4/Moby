package tile_interactive;

import entity.Entity;
import java.util.Objects;
import main.Gamepanel;

public class IT_Wood1 extends InteractiveTile {

   public IT_Wood1(Gamepanel gp, int col, int row) {
      super(gp, col, row);
      this.gp = gp;
      worldX = gp.tileSize * col;
      worldY = gp.tileSize * row;
      down1 = setup("/Tiles_Interactive/wood1");
      destructible = true;
      hitpoint = 30;
   }

   public boolean isCorrectItem(Entity entity) {
      boolean isCorrectItem = false;
      if (entity.currentWeapon.type == type_axe) {
         isCorrectItem = true;
      }

      return isCorrectItem;
   }
}
