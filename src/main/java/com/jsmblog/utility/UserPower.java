package com.jsmblog.utility;

public enum UserPower {
	
	P000(0),
	P010(10),
	P020(20),
	P030(30),
	P040(40),
	P050(50),
	P060(60),
	P070(70),
	P080(80),
	P090(90),
	P100(100);
	
	public final int power;
	
	UserPower(int power){
		this.power = power;
	}

}
