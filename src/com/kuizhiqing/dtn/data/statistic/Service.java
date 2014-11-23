package com.kuizhiqing.dtn.data.statistic;

public class Service {

	public static void main(String[] args) {
		String file = "pmtr.txt";
		ContactStat cs = new ContactStat();
		try {
			System.out.println("op...");
			cs.op(file);
			System.out.println("report...");
			cs.report("pmtr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
