package ghost;

import entity.Entity;
import java.util.Random;
import main.Gamepanel;

public class GHOST_BadGhost extends Entity {
   public GHOST_BadGhost(Gamepanel gp) {
      super(gp);
      this.type = 2;
      name = "BadGhost";
      defaultSpeed = 3;
      speed = defaultSpeed;
      maxHitPoint = 50;
      hitpoint = maxHitPoint;
      solidArea.x = 3;
      solidArea.y = 3;
      solidArea.width = 42;
      solidArea.height = 42;
      solidAreaDefaultX = solidArea.x;
      solidAreaDefaultY = solidArea.y;
      getImage();
   }

   public void getImage() {
      up1 = setup("/otherEntity/BGhostRight");
      up2 = setup("/otherEntity/BGhostRight");
      down1 = setup("/otherEntity/BGhostRight");
      down2 = setup("/otherEntity/BGhostRight");
      left1 = setup("/otherEntity/BGhostLeft");
      left2 = setup("/otherEntity/BGhostLeft");
      right1 = setup("/otherEntity/BGhostRight");
      right2 = setup("/otherEntity/BGhostRight");
   }

   public void setAction() {
      ++actionLockCounter;
      if (actionLockCounter == 80) {
         Random random = new Random();
         int i = random.nextInt(100) + 1;
         if (i <= 25) {
            direction = "up";
         }

         if (i > 25 && i <= 50) {
            direction = "down";
         }

         if (i > 50 && i <= 75) {
            direction = "left";
         }

         if (i > 75 && i <= 100) {
            direction = "right";
         }

         actionLockCounter = 0;
      }

   }
}
