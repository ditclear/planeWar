package com.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BulletBoss {
	int x;
	int y;
	int width;
	int height;
	int fx;
	boolean alive;
	GameStart gs;
	Boss boos;
	Plane plane;
	Random random = new Random();
	int index = random.nextInt(10);
	public BulletBoss(boolean alive, GameStart gs, Boss boos) {
		super();
		this.y = boos.y+50;
		this.x = boos.x;
		this.alive = alive;
		this.gs = gs;
		this.boos = boos;
		this.plane = gs.plane;
	}

	public void drawMe(Graphics g) {
		
		if (alive) {
			if(index >= 9){
				fx=x+20;
				height=width=34;
				g.drawImage(gs.bulletBossImgs[1], fx, y, width, height, null);
				y += 8;
				if (plane.alive&&plane.getRectangle().intersects(getRectangle(fx,y,34,34))) {
					alive = false;
					plane.count--;
					System.out.println("plane count:"+plane.count);
					plane.canK=false;
					if (plane.count==0) {
						gs.explodes.add(new Explode(plane.x, plane.y, gs, true));
						plane.alive = false;
					}
				}
			}else if(index<=3){
				height=11;
				width=13;
				fx=x+400;
				g.drawImage(gs.bulletBossImgs[0], x+400, y+100, height, width, null);
				move();
				if (plane.alive&&plane.getRectangle().intersects(getRectangle(fx,y+100,15,13))) {
					alive = false;
					plane.count--;
					System.out.println("plane count:"+plane.count);
					plane.canL=false;
					if (plane.count==0) {
						gs.explodes.add(new Explode(plane.x, plane.y, gs, true));
						plane.alive = false;
					}
				}
			}
		}
		move();
	}

	public void isHitted() {
		
		
	}

	public void move() {
		y += 6;
		if (y > 700) {
			alive = false;
		}
	}

	public Rectangle getRectangle(int... a) {
		return new Rectangle(a[0],a[1],a[2],a[3]);
	}
}
