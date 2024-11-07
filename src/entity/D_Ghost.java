package entity;

import java.util.Random;
import main.Gamepanel;

public class D_Ghost extends Entity {
   public D_Ghost(Gamepanel gp) {
      super(gp);
      direction = "left";
      speed = 0;
      getDImage();
      setDialogue();
   }

   public void getDImage() {
      left1 = setup("/otherEntity/DGhostLeft");
      left2 = setup("/otherEntity/DGhostLeft");
      right1 = setup("/otherEntity/DGhostRight");
      right2 = setup("/otherEntity/DGhostRight");
      up1 = setup("/otherEntity/DGhostRight");
      up2 = setup("/otherEntity/DGhostRight");
      down1 = setup("/otherEntity/DGhostRight");
      down2 = setup("/otherEntity/DGhostRight");
   }

   public void setDialogue() {
      dialogues[0] = "First,You need to find the water.";
      dialogues[1] = "And then,You need water the plants.";
      dialogues[2] = "And you will receive the ring.";
      dialogues[3] = "And than put the ring in my drawer.";
      dialogues[4] = "And I will give you the Exit key";
      dialogues[5] = "Oh! You can take my healing potion over there.";
      dialogues[6] = "Good luck!";
   }

   public void setAction() {
      ++actionLockCounter;
      if (actionLockCounter == 120) {
         Random random = new Random();
         int i = random.nextInt(100) + 1;
         if (i > 50 && i <= 75) {
            direction = "left";
         }

         if (i > 75 && i <= 100) {
            direction = "right";
         }

         actionLockCounter = 0;
      }

   }

   public void speak() {
      super.speak();
   }
}
