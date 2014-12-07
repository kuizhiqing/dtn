package com.kuizhiqing.dtn.data.contact;

import java.io.*;
import java.util.ArrayList;

public class ContactService {
	
	private ArrayList<ArrayList<Dot>> list;
	public int node;
	
	public void init(){
		node = 0;
		list = new ArrayList<ArrayList<Dot>>();
	}
	
	public void loaddata(String file) throws Exception{
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
	}
	
	public void printdata(String file) throws IOException{
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
	
	/**
	 * get the direct path between dot n1 and n2
	 * @param n1
	 * @param n2
	 * @return the path as arrayList which must be size 2
	 */
	public ArrayList<Dot> hop(Dot n1, Dot n2){
//		System.out.println("hop : " + n1.getId()+ " after " + n1.getTime());
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
	 * collect all paths direct from n1 to n2
	 * @param n1
	 * @param n2
	 * @return
	 */
	public ArrayList<ArrayList<Dot>> hops(Dot n1, Dot n2){
//		System.out.println("hop : " + n1.getId()+ " after " + n1.getTime());
		ArrayList<ArrayList<Dot>> result = new ArrayList<ArrayList<Dot>>();
		ArrayList<Dot> tmp = new ArrayList<Dot>();
		tmp.add(n1);
		for(Dot d : list.get(n1.getId())){
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				if(d.getId()==n2.getId()){
					tmp.add(d);
					result.add(tmp);
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
	public ArrayList<Dot> twoHop(Dot n1, Dot n2){
		return twoHop(n1,n2,true,true);
	}
	public ArrayList<Dot> twoHop(Dot n1, Dot n2, boolean depth){
		return twoHop(n1,n2,false,depth);
	}
	/**
	 * attention : this function is DEPTH FIRST if non strict !!!
	 *             time first do not mean with less time  ATTENTION !!! 
	 * true true   严格2跳,深度优先  strict two hop, time first
	 * true false  无限制，广度优先   in two hop, hop first
	 * false false 无限制，广度优先   in two hop, hop first
	 * false true  无限制，深度优先   in two hop, time first
	 * @param n1
	 * @param n2
	 * @param strict if allowed direct contact
	 * @return
	 */
	private ArrayList<Dot> twoHop(Dot n1, Dot n2, boolean strict, boolean depth){
//		System.out.println("twohop : " + n1.getId()+ " after " + n1.getTime());
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
					ArrayList<Dot> h = hop(d,n2);
					if(h==null){
						continue;
					}else{
						result.addAll(h);
						return result;
					}
				}
			}
		}
		if(result.size()<=1) return null;
		return null;
	}
	
	/**
	 * collect all paths possible from n1 to n2 in 2 hop
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	public ArrayList<ArrayList<Dot>> twoHops(Dot n1, Dot n2){
//		System.out.println("twohops : " + n1.getId()+ " after " + n1.getTime());
		ArrayList<ArrayList<Dot>> result = new ArrayList<ArrayList<Dot>>();
		ArrayList<Dot> tmp = null;
		for(Dot d : list.get(n1.getId())){
			tmp = new ArrayList<Dot>();
			tmp.add(n1);
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				if(d.getId()==n2.getId()){
					tmp.add(d);
					result.add(tmp);
				}else{
					ArrayList<Dot> h = hop(d,n2);
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
	
	public ArrayList<ArrayList<Dot>> threeHops(Dot n1, Dot n2){
		ArrayList<ArrayList<Dot>> result = new ArrayList<ArrayList<Dot>>();
		ArrayList<Dot> tmp = null;
		for(Dot d : list.get(n1.getId())){
			tmp = new ArrayList<Dot>();
			tmp.add(n1);
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				if(d.getId()==n2.getId()){
					tmp.add(d);
					result.add(tmp);
				}else{
					ArrayList<Dot> h = quikTwoHop(d,n2);
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
	
	public ArrayList<Dot> quikTwoHop(Dot n1, Dot n2){
//		System.out.println("twohops : " + n1.getId()+ " after " + n1.getTime());
		ArrayList<Dot> result = new ArrayList<Dot>();
		ArrayList<Dot> tmp = null;
		for(Dot d : list.get(n1.getId())){
			tmp = new ArrayList<Dot>();
			tmp.add(n1);
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				if(d.getId()==n2.getId()){
					tmp.add(d);
				}else{
					ArrayList<Dot> h = hop(d,n2);
					if(h==null){
						continue;
					}else{
						tmp.addAll(h);
					}
				}
				if(result.size()==0){
					result = tmp;
				}else if(result.get(result.size()-1).getTime()>tmp.get(tmp.size()-1).getTime()){
					result = tmp;
				}else if(result.get(result.size()-1).getTime()==tmp.get(tmp.size()-1).getTime()&&result.size()>tmp.size()){
					result = tmp;
				}
			}
		}
		if(result.size()<=1) return null;
		return result;
	}
	
	public ArrayList<Dot> quikThreeHop(Dot n1, Dot n2){
		ArrayList<Dot> result = new ArrayList<Dot>();
		ArrayList<Dot> tmp = null;
		for(Dot d : list.get(n1.getId())){
			tmp = new ArrayList<Dot>();
			tmp.add(n1);
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				if(d.getId()==n2.getId()){
					tmp.add(d);
				}else{
					ArrayList<Dot> h = quikTwoHop(d,n2);
					if(h==null){
						continue;
					}else{
						tmp.addAll(h);
					}
				}
				if(result.size()==0){
					result = tmp;
				}else if(result.get(result.size()-1).getTime()>tmp.get(tmp.size()-1).getTime()){
					result = tmp;
				}else if(result.get(result.size()-1).getTime()==tmp.get(tmp.size()-1).getTime()&&result.size()>tmp.size()){
					result = tmp;
				}
			}
		}
		if(result.size()<=1) return null;
		return result;
	}
	
	public ArrayList<Dot> quikHop(Dot n1, Dot n2, int n){
//		if(n==3) return quikThreeHop(n1, n2);
		//System.out.println("quikHop : " + n1.getId()+ " after " + n1.getTime());
		ArrayList<Dot> result = new ArrayList<Dot>();
		ArrayList<Dot> tmp = null;
		for(Dot d : list.get(n1.getId())){
			tmp = new ArrayList<Dot>();
			tmp.add(n1);
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				if(d.getId()==n2.getId()){
					tmp.add(d);
				}else{
					if(n<=1) continue;
					ArrayList<Dot> h = quikHop(d,n2,n-1);
					if(h==null){
						continue;
					}else{
						tmp.addAll(h);
					}
				}
				if(result.size()==0){
					result = tmp;
				}else if(result.get(result.size()-1).getTime()>tmp.get(tmp.size()-1).getTime()){
					result = tmp;
				}else if(result.get(result.size()-1).getTime()==tmp.get(tmp.size()-1).getTime()&&result.size()>tmp.size()){
					result = tmp;
				}
			}
		}
		if(result.size()<=1) return null;
		return result;
	}
	
	
	public void console(ArrayList<Dot> path){
		System.out.println("------------");
		if(path==null) return;
		String line = "";
		for(Dot d : path){
			line += d.getId()+"@"+d.getTime()+",";
		}
		System.out.println(line);
	}
	
	public void consoleList(ArrayList<ArrayList<Dot>> path){
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
		ContactService cs = new ContactService();
		cs.init();
		try {
			cs.loaddata(filename);
			//cs.printdata(outdata);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Dot n1 = new Dot(16,0);
		Dot n2 = new Dot(20,1000000);
		ArrayList<Dot> re = cs.hop(n1, n2);
		cs.console(re);
		ArrayList<Dot> re0 = cs.twoHop(n1, n2);
//		cs.console(re0);
		ArrayList<Dot> re1 = cs.twoHop(n1, n2,true);
//		cs.console(re1);
		ArrayList<Dot> re2 = cs.twoHop(n1, n2,false);
//		cs.console(re2);
		ArrayList<Dot> re3 = cs.quikTwoHop(n1, n2);
		cs.console(re3);
		ArrayList<Dot> re4 = cs.quikThreeHop(n1, n2);
		cs.console(re4);
		ArrayList<Dot> rek = cs.quikHop(n1, n2,3);
		cs.console(rek);
		
		ArrayList<ArrayList<Dot>> ref = cs.twoHops(n1, n2);
		ArrayList<ArrayList<Dot>> ref3 = cs.threeHops(n1, n2);
		//cs.consoleList(ref);
		//cs.consoleList(ref3);

		System.out.println("time:"+(System.currentTimeMillis()-runtime));
	}
	

}