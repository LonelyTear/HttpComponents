package com.bobo.code.dao;

import java.util.List;

import com.bobo.code.model.BankAccount;

public interface BankAccountDAO {
	public List<BankAccount> select(Integer  id) ;
	
	public List<BankAccount> select(BankAccount account);

	public Integer delete(BankAccount account);

	public Integer insert(BankAccount account);

	public Integer update(BankAccount account);

}
