package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class obj_boots extends SuperObjects {

    
    public obj_boots(){

        name = "Boots";

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/main/res/objects/boots.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    
}
