package com.kuizhiqing.dtn.model.universal;

import java.util.ArrayList;

/**
 * 
 * @author kuizhiqing
 *
 */
public class World {
	
	private ArrayList<Dot> dots;
	private ArrayList<Path> paths;
	private ArrayList<Route> routes;
	
	public World() {
		super();
		this.dots = new ArrayList<Dot>();
		this.paths = new ArrayList<Path>();
		this.routes = new ArrayList<Route>();
	}
	
	public ArrayList<Dot> getDots() {
		return dots;
	}
	public void setDots(ArrayList<Dot> dots) {
		this.dots = dots;
	}
	public ArrayList<Path> getPaths() {
		return paths;
	}
	public void setPaths(ArrayList<Path> paths) {
		this.paths = paths;
	}
	public ArrayList<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(ArrayList<Route> routes) {
		this.routes = routes;
	}

}
