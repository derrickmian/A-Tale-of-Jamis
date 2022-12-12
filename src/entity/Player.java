package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

//import java.awt.Color;


public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;

	//These are final because the player character's positions doesn't change. The background does. 
	public final int screenX;
	public final int screenY;
	
	public Player (GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		//Keeps player position in the center of the screen (not really)
		// screenX = gp.screenWidth/2;
		// screenY = gp.screenHeight/2;
		//Because the center of the screen is determined by the top left corner of the tile, it isn't actually center
		//Comment out and test for yourself.

		//Correct Player Position, which subtracts half a tile length from both X and Y to get the center of the current player tile:
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);

		//Instantiate Collision Rectangle
		solidArea = new Rectangle(8, 16, 28, 28); 

		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 6;
		direction = "down";

	}

	public void getPlayerImage(){

		try{

			up1 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/boy_right_2.png"));

		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		
		//Player Sprite doesn't change unless player is moved
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
			//Upper-left corner ix X:0 and Y:0 so -= playerSpeed brings player closer to 0
			if (keyH.upPressed == true) {
				direction = "up";
			}
			else if (keyH.downPressed == true) {
				direction = "down";
			}
			else if (keyH.leftPressed == true) {
				direction = "left";
			}
			else if (keyH.rightPressed == true) {
				direction = "right";
			}

			//CHECK TILE COLLISION
			collisionOn = false;

			//Pass through the Player class itself.
			//Because the Player class is a subclass of the entity class, the collsion checker
			//can recieve player class as entity.
			gp.cChecker.CheckTile(this);

			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false){

				switch(direction){
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}




			//increases everytime update is called, 60 times per second
			spriteCounter++;
			if(spriteCounter > 12){
				if(spriteNum == 1){
					spriteNum = 2;
				}
			else if(spriteNum == 2){
				spriteNum = 1;
			}
			spriteCounter = 0;
			}

		}
		
	}
	
	public void draw(Graphics2D g2) {
		//Rectangle is being replaced
		// g2.setColor(Color.white);
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;

		switch(direction){
			case "up": 
				if(spriteNum == 1){
				image = up1;
				}
				if(spriteNum == 2){
				image = up2;
				}
				break;
			case "down":
			if(spriteNum == 1){
				image = down1;
				}
				if(spriteNum == 2){
				image = down2;
				}
				break;
			case "left":
			if(spriteNum == 1){
				image = left1;
				}
				if(spriteNum == 2){
				image = left2;
				}
				break;
			case "right":
			if(spriteNum == 1){
				image = right1;
				}
				if(spriteNum == 2){
				image = right2;
				}
				break;

		}

		//Draws Image on Screen
		//null at the end is referred to as the Image Observer
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

	}
}
