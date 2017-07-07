package com.wt.jpa.demo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "last_name")
	private String lastName;
	private String email;
	private int age;
	// 添加该注解表示该属性并非数据库的一个字段,即生成表示不适用该字段
	// 如果在方法上做标记的话表示该方法对应的属性不作为属性存入数据库
	// jpa是通过get方法来判定有啥子属性，所以如果有啥子工具方法写在该类中的话，就需要加上这个注解
	// 这些注解都可以加在属性上，也可以加在get方法上，建议加在get方法上，不是set方法
	@Transient
	private String info;

	// 该注解来指定存入日期的精度
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	@Temporal(TemporalType.DATE)
	private Date birthday;

	// 这里的c是对方实体类里面自己的属性名，mappedBy加了之后不会生成中间表
	@OneToMany(mappedBy = "c", fetch = FetchType.EAGER, orphanRemoval = true)
	private List<SeatInfo> sis;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, orphanRemoval = true,targetEntity=Order.class)
	private Set<Order> orders=new HashSet<Order>();

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public List<SeatInfo> getSis() {
		return sis;
	}

	public void setSis(List<SeatInfo> sis) {
		this.sis = sis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	// 如果不是get开头的方法就不用加@@Transient注解
	public String tototo() {
		return "";
	}


}
