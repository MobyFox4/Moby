package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_GrassScissors extends Entity{
    
    public OBJ_GrassScissors(Gamepanel gp){
        super(gp);
        
        type = type_scissors;
        name = "GrassScissors";
        down1 = setup("/object/grass_scissors");
        attackValue = 5;
        description = "[" + name + "]\nCan cut some grass\nDamage = 5";
    }
}
