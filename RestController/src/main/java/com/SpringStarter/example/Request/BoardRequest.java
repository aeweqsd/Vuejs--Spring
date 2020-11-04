package com.SpringStarter.example.Request;


public class BoardRequest {
	private String title;
	private String subject;
	private String[] othersub;
	public String[] getOthersub() {
		return othersub;
	}
	public void setOthersub(String[] othersub) {
		this.othersub = othersub;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	private String content;
	private String userid;
}
