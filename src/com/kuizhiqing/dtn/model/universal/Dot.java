package com.kuizhiqing.dtn.model.universal;

/**
 * 
 * @author kuizhiqing
 *
 */
public class Dot {
	
	private Integer id;
	protected Integer nid; // node id = path id
	protected Integer mid; // message id = route id
	protected Double time;
	
	public Dot() {
		super();
	}
	public Dot(Integer id, Double x, Double y) {
		super();
		this.id = id;
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
}
