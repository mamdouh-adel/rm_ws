package ua.at.mamdouh.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import ua.at.mamdouh.model.READ;
import ua.at.mamdouh.model.SENDER;
import ua.at.mamdouh.utils.Action;
import ua.at.mamdouh.utils.DbHelper;

public class TestSend1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String sender = SENDER.R.getValue();
		
		try {
			URL url = new URL("http://localhost:8080/rm_web_serv/api/RMservice");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(new Action().send(sender));
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		///	while (br.readLine() != null) {}
			
			System.out.println("\nRM REST Service Invoked Successfully..");
			
			in.close();
		} catch (Exception e) {
			System.out.println("\nError while calling RM REST Service");
			System.out.println(e);
		}
	}

}
