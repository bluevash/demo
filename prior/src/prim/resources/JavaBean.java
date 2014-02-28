package prim.resources;

import java.util.ArrayList;

import prim.resources.Worker;

public class JavaBean {
	
	public Database dataBaseParams = new Database();
	public ArrayList<String> navBarList 	= new ArrayList<String>();
	public ArrayList<Worker> workerList = new ArrayList<Worker>();
	
	
	public Database getDataBaseConfiguration(){
		return dataBaseParams;
	}
	
	public ArrayList<String> getAllNavDef(){
		return navBarList;
	}
	
	//Add Worker
	public void addWorker( Worker w ){
			workerList.add(w);		
	}
	//Add Worker by index
	public void addWorkerByIndex( int k, Worker w ){
				workerList.add( k, w );		
	}
	//Clean list of workers
	public void resetListOfWorkers(){
		workerList = new ArrayList<Worker>();
	}
	//Get list of Workers
	public ArrayList<Worker> getAllWorkers(){
		return workerList;
	}
}
