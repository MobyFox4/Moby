package object;

import entity.Entity;
import java.util.Objects;
import main.Gamepanel;

public class OBJ_YellowKey extends Entity {
   Gamepanel gp;

   public OBJ_YellowKey(Gamepanel gp) {
      super(gp);
      this.gp = gp;
      type = type_consumable;
      name = "YellowKey";
      down1 = setup("/object/Yellowkey");
      description = "[" + name + "]";
   }

   public boolean use(Entity entity) {
       
      gp.gameState = gp.dialogueState;
      int objIndex = getDetected(entity, gp.obj, "YellowDoor");
       System.out.println(objIndex);
      if (objIndex != 999) {
         gp.ui.currentDialogue = "Door is opening";
         gp.playSE(3);
         gp.obj[gp.currentMap][objIndex] = null;
         return true;
      } else {
         gp.ui.currentDialogue = "Use with YellowDoor";
         return false;
      }
   }
}
