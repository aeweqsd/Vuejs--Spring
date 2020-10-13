package com.SpringStarter.example.Domain;



public class Board {
	private int idboard;
	private String boardname;
	private String boardsubject;
	private String boardcontent;
	private String boardwriter;
	private int hit;
	private int likenum;
	private String datetime;
	private String timediff;
	public String getTimediff() {
		return timediff;
	}
	public void setTimediff(String timediff) {
		this.timediff = timediff;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "Board [idboard=" + idboard + ", boardname=" + boardname + ", boardsubject=" + boardsubject
				+ ", boardcontent=" + boardcontent + ", boardwriter=" + boardwriter + ", hit=" + hit + ", likenum="
				+ likenum + ", datetime=" + datetime + "]";
	}
	public int getIdboard() {
		return idboard;
	}
	public void setIdboard(int idboard) {
		this.idboard = idboard;
	}
	public String getBoardname() {
		return boardname;
	}
	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}
	public String getBoardsubject() {
		return boardsubject;
	}
	public void setBoardsubject(String boardsubject) {
		this.boardsubject = boardsubject;
	}
	public String getBoardcontent() {
		return boardcontent;
	}
	public void setBoardcontent(String boardcontent) {
		this.boardcontent = boardcontent;
	}
	public String getBoardwriter() {
		return boardwriter;
	}
	public void setBoardwriter(String boardwriter) {
		this.boardwriter = boardwriter;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getLikenum() {
		return likenum;
	}
	public void setLikenum(int likenum) {
		this.likenum = likenum;
	}
	

}
