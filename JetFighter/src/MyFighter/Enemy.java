package MyFighter;

import java.awt.Graphics;

public class Enemy {
	private int x;
	private int y;
	private int count = 65;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	public void init() {
	}
	
	public void tick() {
		y+=1;
		if(count % 80 == 0) {
			fires();
			count = 0;
		}
		count++;
		
	}
	
	public void render(Graphics g) {
		g.drawImage(loadImage.enemyj ,x , y, 55, 55, null);
		
	}
	
	public int getX() {
		return x;
	}
	
	
	public int getY() {
		return y;
	}
	
	public void fires() {
		gameManager.enemybullet.add(new Bullet(x+9,y));
		
	}
	
}
