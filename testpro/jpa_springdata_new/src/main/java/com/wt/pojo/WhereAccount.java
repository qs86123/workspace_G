package com.wt.pojo;

import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:54 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "account")
//该注解只在查询本实体的时候才有效，从WhereEntity查询该实体的时候不会用到此条件
@Where(clause = "active = true")
public class WhereAccount extends AbstractMappedType {

    public WhereAccount() {
    }

    public WhereAccount(WhereEntity client, AccountType type, boolean active) {
        this.client = client;
        this.type = type;
        this.active = active;
    }

    public enum AccountType {
        DEBIT,
        CREDIT
    }

    @ManyToOne
    private WhereEntity client;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    private boolean active;

    public WhereEntity getClient() {
        return client;
    }

    public void setClient(WhereEntity client) {
        this.client = client;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "WhereAccount{" +
                "client=" + client +
                ", type=" + type +
                ", active=" + active +
                '}';
    }
}