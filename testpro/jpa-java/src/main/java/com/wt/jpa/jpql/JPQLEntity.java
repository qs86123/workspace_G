package com.wt.jpa.jpql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "testNamedQuery", query = "from JPQLEntity where id = ?") })
@Entity
@Table(name = "jpa_jpql")
public class JPQLEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String age;
	private String tel;

	public JPQLEntity() {

	}

	public JPQLEntity(String name, String age) {
		this.name = name;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "JPQLEntity [id=" + id + ", name=" + name + ", age=" + age + ", tel=" + tel + "]";
	}

}
