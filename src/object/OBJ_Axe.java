package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Axe extends Entity{
    
    public OBJ_Axe(Gamepanel gp){
        
        super(gp);
        type = type_axe;
        name = "Axe";
        down1 = setup("/object/Axe");
        attackValue = 15;
        description = "[" + name + "]\nCan cut some wood\nDamage = 15";
    }
    
}
