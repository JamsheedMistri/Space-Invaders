package SpaceInvaders;

import apcs.Window;

public class player {
	int x, y, width, height, cooldown;
	
	public player() {
		x = Window.width() / 2;
		y = Window.height() - 100;
		width = 50;
		height = 20;
		cooldown = 5;
		
	}
	
	public void draw() {
		//Window.out.color("green");
		//Window.out.rectangle(x, y, width, height);
		
		Window.out.image("AuLmBrg.png", x-25, y);
	}
	
	public void move() {
		if (Window.key.pressed("right") && x < Window.width() - width / 2) {
			x += 5;
		}
		else if (Window.key.pressed("left") && x > width / 2) {
			x -= 5;
		}
		cooldown++;
	}
	
	public boolean shoot() {
		if (Window.key.pressed("space") && cooldown >= 5) {
			cooldown = 0;
			return true;
			
		}
		
		return false;
	}
	
	public void reset() {
		x = Window.width() / 2;
		y = Window.height() - 100;
		width = 50;
		height = 20;
		cooldown = 5;
	}
}
