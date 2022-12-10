package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		// Creates new game window
		JFrame window = new JFrame();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows window to close when "x" is pressed
		window.setResizable(false); // Retains screen size
		window.setTitle("A Tale of Jamis"); // Sets name of window

		// Essentially a JPanel Class with a bit more functions
		GamePanel gamepanel = new GamePanel();
		window.add(gamepanel);

		window.pack(); // sets window to size specified in GamePanel

		window.setLocationRelativeTo(null); // Positions screen in the center
		window.setVisible(true); // screen can be visible

		gamepanel.startGameThread(); // Starts game

	}

}
