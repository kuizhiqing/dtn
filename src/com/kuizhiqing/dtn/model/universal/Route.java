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
	private Integer hop;
	
	public Route(){
		super();
		this.id = World.routes.size();
		this.beginTime = World.time;
		this.endTime = 0d;
		this.isComplete = false;
		this.hop = 0;
	}
	
	@Override
	public boolean add(Dot e){
		boolean flag = super.add(e);
		update();
		return flag;
	}
	@Override
	public Dot remove(int index){
		Dot dot = super.remove(index);
		update();
		return dot;
	}
	
	public boolean update(){
		boolean flag = true;
		this.hop = this.size()-1;
		if(this.get(this.size()-1).getId() == this.did) this.isComplete = true;
		return flag;
	}
	
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
	public Integer getHop() {
		return hop;
	}
	public void setHop(Integer hop) {
		this.hop = hop;
	}
	
}
