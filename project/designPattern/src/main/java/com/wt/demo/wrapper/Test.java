package com.wt.demo.wrapper;

public class Test {
	public static void main(String[] args) {
		Man man = new Man();
		ClothSuitPerson suit = new ClothSuitPerson();
		ClothHatPerson hat = new ClothHatPerson();
		suit.setPerson(man);
		hat.setPerson(suit);
		hat.show();
	}
}
