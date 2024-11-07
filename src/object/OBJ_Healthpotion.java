package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Healthpotion extends Entity{
    
    
    public OBJ_Healthpotion(Gamepanel gp){
        
        super(gp);
        name = "Healthpotion";
        down1 = setup("/object/Healthpotion");

    }
    
}
