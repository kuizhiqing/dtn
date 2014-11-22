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
	
	public void op() throws Exception{
		String file = "";
		boolean isup = true;
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = br.readLine();
		while(line!=null){
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
			if(node>nodes.size()){
				for(int i=0;i<node-nodes.size();i++){
					nodes.add(new Node(i+nodes.size()));
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
				
		}
		br.close();
	}
	
	public void report() throws IOException{
		String file = "";
		BufferedWriter wr = new BufferedWriter(new FileWriter(new File(file)));
		for(int i=0;i<nodes.size();i++){
			wr.write(i+",");
			Node node = nodes.get(i);
			wr.write(node.getId());
			wr.write("\r\n");
		}
		wr.flush();
		wr.close();
	}

}
