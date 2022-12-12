package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class obj_chest extends SuperObjects{

    public obj_chest(){

        name = "Chest";

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/main/res/objects/chest.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
