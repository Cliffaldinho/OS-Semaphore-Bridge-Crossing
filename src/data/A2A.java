package data;
import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class A2A {

	public static void main(String[] args) throws InterruptedException {
		
		//puts all the farmers into a queue
		FarmersGroup.initializeFarmers(2, 2);
		
		//gets the queue and it's size
		Queue<Farmer> farmersQueue = FarmersGroup.getFarmersQueue();
		int totalFarmers = FarmersGroup.getFarmersQueue().size();
		
		//initializes a new arraylist of threads to iterate through to execute
		ArrayList<Thread> threadsList;
		threadsList = new ArrayList<>();
		
		//put each farmer from the queue into the arraylist
		while(!farmersQueue.isEmpty()) {
			Farmer farmer = farmersQueue.poll();
			Thread thread = new Thread(farmer);
			threadsList.add(thread);
			
			//add them to another list also for output purposes
			FarmersGroup.getOutputFarmersList().add(farmer);
		}
		
		//loops forever
		while(true) {
		
		//executor service to run threads
		ExecutorService executor = Executors.newFixedThreadPool(totalFarmers);
		
		//loops through the whole arraylist of threads
		for(int i=0;i<threadsList.size();i++) {
			
			//and executes each one of them, so as to get all threads concurrently running
			executor.execute(threadsList.get(i));
		}
		
		//shut down executor
		executor.shutdown();
		
		//waits for all threads to finish, then proceed
		executor.awaitTermination(1, TimeUnit.HOURS);
		
		//loop again and again.
		}
		
	}

}
