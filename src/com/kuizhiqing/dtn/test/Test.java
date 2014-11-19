package com.kuizhiqing.dtn.test;

import java.util.ArrayList;

public class Test {
	
	private int id;
	
	public Test(){
	}
	
	public Test(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t = new Test();
		t.test1();
	}
	
	public void test1(){
		ArrayList<ArrayList<Test>> ll = new ArrayList<ArrayList<Test>>(); 
		ArrayList<Test> str = null;
		for(int i=0;i<10;i++){
			str = new ArrayList<Test>();
			str.add(new Test(i));
			ll.add(str);
		}
		
		for(ArrayList<Test> t : ll){
			System.out.println(t.get(0).getId());
		}
	}

}
