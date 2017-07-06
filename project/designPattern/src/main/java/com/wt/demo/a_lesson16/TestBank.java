package com.wt.demo.a_lesson16;

public class TestBank {

	public static void main(String[] args) {
		Bank bank = new Bank(1000);
		new BankThread("hehe1", bank, 800).start();
		new BankThread("hehe1", bank, 800).start();
	}

}

class Bank {
	int money;

	public Bank(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public synchronized void outMoney(int out) {
		if (money > out) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			money -= out;
			System.out.println("取钱成功，还剩：" + money);
		} else
			System.out.println("余额不足，还剩：" + money);
	}

}

class BankThread extends Thread {
	private Bank bank;
	private int out;

	public BankThread(String name, Bank bank, int out) {
		super(name);
		this.bank = bank;
		this.out = out;
	}

	@Override
	public void run() {
		bank.outMoney(out);
	}
}