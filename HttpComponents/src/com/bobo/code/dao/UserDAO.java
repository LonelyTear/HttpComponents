package com.bobo.code.dao;

import java.util.List;

import com.bobo.code.model.User;

public interface UserDAO {

	public User select(User user);

	public List findAll();

	public void delete(User user);

	public void save(User user);

	public void update(User user);

}