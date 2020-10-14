package com.SpringStarter.example.Request;



public class CommentRequest {
	private String comment;
	private String userid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIdboard() {
		return idboard;
	}
	public void setIdboard(int idboard) {
		this.idboard = idboard;
	}
	private int idboard;

}
