package object;

import entity.Entity;
import java.util.Objects;
import main.Gamepanel;

public class OBJ_ExitKey extends Entity {
   Gamepanel gp;

   public OBJ_ExitKey(Gamepanel gp) {
      super(gp);
      this.gp = gp;
      type = type_consumable;
      name = "ExitKey";
      down1 = setup("/object/Exitkey");
      description = "[" + name + "]";
   }

   public boolean use(Entity entity) {
      Objects.requireNonNull(gp);
      gp.gameState = gp.dialogueState;
      int objIndex = getDetected(entity, gp.obj, "ExitDoor");
      if (objIndex != 999) {
         gp.playSE(3);
         gp.obj[gp.currentMap][objIndex] = null;
         if (gp.currentMap == 1) {
            gp.gameState = gp.winState;
         } else {
            gp.gameState = 6;
         }

         return true;
      } else {
         gp.ui.currentDialogue = "Use with ExitDoor";
         return false;
      }
   }
}
