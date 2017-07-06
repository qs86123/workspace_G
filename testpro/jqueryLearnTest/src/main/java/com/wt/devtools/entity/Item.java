package com.wt.devtools.entity;

public class Item {

	private String itemName;
	private Integer num;
	private Person p;

	public Person getP() {
		return p;
	}

	public void setP(Person p) {
		this.p = p;
	}

	public Item(String itemName, Integer num) {
		this.itemName = itemName;
		this.num = num;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
