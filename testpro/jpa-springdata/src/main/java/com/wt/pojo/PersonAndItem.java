package com.wt.pojo;

import javax.persistence.*;

@Entity
public class PersonAndItem {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String personName;
	private String itemPrice;

	public PersonAndItem() {

	}

	public PersonAndItem(String personName, String itemPrice) {
		this.personName = personName;
		this.itemPrice = itemPrice;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PersonAndItem [personName=" + personName + ", itemPrice=" + itemPrice + "]";
	}

}
