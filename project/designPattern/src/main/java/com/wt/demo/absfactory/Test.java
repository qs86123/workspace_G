package com.wt.demo.absfactory;

import com.wt.demo.absfactory.animal.ICat;
import com.wt.demo.absfactory.animal.IDog;
import com.wt.demo.absfactory.fac.BlackAnimalFactory;
import com.wt.demo.absfactory.fac.IAnimalFactory;
import com.wt.demo.absfactory.fac.WhiteAnimalFactory;

/**
 * 抽象工长模式测试
 * 
 * @author wangtao
 * @date 2017年4月12日下午4:11:40
 */
public class Test {
	public static void main(String[] args) {
		IAnimalFactory blackAnimallFactory = new BlackAnimalFactory();
		ICat blackCat = blackAnimallFactory.createCat();
		IDog blackDog = blackAnimallFactory.createDog();
		blackCat.eat();
		blackDog.eat();

		IAnimalFactory whiteAnimallFactory = new WhiteAnimalFactory();
		ICat whiteCat = whiteAnimallFactory.createCat();
		IDog whiteDog = whiteAnimallFactory.createDog();
		whiteCat.eat();
		whiteDog.eat();
	}

}
