package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_GreenDoor extends Entity{

    Gamepanel gp;

    public OBJ_GreenDoor(Gamepanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_obstacle;
        down1 = setup("/object/GreenDoor");
        name = "GreenDoor";

        collision = true;
    }
    
    public void interact(){
        
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a Green key to open this";
    }
}
