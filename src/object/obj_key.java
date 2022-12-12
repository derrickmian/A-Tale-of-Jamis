package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class obj_key extends SuperObjects{

    public obj_key(){

        name = "Key";

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/main/res/objects/key.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
