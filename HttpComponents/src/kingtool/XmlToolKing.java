package kingtool;

import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;



/**
 * Xml工具类
 *
 * @version  1.0   
 *
 */
public class XmlToolKing {
	private static final String DEFAULT_ENCODING = "GBK";
	/**
	 * 带xml version
	 * 带body
	 * 带list
	 * @author King
	 * @param info
	 * @return
	 */
	public static String formatXml(String info) throws Exception{
		Document document = null;
		Element root = null;
		StringWriter sw = new StringWriter();
		try {
			document = DocumentHelper.parseText(info);
	        OutputFormat format = OutputFormat.createPrettyPrint(); 
	        format.setEncoding(DEFAULT_ENCODING);
	        XMLWriter writer = new XMLWriter(sw , format );  
	        writer.write( document );  
		} catch (Exception ex) {
			throw ex;
		}
		return sw.toString();
	}
	
	/**
	 * 带xml version
	 * 带body
	 * 带list
	 * @author King
	 * @param info
	 * @return
	 */
	public static String formatXml(String info,String encoding) throws Exception{
		Document document = null;
		Element root = null;
		StringWriter sw = new StringWriter();
		try {
			document = DocumentHelper.parseText(info);
	        OutputFormat format = OutputFormat.createPrettyPrint(); 
	        format.setEncoding(encoding);
	        XMLWriter writer = new XMLWriter(sw , format );  
	        writer.write( document );  
		} catch (Exception ex) {
			throw ex;
		}
		return sw.toString();
	}
}
