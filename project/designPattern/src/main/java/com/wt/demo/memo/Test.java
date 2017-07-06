package com.wt.demo.memo;

public class Test {
	public static void main(String[] args) {
		GamePlayer gp = new GamePlayer(100, 100, 100, 100);
		// 这里保存对象的时候不能保存gp对象，必须行new一个，具体为何看saveMemo方法，
		// 在memo方法中如果直接传this，那么后边角色状太是没法恢复的，
		// 因为如果穿this的话，memo里面的对象引用和当前你的gp都是指向同一个对象
		MemoBox<GamePlayer> memo = gp.saveMemo();
		GamePlayerMemoManager<GamePlayer> gpmm = new GamePlayerMemoManager<GamePlayer>();
		gpmm.setMemoBox(memo);
		System.out.println("大战前角色状态：" + gp);
		gp.fight();
		System.out.println("大战后角色状态：" + gp);
		gp.recoveryPlayer(gpmm.getMemoBox());
		System.out.println("恢复后角色状态：" + gp);

	}
}
