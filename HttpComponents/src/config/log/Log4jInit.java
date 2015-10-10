package config.log;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet{
	public void init() {
		String prefix = getServletContext().getRealPath("/");
		String file = getServletConfig().getInitParameter("log4j-config-file");
		System.out.println(prefix+file);
		if (file != null) {
			PropertyConfigurator.configure(prefix + file);
		}
	}
}
