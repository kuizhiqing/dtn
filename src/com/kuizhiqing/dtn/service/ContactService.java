package com.kuizhiqing.dtn.service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.kuizhiqing.dtn.data.contact.Dot;

public class ContactService {
	
	private ArrayList<ArrayList<Dot>> list;
	
	private HashMap<String,String> subpath;
	
	public void init(int size){
		list = new ArrayList<ArrayList<Dot>>();
		for(int i=0;i<size;i++){
			list.add(new ArrayList<Dot>());
		}
		subpath = new HashMap<String,String>();
	}
	
	public void read(String file) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = br.readLine();
		int test = 100;
		while(line!=null){
			if(line.contains("up")){
				String[] ls = line.split(" ");
				double time = Double.parseDouble(ls[0]);
				int n1 = Integer.parseInt(ls[2].replace("n", ""));
				int n2 = Integer.parseInt(ls[3].replace("n", ""));
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
			/*
			 * 
			 */
			if(test>0){
				test--;
			}else{
				break;
			}
			/*
			 */
		}
	}
	
	public void print(String file) throws IOException{
		BufferedWriter wr = new BufferedWriter(new FileWriter(new File(file)));
		for(int i=0;i<list.size();i++){
			wr.write(i+",");
			ArrayList<Dot> dlist = list.get(i);
			if(dlist==null) continue;
			//System.out.print(i+"|");
			for(Dot d : dlist){
				if(d==null) continue;
				//System.out.print(d.getId()+"|"+d.getTime()+",");
				wr.write(d.getId()+"|"+d.getTime()+",");
			}
			wr.write("\r\n");
			//System.out.println("\r\n-----------------------------");
		}
		wr.flush();
		wr.close();
	}
	
	public void getRoute(int n1, int n2, double time){
		//direct
		for(Dot d : list.get(n1)){
			if(d.getTime()>time){
				if(d.getId()==n2){
					System.out.println(n1+"||"+n2+"||"+(d.getTime()-time));
				}
			}
		}
	}
	
	public String searchRoute(int n1, int n2, double time){
		String key = n1+"_"+n2+"_"+time;
		if(subpath.containsKey(key)){
			System.out.println("log:"+key);
			return subpath.get(key);
		}
		System.out.println("search : " + n1+ " and " + n2 +" after " + time);
		String result = "";
		String ids = "";
		for(Dot d : list.get(n1)){
			if(ids.contains(","+d.getId()+",")) continue;
			ids += d.getId()+",";
			String tmp = "";
			if(d.getTime()<=time) continue;
			if(d.getId()==n2){
				tmp =  d.getId()+".\r\n";
			}else{
				tmp =  d.getId()+","+searchRoute(d.getId(),n2,d.getTime());
			}
			result += tmp;
		}
		System.out.println("++"+n1);
		if(!result.equals("")) subpath.put(key, result);
		return result;
	}
	
	public void search(int n1, int n2, double time){
		String result = "";
		for(Dot d : list.get(n1)){
			if(d.getTime()<=time) continue;
			if(d.getId()==n2){
				result =  d.getId()+".\r\n";
				System.out.println(result+"-");
			}else{
				//result =  d.getId()+","+searchRoute(d.getId(),n2,d.getTime());
				searchRoute(d.getId(),n2,d.getTime());
				result =  d.getId()+",";
				System.out.println(result+"-");
			}
		}
		//return result;
	}
	
	public void logMap(String file) throws IOException{
		BufferedWriter wr = new BufferedWriter(new FileWriter(new File(file)));
		for(String key : subpath.keySet()){
			wr.write(key+":"+subpath.get(key)+"\r\n");
		}
		wr.flush();
		wr.close();
	}

	public static void main(String[] args) {
		String filename = "pmtr.txt";
		String outdata = "pmtr_nodes.csv";
		String result = "pmtr_results.txt";
		String logmap = "pmtr_logmap.txt";
		ContactService cs = new ContactService();
		cs.init(44);
		try {
			cs.read(filename);
			cs.print(outdata);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//cs.getRoute(14,20,0);
		System.out.println("======================================");
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter(new File(result)));
			wr.write(cs.searchRoute(14,30,0));
			wr.flush();
			wr.close();
			cs.logMap(logmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(cs.searchRoute(14,30,0));
		//cs.search(14, 20, 0);

	}
	

}
