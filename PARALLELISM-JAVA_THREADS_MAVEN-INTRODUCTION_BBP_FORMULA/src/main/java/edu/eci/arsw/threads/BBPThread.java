package edu.eci.arsw.threads;

import edu.eci.arsw.math.PiDigits;

public class BBPThread extends Thread {
	
	private int start, count;
	private byte[] res;
	
	public BBPThread(int start, int count) {
		this.start = start;
		this.count = count;
		this.res = null;
	}
	
	@Override
	public void run() {
		res = PiDigits.getDigits(start, count);
	}
	
	
	public byte[]  getRes() {
		return res;
	}
}
