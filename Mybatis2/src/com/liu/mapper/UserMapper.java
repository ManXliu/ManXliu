package com.liu.mapper;

import java.util.List;

import com.liu.domain.User;
//构建mapper接口原则
//在UserMapper.xml中写接口全路径
//接口映射文件的ID要跟方法名一致
//输入类型要更parameterType一致
//输出类型要跟resultType一致
public interface UserMapper {
	User queryUserById(Integer id);
	void saveUser(User user);
	List<User> querUserByname1(String username);
}
