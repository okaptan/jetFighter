package MyFighter;

import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
	static String name;
	static String password;

	public static void main(String[] args) throws FileNotFoundException {
		String schoice;
		int ichoice;
		schoice = JOptionPane.showInputDialog("1.Log in\n2.Create new user");	
		ichoice = Integer.parseInt(schoice);
		
		if(ichoice == 2) {
			name = JOptionPane.showInputDialog("Enter name");
			password = JOptionPane.showInputDialog("Enter password");
			if(Files.checkName(name)) {
				Files.save(name, password);	
			}
			else {
				while(true) {
					JOptionPane.showMessageDialog(null, "Another user have this name please change your name", "Error", JOptionPane.ERROR_MESSAGE);
					name = JOptionPane.showInputDialog("Enter name");
					password = JOptionPane.showInputDialog("Enter password");
					if(Files.checkName(name)) {
						name = JOptionPane.showInputDialog("Enter name");
						password = JOptionPane.showInputDialog("Enter password");
						break;
					}
				}
			}
		}
		else if(ichoice == 1) {
			while(true) {
				name = JOptionPane.showInputDialog("Enter name");
				password = JOptionPane.showInputDialog("Enter password");
				if(Files.search(name, password)) {
					JOptionPane.showMessageDialog(null, "Welcome to my game", "Welcome", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				else {
					JOptionPane.showMessageDialog(null, "Please try again", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		
		}
		
		
		
		
		gameSetUp game = new gameSetUp("SkyFighter Game", 960, 640);
		game.start();
	}

}
