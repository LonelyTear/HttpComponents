package com.bobo.code.service.impl;

import java.util.List;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bobo.code.dao.BankAccountDAO;
import com.bobo.code.model.BankAccount;

//如果只用@Service注解,那么bean名默认为bankAccountService,即类名首字母小写
@Service("bankAccountService")
@Transactional(readOnly=false , propagation = Propagation.REQUIRED , rollbackFor = Throwable.class) 
public class BankAccountService {
//	@Autowired
//	@Qualifier(value="txManager")
//	org.springframework.jdbc.datasource.DataSourceTransactionManager txManager;
	
	@Autowired(required = true)
	@Qualifier("bankAccountDAO")
	BankAccountDAO bankAccountDAOImpl;
	
	/**
	 * 
	 * 转帐汇款.
	 * 该方法用于事务测试
	 * @Transactional 只能被应用到public方法上
	 * @param account 源帐户
	 * @param remitDestId 目标帐户id
	 * @param remitMoney 转帐金额
	 * @return
	 */  
	@Transactional(readOnly=false , propagation = Propagation.REQUIRED , rollbackFor = Throwable.class)
	public void remit(BankAccount account ,Integer remitDestId,Double remitMoney) {
//		DefaultTransactionDefinition td = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
//		td.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
//		td.setTimeout(10);
//		TransactionState ts = (TransactionState) transactionManager.getTransaction(td);
		System.out.println("SERVICE-------------------------------------------");
		//源帐户减钱
		List<BankAccount> srcAccountList = bankAccountDAOImpl.select(account);
		BankAccount srcAccount = srcAccountList.get(0);
		srcAccount.setBalance(srcAccount.getBalance() - remitMoney);
		bankAccountDAOImpl.update(srcAccount);
		//目标帐户加钱
		List<BankAccount> destAccountList = bankAccountDAOImpl.select(remitDestId);
		BankAccount destAccount = destAccountList.get(0);
		destAccount.setBalance(destAccount.getBalance() + remitMoney);
		bankAccountDAOImpl.update(destAccount);
		
		String nullStr = null;  
	    nullStr.length();
//		transactionManager.rollback((TransactionStatus) ts);
	}
	
	public BankAccountDAO getBankAccountDAOImpl() {
		return bankAccountDAOImpl;
	}

	public void setBankAccountDAOImpl(BankAccountDAO bankAccountDAOImpl) {
		this.bankAccountDAOImpl = bankAccountDAOImpl;
	}

	public void insertOrUpdateBankAccount(BankAccount bankAccount){
		if( bankAccount.getId() == null){//没有id,那么肯定是新增
			bankAccountDAOImpl.insert(bankAccount);
		}else{//已有id,那么肯定是更新
			bankAccountDAOImpl.update(bankAccount);
		}
	}
	
	public List select(BankAccount bankAccount){
		List<BankAccount> stuList = bankAccountDAOImpl.select(bankAccount);
		return stuList;
	}
	
	public static void main(String[] args) {
	}
}
