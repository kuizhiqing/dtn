package com.kuizhiqing.dtn.data.contact;

import java.util.ArrayList;

public class LatencyHop {
	
	private static int getRandom(int min, int max){
		double tmp = Math.random()*(max-min)+min;
		return (int)tmp;
	}
	
	public static ArrayList<Dot> getProbeDots(ArrayList<ArrayList<Dot>> list, int n){
		ArrayList<Dot> result = new ArrayList<Dot>();
		int len = list.size();
		ArrayList<Dot> tmp = null;
		for(int i=0;i<n;i++){
			tmp = list.get(getRandom(0,len));
			Dot d1 = tmp.get(getRandom(0,tmp.size()));
			tmp = list.get(getRandom(0,len));
			Dot d2 = tmp.get(getRandom(0,tmp.size()));
			if(d1.getTime()>d2.getTime()){
				result.add(d2);
				result.add(d1);
			}else{
				result.add(d1);
				result.add(d2);
			}
		}
		return result;
	}
	
	ArrayList<Double> hop1times = new ArrayList<Double>();
	public void statistic(ArrayList<ArrayList<Dot>> list, ArrayList<Dot> probes ){
		Hop hop = new Hop(list);
		ArrayList<Dot> hop1 = null;
		for(int i=0;i<probes.size();i+=2){
			hop1 = hop.hop(probes.get(i), probes.get(i+1));
			hop1times.add(hop1.get(hop1.size()-1).getTime()-hop1.get(0).getTime());
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("======================================");
		long runtime = System.currentTimeMillis();
		
		String filename = "data/pmtr.txt";
		ArrayList<ArrayList<Dot>> list = null;
		try {
			list = IOService.loaddata(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Dot> probes = getProbeDots(list, 20);
		Console.console(probes);
		
		System.out.println("time:"+(System.currentTimeMillis()-runtime)/1000+"s");
	}

}
