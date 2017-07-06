package com.changhong.data.meeting.dao;

import java.util.List;

import com.changhong.data.meeting.entity.UserOpenid;

public interface UserOpenidDao extends BaseJpaDao<UserOpenid> {

	UserOpenid findByOpenid(String openid);

	List<UserOpenid> findByCardNumIn(List<String> cardNums);

	List<UserOpenid> findByOpenidIn(List<String> openids);

}
