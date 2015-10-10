package com.bobo.code.web.controller;

import java.util.List;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bobo.code.model.BankAccount;
import com.bobo.code.model.Student;
import com.bobo.code.service.impl.BankAccountService;
import com.bobo.code.service.impl.StudentService;
//注释1 标注它是一个Controller
@Controller
@RequestMapping(value = BankAccountController.DIR)
public class BankAccountController {
	protected static final String DIR = "bankaccount/*";
	
	@Autowired(required = true)
	@Qualifier("bankAccountService")
	BankAccountService bankAccountService;

	@RequestMapping(value = "remit.do", method = RequestMethod.GET)
	public ModelAndView insertOrUpdateStudent( BankAccount account ,Integer remitDestId,Double remitMoney, final ModelMap model)
	{
		System.out.println("CONTROLLER-------------------------------------------");
		bankAccountService.remit(account,remitDestId,remitMoney);
		//把account帐号的remitMoney钱给Id为remitDestId的这个人
		List<BankAccount> accountList = bankAccountService.select(null);
		
		return new ModelAndView("jsp/bankaccount/listBankAccounts.jsp", "accountList", accountList);
	}
	
}
