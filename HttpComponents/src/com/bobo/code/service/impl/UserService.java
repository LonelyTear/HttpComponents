package com.bobo.code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bobo.code.dao.UserDAO;
import com.bobo.code.model.User;

@Service("userService")
public class UserService {
	@Autowired(required=true)
	@Qualifier("userDAO")
	UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User select(User user){
		 User userRet = userDAO.select(user);
		 return userRet;
	}
}
