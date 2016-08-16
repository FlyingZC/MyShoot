package com.zc.fly;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//界面.继承JPanel
public class ShootGame extends JPanel{
	public static final int WIDTH=400;
	public static final int HEIGHT=654;
	
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	//飞机贴图
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	
	//存放敌飞机,蜜蜂
	private FlyingObject[] flyings;
	//
	private Bullet[] bullets;
	private Hero hero;
	
	//飞行物入场计数
	private int flyEnteredIndex=0;
	
	//定时器
	private Timer timer;
	//时间间隔
	private int intervel=1000/100;
	
	//构造方法
	public ShootGame(){
		//初始化一只蜜蜂一架飞机
		flyings=new FlyingObject[2];
		flyings[0]=new Airplane();
		flyings[1]=new Bee();
		//
		bullets=new Bullet[1];
		bullets[0]=new Bullet(200,300);
		
		hero=new Hero();
	}
	
	
	static{
		try {
			background=ImageIO.read(ShootGame.class.getResource("background.png"));
			airplane=ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bee=ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet=ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0=ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1=ImageIO.read(ShootGame.class.getResource("hero1.png"));
			pause=ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover=ImageIO.read(ShootGame.class.getResource("gameover.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		JFrame jf=new JFrame("Fly");
		ShootGame game=new ShootGame();//面板对象
		//将JPanel添加到JFrame中
		jf.add(game);
		jf.setSize(WIDTH,HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);//设置窗体初始位置
		jf.setVisible(true);
		
		//入场飞行物,移动,重绘
		game.action();
		
	}
	
	
	
	
	//重写JPanel中的paint()
	@Override
	public void paint(Graphics g) {
		paintHero(g);
		paintBullets(g);
		paintFlyingObjects(g);
	}
	
	public void paintHero(Graphics g){
		//
		g.drawImage(hero.getImage(),hero.getX(),hero.getY(),null);
	}
	
	public void paintBullets(Graphics g){
		for(int i=0;i<bullets.length;i++){
			g.drawImage(bullets[i].image,bullets[i].getX(),bullets[i].getY(),null);
		}
	}
	
	public void paintFlyingObjects(Graphics g){
		for(int i=0;i<flyings.length;i++){
			FlyingObject fly=flyings[i];
			g.drawImage(fly.image,fly.getX(),fly.getY(),null);
		}
	}
	
	
	/**
	 * 定时入场飞行物,并移动,再重绘
	 * */
	public void action(){
		timer=new Timer();
		//该方法的使用??
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				enterAction();//飞行物进场
				stepAction();//走一步
				//***重绘***
				repaint();
			}
		}, intervel, intervel);
	}
	
	
	
	/**
	 * 随机生成飞行物
	 * @return 飞行物体
	 * */
	public static FlyingObject nextOne(){
		Random rand=new Random();
		//随机数.产生蜜蜂的几率
		int type=rand.nextInt(20);
		if(type==0){
			return new Bee();
		}else{
			return new Airplane();			
		}
	}
	
	
	/**
	 * 添加新的飞行物入场
	 * */
	public void enterAction(){
		flyEnteredIndex++;
		//每过
		if(flyEnteredIndex%40==0){
			FlyingObject fly=nextOne();
			//飞行物数组扩容.(以后考虑替换)
			flyings=Arrays.copyOf(flyings,flyings.length+1);
			//将飞行物放到末尾
			flyings[flyings.length-1]=fly;
		}
	}
	
	/**
	 * 飞行物移动
	 * */
	public void stepAction(){
		//飞行物移动
		for(int i=0;i<flyings.length;i++){
			//每个飞行物移动
			flyings[i].step();
		}
		//子弹移动
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();
		}
		
		//飞机移动
		hero.step();
	}
	
}
