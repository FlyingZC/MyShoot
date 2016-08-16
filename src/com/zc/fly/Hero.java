package com.zc.fly;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject{
	//发射子弹
	//英雄贴图
	protected BufferedImage[] images={};
	//图片交替的计数
	protected int index=0;
	//双倍火力
	private int doubleFire;
	//增命
	private int life;
	
	public Hero(){
		life=3;
		doubleFire=0;
		image=ShootGame.hero0;
		images=new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
		width=image.getWidth();
		height=image.getHeight();
		x=150;
		y=400;
	}

	@Override
	public void step() {
		if(images.length>0){
			//切换图片0,1??
			image=images[index++/10%images.length];
		}
	}
	
}
