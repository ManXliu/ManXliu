package com.liu.mapper;

import java.util.List;

import com.liu.domain.User;
//����mapper�ӿ�ԭ��
//��UserMapper.xml��д�ӿ�ȫ·��
//�ӿ�ӳ���ļ���IDҪ��������һ��
//��������Ҫ��parameterTypeһ��
//�������Ҫ��resultTypeһ��
public interface UserMapper {
	User queryUserById(Integer id);
	void saveUser(User user);
	List<User> querUserByname1(String username);
}
