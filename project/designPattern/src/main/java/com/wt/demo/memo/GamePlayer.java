package com.wt.demo.memo;

public class GamePlayer {

	private int blood;
	private int blue;
	private int attack;
	private int defence;

	public GamePlayer() {

	}

	public GamePlayer(int blood, int blue, int attack, int defence) {
		this.blood = blood;
		this.blue = blue;
		this.attack = attack;
		this.defence = defence;
	}

	public MemoBox<GamePlayer> saveMemo() {
		// 注意：这里不能直接传this，如果传this，那么我们操作的对象就只有一个，
		// 后边recoveryPlayer的时候获取到的player对象引用和this都是指向的相同的对象,
		// return new MemoBox<GamePlayer>(this);
		return new MemoBox<GamePlayer>(new GamePlayer(this.blood, this.blue, this.attack, this.defence));
	}

	public void recoveryPlayer(MemoBox<GamePlayer> memo) {
		GamePlayer player = memo.getObj();
		this.blood = player.blood;
		this.blue = player.blue;
		this.attack = player.attack;
		this.defence = player.defence;
	}

	public void fight() {
		this.blood -= 80;
		this.blue -= 80;
		this.attack -= 80;
		this.defence -= 80;
	}

	public int getBlood() {
		return blood;
	}

	public int getBlue() {
		return blue;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	@Override
	public String toString() {
		return "GamePlayer [blood=" + blood + ", blue=" + blue + ", attack=" + attack + ", defence=" + defence + "]";
	}

}
