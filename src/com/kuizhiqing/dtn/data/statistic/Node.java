package com.kuizhiqing.dtn.data.statistic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

public class Node {
	/*
	 * author : kuizhiqing
	 * mail : kuizhiqing@msn.com
	 * 
	 */
	
	public Integer id;
	public Vector<Integer> contacts;
	public Vector<Double> duration;
	public HashMap<Integer,Integer> friends;  //id-times
	public HashMap<Integer,Double> interval;  //id-time
	
	public HashMap<Integer,Integer> contactTimesDistribution;  //times-times 10-30 : there 30 friends < 10 times contact
	
	public Node(){
		
	}
	
	public Node(int id){
		this.id = id;
		contacts = new Vector<Integer>();
		duration = new Vector<Double>();
		friends = new HashMap<Integer,Integer>();
		interval = new HashMap<Integer,Double>();
	}
	
	public void process(int id,double time,boolean isup){
		if(isup){
			contacts.add(id);
			int tmp = 1;
			if(friends.containsKey(id)) tmp = friends.get(id)+1;
			friends.put(id, tmp);
			interval.put(id, time);
		}else{
			if(interval.containsKey(id)){
				double inter = time - interval.get(id);
				duration.add(inter);
				interval.remove(id);
			}else{
				System.out.println("something wrong when down "+this.id +"|"+ id);
			}
		}
	}
	// statistic 
	public double getAverageDuration(){
		double amount = 0;
		for(double d : duration){
			amount += d;
		}
		return amount/duration.size();
	}
	public double getAverageContactTimes(){
		double amount = 0;
		Collection<Integer> c = friends.values();
		for (int i : c) {
			amount += i;
		}
		return amount/friends.size();
	}
	public HashMap<Integer,Integer> getContactTimesDistribution(){
		contactTimesDistribution = new HashMap<Integer,Integer>();
		Collection<Integer> c = friends.values();
		for (int i : c) {
			//int key = ((i-1)/10+1)*10;
			int key = i;
			int tmp = 1;
			if(contactTimesDistribution.containsKey(key)) tmp =  contactTimesDistribution.get(key)+1;
			/*if(key>100){
				contactTimesDistribution.put(110,tmp);
				continue;
			}*/
			contactTimesDistribution.put(key,tmp);
		}
		return contactTimesDistribution;
	}
	public int contactTimes(){
		return contacts.size();
	}
	public int friends(){
		return friends.size();
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

	public Vector<Double> getDuration() {
		return duration;
	}

	public void setDuration(Vector<Double> duration) {
		this.duration = duration;
	}

	public HashMap<Integer, Integer> getFriends() {
		return friends;
	}

	public void setFriends(HashMap<Integer, Integer> friends) {
		this.friends = friends;
	}

	public HashMap<Integer, Double> getInterval() {
		return interval;
	}

	public void setInterval(HashMap<Integer, Double> interval) {
		this.interval = interval;
	}



}
