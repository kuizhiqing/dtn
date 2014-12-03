package com.kuizhiqing.dtn.data.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Global {

	/**
	 * Statistic for some factors
	 * @author kuizhiqing
	 * @param args
	 */
	public static void main(String[] args) {
		String[] pres = {"pmtr","sassy","infocom2006","mit_l","roller"};
		for(String pre : pres){
			String file = pre+".txt";
			Global cs = new Global();
			try {
				System.out.println("op...");
				cs.go(file);
				System.out.println("report...");
				cs.report(pre+"_vect");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	HashMap<Double,Integer> vect = new HashMap<Double,Integer>();
	public void go(String file) throws Exception{
		boolean isup = true;
		int lineindex = 0;
		double time = 0;
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = br.readLine();
		while(line!=null){
			lineindex++;
			if(line.contains("up")){
				isup = true;
				String[] ls = line.split(" ");
				time = Double.parseDouble(ls[0]);
				vect.put(time, lineindex);
			}
			line = br.readLine();
		}
		br.close();
	}
	
	public void report(String file) throws Exception{
		
		BufferedWriter wr  = new BufferedWriter(new FileWriter(new File("x"+file)));
		BufferedWriter yr  = new BufferedWriter(new FileWriter(new File("y"+file)));
		Set<Double> c = vect.keySet();
		for(double i : c){
			double tmp = vect.get(i);
			wr.write(i+",");
			yr.write(tmp+",");
		}
		wr.write("0");
		yr.write("0");
		wr.close();
		yr.close();
	}

}
