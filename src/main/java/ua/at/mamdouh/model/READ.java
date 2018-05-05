package ua.at.mamdouh.model;

public enum READ {

	YES(1),
	NO(0);

  private int isRead;
  
   private READ(int isRead){
	   
	   this.isRead = isRead;
   }
   
   public int getValue() {
	   
	   return isRead;
   }
   
}