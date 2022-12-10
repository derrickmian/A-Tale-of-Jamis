package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
		
	//Screen Settings
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3; //Creates scale to multiply by
		
	//Customizing Screen Settings
	final int tileSize = originalTileSize * scale; // each tile is 48x48 to adhere to modern screen sizes
	final int maxScreenCol = 16;
	final int maxScreenRow = 12; 
	final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	//FPS
	int FPS = 60;
		
	//Allows gamepanel to recognize key input
	KeyHandler keyH = new KeyHandler();

	//Keeps program running, automatically calls the run() method
	Thread gameThread;
	
	Player player = new Player(this,keyH);
	
	//Set Player Default Position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 5; //Moving 4 pixels
	
	//GamePanel Constructor	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //improves game rendering performance 
		this.addKeyListener(keyH); //GamePanel can listen to key input
		this.setFocusable(true);
		
	}

	//Used to keep track of time. 
	public void startGameThread() {
		
		gameThread = new Thread(this); //pass GamePanel class through the thread
		gameThread.start();
		
	}
	
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			        //CurrentTime - LastTime = how much time has past
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			if (delta >= 1) {
				
				//UPDATE: Needs to update player position every time a key is pressed
				update();
				//DRAW: Needs to redraw screen 30 or 60 times per second based on new player position 
				repaint();
				delta--;
				drawCount++;
				
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
		
	}
	
	public void update() {
		
		if (keyH.upPressed == true) {
			playerY -= playerSpeed; //Upper-left corner ix X:0 and Y:0 so -= playerSpeed brings player closer to 0
		}
		else if (keyH.downPressed == true) {
			playerY += playerSpeed;
		}
		else if (keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		else if (keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); //super imports from parent class JPanel. Standard to use the paintCompoment method.	
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();
	}
	
}


