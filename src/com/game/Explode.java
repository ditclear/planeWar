package com.game;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class Explode {
	int x;
	int y;
	int width = 96;
	int height = 96;
	GameStart gs;
	int index = 0;
	public boolean alive;
	boolean isBoss;
	public Explode(int x, int y, GameStart gs,boolean alive) {
		super();
		this.x = x;
		this.y = y;
		this.gs = gs;
		this.alive=alive;
	}
	public Explode(int x, int y, GameStart gs, boolean alive, boolean isBoss) {
		super();
		this.x = x;
		this.y = y;
		this.gs = gs;
		this.alive = alive;
		this.isBoss = isBoss;
	}

	public void drawMe(Graphics g) {
		if (index>=10)
			alive = false;
		if (isBoss) {
			g.drawImage(gs.boomImgs[index], x-96, y-96, width*2, height*2, null);
		}else {
			g.drawImage(gs.boomImgs[index], x, y, width, height, null);
		}
		
		index++;
		
		
	}

}
