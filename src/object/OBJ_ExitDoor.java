package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_ExitDoor extends Entity{
    
    Gamepanel gp;

    public OBJ_ExitDoor(Gamepanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_obstacle;
        down1 = setup("/object/exitDoor");
        name = "ExitDoor";

        collision = true;
    }
    
    public void interact(){
        
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need an exit key to open this";
    }
}
