package com.SpringStarter.example.Domain;


public class Popular {
	private final int boardwe= 10;
	private final int comwe = 3;
	private final int hitwe = 1;
	private String subject;
	@Override
	public String toString() {
		return "Popular [subject=" + subject + ", numofboard=" + numofboard + ", numofhit=" + numofhit + ", numofcom="
				+ numofcom + "]";
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getNumofboard() {
		return numofboard;
	}
	public void setNumofboard(int numofboard) {
		this.numofboard = numofboard;
	}
	public int getNumofhit() {
		return numofhit;
	}
	public void setNumofhit(int numofhit) {
		this.numofhit = numofhit;
	}
	public int getNumofcom() {
		return numofcom;
	}
	public void setNumofcom(int numofcom) {
		this.numofcom = numofcom;
	}
	private int numofboard;
	private int numofhit;
	private int numofcom;
	public int getpop() {
		return this.numofboard*boardwe + this.numofcom*comwe+this.numofhit*hitwe;
	}
}
