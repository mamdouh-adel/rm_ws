package ua.at.mamdouh.model;

public enum SENDER {
	
	R("Habiby"),
	M("Mamdouh");

	private String sender;
	
	private SENDER(String sender) {
		
		this.sender = sender;
	}
	
	public String getValue() {
		
		return sender;
	}
}
