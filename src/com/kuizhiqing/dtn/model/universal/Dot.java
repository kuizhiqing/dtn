package com.kuizhiqing.dtn.model.universal;

public class Dot {
	
	private Integer id;
	private Integer snapshot;
	private Integer nid; // node id = path id
	private Integer mid; // route id
	private Double time;
	private Double x;
	private Double y;
	
	public Dot() {
		super();
	}
	public Dot(Integer id, Double x, Double y) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
	}
	public Dot(Integer nid, Double time) {
		super();
		this.nid = nid;
		this.time = time;
	}
	public Dot(Integer id, Integer nid, Double time) {
		super();
		this.id = id;
		this.nid = nid;
		this.time = time;
	}
	public Dot(Integer id, Integer snapshot, Integer nid, Integer mid,
			Double time, Double x, Double y) {
		super();
		this.id = id;
		this.snapshot = snapshot;
		this.nid = nid;
		this.mid = mid;
		this.time = time;
		this.x = x;
		this.y = y;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSnapshot() {
		return snapshot;
	}
	public void setSnapshot(Integer snapshot) {
		this.snapshot = snapshot;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Double getTime() {
		return time;
	}
	public void setTime(Double time) {
		this.time = time;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}

	
}
