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
	public final int screenX; 	//These are final because the player character's positions doesn't change. The background does. 
	public final int screenY;
	public int hasKey = 0;


	
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
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 20;
		solidArea.height = 20;

		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 5;
		direction = "down";

	}

	public void getPlayerImage(){

		try{

			up1 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_up_2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_up_3.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_up_4.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_down_2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_down_3.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_down_4.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_left_2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_left_3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_left_4.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_right_2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_right_3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/main/res/player/jamis_right_4.png"));

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

			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObj((objIndex));

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
					spriteNum = 3;
				}
				else if(spriteNum == 3){
					spriteNum = 4;
				}
				else if(spriteNum == 4){
					spriteNum = 1;
				}

			spriteCounter = 0;
			}

			}

		}
		
	

	public void pickUpObj(int i){

		//if 999, an object has been touched
		if(i != 999){

			String objectName = gp.obj[i].name;

			switch(objectName){
				case "Key":
					gp.playSE(1);
					hasKey++;
					gp.obj[i] = null;
					//gp.ui.showMessage("You got a key!");
					break;
				case "Door":
					gp.playSE(3);
					if(hasKey > 0){
						gp.obj[i] = null;
						hasKey--;
						gp.ui.showMessage("Door Unlocked");
					}
					else{
						gp.ui.showMessage("Key Required");
					}
					break;
				case "Chest":
					gp.playSE(4);
					gp.ui.showMessage("You Win!");
					gp.ui.gameFinished = true;
					gp.stopMusic();
					break;
				case "Boots":
					gp.playSE(2);
					speed += 3;
					gp.ui.showMessage("Movement Speed Increased");
					gp.obj[i] = null;
					break; 
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
				if(spriteNum == 3){
				image = up3;
				}
				if(spriteNum == 4){
				image = up4;
				}
				break;
			case "down":
				if(spriteNum == 1){
				image = down1;
				}
				if(spriteNum == 2){
				image = down2;
				}
				if(spriteNum == 3){
				image = down3;
				}
				if(spriteNum ==4){
				image = down4;
				}
				break;
			case "left":
				if(spriteNum == 1){
				image = left1;
				}
				if(spriteNum == 2){
				image = left2;
				}
				if(spriteNum == 3){
				image = left3;
				}
				if(spriteNum == 4){
				image = left4;
				}
				break;
			case "right":
				if(spriteNum == 1){
				image = right1;
				}
				if(spriteNum == 2){
				image = right2;
				}
				if(spriteNum == 3){
				image = right3;
				}
				if(spriteNum == 4){
				image = right4;
				}
				break;

		}

		//Draws Image on Screen
		//null at the end is referred to as the Image Observer
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

	}
}
