package SpaceInvaders;

import apcs.Window;

public class enemy {
	int x, y, side, speed;
	
	public enemy() {
		x = Window.rollDice(Window.width() - 100) + 50;
		y = (Window.rollDice(7) + 1) * 50;
		side = 25;
		speed = 7;
	}
	
	public void draw() {
		//Window.out.color("red");
		//Window.out.square(x, y, side);
		Window.out.image("space-invader.png", x-25, y);
	}
	
	public void move() {
		x+=speed;
		if (x > Window.width() - side || x < side){
			speed*=-1;
			y+=side;
		}
	}
	
	public boolean shoot() {
		if (Window.rollDice(5000) == 1) {
			return true;
		}
		return false;
	}
	
	
}
