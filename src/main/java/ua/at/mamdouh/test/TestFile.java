package ua.at.mamdouh.test;

import ua.at.mamdouh.model.READ;
import ua.at.mamdouh.model.SENDER;
import ua.at.mamdouh.utils.DbHelper;

public class TestFile {

	public static void main(String[] args) {
		
		
		DbHelper.getInstance().insert(SENDER.R.getValue(), READ.NO.getValue());

	}

}
