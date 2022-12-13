package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import tiles.TileManager;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObjects;

public class GamePanel extends JPanel implements Runnable {
		
	//SCREEN SETTINGS:
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3; //Creates scale to multiply by
		
	//Customizing Screen Settings
	public final int tileSize = originalTileSize * scale; // each tile is 48x48 to adhere to modern screen sizes
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12; 
	public final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	//WORLD SETTINGS:
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	
	//FPS
	int FPS = 60;
		
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(); 	//Allows gamepanel to recognize key input
	Sound sound = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public ObjectSetter oSetter = new ObjectSetter(this);
	Thread gameThread; 						//Keeps program running, automatically calls the run() method

	// ENTITY AND OBJECT
	public Player player = new Player(this,keyH); 	//Creates a new player object
	public SuperObjects obj[] = new SuperObjects[10];   //Alloted 10 slots to display objects, not necessarily 10 total objects.
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //improves game rendering performance 
		this.addKeyListener(keyH); //GamePanel can listen to key input
		this.setFocusable(true);
		
	}

	public void setupGame(){

		oSetter.setObject();  //you want this called before the game even starts

		playMusic(0); //Index for theme music is 0, so we pass 0.

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
				//System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
		
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void paintComponent(Graphics g) {
		
			super.paintComponent(g);    //super imports from parent class JPanel. Standard to use the paintCompoment method.	
			
			Graphics2D g2 = (Graphics2D)g;

			//DRAW TILES
			tileM.draw(g2); 			//Background tiles HAVE to be before player

			//OBJECT
			//check to see if there is an object to even draw
			for(int i = 0; i < obj.length; i++){
				if(obj[i] != null ){
					obj[i].draw(g2, this);
				}
			}

			//DRAW PLAYER
			player.draw(g2);
			
			g2.dispose();
		}

	public void playMusic(int i){

		sound.setFile(i);
		sound.play();
		sound.loop();

	}

	public void stopMusic(){

		sound.stop();
	}
	
	public void playSE(int i){

		sound.setFile(i);
		sound.play();
	}
}


