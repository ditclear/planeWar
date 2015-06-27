package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Enemy {
	int x;
	int y;
	int fx;
	int width=68;
	int height=68;
	boolean alive;
	Image enemyImg;
	GameStart gs;
	int k=1;
	Random random=new Random();
	
	public Enemy(int x, int y, boolean alive,GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.fx=x;
		this.alive = alive;
		this.gs = gs;
		this.enemyImg=gs.enemyImgs[random.nextInt(4)];
	}
	public void drawMe(Graphics g){
		isHitted();
		if(alive){
			g.drawImage(enemyImg, x, y, width, height, null);
		}else {
			gs.explodes.add(new Explode(x, y, gs,true));
			gs.count++;
			gs.score+=100;
			gs.enemies.remove(this);
		}
	
		move();
		if(random.nextInt(100)>97)
			fire();
	}
	public void fire() {
		// TODO Auto-generated method stub
		BulletEm bulletEm=new BulletEm(true, gs, this);
		gs.bulletEms.add(bulletEm);
	}
	public void isHitted(){
		for (int j = 0; j < gs.plane.bullets.size(); j++) {
			Bullet pBullet=gs.plane.bullets.get(j);
			if (pBullet.getRectangle().intersects(getRectangle())) {
				alive=false;
				pBullet.alive=false;
			}
		}
		for (int j = 0; j < gs.plane.ults.size(); j++) {
			Ult ult=gs.plane.ults.get(j);
			if (ult.getRectangle().intersects(getRectangle())) {
				alive=false;
			}
		}
		Plane plane=gs.plane;
		if(plane.alive&&plane.getRectangle().intersects(getRectangle())){
			alive=false;
			plane.count--;
			if (plane.count==0) {
				plane.alive=false;
			}
			
		}
	}
	public Rectangle getRectangle(){
		return new Rectangle(x, y, width, height);
	}
	public void move() {
		if(fx>=500){
			x-=3;
		}else if (fx<=100){
			x+=3;
		}
		y+=5;
		if(y>700)alive=false;
	}
	
}
