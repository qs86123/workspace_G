package com.wt.springboot.demo.jpadao;

import com.wt.springboot.demo.entity.JPAPerson;

public interface JPAPersonDao extends BaseJpaDao<JPAPerson> {

	JPAPerson findByName(String name);

}
