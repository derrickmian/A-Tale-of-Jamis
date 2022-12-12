package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class obj_door extends SuperObjects{

    public obj_door(){

        name = "Door";

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/main/res/objects/door.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
