package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_GreenKey extends Entity {
   Gamepanel gp;

   public OBJ_GreenKey(Gamepanel gp) {
      super(gp);
      this.gp = gp;
      type = type_consumable;
      name = "GreenKey";
      down1 = setup("/object/Greenkey");
      description = "[" + name + "]";
   }

   public boolean use(Entity entity) {
      gp.gameState = gp.dialogueState;
      int objIndex = getDetected(entity, gp.obj, "GreenDoor");
      System.out.println(objIndex);
      if (objIndex != 999) {
         gp.ui.currentDialogue = "Door is opening";
         gp.playSE(3);
         gp.obj[gp.currentMap][objIndex] = null;
         return true;
      } else {
         gp.ui.currentDialogue = "Use with GreenDoor";
         return false;
      }
   }
}
