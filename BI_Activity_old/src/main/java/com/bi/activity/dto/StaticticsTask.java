package com.bi.activity.dto;

public class StaticticsTask {
	//完成任务情况
	private String task;
	//人数
	private String num;
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "StaticticsTask [task=" + task + ", num=" + num + "]";
	}


}
