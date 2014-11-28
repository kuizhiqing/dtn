package com.kuizhiqing.dtn.model.universal;

import java.util.ArrayList;

/**
 * 
 * @author kuizhiqing
 *
 */
@SuppressWarnings("serial")
public class Path extends ArrayList<Dot>{
	
	private Integer id; 
	private Double beginTime;
	private Double endTime;

	private Integer nid; // node id 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Double beginTime) {
		this.beginTime = beginTime;
	}

	public Double getEndTime() {
		return endTime;
	}

	public void setEndTime(Double endTime) {
		this.endTime = endTime;
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}
	
	
}
