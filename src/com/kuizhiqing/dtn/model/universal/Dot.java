package com.kuizhiqing.dtn.model.universal;

/**
 * 
 * @author kuizhiqing
 *
 */
public abstract class  Dot {
	
	protected Integer id;
	protected Double time;
	
	protected Integer nid; // node id = path id
	
	protected Integer mid; // message id = route id
	protected Integer destination; // destination id
	protected Double ttl; // time to life
	protected Integer htl; // hop to life
	
	public boolean hasMessage(){
		return mid==null?false:true;
	}
	
	public abstract double distance(Dot dot);
	
}
