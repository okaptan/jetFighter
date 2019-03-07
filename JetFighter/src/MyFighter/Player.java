package MyFighter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener{
	public static int x;
	public static int y;
	private boolean left, right, up, down;
	private boolean fire;
	private long current;
	private long delay;
	public static int health;
	public static int  highestScore;
	
	public Player() {
		
	}
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void init() {
		Display.frame.addKeyListener(this);
		current = System.nanoTime();
		delay = 50;
		health = 100;
	}
	
	public void tick() {
		if(health > 0) {
			if(left) {
				if(x >= 0) {
					x -= 5;
				}
			}
			if(right) {
				if(x <= 960 - 90) {
					x += 5;
				}
			}
			if(up) {
				if(y >= 0) {
					y-= 5;
				}
			}
			if(down) {
				if(y <= 640-100) {
					y += 5;
				}
			}
		if(fire) {
			long breaks = (System.nanoTime()- current)/1000000;
			if(breaks > delay) {
				gameManager.bullet.add(new Bullet(x+20,y));
			}
			current = System.nanoTime();
		}
	}
	}
	public void render(Graphics g) {
		if(health > 0) {	
			g.drawImage(loadImage.myj,x, y, 72, 100,null);
		}
		g.setColor(Color.RED);
		g.fillRect(5, 5, 200, 50);
		g.setColor(Color.GREEN);
		g.fillRect(5, 5, health*2, 50);
		g.setColor(Color.MAGENTA);
		g.drawRect(5, 5, 200, 50);
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT) {
			left = true;
		}
		if(keyCode == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if(keyCode == KeyEvent.VK_UP) {
			up = true;
		}
		if(keyCode == KeyEvent.VK_DOWN) {
			down = true;
		}
		if(keyCode == KeyEvent.VK_SPACE) {
			fire = true;
		}
	}

	
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT) {
			left = false;
		}		
		if(keyCode == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if(keyCode == KeyEvent.VK_UP) {
			up = false;
		}
		if(keyCode == KeyEvent.VK_DOWN) {
			down = false;
		}
		if(keyCode == KeyEvent.VK_SPACE) {
			fire = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public  int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
}
