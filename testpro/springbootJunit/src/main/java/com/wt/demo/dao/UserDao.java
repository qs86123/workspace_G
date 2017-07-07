package com.wt.demo.dao;

import com.wt.demo.entity.UserInfo;

public interface UserDao extends BaseJpaDao<UserInfo> {

	UserInfo findByName(String name);

}
