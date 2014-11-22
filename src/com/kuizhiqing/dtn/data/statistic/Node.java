package com.kuizhiqing.dtn.data.statistic;

import java.util.Vector;

public class Node {
	
	public Integer id;
	public Vector<Integer> contacts;
	public Vector<Double> ctime; //contact time
	public Vector<Double> itime; //inter time
	public double lastc;		
	public double lasti;		
	
	public Node(){
		
	}
	
	public Node(int id){
		this.id = id;
	}
	
	public void contact(int id){
		contacts.add(id);
	}

	public void process(int id,double time,boolean isup){
		if(isup){
			contacts.add(id);
		}else{
			
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vector<Integer> getContacts() {
		return contacts;
	}

	public void setContacts(Vector<Integer> contacts) {
		this.contacts = contacts;
	}

	public Vector<Double> getCtime() {
		return ctime;
	}

	public void setCtime(Vector<Double> ctime) {
		this.ctime = ctime;
	}

	public Vector<Double> getItime() {
		return itime;
	}

	public void setItime(Vector<Double> itime) {
		this.itime = itime;
	}

	public double getLastc() {
		return lastc;
	}

	public void setLastc(double lastc) {
		this.lastc = lastc;
	}

	public double getLasti() {
		return lasti;
	}

	public void setLasti(double lasti) {
		this.lasti = lasti;
	}


}
