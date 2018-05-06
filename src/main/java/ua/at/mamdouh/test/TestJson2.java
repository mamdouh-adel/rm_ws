package ua.at.mamdouh.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestJson2 {

	public static void main(String[] args) throws IOException, JSONException {
		
		String string = "";
		
		InputStream crunchifyInputStream = new FileInputStream("D:\\test3.txt");
		InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
		BufferedReader br = new BufferedReader(crunchifyReader);
		String line;
		while ((line = br.readLine()) != null) {
			string += line + "\n";
		}

		JSONObject jsonObject = new JSONObject(string);
		System.out.println(jsonObject);
		
		System.out.println("---------------------");
		
		   JSONObject love = jsonObject.getJSONObject("love");
		   
		   System.out.println(love.getString("id"));
		   System.out.println(love.getString("topic"));
		   System.out.println(love.getBoolean("read"));
		     
	
}
	
}
