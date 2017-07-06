package com.wt.demo.a_lesson16;

public class CallBackTest {
	public static void main(String[] args) {
		Li li = new Li();
		Wang w = new Wang(li);
		w.c = new Callback() {
			public void Onfinish(String result) {
				System.out.println("小李说：" + result);
			}
		};
		w.askQuestion("1+1=?");
		
		Fruit f = new Fruit();
		f.c = new Callback() {

			public void Onfinish(String result) {
				System.out.println("输出任务结果：" + result);
			}
		};
		f.save();
	}
}

class Li {

	public void exeQuetion(Callback callback, String question) {
		System.out.println("小王的问题：" + question);
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < 100; j++) {

			}
		}
		callback.Onfinish("答案是2");
	}

}

class Wang {
	Li li;

	Callback c;

	public Wang(Li li) {
		this.li = li;
	}

	public void askQuestion(final String question) {
		if (c != null) {
			new Thread() {
				public void run() {
					li.exeQuetion(c, question);
				}
			}.start();
		}
		play();
	}

	private void play() {
		System.out.println("小王问了问题，逛街去了");
	}

}

class Fruit {
	Callback c;

	public void save() {
		if (c != null) {
			// 开启新线程，执行耗时任务,比如http请求
			new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < 10000; i++) {
						for (int j = 0; j < 10000; j++) {
							// 模拟耗时任务
						}
					}
					System.out.println("开始执行耗时任务");
					// 耗时任务完成,返回任务结果
					c.Onfinish("result");
				}
			}).start();
		}
		play();
	}

	private void play() {
		System.out.println("fruit play");
	}

}

interface Callback {
	void Onfinish(String result);
}
