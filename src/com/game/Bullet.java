package com.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bullet {
	int x;
	int y;
	double speed = 10;
	int width = 6;
	int height = 8;
	boolean alive;
	GameStart gs;
	int bType;
	double π = Math.PI;
	Random ran = new Random();
	double delta;
	
	public Bullet(boolean alive, GameStart gs) {
		super();
		this.x = gs.plane.x + 44;
		this.y = gs.plane.y - 10;
		this.alive = alive;
		this.gs = gs;
	}

	public Bullet(int x, int y, boolean alive, GameStart gs, int isGrape) {
		super();
		this.x = x;
		this.y = y;
		this.alive = alive;
		this.gs = gs;
		this.bType = isGrape;
	}

	public void drawMe(Graphics g) {
		g.drawImage(gs.bulletImg, x, y, width, height, null);
		move();
	}

	/**
	 * 
	 */
	public void move() {
		switch (bType) {
		case 1:
			x -= 2;
			y -= 6;
			break;
		case 2:
			y -= 6;
			break;
		case 3:
			x += 2;
			y -= 6;
			break;
		case 4:
			traceMove();
			break;
		default:
			y -= 5;
			break;
		}
		if (y < 0||y>=700) {
			alive = false;
		}
	}

	private void traceMove() {
		// TODO Auto-generated method stub
		int eSize = gs.enemies.size();
		if (eSize > 0) {
			Enemy enemy = gs.enemies.get(0);
			double deltax = enemy.x - x;
			double deltay = enemy.y - y;
			if (deltax == 0) {
				if (enemy.y>=y ) // 子弹需要下移
					deltax = 0.0000001;
				else
					deltax = -0.0000001;// 子弹需要上移
			}
			if (deltay == 0) {
				if (enemy.x>=x) // 子弹需要右移
					deltay = 0.0000001;
				else
					deltay = -0.0000001;// 子弹需要左移
			}
			if( deltax>0 && deltay>0 )
				delta = Math.atan(fabs(deltay / deltax)); // 第一项限
			else if( deltax<0 && deltay>0 )
				delta = π - Math.atan(fabs(deltay / deltax)); // 第二项限
			else if( deltax<0 && deltay<0 )  
				delta = π + Math.atan(fabs(deltay/deltax)); // 第三项限
			else
				delta = 2*π - Math.atan(fabs(deltay/deltax));
			System.out.println("delta:"+delta);
			x += speed * Math.cos(delta);
			y += speed * Math.sin(delta);
		}else {
			y-=5;
		}

	}

	private double fabs(double d) {
		// TODO Auto-generated method stub
		if (d < 0) {
			d = -d;
		}
		return d;
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
