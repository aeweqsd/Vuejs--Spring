package com.SpringStarter.example.Domain;

public class Comment {
	private int idcomment;
	private String writerid;
	private String comment;
	private String datetime;
	private String timediff;
	private int idboard;
	private int commentcol =0;
	public int getIdcomment() {
		return idcomment;
	}
	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
	}
	public String getWriterid() {
		return writerid;
	}
	public void setWriterid(String writerid) {
		this.writerid = writerid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getTimediff() {
		return timediff;
	}
	public void setTimediff(String timediff) {
		this.timediff = timediff;
	}
	public int getIdboard() {
		return idboard;
	}
	public void setIdboard(int idboard) {
		this.idboard = idboard;
	}
	public int getCommentcol() {
		return commentcol;
	}
	public void setCommentcol(int commentcol) {
		this.commentcol = commentcol;
	}
	
	
	
}
