package com.kuizhiqing.dtn.model.universal;

import java.util.ArrayList;

/**
 * 
 * @author kuizhiqing
 *
 */
@SuppressWarnings("serial")
public class Route extends ArrayList<Dot>{
	
	private Integer id;
	private Double beginTime;
	private Double endTime;
	
	private Integer sid; // source node id
	private Integer did; // destination node id
	private boolean isComplete;// is the destination reached
	
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
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
}
