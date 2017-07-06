package com.wt.demo.wrapper;

/**
 * 这里抽象出一个类，主要作用是避免所有继承至ClothPerson的子类，都需要在重写的show方法中写上super.show()，这里抽象出来，避免子类忘记写
 * 
 * @author wangtao
 * @date 2017年4月27日下午5:23:18
 */
public abstract class AbsClothPerson extends ClothPerson {

	@Override
	public void show() {
		super.show();
		reshow();
	}

	public abstract void reshow();

}
