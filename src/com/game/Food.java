package com.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Food {
	int x;
	int y;
	int width=34;
	int height=36;
	GameStart gs;
	boolean alive;
	int index;
	int k=1;
	int fx1;
	int fx2;
	Random random=new Random();
	public Food(int x, int y, GameStart gs, boolean alive) {
		super();
		this.x = x;
		this.y = y;
		this.fx1=x-100;
		this.fx2=x+100;
		this.gs = gs;
		this.alive = alive;
		index=random.nextInt(2);
	}
	public void drawMe(Graphics g){
		
		g.drawImage(gs.foodImgs[index], x, y, width, height, null);
		move();
		isEaten();
	}
	public void move() {
		// TODO Auto-generated method stub
		if(x<fx1||x>fx2)k=-k;
		x+=5*k;
		y+=6;
		if(y>700){
			alive=false;
		}
	}
	public void isEaten(){
		if(gs.plane.getRectangle().intersects(getRectangle())){
			alive=false;
			if(index==0){
				gs.plane.canL=true;
			}else {
				gs.plane.canK=true;
			}
		}
	}
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
