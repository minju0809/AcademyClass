package login;

import java.util.HashMap;
import java.util.Map;

public class LoginHashMapImpl implements LoginHashMap {

	Map<String, Object>  map = new HashMap<String, Object>();
	
	@Override
	public Map<String, Object> login() {
		map.put("code",100);
		map.put("msg","성공");
		map.put("method","post");
		return map;
	}

}
