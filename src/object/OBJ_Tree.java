package object;

import entity.Entity;
import java.util.Objects;
import main.Gamepanel;

public class OBJ_Tree extends Entity {
   Gamepanel gp;

   public OBJ_Tree(Gamepanel gp) {
      super(gp);
      this.gp = gp;
      type = type_obstacle;
      name = "Tree";
      image = setup("/object/555");
      image2 = setup("/object/bigtree");
      down1 = image;
      collision = true;
   }

   public void interact() {
      gp.gameState = gp.dialogueState;
      gp.ui.currentDialogue = "Tree...";
   }
}
