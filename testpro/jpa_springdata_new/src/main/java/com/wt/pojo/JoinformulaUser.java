package com.wt.pojo;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:13:36 2017/8/8
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "join_formula_user")
public class JoinformulaUser extends AbstractMappedType {

    private String firstName;

    private String language;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    //这里由于是多个user对应一个country，country为主表
    //SUBSTR(phoneNumber,2,2)计算出来的值将作为主表的主键（即id）去查询country，如果没有查询到任何结果会报错
    @JoinFormula(value = "SUBSTR(phoneNumber,2,2)")
    private JoinFormulaCountry country;

    //有问题
    @ManyToOne
    @JoinColumnOrFormula(formula =
    @JoinFormula(
            value = "true",
            referencedColumnName = "is_default"
    ), column = @JoinColumn(
            name = "language",
            referencedColumnName = "primary_language",
            insertable = false,
            updatable = false
    ))
    private JoinFormulaCountry country2;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public JoinFormulaCountry getCountry() {
        return country;
    }

    public void setCountry(JoinFormulaCountry country) {
        this.country = country;
    }

    public JoinFormulaCountry getCountry2() {
        return country2;
    }

    public void setCountry2(JoinFormulaCountry country2) {
        this.country2 = country2;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
