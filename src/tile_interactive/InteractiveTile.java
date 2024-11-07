package tile_interactive;

import entity.Entity;
import main.Gamepanel;

public class InteractiveTile extends Entity {
   Gamepanel gp;
   public boolean destructible = false;

   public InteractiveTile(Gamepanel gp, int col, int row) {
      super(gp);
      this.gp = gp;
   }

   public boolean isCorrectItem(Entity entity) {
      boolean isCorrectItem = false;
      return isCorrectItem;
   }

   public void update() {
      if (invincible) {
         ++invincibleCounter;
         if (invincibleCounter > 30) {
            invincible = false;
            invincibleCounter = 0;
         }
      }

   }
}
