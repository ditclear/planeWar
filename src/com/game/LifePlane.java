package com.game;

import java.awt.Graphics;

public class LifePlane {
	int x;
	int y;
	int width=25;
	int height=21;
	GameStart gs;
	boolean alive;
	public LifePlane(int x, int y, GameStart gs, boolean alive) {
		super();
		this.x = x;
		this.y = y;
		this.gs = gs;
		this.alive = alive;
	}
	public void drawMe(Graphics g){
		g.drawImage(gs.lifePlane, x, y, width, height, null);
	}
}
