package login;

import java.util.HashMap;
import java.util.Map;

public class LoginHashMapImpl implements LoginHashMap {

	Map<String, String> map = new HashMap<>();
	
	@Override
	public Map<String, String> login() {
		map.put("ppk", "1234");
		map.put("young", "1234");
		map.put("kim", "1234");
		return map;
	}

}
