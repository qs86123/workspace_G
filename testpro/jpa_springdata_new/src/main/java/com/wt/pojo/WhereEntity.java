package com.wt.pojo;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:50 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "where_entity")
public class WhereEntity extends AbstractMappedType {

    private String name;

    @Where(clause = "account_type = 'DEBIT'")
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<WhereAccount> debitAccounts = new ArrayList<>();

    @Where(clause = "account_type = 'CREDIT'")
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<WhereAccount> creditAccounts = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WhereAccount> getDebitAccounts() {
        return debitAccounts;
    }

    public void setDebitAccounts(List<WhereAccount> debitAccounts) {
        this.debitAccounts = debitAccounts;
    }

    public List<WhereAccount> getCreditAccounts() {
        return creditAccounts;
    }

    public void setCreditAccounts(List<WhereAccount> creditAccounts) {
        this.creditAccounts = creditAccounts;
    }

}

