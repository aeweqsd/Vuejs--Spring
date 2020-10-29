package com.SpringStarter.example.Domain;

public class Note {
	private int idnote;
	public int getIdnote() {
		return idnote;
	}
	public void setIdnote(int idnote) {
		this.idnote = idnote;
	}
	public String getIdsender() {
		return idsender;
	}
	public void setIdsender(String idsender) {
		this.idsender = idsender;
	}
	public String getIdreceiver() {
		return idreceiver;
	}
	public void setIdreceiver(String idreceiver) {
		this.idreceiver = idreceiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isIs_read() {
		return is_read;
	}
	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}
	private String idsender;
	private String idreceiver;
	private String content;
	private boolean is_read;
	

}
