package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Desk extends Entity{
    
    Gamepanel gp;

    public OBJ_Desk(Gamepanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_obstacle;
        down1 = setup("/object/desk");
        name = "Desk";

        collision = true;
    }
    
    public void interact(){
        
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "look spacial";
    }
}
