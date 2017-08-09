package com.wt.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @Author: wangtao
 * @Date:13:35 2017/8/8
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "join_formula_country")
public class JoinFormulaCountry extends AbstractMappedType {
    private String name;

    @Column(name = "is_default")
    private boolean isDefault;

    @Column(name = "primary_language")
    private String primaryLanguage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }
}
