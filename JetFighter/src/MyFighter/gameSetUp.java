package MyFighter;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class gameSetUp implements Runnable{
	private String title;
	private int width;
	private int height;
	private Thread thread;
	private boolean running;
	private Display display;
	private BufferStrategy buffer;
	private Graphics g;
	private gameManager manager;
	public static final int gameWidth = 960;
	public static final int gameHeight = 640;
	
	public gameSetUp(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	public void init() throws IOException {
		display = new Display(title, width, height);
		loadImage.init();
		manager = new gameManager();
		manager.init();
	}
	
	public synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop() {
		if(!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	public void tick() {
		manager.tick();
	}
	public void render() throws IOException {
		buffer = display.getCanvas().getBufferStrategy();
		if(buffer == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		
		g.drawImage(loadImage.background ,0, 0, gameWidth, gameHeight, null);
		manager.render(g);
		
		
		buffer.show();
		g.dispose();
	}
	
	
	
	
	
	
	
	public void run() {
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long last = System.nanoTime();
		final double amountOfTicks = 60.0;
		double nanosecs = 1000000000/amountOfTicks;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta = delta + (now - last)/nanosecs;
			last = now; 
			if(delta >= 1) {
				tick();
				updates++; 
				delta--;
			}
			try {
				render();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frames++;
			
		}
	}
}
