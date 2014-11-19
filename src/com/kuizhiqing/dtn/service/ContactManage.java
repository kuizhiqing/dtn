package com.kuizhiqing.dtn.service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.kuizhiqing.dtn.data.contact.Dot;

public class ContactManage {
	
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
		//int test = 3000000;
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
			 *
			if(test>0){
				test--;
			}else{
				break;
			}
			*
			 */
		}
		br.close();
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
	
	public void console(ArrayList<Dot> path){
		if(path==null) return;
		String line = "";
		for(Dot d : path){
			line += d.getId()+"["+d.getTime()+"]-";
		}
		System.out.println(line);
	}
	
	public void consoleList(ArrayList<ArrayList<Dot>> path){
		if(path==null) return;
		String line = "";
		for(ArrayList<Dot> list : path){
			for(Dot d : list){
				line += d.getId()+"["+d.getTime()+"]-";
			}
			System.out.println(line);
			line = "";
		}
	}

	/**
	 * get the direct path between dot n1 and n2
	 * @param n1
	 * @param n2
	 * @return the path as arrayList which must be size 2
	 */
	public ArrayList<Dot> direct(Dot n1, Dot n2){
		System.out.println("direct : " + n1.getId()+ " after " + n1.getTime());
		ArrayList<Dot> result = new ArrayList<Dot>();
		result.add(n1);
		for(Dot d : list.get(n1.getId())){
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				if(d.getId()==n2.getId()){
					result.add(d);
					return result;
				}
			}
		}
		return null;
	}
	/**
	 * get the path between dot n1 and n2 less than 2 hop
	 * @param n1
	 * @param n2
	 * @return the path as arrayList
	 */
	public ArrayList<Dot> hop(Dot n1, Dot n2){
		return hop(n1,n2,true,true);
	}
	public ArrayList<Dot> hop(Dot n1, Dot n2, boolean depth){
		return hop(n1,n2,false,depth);
	}
	/**
	 * attention : this function is DEPTH FIRST if non strict !!!
	 * true true   严格2跳,深度优先
	 * true false  无限制，广度优先
	 * false false 无限制，广度优先
	 * false true  无限制，深度优先
	 * @param n1
	 * @param n2
	 * @param strict if allowed direct contact
	 * @return
	 */
	private ArrayList<Dot> hop(Dot n1, Dot n2, boolean strict, boolean depth){
		System.out.println("hop : " + n1.getId()+ " after " + n1.getTime());
		ArrayList<Dot> result = new ArrayList<Dot>();
		result.add(n1);
		if(!depth){
			for(Dot d : list.get(n1.getId())){
				if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
					if(!strict&&d.getId()==n2.getId()){
						result.add(d);
						return result;
					}
				}
			}
		}
		for(Dot d : list.get(n1.getId())){
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				if(!strict&&d.getId()==n2.getId()){
					result.add(d);
					return result;
				}else{
					ArrayList<Dot> h = direct(d,n2);
					if(h==null){
						continue;
					}else{
						result.addAll(h);
						return result;
					}
				}
			}
		}
		return null;
	}
	/**
	 * collect all paths possible from n1 to n2 in 2 hop
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	public ArrayList<ArrayList<Dot>> allHop(Dot n1, Dot n2){
		System.out.println("allhop : " + n1.getId()+ " after " + n1.getTime());
		ArrayList<ArrayList<Dot>> result = new ArrayList<ArrayList<Dot>>();
		ArrayList<Dot> tmp = null;
		for(Dot d : list.get(n1.getId())){
			tmp = new ArrayList<Dot>();
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				if(d.getId()==n2.getId()){
					tmp.add(d);
					result.add(tmp);
				}else{
					ArrayList<Dot> h = direct(d,n2);
					if(h==null){
						continue;
					}else{
						tmp.addAll(h);
						result.add(tmp);
					}
				}
			}
		}
		return result;
	}
	String ids = ","; // nothing for git
	public String searchRoute(int n1, int n2, double time){
		ids += n1+",";
		String key = n1+"_"+n2+"_"+time;
		if(subpath.containsKey(key)){
			System.out.println("log:"+key);
			return subpath.get(key);
		}
		System.out.println("search : " + n1+ " and " + n2 +" after " + time);
		String result = "";  
		for(Dot d : list.get(n1)){
			if(ids.contains(","+d.getId()+",")) continue;
			ids += d.getId()+",";  
			String tmp = "";
			if(d.getTime()<=time) continue;
			if(d.getId()==n2){
				tmp =  d.getId()+".";
			}else{
				tmp =  d.getId()+","+searchRoute(d.getId(),n2,d.getTime());
			}
			result += tmp+"|";
		}
		System.out.println("++"+n1);
		if(!result.equals("")){
			subpath.put(key, result);
		}else{
			ids = ",";
		}
		return "("+result+")";
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
	
	/**
	 * give out a path possible
	 * only one path will be given using long depth
	 * @param n1 source node
	 * @param n2 destination node
	 * @param time begin time
	 * @return
	 */
	public String searchDirect(int n1, int n2, double time){
		System.out.println("search direct : " + n1+ " and " + n2 +" after " + time);
		for(Dot d : list.get(n1)){
			if(d.getTime()<=time) continue;
			if(d.getId()==n2){
				return  d.getId()+"|"+d.getTime()+".\r\n";
			}else{
				return  d.getId()+"|"+d.getTime()+",\r\n"+searchDirect(d.getId(),n2,d.getTime());
			}
		}
		return "-";
	}
	/**
	 * give one path using long depth with the restriction of ttl
	 * @param n1
	 * @param n2
	 * @param time
	 * @param deadtime
	 * @return
	 */
	public String searchDirect(int n1, int n2, double time, double deadtime){
		String key = n1+"_"+n2+"_"+time;
		if(subpath.containsKey(key)){
			System.out.println("log:"+key);
			if(subpath.get(key).equals("-")) return null;
			return subpath.get(key);
		}
		System.out.println("search direct : " + n1+ " and " + n2 +" after " + time);
		for(Dot d : list.get(n1)){
			if(d.getTime()<=time) continue;
			if(d.getTime()>=deadtime){
				subpath.put(key, "-");
				return null;
			}
			if(d.getId()==n2){
				String re = d.getId()+"|"+d.getTime()+".\r\n";
				subpath.put(key, re);
				return  re;
			}else{
				String sub = searchDirect(d.getId(),n2,d.getTime(),deadtime);
				if(sub==null){
					continue;
				}else{
					String re = d.getId()+"|"+d.getTime()+",\r\n"+sub;
					subpath.put(key, re);
					return re;
				}
			}
		}
		subpath.put(key, "-");
		return "-";
	}
	
	public String searchHop(int n1, int n2, double time, double deadtime, int hop){
		return null;
	}

	public static void main(String[] args) {
		long runtime = System.currentTimeMillis();
		String filename = "pmtr.txt";
		String outdata = "pmtr_nodes.csv";
		String result = "pmtr_results.txt";
		String logmap = "pmtr_logmap.txt";
		ContactManage cs = new ContactManage();
		cs.init(44);
		try {
			cs.read(filename);
			//cs.print(outdata);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Dot n1 = new Dot(14,0);
		Dot n2 = new Dot(20,1000000);
		//ArrayList<Dot> re = cs.direct(n1, n2);
		//ArrayList<Dot> re0 = cs.hop(n1, n2);
		//ArrayList<Dot> re1 = cs.hop(n1, n2,true);
		//ArrayList<Dot> re2 = cs.hop(n1, n2,false);
		//cs.console(re0);
		//cs.console(re1);
		//cs.console(re2);
		
		ArrayList<ArrayList<Dot>> re = cs.allHop(n1, n2);
		cs.consoleList(re);

		//cs.getRoute(14,20,0);
		System.out.println("======================================");
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter(new File(result)));
			//System.out.println(cs.searchDirect(14, 30, 0));
			//wr.write(cs.searchDirect(14,30,0));
			/*String fin = cs.searchDirect(16,32,0,472411);
			if(fin!=null){
				wr.write(fin);
			}else{
				System.out.println("nullllllllllllllllllllllll");
			}*/
			wr.flush();
			wr.close();
			cs.logMap(logmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(cs.searchRoute(14,30,0));
		//cs.search(14, 20, 0);
		System.out.println("time:"+(System.currentTimeMillis()-runtime));
	}
	

}