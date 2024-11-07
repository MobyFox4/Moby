package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Knife extends Entity{
    
    public OBJ_Knife(Gamepanel gp){
        
        super(gp);
        type = type_knife;
        name = "Knife";
        down1 = setup("/object/knife");
        attackValue = 10;
        description = "[" + name + "]\nDamage = 10";
    }
    
}
