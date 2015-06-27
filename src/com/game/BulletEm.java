package com.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BulletEm {
	int x;
	int y;
	int width=3;
	int height=4;
	boolean alive;
	GameStart gs;
	Enemy enemy;
	Graphics g;
	public BulletEm(boolean alive,GameStart gs,Enemy enemy) {
		super();
		this.y = enemy.y+80;
		this.x=enemy.x+34;
		this.alive = alive;
		this.gs = gs;
		this.enemy=enemy;
	}
	public void drawMe(Graphics g){
		isHitted();
		if (alive) {
			g.drawImage(gs.bulletEm1Img, x,y, width, height, null);
		}
		move();
	}
	public void isHitted(){
		Plane plane=gs.plane;
		if (plane.alive&&plane.getRectangle().intersects(getRectangle())) {
			alive=false;
			plane.count--;
			plane.canK=false;
			plane.canL=false;
			if (plane.count==0) {
				gs.explodes.add(new Explode(plane.x, plane.y, gs,true));
				plane.alive=false;
			}
			
		}
	}
	public void move(){
		y+=8;
		if(y>700){
			alive=false;
		}
	}
	public void ult(){
		
	}
	public Rectangle getRectangle(){
		return new Rectangle(x, y, width, height);
	}
}
