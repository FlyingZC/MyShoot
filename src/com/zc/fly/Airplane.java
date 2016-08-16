package com.zc.fly;

import java.util.Random;

public class Airplane extends FlyingObject implements Enemy{
	//每次移动3
	private int speed=3;
	
	public Airplane(){
		image=ShootGame.airplane;
		height=image.getHeight();
		width=image.getWidth();
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-width);
		y=-height;
	}
	
	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public void step() {
		y+=speed;
	}
}
