package com.bcx.managersystem.entity;

import java.io.Serializable;

public class MyEntity implements Serializable{
	private int id;
	private String date;
	private String time;
	private String title;
	private String content;
	public MyEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyEntity(int id, String date, String time, String title, String content) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.title = title;
		this.content = content;
	}
	@Override
	public String toString() {
		return "Entity [id=" + id + ", date=" + date + ", time=" + time + ", title=" + title + ", content=" + content
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	
}
