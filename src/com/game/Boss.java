package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss {
	int x;
	int y;
	int fx;
	int width = 424;
	int height = 128;
	GameStart gs;
	boolean alive;
	int k = 1;
	int count = 0;
	int blood = 200;
	Random random = new Random();
	public List<BulletBoss> bullets = new ArrayList<BulletBoss>();

	public Boss(int x, int y, GameStart gs, boolean alive) {
		super();
		this.x = x;
		this.fx = x;
		this.y = y;
		this.gs = gs;
		this.alive = alive;
	}

	public void isHitted() {
		for (int j = 0; j < gs.plane.bullets.size(); j++) {
			Bullet pBullet = gs.plane.bullets.get(j);
			if (alive && pBullet.getRectangle().intersects(getRectangle())) {
				blood -= 5;
				expolde();
				pBullet.alive = false;
			}
		}
		for (int j = 0; j < gs.plane.ults.size(); j++) {
			Ult ult = gs.plane.ults.get(j);
			if (alive && ult.getRectangle().intersects(getRectangle())) {
				blood -= 10;
				expolde();
			}
		}
	}

	public void expolde() {
		if (blood <= 0 && alive) {
			alive = false;
			gs.score += 1000;
			gs.explodes.add(new Explode(x + 212, y + 64, gs, true,true));
			gs.count = 0;
			gs.level++;
			gs.bossTime+=10;
			gs.plane.count++;
			if (gs.plane.count>5) {
				gs.plane.count=5;
			}
		}
	}

	public void drawMe(Graphics g) {
		isHitted();
		if (alive) {
			g.setColor(Color.WHITE);
			g.drawRect(x + 117, y - 17, 200, 11);
			g.setColor(Color.RED);
			g.fillRect(x + 118, y - 16, blood - 1, 10);
			g.drawImage(gs.boosImg, x, y, width, height, null);
			move();
			if (random.nextInt(100) > 80) {
				fire();
			}
		}else {
			blood=200;
		}
		System.out.println("boss bullets:" + bullets.size());
		for (int i = 0; i < bullets.size(); i++) {
			BulletBoss bulletBoss = bullets.get(i);
			if (bulletBoss.alive) {
				bulletBoss.drawMe(g);
			} else {
				bullets.remove(i);
			}
		}
	}

	private void fire() {
		// TODO Auto-generated method stub
		bullets.add(new BulletBoss(true, gs, this));
	}

	private void move() {
		// TODO Auto-generated method stub
		if (x > 400 || x < -200)
			k = -k;
		x += 5 * k;
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
