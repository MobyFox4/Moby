package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Water extends Entity {
   Gamepanel gp;

   public OBJ_Water(Gamepanel gp) {
      super(gp);
      this.gp = gp;
      type = type_consumable;
      name = "Water";
      down1 = setup("/object/water");
      description = "[" + name + "]";
   }

   public boolean use(Entity entity) {

      gp.gameState = 3;
      int objIndex = getDetected(entity, gp.obj, "Tree");
      if (objIndex != 999) {
         gp.ui.currentDialogue = "You found a ring";
         gp.player.inventory.add(new OBJ_Ring(gp));
         gp.playSE(11);
         gp.obj[gp.currentMap][objIndex].grow = true;
         gp.obj[gp.currentMap][objIndex].down1 = gp.obj[gp.currentMap][objIndex].image2;
         return true;
      } else {
         gp.ui.currentDialogue = "???";
         return false;
      }
   }
}
