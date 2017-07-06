package com.wt.demo.absfactory.fac;

import com.wt.demo.absfactory.animal.ICat;
import com.wt.demo.absfactory.animal.IDog;

/**
 * 生产动物的抽象工厂
 * 
 * @author wangtao
 * @date 2017年4月12日下午4:06:48
 */
public interface IAnimalFactory {

	ICat createCat();

	IDog createDog();

}
