package com.changhong.data.activity.dao;

import java.util.List;

import com.changhong.data.activity.entity.UserOpenid;

public interface UserOpenidDao extends BaseJpaDao<UserOpenid> {

	UserOpenid findByOpenid(String openid);

	List<UserOpenid> findByCardNumIn(List<String> cardNums);

	List<UserOpenid> findByOpenidIn(List<String> openids);

}
