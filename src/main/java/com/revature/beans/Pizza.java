package com.revature.beans;

public class Pizza {
	private String type;
	private String size;
	private String status;
	public Pizza() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pizza(String type, String size, String status) {
		super();
		this.type = type;
		this.size = size;
		this.status = status;
	}
	@Override
	public String toString() {
		return (size+" "+type+" pizza, status: "+status);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}