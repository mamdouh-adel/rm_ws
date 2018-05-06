package ua.at.mamdouh.utils;

public class Hit {

	private boolean R, M;

	private int countR, countM;

	private static Hit hit;

	public static Hit getInstance() {

		if (hit == null) {

			hit = new Hit();
		}

		return hit;
	}

	private Hit() {
		
		setFalse();
	}

	public boolean isR() {
		return R;
	}

	public void setR(boolean r) {
		R = r;
	}

	public boolean isM() {
		return M;
	}

	public void setM(boolean m) {
		M = m;
	}

	public int getCountR() {
		return countR;
	}

	public void setCountR(int countR) {
		this.countR = countR;
	}

	public int getCountM() {
		return countM;
	}

	public void setCountM(int countM) {
		this.countM = countM;
	}

	public void resetCountM() {

		countM = 0;
	}

	public void resetCountR() {

		countR = 0;
	}
	
	private void setFalse() {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				while(true) {
					
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					synchronized (hit) {
						
						if(R==true) {
							
							R = false;
						}
						
						if(M==true) {
							
							M = false;
						}
					}
				
				}
				
			}}).start();
	}

}
