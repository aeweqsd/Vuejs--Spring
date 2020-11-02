package com.SpringStarter.example.Request;

public class selection {
	private int boardpage;
	@Override
	public String toString() {
		return "selection [boardpage=" + boardpage + ", boardsubject=" + boardsubject + "]";
	}
	public int getBoardpage() {
		return boardpage;
	}
	public void setBoardpage(int boardpage) {
		this.boardpage = boardpage;
	}
	public String getBoardsubject() {
		return boardsubject;
	}
	public void setBoardsubject(String boardsubject) {
		this.boardsubject = boardsubject;
	}
	private String boardsubject;
}
