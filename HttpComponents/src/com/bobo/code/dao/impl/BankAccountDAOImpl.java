package com.bobo.code.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.bobo.code.dao.BankAccountDAO;
import com.bobo.code.dao.BaseDao;
import com.bobo.code.model.BankAccount;
@Repository("bankAccountDAO")
public class BankAccountDAOImpl  extends BaseDao implements BankAccountDAO{
	
	@Override
	public List<BankAccount> select(Integer  id) {
		List<BankAccount> bankAccountList = null;
		BankAccount account  = new BankAccount();
		account.setId(id);
		bankAccountList = getSqlMapClientTemplate().queryForList("bankaccount.select", account);
		return bankAccountList;
	}
	
	@Override
	public List<BankAccount> select(BankAccount account) {
		List<BankAccount> bankAccountList = null;
		bankAccountList = getSqlMapClientTemplate().queryForList("bankaccount.select", account);
		return bankAccountList;
	}

	@Override
	public Integer delete(BankAccount account) {
			return getSqlMapClientTemplate().delete("bankaccount.delete", account);
	}

	@Override
	public Integer insert(BankAccount account) {
			return   (Integer) getSqlMapClientTemplate().insert("bankaccount.insertBankAccount", account);
	}

	@Override
	public Integer update(BankAccount account) {
		System.out.println("DAO-------------------------------------------");
		return getSqlMapClientTemplate().update("bankaccount.updateBankAccount", account);
	}

}