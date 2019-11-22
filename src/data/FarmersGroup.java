package data;

import java.util.*;

import data.Farmer.Direction;

public class FarmersGroup {

	private static Queue<Farmer> farmersQueue;
	private static int farmersCrossed;//Neon
	private static ArrayList<Farmer> outputFarmersList;
	
	static {
		farmersQueue = new LinkedList<Farmer>();
		farmersCrossed=0;
		outputFarmersList = new ArrayList<Farmer>();
	}
	
	public static ArrayList<Farmer> getOutputFarmersList() {
		return outputFarmersList;
	}
	
	public static void setOutputFarmersList(ArrayList<Farmer> farmersList) {
			outputFarmersList=farmersList;
	}
	
	public static void incrementFarmersCrossed() {
		
		farmersCrossed++;
	}
	
	public static int getFarmersCrossed() {
		return farmersCrossed;
	}
	
	public static void outputWaitingFarmers(String crossingFarmerID) {
		
		for(int i=0;i<outputFarmersList.size();i++) {
			Farmer outputFarmer = outputFarmersList.get(i);
			if(!outputFarmer.getId().equals(crossingFarmerID)) {
				System.out.println(outputFarmer.getId()+": Waiting for bridge. Going towards "+outputFarmer.getHeadingDirection()+".");
			}
		}
		
		
		
	}
	
	//initializes farmers from input parameters
	public static void initializeFarmers(int n, int s) {
		Queue<Farmer> northFarmersQueue = new LinkedList<>();
		Queue<Farmer> southFarmersQueue = new LinkedList<>();
		
		//n amount heading towards south. set their id
		for(int i=1;i<=n;i++) {
			Farmer farmer = new Farmer();
			farmer.setIndex(i);
			farmer.setHeadingDirection(Direction.South);
			farmer.setId();
			northFarmersQueue.add(farmer);
		}
		
		//s amount heading north
		for(int i=1;i<=s;i++) {
			Farmer farmer = new Farmer();
			farmer.setIndex(i);
			farmer.setHeadingDirection(Direction.North);
			
			//set farmer id based off their index and direction
			farmer.setId();
			southFarmersQueue.add(farmer);
		}
		
		//dequeue them equally into one single queue, 
		//thus providing fair equal crossing from each side to another
		//i.e. N1, S1, N2, S2, N3, S3...
		for(int i=0;i<n;i++) {
			Farmer northFarmer = northFarmersQueue.poll();
		
			farmersQueue.add(northFarmer);
			
			Farmer southFarmer = southFarmersQueue.poll();
	
			farmersQueue.add(southFarmer);
			
		}
		
		
		
		
	}


	public static Queue<Farmer> getFarmersQueue() {
		return farmersQueue;
	}
	

}
