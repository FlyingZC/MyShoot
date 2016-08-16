package com.zc.fly;

import java.util.Random;

public class Bullet extends FlyingObject{
	private int speed=3;//移动速度
	
	public Bullet(){
		
	}

	public Bullet(int x, int y) {
		image=ShootGame.bullet;
		width=image.getWidth();
		height=image.getHeight();
		Random rand=new Random();
		this.x=x;
		this.y=y;
	}

	@Override
	public void step() {
		y-=speed;
	}
}
