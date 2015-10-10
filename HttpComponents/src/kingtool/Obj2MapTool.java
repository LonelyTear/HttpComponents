package kingtool;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bobo.code.model.Student;
/**
 * 对象转Map工具
 * @author King
 *
 */
public class Obj2MapTool {

	public static void main(String[] args) {
		Map m = getMap(new Student(1,"name","address",1000.123,new Date()));
		System.out.println(m);
		
	}

	public  static final Map<String, Object> getMap(Object obj) {  
        Map<String, Object> map = new HashMap<String, Object>();  
        // System.out.println(obj.getClass());  
        // 获取对象f对应类中的所有属性域  
        Field[] fields = obj.getClass().getDeclaredFields();  
        for (int i = 0, len = fields.length; i < len; i++) {  
            String varName = fields[i].getName();  
            try {  
                // 获取原来的访问控制权限  
                boolean accessFlag = fields[i].isAccessible();  
                // 修改访问控制权限  
                fields[i].setAccessible(true);  
                // 获取在对象f中属性fields[i]对应的对象中的变量  
                Object o = fields[i].get(obj);  
                if (o != null)  
                    map.put(varName, o);  
                 System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
                // 恢复访问控制权限  
                fields[i].setAccessible(accessFlag);  
            } catch (IllegalArgumentException ex) {  
                ex.printStackTrace();  
            } catch (IllegalAccessException ex) {  
                ex.printStackTrace();  
            }  
        } 
        System.out.println();
        return map;  
  
    } 
}
