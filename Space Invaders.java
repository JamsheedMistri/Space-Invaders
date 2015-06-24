package SpaceInvaders;

import java.util.ArrayList;

import apcs.Window;

public class SpaceInvaders {
	static boolean alive = false, menu = true, deadmenu = false;
	static int[] starx = new int[200];
	static int[] stary = new int[200];

	static int[] megax = new int[20];
	static int[] megay = new int[20];	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Window.size(1200, 800);

		for (int i = 0; i < starx.length; i++) {
			starx[i] = Window.rollDice(Window.width());
			stary[i] = Window.rollDice(Window.height());
		}

		for (int i = 0; i < megax.length; i++) {
			megax[i] = Window.rollDice(Window.width());
			megay[i] = Window.rollDice(Window.height());
		}

		player p = new player();

		//Arraylist of player bullets
		ArrayList<playerbullet> pbs = new ArrayList<playerbullet>();

		//Arraylist of es
		ArrayList<enemy> es = new ArrayList<enemy>();

		//arraylist of enemy bullets
		ArrayList<enemybullet> ebs = new ArrayList<enemybullet>();

		for (int i = 0; i < 1000; i++) {
			es.add(new enemy());
		}

		while (true) {
			if (menu) {
				drawBackground();
				Window.out.font("monospaced", 100);
				Window.out.color(0, 255, 0);
				Window.out.print("SPACE INVADERS", Window.width() / 2 - 400, Window.height() / 2 - 200);

				Window.out.color(0, 179, 255);
				Window.out.font("monospaced", 30);
				Window.out.print("Controls:", Window.width() / 2 - 25, Window.height() / 2 + 100);
				Window.out.print("Right Arrow Key = move right", Window.width() / 2 - 200, Window.height() / 2 + 170);
				Window.out.print("Left Arrow Key = move left", Window.width() / 2 - 200, Window.height() / 2 + 140);
				Window.out.print("Space Bar = fire", Window.width() / 2 - 100, Window.height() / 2 + 200);

				Window.out.color(255, 179, 0);
				Window.out.font("monospaced", 50);
				Window.out.print("PRESS SPACE BAR TO START", Window.width() / 2 - 350, Window.height() / 2 - 80);
				Window.out.print("GOOD LUCK!", Window.width() / 2 - 125, Window.height() / 2 - 30);

				Window.out.image("space-invader.png", Window.width() / 2 - 300, Window.height() / 2 + 300);
				Window.out.image("AuLmBrg.png", Window.width() / 2 + 300, Window.height() / 2 + 300);

				if (Window.key.pressed("space")) {
					alive = true;
					menu = false;
				}

				Window.frame();
			}
			if (alive) {
				drawBackground();
				//drawMega();

				p.draw();
				p.move();

				if (p.shoot()) {
					pbs.add(new playerbullet(p.x, p.y));
				}

				for (int i = 0; i < es.size(); i++) {
					es.get(i).draw();
					es.get(i).move();

					if (es.get(i).shoot()) {
						ebs.add(new enemybullet(es.get(i).x, es.get(i).y));
					}
				}

				for (int i = 0; i < pbs.size(); i++) {
					pbs.get(i).draw();
					pbs.get(i).move();
				}

				for (int i = 0; i < ebs.size(); i++) {
					ebs.get(i).draw();
					ebs.get(i).move();
					if (ebs.get(i).y > Window.height() + ebs.get(i).height / 2){
						ebs.remove(i);
						i--;
					}

				}

				for (int i = 0; i < ebs.size(); i++) {
					if (ebs.get(i).checkCollision(p)) {
						ebs.remove(i);
						i--;
						alive = false;
						deadmenu = true;
					}
				}

				for (int i = 0; i < pbs.size(); i++) {
					enemyLoop: for (int j = 0; j < es.size(); j++) {
						if (pbs.get(i).checkCollision(es.get(j))) {
							pbs.remove(i);
							es.remove(j);
							i--;
							break enemyLoop;
						}
					}
				}
				for (int i = 0; i < pbs.size(); i++) {
					for (int j = 0; j < ebs.size(); j++) {
						if (pbs.get(i).checkCollision(ebs.get(j))) {
							pbs.remove(i);
							ebs.remove(j);
							i--;
							break;
						}
					}
				}
				Window.frame();
			}
			if (deadmenu) {
				drawMega();
				Window.out.font("monospaced", 30);
				Window.out.print("THE ALIENS HAVE TAKEN OVER THE UNIVERSE!", Window.width() / 2 - 400, Window.height() / 2);
				Window.out.print("PRESS SPACE TO RESTART! PRESS 'M' TO GO BACK TO THE MENU!", Window.width() / 2 - 500, Window.height() / 2 + 100);

				if (Window.key.pressed("space")) {
					p.reset();
					es.clear();
					pbs.clear();
					ebs.clear();
					for (int i = 0; i < 250; i++) {
						es.add(new enemy());
					}
					alive = true;
					deadmenu = false;
				}
				else if (Window.key.pressed("m")) {
					deadmenu = false;
					menu = true;
				}
				Window.frame();
			}


		}





	}

	public static void drawBackground() {
		Window.out.background("black");
		for (int i = 0; i < starx.length; i++) {
			Window.out.color("white");
			Window.out.circle(starx[i], stary[i], Window.rollDice(4));
		}
	}

	public static void drawMega() {
		for (int i = 0; i < megax.length; i++) {
			Window.out.color("red");
			Window.out.circle(megax[i], megay[i], Window.rollDice(10));
		}
	}

}
