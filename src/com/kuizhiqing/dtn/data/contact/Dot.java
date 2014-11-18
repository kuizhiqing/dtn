package com.kuizhiqing.dtn.data.contact;

import java.util.ArrayList;

public class Dot {
	
	private int id;
	private double time;
	
	public Dot() {
		super();
	}
	
	public Dot(int id){
		this.id = id;
	}

	public Dot(int id, double time) {
		super();
		this.id = id;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

}
