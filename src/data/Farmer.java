package data;


import java.util.concurrent.*;

public class Farmer implements Runnable {

	private int steps;

	private String id;
	private Direction headingDirection;
	private int index;
	private static Semaphore semaphore = new Semaphore(1);
	
	public enum Direction {
		North,
		South;
	}
	
	public Farmer() {
		steps = 15;
	}
	
	
	
	public void run() {
		
		//farmer tries to acquire the semaphore
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		//farmer has acquired the semaphore
			
		//critical section
		crossTheBridge();
		
		//reset farmer details
		resetFarmerDetails();
		
		//release semaphore for another farmer
		semaphore.release();
		
		
	}
	
	public void crossTheBridge() {
		
		//outputs waiting farmers info
		FarmersGroup.outputWaitingFarmers(id);
		
		//while the bridge hasn't been totally crossed
		while(steps>0) {
			
			//sleep for 1 unit of time
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//decrement steps
			steps--;
			
			//output steps crossed
			if(steps==10) {
				System.out.println(id+": Crossing bridge Step 5. Heading "+headingDirection+".");
			}
			
			if(steps==5) {
				System.out.println(id+": Crossing bridge Step 10. Heading "+headingDirection+".");
			}
			
			if(steps==0) {
				System.out.println(id+": Crossed the bridge. At "+headingDirection+" Island.");
			}
		}
	}
	
	public void resetFarmerDetails() {
		
		//time to change direction
		switch(headingDirection) {
			case North:
				headingDirection = Direction.South;
				break;
			
			case South:
				headingDirection = Direction.North;
				break;
				
			default:
				System.out.println("Error: Farmer has no heading direction setted.");
				break;
		}
		
		//reset steps needed to cross
		steps=15;

		//increment amount of farmer crossed, and output it.
		FarmersGroup.incrementFarmersCrossed();
		System.out.println("NEON = "+FarmersGroup.getFarmersCrossed());
		
		
		
	}
	
	public String getId() {
		return id;
	}

	public void setId() {
		String origin="";
		
		//takes the farmer heading direction
		switch(headingDirection) {
		
		case North:
			origin="S";
			break;
		
		case South:
			origin="N";
			break;
		
		default:
			System.out.println("Error: No heading direction setted for id.");
			break;
			
		}
		
		//sets their id based off their heading direction and index
		id= origin+"_Farmer"+index;
		
	}

	public Direction getHeadingDirection() {
		return headingDirection;
	}

	public void setHeadingDirection(Direction direction) {
		headingDirection = direction;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int in) {
		index = in;
	}
	
}
