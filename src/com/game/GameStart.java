package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class GameStart extends Frame {
	int height = 700;
	int width = 600;
	int count = 0;
	int num = 0;
	int score = 0;
	int bossTime = 5;
	int level=1;
	boolean isOver = false;
	Random ran = new Random();
	Background bg = new Background(this);
	Plane plane = new Plane(250, 500, false, this);
	Boss boss = new Boss(30, 50, this, true);
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	List<Enemy> enemies = new ArrayList<Enemy>();
	public List<BulletEm> bulletEms = new ArrayList<BulletEm>();
	List<Explode> explodes = new ArrayList<Explode>();
	List<Food> foods = new ArrayList<Food>();
	Image img, bgImg, bulletImg, planeImg, bulletEmImg, bulletEm1Img, boosImg,
			ult, continueImg, lifePlane, startImg, overImg;
	Image[] enemyImgs, boomImgs, bulletBossImgs, foodImgs;
	public GameStart() {
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setFont(new Font("楷体", Font.PLAIN, 16));
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				plane.keyPressed(e);
				if (!plane.alive && e.getKeyCode() == KeyEvent.VK_ENTER) {
					plane.alive = true;
					plane.isFirst = false;
					plane.count = 5;
					plane.x = 250;
					plane.y = 500;
				}
				if (!plane.alive &&e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					int n = JOptionPane.showConfirmDialog(null, "退出游戏?", "飞机大战",JOptionPane.YES_NO_OPTION);
					System.out.println("n:"+n);
					if (n==0) {
						System.exit(0);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				plane.keyReleased(e);

			}
		});
		new MyThread().start();
	}

	public void initView() {
		bgImg = toolkit.getImage(GameStart.class.getResource("/imgs/bg01.jpg"));
		if (plane.isLeft) {
			planeImg = toolkit.getImage(GameStart.class
					.getResource("/imgs/51.png"));
		} else if (plane.isRight) {
			planeImg = toolkit.getImage(GameStart.class
					.getResource("/imgs/61.png"));
		} else {
			planeImg = toolkit.getImage(GameStart.class
					.getResource("/imgs/7.png"));
		}
		boomImgs = new Image[] {
				toolkit.getImage(GameStart.class.getResource("/imgs/b1.gif")),
				toolkit.getImage(GameStart.class.getResource("/imgs/b2.gif")),
				toolkit.getImage(GameStart.class.getResource("/imgs/b3.gif")),
				toolkit.getImage(GameStart.class.getResource("/imgs/b4.gif")),
				toolkit.getImage(GameStart.class.getResource("/imgs/b5.gif")),
				toolkit.getImage(GameStart.class.getResource("/imgs/b6.gif")),
				toolkit.getImage(GameStart.class.getResource("/imgs/b7.gif")),
				toolkit.getImage(GameStart.class.getResource("/imgs/b8.gif")),
				toolkit.getImage(GameStart.class.getResource("/imgs/b9.gif")),
				toolkit.getImage(GameStart.class.getResource("/imgs/b10.gif")),
				toolkit.getImage(GameStart.class.getResource("/imgs/b11.gif")) };
		enemyImgs = new Image[] {
				toolkit.getImage(GameStart.class.getResource("/imgs/5.png")),
				toolkit.getImage(GameStart.class.getResource("/imgs/21.png")),
				toolkit.getImage(GameStart.class.getResource("/imgs/15.png")),
				toolkit.getImage(GameStart.class.getResource("/imgs/敌机2.png")) };
		bulletImg = toolkit.getImage(GameStart.class
				.getResource("/imgs/子弹1.png"));
		bulletEmImg = toolkit.getImage(GameStart.class
				.getResource("/imgs/敌军子弹.png"));
		bulletEm1Img = toolkit.getImage(GameStart.class
				.getResource("/imgs/敌军子弹1.png"));
		boosImg = toolkit.getImage(GameStart.class
				.getResource("/imgs/BossA.png"));
		bulletBossImgs = new Image[] {
				toolkit.getImage(GameStart.class
						.getResource("/imgs/BOSS子弹.png")),
				toolkit.getImage(GameStart.class.getResource("/imgs/子弹2.png")) };
		ult = toolkit.getImage(GameStart.class.getResource("/imgs/BKILL.png"));
		continueImg = toolkit.getImage(GameStart.class
				.getResource("/imgs/13.png"));

		overImg = toolkit.getImage(GameStart.class
				.getResource("/imgs/fails1.png"));
		foodImgs = new Image[] {
				toolkit.getImage(GameStart.class.getResource("/imgs/食物1.jpg")),
				toolkit.getImage(GameStart.class.getResource("/imgs/22.png")) };
		lifePlane = toolkit.getImage(GameStart.class
				.getResource("/imgs/飞猪boss子弹.png"));
		startImg = toolkit.getImage(GameStart.class
				.getResource("/imgs/gamebegin1.gif"));
	}

	{
		explodes.add(new Explode(100, 100, this, true));
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				initView();
				repaint();
			}
		}
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if (img == null) {
			img = this.createImage(width, height);
		}
		Graphics graphics = img.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		print(graphics);
		g.drawImage(img, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if (!plane.isFirst) {
			bg.drawMe(g);
			System.out.println(count);
			if (ran.nextInt(100) > 97) {
				enemies.add(new Enemy(ran.nextInt(500) + 10, 0, true,
						GameStart.this));
			}
			if (foods.size() <1&&count==10) {
				foods.add(new Food(ran.nextInt(500) + 10, 0, GameStart.this,
						true));
			}
			for (int i = 0; i < enemies.size(); i++) {
				Enemy enemy = enemies.get(i);
				if (enemy.alive) {
					enemy.drawMe(g);
				} else {
					enemies.remove(i);
				}
			}
			for (int i = 0; i < bulletEms.size(); i++) {
				BulletEm bullet = bulletEms.get(i);
				if (bullet.alive) {
					bullet.drawMe(g);
				} else {
					bulletEms.remove(i);
				}
			}

			for (int i = 0; i < explodes.size(); i++) {
				Explode explode = explodes.get(i);
				if (explode.alive) {
					explode.drawMe(g);
				} else {
					explodes.remove(explode);
				}
			}
			if (foods.size()>0) {
				Food food=foods.get(0);
				if (food.alive) {
					food.drawMe(g);
				}else {
					foods.remove(food);
				}
			}
			System.out.println("explode.size:" + explodes.size());
			System.out.println("boostime:"+bossTime+"count:"+count);
			if (count >= bossTime) {
				boss.alive=true;
				boss.drawMe(g);
			}
			g.drawString("第"+level+"关", 500, 50);
			g.drawString("得分：" + score, 500, 80);
		}
		plane.drawMe(g);
	}

	public static void main(String[] args) {
		new GameStart();

	}
}
