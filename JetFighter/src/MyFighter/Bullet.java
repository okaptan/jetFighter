package MyFighter;

import java.awt.Graphics;

public class Bullet {
	private int x;
	private int y;
	public  int speed;
	private boolean enemy;
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void tick() {
		y += speed;
	}
	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
	public void render(Graphics g) {
		if(enemy) {
		g.drawImage(loadImage.enemybull, x, y, 35, 31,null);
		}
		
		else{
			g.drawImage(loadImage.mybull, x, y, 30, 35,null);
		}
		
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setEnemy(boolean enemy){
		this.enemy = enemy;
	}
}
