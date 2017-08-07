package com.wt.apptest;

import com.wt.pojo.WhereAccount;
import com.wt.pojo.WhereEntity;
import com.wt.repositories.AccountRepository;
import com.wt.repositories.WhereEntityRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:56 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
public class WhereEntityTest extends SupperTest {

    private WhereEntityRepository wer = context.getBean(WhereEntityRepository.class);

    private AccountRepository ar = context.getBean(AccountRepository.class);

    @Test
    public void save() {
        WhereEntity we = new WhereEntity();
        we.setName("name");
        List<WhereAccount> debitAccounts = new ArrayList<>();
        List<WhereAccount> creditAccounts = new ArrayList<>();
        debitAccounts.add(new WhereAccount(we, WhereAccount.AccountType.DEBIT, true));
        debitAccounts.add(new WhereAccount(we, WhereAccount.AccountType.DEBIT, false));
        creditAccounts.add(new WhereAccount(we, WhereAccount.AccountType.CREDIT, true));
        creditAccounts.add(new WhereAccount(we, WhereAccount.AccountType.CREDIT, false));
        we.setDebitAccounts(debitAccounts);
        we.setCreditAccounts(creditAccounts);
        wer.save(we);
        ar.save(debitAccounts);
        ar.save(creditAccounts);
    }

    @Test
    public void find() {
        List<WhereEntity> all = wer.findAll();
        for (WhereEntity we : all) {
            System.out.println("--------whereEntity name:" + we.getName());
            show(we.getCreditAccounts());
            show(we.getDebitAccounts());
        }
    }

    @Test
    public void findAccount() {
        show(ar.findAll());
    }
}
