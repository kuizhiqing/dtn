package com.kuizhiqing.dtn.data.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

public class ContactStat {
	
	// system statistics
	public int dataLines;
	public int node;
	public double beginTime;
	public double endTime;
	public Vector<Node> nodes;
	
	public HashMap<Integer,Integer> contactTimesDistribution;
	public int sumFriens;
	
	public ContactStat(){
		dataLines = 0;
		node = 0;
		beginTime = 0;
		endTime = 0;
		nodes = new Vector<Node>();
		contactTimesDistribution = new HashMap<Integer,Integer>();
		sumFriens = 0;
	}
	
	public void op(String file) throws Exception{
		boolean isup = true;
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = br.readLine();
		while(line!=null){
			//System.out.println("line"+dataLines);
			dataLines++;
			if(line.contains("up")){
				isup = true;
			}else{
				isup = false;
			}
			String[] ls = line.split(" ");
			double time = Double.parseDouble(ls[0]);
			int n1 = Integer.parseInt(ls[2].replace("n", ""));
			int n2 = Integer.parseInt(ls[3].replace("n", ""));
			if(n1>node) node = n1;
			if(n2>node) node = n2;
			int nodesize = nodes.size();
			if(node>=nodesize){
				for(int i=0;i<=node-nodesize;i++){
					nodes.add(new Node(i+nodesize));
				}
			}
			if(time>=endTime){
				endTime = time;
			}else if(time<=beginTime){
				beginTime = time;
			}
			Node node1 = nodes.get(n1);
			node1.process(n2, time, isup);
			Node node2 = nodes.get(n2);
			node2.process(n1, time, isup);
			line = br.readLine();
		}
		br.close();
	}
	
	public void report(String name) throws IOException{

		String line = "";
		BufferedWriter wr = null;
		
		wr = new BufferedWriter(new FileWriter(new File(name+"_detail.txt")));
		for(int i=0;i<nodes.size();i++){
			Node node = nodes.get(i);
			line = "id="+node.getId()+"\r\n";
			line += "friends="+node.friends()+"\r\n";
			line += "contacts="+node.contactTimes()+"\r\n";
			line += "aduration="+node.getAverageDuration()+"\r\n";
			line += "atimes="+node.getAverageContactTimes()+"\r\n";
			line += "distribution="+node.getContactTimesDistribution().toString()+"\r\n";
			wr.write(line);
			appendContactTimesDistribution(node.getContactTimesDistribution());
			sumFriens += node.friends();
		}
		//System.out.println("----"+contactTimesDistribution.toString());
		wr.flush();
		wr.close();
		
		wr = new BufferedWriter(new FileWriter(new File(name+"_sum.txt")));
		line = "name="+name+"\r\n";
		line += "datalines="+dataLines+"\r\n";
		line += "allcontact="+dataLines/2+"\r\n";
		line += "afriends="+sumFriens*1.0/(node+1)+"\r\n";
		line += "friendratio="+sumFriens*1.0/(node+1)/(node+1)+"\r\n";
		line += "begintime="+beginTime+"\r\n";
		line += "endtime="+endTime+"\r\n";
		line += "node="+(node+1)+"\r\n";
		line += "distribution="+contactTimesDistribution.toString()+"\r\n";
		wr.write(line);
		wr.flush();
	}
	
	public void appendContactTimesDistribution(HashMap<Integer,Integer> d){
		Set<Integer> c = d.keySet();
		for(int i : c){
			int tmp = d.get(i);
			if(contactTimesDistribution.containsKey(i)) tmp += contactTimesDistribution.get(i);
			contactTimesDistribution.put(i, tmp);
		}
	}

}
