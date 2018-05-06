package ua.at.mamdouh.test;

import org.json.JSONException;
import org.json.JSONObject;

import ua.at.mamdouh.model.READ;
import ua.at.mamdouh.model.SENDER;

public class TestJson3 {

	public static void main(String[] args) throws JSONException {
		
		JSONObject obj = new JSONObject();
		
		obj.put("id", SENDER.R.getValue());
		obj.put("read", READ.NO.getValue());
		
		JSONObject love = new JSONObject();
		
		love.put("love",obj);
		
		System.out.println(love);

	}

}
