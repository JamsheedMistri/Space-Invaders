package SpaceInvaders;

import apcs.Window;

public class playerbullet {
	int x, y, width, height, speed;
	
	public playerbullet(int x, int y) {
		this.x = x;
		this.y = y;
		width = 5;
		height = 10;
		speed = 10;
		
		
	}
	
	public void draw() {
		Window.out.color("gold");
		Window.out.rectangle(x, y, width, height);
	}
	
	public void move() {
		y -= speed;
	}
	
	public boolean checkCollision(enemy e) {
		if (Math.abs(x - e.x) <= width / 2 + e.side / 2 && Math.abs(y - e.y) <= height / 2 + e.side / 2) {
			return true;
		}
		return false;
	}
	
	public boolean checkCollision (enemybullet e) {
		if (Math.abs(x - e.x) <= width / 2 + e.width / 2 && Math.abs(y - e.y) <= height / 2 + e.height / 2) {
			return true;
		}
		return false;
	}
}
