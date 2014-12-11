package com.kuizhiqing.dtn.data.contact;

import java.util.ArrayList;
import java.util.HashMap;

public class Hop {
	
	private ArrayList<ArrayList<Dot>> list;
	private HashMap<String,ArrayList<Dot>> subpath;
	
	
	public Hop(ArrayList<ArrayList<Dot>> list){
		this.list = list;
		subpath = new HashMap<String,ArrayList<Dot>>();
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
		ArrayList<Integer> friends = new ArrayList<Integer>();
		for(Dot d : list.get(n1.getId())){
			if(friends.contains(d.getId())) continue;
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				friends.add(d.getId());
				tmp = new ArrayList<Dot>();
				tmp.add(n1);
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
		ArrayList<Integer> friends = new ArrayList<Integer>();
		for(Dot d : list.get(n1.getId())){
			if(friends.contains(d.getId())) continue;
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				friends.add(d.getId());
				tmp = new ArrayList<Dot>();
				tmp.add(n1);
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
		String title = n1.getId()+"_"+n1.getTime()+"_"+n2.getId()+"_"+n2.getTime()+"_"+n;
		if(subpath.containsKey(title)){
			return subpath.get(title);
		}
		ArrayList<Dot> result = new ArrayList<Dot>();
		ArrayList<Dot> tmp = null;
		ArrayList<Integer> friends = new ArrayList<Integer>();
		for(Dot d : list.get(n1.getId())){
			if(friends.contains(d.getId())) continue;
			if(d.getTime()>=n1.getTime()&&d.getTime()<=n2.getTime()){
				friends.add(d.getId());
				tmp = new ArrayList<Dot>();
				tmp.add(n1);
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
		subpath.put(title, result);
		return result;
	}

}
