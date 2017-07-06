package com.wt.demo.absfactory.fac;

import com.wt.demo.absfactory.animal.ICat;
import com.wt.demo.absfactory.animal.IDog;
import com.wt.demo.absfactory.animal.WhiteCat;
import com.wt.demo.absfactory.animal.WhiteDog;

/**
 * 生产白色动物的工厂
 * 
 * @author wangtao
 * @date 2017年4月12日下午4:11:40
 */
public class WhiteAnimalFactory implements IAnimalFactory {

	public ICat createCat() {
		return new WhiteCat();
	}

	public IDog createDog() {
		return new WhiteDog();
	}

}
