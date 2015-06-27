package com.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Ult {
	int x;
	int y;
	int width=600;
	int height=10;
	GameStart gs;
	boolean alive;
	public Ult(GameStart gs, boolean alive) {
		super();
		this.x=0;
		this.y=gs.height;
		this.gs = gs;
		this.alive = alive;
	}
	public void drawMe(Graphics g){
		if(alive){
			g.drawImage(gs.ult, x, y, width, height, null);
		}
		move();
	}
	public void move(){
		y-=10;
		if(y<0)alive=false;
	}
	public Rectangle getRectangle(){
		return new Rectangle(x, y, width, height);
	}
}
