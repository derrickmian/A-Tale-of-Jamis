package main;

import object.obj_key;
import object.obj_door;
import object.obj_chest;
import object.obj_boots;

public class ObjectSetter {

    GamePanel gp;

    public ObjectSetter(GamePanel gp){

        this.gp = gp;

    }

    public void setObject(){
        //Creates Objects
        //Because OBJ_key is a subclass of a SuperObject class, you can instantiate it one of the arrays.
        gp.obj[0] = new obj_key();
        gp.obj[0].worldX = 23 * gp.tileSize;  //SetObject in the world at location 23x7 WorldX and WorldY
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new obj_key();
        gp.obj[1].worldX = 23 * gp.tileSize;  
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new obj_key();
        gp.obj[2].worldX = 38 * gp.tileSize;  
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[3] = new obj_door();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;

        gp.obj[4] = new obj_door();
        gp.obj[4].worldX = 8 * gp.tileSize; 
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5] = new obj_door();
        gp.obj[5].worldX = 12 * gp.tileSize;  
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[6] = new obj_chest();
        gp.obj[6].worldX = 10 * gp.tileSize;  
        gp.obj[6].worldY = 8 * gp.tileSize;

        gp.obj[7] = new obj_boots();
        gp.obj[7].worldX = 11 * gp.tileSize;  
        gp.obj[7].worldY = 33 * gp.tileSize;



    }
    
}
