package com.wt.demo.a_lesson16;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjSerilaze {
	public static void main(String[] args) throws Exception {
		Wolf w = Wolf.getInstence("灰太狼");
		System.out.println("灰太狼--->对象创建完成");
		Wolf w2 = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("D:/test/w.bin"));
			ois = new ObjectInputStream(new FileInputStream("D:/test/w.bin"));
			oos.writeObject(w);
			oos.flush();
			w2 = (Wolf) ois.readObject();
			System.out.println(w.equals(w2));
			System.out.println(w == w2);
			System.out.println(w + ":" + w2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null)
				oos.close();
			if (ois != null)
				ois.close();
		}
	}
}

class Wolf implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Wolf wolf;

	transient private String name = null;

	private Wolf(String name) {
		System.out.println("name构造");
		this.name = name;
	}

	public static Wolf getInstence(String name) {
		if (wolf == null)
			wolf = new Wolf(name);
		return wolf;
	}

	private Object readResolve() {
		System.out.println("readResolve");
		return wolf;
	}

	@Override
	public String toString() {
		return "Wolf [name=" + name + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this.name == null) {
			System.out.println("this.name == null");
			return false;
		}
		if (this == obj) {
			System.out.println("this equals obj");
			return true;
		}
		if (obj.getClass() == Wolf.class) {
			Wolf target = (Wolf) obj;
			if (target.name == null) {
				System.out.println("obj.name == null");
				return false;
			}
			System.out.println("this.name equals obj.name");
			return target.name.equals(this.name);
		}
		return false;
	}
}