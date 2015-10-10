package debug.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bobo.code.model.BankAccount;
import com.bobo.code.service.impl.BankAccountService;

public class TestSpringBean {

		public void classPath(){
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
//		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
			BankAccountService bankAccountService = (BankAccountService) context.getBean("bankAccountService");
			
			BankAccount srcAccount = new BankAccount(1,"bobo",null,null	);
			bankAccountService.remit(srcAccount,2,100.0);
			System.out.println( bankAccountService.select(null));
			System.out.println("汇款成功！");
		}
		
		
	
}
