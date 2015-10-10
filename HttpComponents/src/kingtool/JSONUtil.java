package kingtool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 完全没有gson.jar好用,本工具类暂时打入冷宫
 * @author King
 *
 */
public class JSONUtil {
	/**
	 * obj转换成JSON
	 * @param list
	 * @return
	 */
	public static JSONObject objToJson(Object obj) {
		JSONObject jsonObj = JSONObject.fromObject(obj);
		return jsonObj;
	}
	
	/**
	 * List转换成JSON
	 * @param list
	 * @return
	 */
	public static JSONArray listToJson(List list) {
		JSONArray jsonArray = null;
		jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}

	/**
	 * map转换成JSON
	 * @param map
	 * @return
	 */
	public static JSONObject mapToJson(Map<String, Object> map) {
		JSONObject resultJSON = null;
		resultJSON = JSONObject.fromObject(map);
		return resultJSON;
	}

	public static void main(String args[]) {
		// List
//		List<User> list = new ArrayList<User>();
//		User user = null;
//		for (int i = 0; i < 2; i++) {
//			user = new User();
//			user.setUserId(i);
//			user.setUserName("Java" + i);
//			user.setBirth(new Date());
//			list.add(user);
//		}
//		String strListJson=listToJson(list).toString();
//		System.out.println(strListJson);
//
//		// Map
//		Map<String, Object> map = new HashMap<String, Object>(3);
//		map.put("total", "1");
//		map.put("data", listToJson(list));
//		map.put("success", "true");
//		System.out.println(mapToJson(map).toString(1,1));//toString(i,i)：将JSON对象转换为字符串，如果包含参数，是将其美化后输出。

	}

}
