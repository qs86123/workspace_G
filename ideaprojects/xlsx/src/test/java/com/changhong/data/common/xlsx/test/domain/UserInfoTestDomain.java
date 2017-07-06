/**
 * 
 */
package com.changhong.data.common.xlsx.test.domain;

import java.util.Date;

import com.changhong.data.common.xlsx.annotation.Xlsx;
import com.changhong.data.common.xlsx.annotation.XlsxColumn;

/**
 * 用于测试xlsx生成的domain类
 * 
 * @author QiYao yao.qi@changhong.com

 * @version 1.0.0 2014年10月10日
 */
@Xlsx(value = "用户信息Anno", size = 500001)
public class UserInfoTestDomain {

	@XlsxColumn("地址")
	private String address;

	private String name;

	@XlsxColumn("生日")
	private Date birthday;

	@XlsxColumn
	private int age;

	private double salary;

	@XlsxColumn("活跃")
	private boolean active;

	public UserInfoTestDomain() {

	}

	/**
	 * @param address
	 * @param name
	 * @param birthday
	 * @param age
	 * @param salary
	 */
	public UserInfoTestDomain(String address, String name, Date birthday, int age, double salary) {
		super();
		this.address = address;
		this.name = name;
		this.birthday = birthday;
		this.age = age;
		this.salary = salary;
	}

	/**
	 * @return the name
	 */
	@XlsxColumn
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

    public boolean getActive() {
        return active;
    }

	public void setActive(boolean active) {
		this.active = active;
	}
}
