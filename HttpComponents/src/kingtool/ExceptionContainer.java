package kingtool;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
 
/**
 * 说明： 异常容器
 *   用来处理多个异常一起处理的业务场景
 * @author zhugh
 * @createtime 2014-4-11
 *
 */
public class ExceptionContainer {
	public static ThreadLocal<List<Exception>> threadLocal = new  ThreadLocal<List<Exception>>();
	
	/**
	 * 说明：把异常放进异常容器里
	 * @author zhugh
	 * @Createtime 2014-4-11
	 * @param e
	 */
	public static void setException(Exception e){
		if(threadLocal.get()==null){
			List<Exception> le = new ArrayList<Exception>();
			le.add(e);
			threadLocal.set(le);
		}else{
			threadLocal.get().add(e);
		}
		
	}
	
      /**
       * 删除一个异常 （需要特殊处理的异常时需要用到）   
       * @param e
       */
	public static void removeException(Exception e){
		if(threadLocal.get()!=null){
			threadLocal.get().remove(e);
		}
	
	}
		
    /**
     * 说明：获取异常的List
     * @author zhugh
     * @Createtime 2014-4-11
     */
    public static List<Exception> getExceptionList(){
		return threadLocal.get();
	}
    
    /**
     * 说明：获取异常容器里面的异常个数
     * @author zhugh
     * @Createtime 2014-4-11
     */
    public static int getExceptionListSize(){
    	if(threadLocal.get()==null){
			return 0;
		}else{
			return threadLocal.get().size();
		}
	}
    
    /**
     * 说明：清除异常容器里的异常
     *      注意 如果调用了set方法，一定要调用remove方法来清除本地线程变量里的值
     * @author r
     * @Createtime 2014-4-11下午3:58:26
     */
    public static void clearAll(){
    	threadLocal.remove();
    } 
    
    /**
     * 把异常的栈轨迹以String形式返回,而不是直接打印到console
     * @author 剑波
     * @time 2015-04-29
     * @return
     */
    public static String getExceptionInfo(Throwable e){   
        StringWriter sw = new StringWriter();   
        PrintWriter pw = new PrintWriter(sw, true);   
        e.printStackTrace(pw);   
        pw.flush();   
        sw.flush();   
        return sw.toString();   
} 
}
