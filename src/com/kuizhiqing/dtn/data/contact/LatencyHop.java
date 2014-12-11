package com.kuizhiqing.dtn.data.contact;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LatencyHop {
	
	public static final int probe = 100;
	public static final int maxhops = 5;
	
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
	
	static ArrayList<ArrayList<Double>> hoptimes = new ArrayList<ArrayList<Double>>();
	public void statistic(ArrayList<ArrayList<Dot>> list, ArrayList<Dot> probes ){
		Hop hop = new Hop(list);
		ArrayList<Dot> hops = null;
		for(int j=0;j<maxhops;j++){
			hoptimes.add(new ArrayList<Double>());
			for(int i=0;i<probes.size();i+=2){
				if(j==0){
					hops = hop.hop(probes.get(i), probes.get(i+1));
				}else if(j==1){
					hops = hop.quikTwoHop(probes.get(i), probes.get(i+1));
				}else if(j==2){
					hops = hop.quikThreeHop(probes.get(i), probes.get(i+1));
				}else{
					hops = hop.quikHop(probes.get(i), probes.get(i+1), j+1);
				}
				if(hops!=null){
					hoptimes.get(j).add(hops.get(hops.size()-1).getTime()-hops.get(0).getTime());
				}
				System.out.println((j+1)+"-hop "+(i+1)+"-th probe over");
			}
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("======================================");
		long runtime = System.currentTimeMillis();
		
		String pre = "mit_l";
		String filename = "data/"+pre+".txt";
		ArrayList<ArrayList<Dot>> list = null;
		LatencyHop lh = new LatencyHop();
		try {
			list = IOService.loaddata(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Dot> probes = getProbeDots(list, probe);
		Console.console(probes);
		lh.statistic(list, probes);
		
		String file="result/hop/"+pre;
		String file1="result/hop/"+pre;
		BufferedWriter wr = null;
		BufferedWriter wr1 = null;
		try {
			wr = new BufferedWriter(new FileWriter(new File(file)));
			wr1 = new BufferedWriter(new FileWriter(new File(file1)));
			int i=0;
			for(ArrayList<Double> tt : hoptimes){
				double sum = 0;
				for(double d : tt){
					sum += d;
				}
				wr.write(sum/hoptimes.get(i).size()+"\r\n");
				wr1.write(hoptimes.get(i).size()+"\r\n");
				System.out.println((i+1)+"-hop|"+sum/hoptimes.get(i).size());
				System.out.println((i+1)+"-hop|"+hoptimes.get(i).size());
				i++;
			}
			wr.close();
			wr1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("time:"+(System.currentTimeMillis()-runtime)/1000+"s");
	}

}
