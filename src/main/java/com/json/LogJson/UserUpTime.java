package com.json.LogJson;

import java.util.ArrayList;

public class UserUpTime {
	
	private String userName;
	private ArrayList<String> startTime;
	private ArrayList<String> endTime;
	
	public UserUpTime(String userName) {
		this.userName = userName;
		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ArrayList<String> getStartTime() {
		return startTime;
	}
	public void setStartTime(ArrayList<String> startTime) {
		this.startTime = startTime;
	}
	public ArrayList<String> getEndTime() {
		return endTime;
	}
	public void setEndTime(ArrayList<String> endTime) {
		this.endTime = endTime;
	}
		
}
