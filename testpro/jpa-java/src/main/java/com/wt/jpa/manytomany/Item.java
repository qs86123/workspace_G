package com.wt.jpa.manytomany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name = "jpa_item")
@Entity
public class Item {

	@GeneratedValue
	@Id
	private Integer id;
	@Column(name = "item_name")
	private String itemName;

	// 1. 使用JoinTable来映射中间表
	// 2. JoinColums：映射当前类所在的表在中间表中的外键
	// 2.1 name指定外键列名
	// 2.2 referencedColumnName 指定外键关联当前表的哪一列
	// 3. inverseJoinColumns 映射关联的类所在中间表的外键
	@JoinTable(name = "jpa_item_category", 
			joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id") }, 
			inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id") })
	@ManyToMany
	private Set<Category> cs = new HashSet<Category>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Set<Category> getCs() {
		return cs;
	}

	public void setCs(Set<Category> cs) {
		this.cs = cs;
	}
}
