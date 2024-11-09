package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Ring extends Entity {
   Gamepanel gp;

   public OBJ_Ring(Gamepanel gp) {
      super(gp);
      this.gp = gp;
      type = type_consumable;
      name = "Ring";
      down1 = setup("/object/ring");
      description = "[" + name + "]";
   }

   public boolean use(Entity entity) {

      gp.gameState = gp.dialogueState;
      int objIndex = getDetected(entity, gp.obj, "Desk");
      if (objIndex != 999) {
         gp.ui.currentDialogue = "You found a key";
         gp.player.inventory.add(new OBJ_ExitKey(gp));
         gp.playSE(1);
         return true;
      } else {
         gp.ui.currentDialogue = "???";
         return false;
      }
   }
}
