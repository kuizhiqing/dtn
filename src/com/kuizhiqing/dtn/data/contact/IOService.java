package com.kuizhiqing.dtn.data.contact;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOService {
	
	public ArrayList<ArrayList<Dot>> loaddata(String file) throws Exception{
		int node = 0;
		ArrayList<ArrayList<Dot>> list = new ArrayList<ArrayList<Dot>>();
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = br.readLine();
		int MAXLINE = -1;  // -1 for all
		while(line!=null){
			if(line.contains("up")){
				String[] ls = line.split(" ");
				double time = Double.parseDouble(ls[0]);
				int n1 = Integer.parseInt(ls[2].replace("n", ""));
				int n2 = Integer.parseInt(ls[3].replace("n", ""));
				if(n1>node) node = n1;
				if(n2>node) node = n2;
				int nodesize = list.size();
				if(node>=nodesize){
					for(int i=0;i<=node-nodesize;i++){
						list.add(new ArrayList<Dot>());
					}
				}
				Dot dot1 = new Dot(n1,time);
				Dot dot2 = new Dot(n2,time);
				ArrayList<Dot> l1 = list.get(n1);
				ArrayList<Dot> l2 = list.get(n2);
				if(l1.size()==0||l1.get(l1.size()-1).getTime()<=time){
					l1.add(dot2);
				}else{
					if(l1.size()>0) System.out.println("||"+l1.get(l1.size()-1).getTime()+"|"+l1.get(l1.size()-1).getId()+"|");
					System.out.println("||"+time+"|"+n1+"|"+n2);
				}
				if(l2.size()==0||l2.get(l2.size()-1).getTime()<=time){
					l2.add(dot1);
				}else{
					if(l2.size()>0) System.out.println("||"+l2.get(l2.size()-1).getTime()+"|"+l2.get(l2.size()-1).getId()+"|");
					System.out.println("||"+time+"|"+n1+"|"+n2);
				}
			}
			line = br.readLine();
			if(MAXLINE>0){
				MAXLINE--;
			}else if(MAXLINE==0){
				break;
			}
		}
		br.close();
		return list;
	}
	
	public void printdata(String file, ArrayList<ArrayList<Dot>> list) throws IOException{
		BufferedWriter wr = new BufferedWriter(new FileWriter(new File(file)));
		for(int i=0;i<list.size();i++){
			wr.write(i+":");
			ArrayList<Dot> dlist = list.get(i);
			if(dlist==null) continue;
			for(Dot d : dlist){
				if(d==null) continue;
				wr.write(d.getId()+"@"+d.getTime()+",");
			}
			wr.write("\r\n");
		}
		wr.flush();
		wr.close();
	}

}
