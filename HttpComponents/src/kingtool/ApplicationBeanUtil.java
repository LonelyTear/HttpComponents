package kingtool;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
/**
 * 
 * @author King
 *
 */
public class ApplicationBeanUtil {
	private static ApplicationContext app;
	static {
		if (app == null) {
//			app =new FileSystemXmlApplicationContext("C:\\Users\\King\\Documents\\Eclipse2013Workspace\\platformproxy\\WebRoot\\WEB-INF\\applicationContext.xml");
			app = new ClassPathXmlApplicationContext(new String[] {
					"src/resources/spring/framework-context.xml",
					"src/resources/spring/framework-bean.xml",
					"src/resources/spring/framework-jdbc.xml" });
		}
	}

	public static Object getBean(String beanName) {
		return app.getBean(beanName);
	}

	public static void main(String[] args) {
		System.out.println(getBean("pltmHelperService"));
	}

}
