package com.changhong.data.meeting.dao;

import java.util.List;

import com.changhong.data.meeting.entity.UserInfo;

public interface UserInfoDao extends BaseJpaDao<UserInfo> {

	List<UserInfo> findByCardNumIn(List<String> userIds);

	UserInfo findByCardNum(String userId);

}
