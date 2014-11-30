package com.kuizhiqing.dtn.data.statistic;

public class Service {

	public static void main(String[] args) {
		String[] pres = {"pmtr","sassy","infocom2006","mit_l","roller"};
		for(String pre : pres){
			String file = pre+".txt";
			ContactStat cs = new ContactStat();
			try {
				System.out.println("op...");
				cs.op(file);
				System.out.println("report...");
				cs.report(pre);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
