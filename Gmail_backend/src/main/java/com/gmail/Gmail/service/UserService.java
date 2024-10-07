package com.gmail.Gmail.service;

import com.gmail.Gmail.model.User;

import java.util.List;


public interface UserService {
//	User saveUser(User user);
	List<User> getUsers();
	User getUser(String mailid);
	User updateUser(User user,String mailid);
	void deleteUser(String mailid);
	User signIn(String mailid,String password);
	User forgotPassword(User user);
//	public String convertImageBytesToBase64(byte[] data);
	User updateImage(String mailid,User user);
	User changePassword(String mailid,String password);
}