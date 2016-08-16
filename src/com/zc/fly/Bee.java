package com.zc.fly;

import java.util.Random;

public class Bee extends FlyingObject implements Award{
	
	private int xSpeed=1;
	private int ySpeed=2;//移动速度
	
	private int awardType;
	
	public Bee(){
		image=ShootGame.bee;
		width=image.getWidth();
		height=image.getHeight();
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-width);
		//y初始值为
		y=-height;
	}
	
	@Override
	public int getType() {
		
		return 0;
	}

	@Override
	public void step() {
		x+=xSpeed;
		y+=ySpeed;
		//如果碰墙
		if(x>ShootGame.WIDTH-width){
			xSpeed=-1;
		}
		if(x<0){
			xSpeed=1;
		}
	}

}
