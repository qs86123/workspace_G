package com.wt.demo.absfactory.fac;

import com.wt.demo.absfactory.animal.BlackCat;
import com.wt.demo.absfactory.animal.BlackDog;
import com.wt.demo.absfactory.animal.ICat;
import com.wt.demo.absfactory.animal.IDog;

/**
 * 生产黑色动物的工厂
 * 
 * @author wangtao
 * @date 2017年4月12日下午4:11:40
 */
public class BlackAnimalFactory implements IAnimalFactory {

	public ICat createCat() {
		return new BlackCat();
	}

	public IDog createDog() {
		return new BlackDog();
	}

}
