package com.changhong.data.common.xlsx.test.domain;

import com.changhong.data.common.xlsx.annotation.Xlsx;
import com.changhong.data.common.xlsx.annotation.XlsxColumn;

import java.util.Date;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月18日
 */
@Xlsx(value = "用户信息Anno", size = 500001)
public class MultiColUser{
    @XlsxColumn("地址1")
    private String address1;
    @XlsxColumn("地址2")
    private String address2;
    @XlsxColumn("地址3")
    private String address3;
    @XlsxColumn("地址4")
    private String address4;
    @XlsxColumn("地址5")
    private String address5;
    @XlsxColumn("地址6")
    private String address6;
    @XlsxColumn("地址7")
    private String address7;
    @XlsxColumn("地址8")
    private String address8;
    @XlsxColumn("地址9")
    private String address9;
    @XlsxColumn("地址10")
    private String address10;
    @XlsxColumn("地址11")
    private String address11;
    @XlsxColumn("地址12")
    private String address12;
    @XlsxColumn("地址13")
    private String address13;
    @XlsxColumn("地址14")
    private String address14;
    @XlsxColumn("地址15")
    private String address15;
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


    /**
     * @param address
     * @param name
     * @param birthday
     * @param age
     * @param salary
     */
    public void init(String address, String name, Date birthday, int age, double salary) {
        this.address = address;
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.salary = salary;
    }


    public MultiColUser(String address, String name, Date birthday, int age, double salary) {
        init(address, name, birthday, age, salary);
        this.address1=address;
        this.address2=address;
        this.address3=address;
        this.address4=address;
        this.address5=address;
        this.address6=address;
        this.address7=address;
        this.address8=address;
        this.address9=address;
        this.address10=address;
        this.address11=address;
        this.address12=address;
        this.address13=address;
        this.address14=address;
        this.address15=address;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getAddress5() {
        return address5;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    public String getAddress6() {
        return address6;
    }

    public void setAddress6(String address6) {
        this.address6 = address6;
    }

    public String getAddress7() {
        return address7;
    }

    public void setAddress7(String address7) {
        this.address7 = address7;
    }

    public String getAddress8() {
        return address8;
    }

    public void setAddress8(String address8) {
        this.address8 = address8;
    }

    public String getAddress9() {
        return address9;
    }

    public void setAddress9(String address9) {
        this.address9 = address9;
    }

    public String getAddress10() {
        return address10;
    }

    public void setAddress10(String address10) {
        this.address10 = address10;
    }

    public String getAddress11() {
        return address11;
    }

    public void setAddress11(String address11) {
        this.address11 = address11;
    }

    public String getAddress12() {
        return address12;
    }

    public void setAddress12(String address12) {
        this.address12 = address12;
    }

    public String getAddress13() {
        return address13;
    }

    public void setAddress13(String address13) {
        this.address13 = address13;
    }

    public String getAddress14() {
        return address14;
    }

    public void setAddress14(String address14) {
        this.address14 = address14;
    }

    public String getAddress15() {
        return address15;
    }

    public void setAddress15(String address15) {
        this.address15 = address15;
    }
}
