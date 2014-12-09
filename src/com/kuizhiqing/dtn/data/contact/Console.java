package com.kuizhiqing.dtn.data.contact;

import java.util.ArrayList;

public class Console {
	
	public static void console(ArrayList<Dot> path){
		System.out.println("------------");
		if(path==null) return;
		String line = "";
		for(Dot d : path){
			line += d.getId()+"@"+d.getTime()+",";
		}
		System.out.println(line);
	}
	
	public static void consoleList(ArrayList<ArrayList<Dot>> path){
		System.out.println("------------");
		if(path==null) return;
		String line = "";
		for(ArrayList<Dot> list : path){
			for(Dot d : list){
				line += d.getId()+"@"+d.getTime()+",";
			}
			System.out.println(line);
			line = "";
		}
	}
	
	public static void main(String[] args) {
		System.out.println("======================================");
		long runtime = System.currentTimeMillis();
		String filename = "data/pmtr.txt";
		String outdata = "result/contact/pmtr_paths.csv";
		//String result = "result/contact/pmtr_results.txt";
		
		ArrayList<ArrayList<Dot>> list = null;
		IOService is = new IOService();
		try {
			list = is.loaddata(filename);
			//is.printdata(outdata,list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Hop hop = new Hop(list);
		Dot n1 = new Dot(16,0);
		Dot n2 = new Dot(20,1000000);
		ArrayList<Dot> re = hop.hop(n1, n2);
		console(re);
		ArrayList<Dot> re0 = hop.twoHop(n1, n2);
//		cs.console(re0);
		ArrayList<Dot> re1 = hop.twoHop(n1, n2,true);
//		cs.console(re1);
		ArrayList<Dot> re2 = hop.twoHop(n1, n2,false);
//		cs.console(re2);
		ArrayList<Dot> re3 = hop.quikTwoHop(n1, n2);
		console(re3);
		ArrayList<Dot> re4 = hop.quikThreeHop(n1, n2);
		console(re4);
		ArrayList<Dot> rek = hop.quikHop(n1, n2,4);
		console(rek);
		
		ArrayList<ArrayList<Dot>> ref = hop.twoHops(n1, n2);
		ArrayList<ArrayList<Dot>> ref3 = hop.threeHops(n1, n2);
		//cs.consoleList(ref);
		//cs.consoleList(ref3);

		System.out.println("time:"+(System.currentTimeMillis()-runtime));
	}
	

}