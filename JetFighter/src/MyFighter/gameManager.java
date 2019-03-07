package MyFighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class gameManager{
	private Scanner scan;
	static boolean flag = false;
	static boolean done = false;
	private Player player;
	public static ArrayList<Bullet> bullet;
	private ArrayList<Enemy> enemies;
	public static ArrayList<Bullet> enemybullet;
	private long current;
	private long delay;
	public static int score;
	String line;
	public static boolean start;
	private Menu menu;
	int exscore;
    File file;
    BufferedWriter out;
    BufferedReader br;
	
	public gameManager() {
		
	}
	
	
	public void init() throws IOException {
		player = new Player(gameSetUp.gameWidth/2 - 50,gameSetUp.gameHeight-97);
		player.init();
		bullet = new ArrayList<Bullet>();
		enemybullet = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		current = System.nanoTime();
		scan = new Scanner("HighScores.txt");
		delay = 2000;
		score = 0;
		menu = new Menu();
		menu.init();
		file = new File("HighScores.txt");
		out = new BufferedWriter(new FileWriter("HighScores.txt"));
		br = new BufferedReader(new FileReader(file));
		
	}

	public void tick() {
		if(start) {
			player.tick();
			for(int i = 0; i < bullet.size(); i++) {
				bullet.get(i).setEnemy(false);
				bullet.get(i).setSpeed(-10);
				bullet.get(i).tick();
			}
			
			for(int i = 0; i < enemybullet.size(); i++) {
				enemybullet.get(i).setEnemy(true);
				enemybullet.get(i).setSpeed(5);
				enemybullet.get(i).tick();
			}
			long breaks = (System.nanoTime() - current)/ 1000000;
			if(breaks > delay) {
				for(int i = 0; i < 2; i++) {
					Random rand = new Random();
					int randX = rand.nextInt(960);
					if(Player.health > 0) {
						enemies.add(new Enemy(randX, 0));
					}
				}
				current = System.nanoTime();
			}
			for(int i = 0; i < enemies.size(); i++) {
				enemies.get(i).tick();
			}
		}
	}

	public void render(Graphics g) throws IOException {
		if(!start) {
			menu.render(g);
		}
		if(start) {
			if(Player.health <= 0) {
				menu.render(g);
			}
		player.render(g);
		for(int i = 0; i < bullet.size(); i++) {
			bullet.get(i).render(g);
		}
		for(int i = 0; i < bullet.size(); i++) {
			if(bullet.get(i).getY() <= 0) {
				bullet.remove(i);
				i--;
			}
		}
		for(int i = 0; i < enemybullet.size(); i++) {
			enemybullet.get(i).render(g);
		}
		for(int i = 0; i < enemybullet.size(); i++) {
			if(enemybullet.get(i).getY() >= 640) {
				enemybullet.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			if(!(enemies.get(i).getX() <= 0 || enemies.get(i).getX() >= 960 - 55 || enemies.get(i).getY() >= 640 - 55)) {
				if(enemies.get(i).getY()>=0) {
					enemies.get(i).render(g);
				}
			}
			else {
				enemies.remove(i);
			}
		}
		for(int i = 0; i < enemies.size(); i++) {
			int pxx = player.getX();
			int pyy = player.getY();
			for(int j = 0 ; j < enemybullet.size(); j++) {
				int bxx = enemybullet.get(j).getX();
				int byy = enemybullet.get(j).getY();
				if(pxx < bxx + 35 && pxx + 90 > bxx && pyy < byy + 35 && pyy + 90 > byy) {
					Player.health -=5;
					enemybullet.remove(j);
					if(Player.health <= 0) {
					  

					   
					    	while ((line = br.readLine()) != null) {
					    		if(line.equals(Main.name) && !done) {
					    			line = br.readLine();
					    			Integer.parseInt(line);
					    			flag = true;
					    			if(exscore < score) {
					    				String sScore = String.valueOf(score);
					    				line = line.replaceAll(line , sScore);
					    				System.out.println("Here1");
					    				done = true;
					    			}
					    		}
					    	}
					   
					    if(!flag && !done) {
					    	Files.highScoreWrite(Main.name, score);
					    	System.out.println("Here2");
					    	System.out.println("Exscore is " + exscore);
					    	done = true;
					    }
						
						enemies.removeAll(enemies);
						enemybullet.removeAll(enemybullet);
						bullet.removeAll(bullet);
						Player.health = 0;
						Player.x = gameSetUp.gameWidth/2 - 50;
						Player.y = gameSetUp.gameHeight-97;
					}
				}
			}
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			int ex = enemies.get(i).getX();
			int ey = enemies.get(i).getY();
			
			int px = player.getX();
			int py = player.getY();
			
			if(px < ex + 55  && px + 90 > ex && py < ey +55 && py + 90 > ey) {
				enemies.remove(i);
				i--;
				Player.health-=10;
				if(Player.health <= 0) {
				      
				    	while ((line = br.readLine()) != null && !done) {
				    		if(line.equals(Main.name)) {
				    			line = br.readLine();
				    			flag = true;
				    			int exscore = Integer.parseInt(line);
				    			if(exscore < score) {
				    				System.out.println("Here3");
				    				String sScore = String.valueOf(score);
				    				line = line.replaceAll(line , sScore);
				    			}
				    		}
				    	}
		
				    if(!flag && !done) {
				    	Files.highScoreWrite(Main.name, score);
				    	System.out.println("Here4");
				    }
					
					
					enemies.removeAll(enemies);
					enemybullet.removeAll(enemybullet);
					bullet.removeAll(bullet);
					Player.health = 0;
					Player.x = gameSetUp.gameWidth/2 - 50;
					Player.y = gameSetUp.gameHeight-97;
				}
			}
			
			for(int j = 0; j < bullet.size(); j++) {
				int bx = bullet.get(j).getX();
				int by = bullet.get(j).getY();
				
				
				
				
				if(ex < bx + 30 && ex + 55 > bx && ey < by + 30 && ey + 55 > by) {
					enemies.remove(i);
					i--;
					bullet.remove(j);
					j--;
					score = score +1;
				}
			}
			
			g.setColor(Color.ORANGE);
			g.setFont(new Font("arial", Font.BOLD,40));
			g.drawString("Score: "+ score, 780, 32);
			
		}
	
	}
	
	
	
	}


	
}
