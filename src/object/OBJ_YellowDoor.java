package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_YellowDoor extends Entity{
    
    Gamepanel gp;

    public OBJ_YellowDoor(Gamepanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_obstacle;
        down1 = setup("/object/YellowDoor");
        name = "YellowDoor";

        collision = true;
    }
    
    public void interact(){
        
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a Yellow key to open this";
    }
}
