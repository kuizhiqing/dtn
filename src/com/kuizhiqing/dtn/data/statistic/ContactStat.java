package com.kuizhiqing.dtn.data.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class ContactStat {
	
	// system statistics
	public int dataLines;
	public int node;
	public double beginTime;
	public double endTime;
	public Vector<Node> nodes;
	
	public ContactStat(){
		dataLines = 0;
		node = 0;
		beginTime = 0;
		endTime = 0;
		nodes = new Vector<Node>();
	}
	
	public void op(String file) throws Exception{
		boolean isup = true;
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = br.readLine();
		while(line!=null){
			System.out.println("line"+dataLines);
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
					System.out.println(node+"|"+(i+nodesize));
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
		BufferedWriter wr = new BufferedWriter(new FileWriter(new File(name+"_sum.txt")));
		String line = "";
		line = "name="+name+"\r\n";
		line += "allcontact="+dataLines/2+"\r\n";
		line += "begintime="+beginTime+"\r\n";
		line += "endtime="+endTime+"\r\n";
		line += "node="+node+"\r\n";
		wr.write(line);
		wr.flush();
		wr = new BufferedWriter(new FileWriter(new File(name+"_detail.txt")));
		for(int i=0;i<nodes.size();i++){
			Node node = nodes.get(i);
			line = "id="+node.getId()+"\r\n";
			line += "friends="+node.friends()+"\r\n";
			line += "contacts="+node.contactTimes()+"\r\n";
			line += "aduration="+node.getAverageDuration()+"\r\n";
			wr.write(line);
		}
		wr.flush();
		wr.close();
	}

}
