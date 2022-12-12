package object;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;

//Parent Class for all objects created.
//Similar to the Entity Class
public class SuperObjects {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    //If we know an objects position in the world, WorldX and WorldY,
    //We can find out where its position will be on the scrreen, ScreenX and ScreenY 
    //based on the player's position in the world and on the screen,
    //
    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
           worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            //tile[tileNum[]] works as an index of the Tile[] tile array.
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }

}
