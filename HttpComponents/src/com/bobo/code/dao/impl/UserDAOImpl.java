package com.bobo.code.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bobo.code.dao.UserDAO;
import com.bobo.code.model.User;
import com.ibatis.sqlmap.client.SqlMapClient;
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	private SqlMapClient sqlMapClient ;
	
	public void doIt() {
		System.out.println("dao");
	}

	public User select(User user) {
		User u = new User();
		u.setId("123");
//		try {
//			u = (User) sqlMapClient.queryForObject("selectUser",user);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		System.out.println(u);
		return u;
	}

	public List findAll() {
		try {
			return sqlMapClient.queryForList("user.selectAllUsers");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void delete(User user) {
		try {
			sqlMapClient.delete("user.deleteUser ", user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save(User user) {
		try {
			sqlMapClient.insert("user.insertUser ", user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(User user) {
		try {
			sqlMapClient.update("user.updateUser ", user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	
}