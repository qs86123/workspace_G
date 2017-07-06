package com.wt.demo.bridge;

/**
 * 桥接模式，维度的变化不会引入额外的复杂度
 * 如果使用传统的类继承模式，类的结构过于复杂，继承关系太多，难于维护，最后最致命的一点是扩展性太差。如果变化沿着汽车的类型和不同的道路两个方向变化，
 * 我们会看到这个类的结构会迅速的变庞大。
 * 看博客http://blog.csdn.net/jason0539/article/details/22568865
 * 
 * 包two中的手机和软件的桥接例子很容易懂，见《大话设计模式》的桥接模式
 * 
 * @author wangtao
 * @date 2017年4月14日上午11:35:47
 */
public class Test {
	public static void main(String[] args) {
		// 二维情况，汽车和公路
		System.out.println("-----二维情况------");
		AbsCar smallCar = new SmallCar();
		AbsCar bigCar = new BigCar();
		AbsRoad speedRoad = new SpeedRoad();
		AbsRoad countryRoad = new CountryRoad();

		System.out.println(smallCar.runOnRoad(speedRoad));
		System.out.println(smallCar.runOnRoad(countryRoad));
		System.out.println(bigCar.runOnRoad(speedRoad));
		System.out.println(bigCar.runOnRoad(countryRoad));
		// 三维情况，人，汽车和公路
		System.out.println("-----三维情况------");
		AbsPerson man = new Man();
		AbsPerson lady = new Lady();
		System.out.println(man.drive(smallCar, countryRoad));
		System.out.println(man.drive(smallCar, speedRoad));
		System.out.println(man.drive(bigCar, countryRoad));
		System.out.println(man.drive(bigCar, speedRoad));
		System.out.println(lady.drive(smallCar, countryRoad));
		System.out.println(lady.drive(smallCar, speedRoad));
		System.out.println(lady.drive(bigCar, countryRoad));
		System.out.println(lady.drive(bigCar, speedRoad));

	}
}
