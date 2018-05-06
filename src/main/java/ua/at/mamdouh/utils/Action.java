package ua.at.mamdouh.utils;

import org.json.JSONException;
import org.json.JSONObject;

import ua.at.mamdouh.model.READ;

public class Action {
	
	public String send(String sender) {
		
	JSONObject obj = new JSONObject();
	
	JSONObject love = new JSONObject();
		
		try {
			
			obj.put("id", sender);
			obj.put("read", READ.NO.getValue());
			
			love.put("love",obj);
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return love.toString();
	}

}
