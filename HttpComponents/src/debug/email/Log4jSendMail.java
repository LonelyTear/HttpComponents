package debug.email;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.net.SMTPAppender;

public class Log4jSendMail {
	static Logger logger = Logger.getLogger(Log4jSendMail.class);  
    SMTPAppender appender = new SMTPAppender();
    public Log4jSendMail() {  
        try {  
            appender.setSMTPUsername("sexjl1");  
            appender.setSMTPPassword("259695");  
            appender.setTo("sexjl2@163.com");  
            appender.setFrom("sexjl1@163.com");  
//             SMTP服务器 smtp.163.com  
//            版本低于log4j-1.2.14.jar不支持SMTP认证
            appender.setSMTPHost("smtp.163.com");  
            appender.setLocationInfo(true);  
            appender.setSubject("Test Mail From Log4J");  
            appender.setLayout(new PatternLayout());  
            appender.activateOptions();  
            logger.addAppender(appender);  
            logger.error("Hello World");  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("Printing ERROR Statements", e);  
        }  
    }  
    public static void main(String args[]) {  
        new Log4jSendMail();  
    } 
}
