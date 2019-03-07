package MyFighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu implements MouseListener{
	
	public Rectangle play;
	public Rectangle highScores;
	public Rectangle quit;
	public Rectangle retn;
	public int mx,my;	
	public boolean clicked;

	
	
	void init() {
		play = new Rectangle(gameSetUp.gameWidth/2 - 40, 150, 100, 50);
		highScores = new Rectangle(gameSetUp.gameWidth/2 - 40, 250, 220, 50);
		quit = new Rectangle(gameSetUp.gameWidth/2 - 40, 350, 100, 50);
		retn = new Rectangle(gameSetUp.gameWidth/2 - 200, 300, 400, 50);
		Display.canvas.addMouseListener(this);
	}
	
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font fnt2 = new Font("ariel", Font.BOLD, 30);
		Font fnt = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt);
		if(Player.health <= 0) {
			g.setFont(new Font("arial", Font.BOLD, 80));
			g.setColor(Color.WHITE);
			g.drawString("Your Score is : " + gameManager.score, gameSetUp.gameWidth/2 - 320, gameSetUp.gameHeight/2 - 100);
			g.setFont(new Font("arial", Font.BOLD, 40));
			g.drawString("Return to main menu", retn.x + 5, retn.y + 40);
			g2d.draw(retn);
		}
		else {
			g.setColor(Color.WHITE);
			g.drawString("Space Game", gameSetUp.gameWidth/4 + 100, 100);
			g.setFont(fnt2);
			g.drawString("PLAY", play.x + 10, play.y +35);
			g.drawString("HIGH SCORES", highScores.x + 10, highScores.y +35);
			g.drawString("QUIT", quit.x + 10, quit.y +35);
			g2d.draw(play);
			g2d.draw(highScores);
			g2d.draw(quit);
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}




	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}




	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		if(Player.health <= 0) {
			if(mx >= gameSetUp.gameWidth/2 - 200 && mx <= gameSetUp.gameWidth/2 + 200) {
				if(my >= 300 && my <= 350) {
					gameManager.start = false;
					Player.health = 100;
					gameManager.score = 0;
					gameManager.done = false;
					gameManager.flag = false;
				}	
			}
		}
		if(!gameManager.start) {
			if(Player.health > 0) {
			if(mx >= gameSetUp.gameWidth/2 - 40 && mx <= gameSetUp.gameWidth/2 + 60) {
				if(my >= 150 && my <= 200) {
					gameManager.start = true;
				}		
			}
			if(mx >= gameSetUp.gameWidth/2 - 40 && mx <= gameSetUp.gameWidth/2 + 180) {
				if(my >= 250 && my <= 300) {
				System.out.println("Done");
			}		
		}
		if(mx >= gameSetUp.gameWidth/2 - 40 && mx <= gameSetUp.gameWidth/2 + 60) {
			if(my >= 350 && my <= 400) {
				System.exit(1);
			}
			
		}
	}
			}

	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
