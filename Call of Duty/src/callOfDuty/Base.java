package callOfDuty;
import java.util.Random;
/**
 * This contains a 10x10 array of Targets, representing a "base", and 
 * some methods to manipulate it. Think of it as the map in this game. 
 */
public class Base {
	/*
	 * Keeps a reference to the location of every Target in the game. Every 
	   location in this array points to a Target, specifically, an instance of a 
	   subclass of Target.
	 */
	private Target[][] targets;
	//The total number of shots fired by the user.
	private int shotsCounts;
	//The number of targets destroyed.
	private int destroyedTargetCount;
	/**
	 * Creates an 10x10 empty Base (and fills the Targets array with Ground 
	   objects). You could create a private helper method to do this. Also initializes 
	   any game variables, such as how many shots have been fired.
	 */
	public Base() {
		this.targets = new Target[10][10];
		//iterate through base array
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				//fill each coordinate with new ground object
				placeTargetAt(new Ground(this),row,col,true);
			}
		}
		shotsCounts=0;
		destroyedTargetCount=0;
	}
	/**
	 *  Create and place all Targets randomly on the Base (initially filled 
		with Ground). Place larger Target before smaller ones, and place 
		buildings before tanks and oil drums, or you may end up with no legal 
		place to put a Target. You will want to use the Random class in the 
		java.util package, so look that up in the Java API.
	 */
	 public void placeAllTargetRandomly() {
		//instance of random class
		Random rand = new Random(); 
		//initialize all targets
		HeadQuarter hq=new HeadQuarter(this);
		Armory arm1=new Armory(this);
		Armory arm2=new Armory(this);
		Barrack barr1=new Barrack(this);
		Barrack barr2=new Barrack(this);
		Barrack barr3=new Barrack(this);
		SentryTower sent1=new SentryTower(this);
		SentryTower sent2=new SentryTower(this);
		SentryTower sent3=new SentryTower(this);
		SentryTower sent4=new SentryTower(this);
		Tank tk1=new Tank(this);
		Tank tk2=new Tank(this);
		Tank tk3=new Tank(this);
		Tank tk4=new Tank(this);
		OilDrum od1=new OilDrum(this);
		OilDrum od2=new OilDrum(this);
		OilDrum od3=new OilDrum(this);
		OilDrum od4=new OilDrum(this);
		//create array of all targets to iterate through
		Target [] targetList= {hq,arm1,arm2,barr1,barr2,barr3,sent1,sent2,sent3,sent4,
				tk1,tk2,tk3,tk4,od1,od2,od3,od4};
		//iterate through target array
		for (Target tar:targetList) {
			//generate first value and variable to store the random number and boolean
			int randRow=rand.nextInt(10);
			int randCol=rand.nextInt(10);
			boolean randHoz=rand.nextBoolean();
			//while it is not ok to place that target, keep randomly generating row, col, and hoz
			while(!okToPlaceTargetAt(tar,randRow,randCol,randHoz)) {
				randRow=rand.nextInt(10);
				randCol=rand.nextInt(10);
				randHoz=rand.nextBoolean();
			}
			//with correct metric place targets
			placeTargetAt(tar,randRow,randCol,randHoz);
		}
	 }
	 /**
	  * Based on the given row, column, and orientation, returns true if it is
	    okay to put the Target with its head at this location; false otherwise. 
		The buildings must not overlap another Target, or touch another 
		building (vertically, horizontally, or diagonally). And targets must 
		not "stick out" beyond the base. Does not actually change either the 
		Target or the Base - it just says if it is legal to do so.
	  * @param target
	  * @param row
	  * @param column
	  * @param horizontal
	  * @return
	  */
	 public boolean okToPlaceTargetAt(Target target, int row, int column, boolean horizontal) {
		 //if target is a tank or oil drum, they can touch buildings, just need to check if overlap
		 if (target.getTargetName().equals("tank") || target.getTargetName().equals("oilDrum")) {
			 if (isOccupied(row, column)) {
				 return false;
			 }else {
				 return true;
			 }
		 }
		 //if object is not horizontal, length added to row and width to column
		 if (!horizontal) {
			 //if exceed base return false
			 if (row+target.getLength()>10||column+target.getWidth()>10) {
				 return false;
			 }
			 //set i and j to -1 to inspect surrounding coordinates
			 for (int i=-1;i<=target.getLength();i++) {
				 /*
				  * i and j ends at target length, when added to row,column is one more than 
				  * where target ends, because target starts at 0
				  */
				 for (int j=-1;j<=target.getWidth();j++) {
					 if(row+i<10 && row+i>=0 && column+j>=0 && column+j<10) {
						 /*
						  * if any of the coordinates inspected are occupied
						  * (whether surrounding or target position)
						  */
						 if (isOccupied(row+i, column+j)) {
							 //return false
							 return false;
						 }
					 }
				 }
			 }
		//if object is horizontal, length added to column and width to row
		 }else {
			//if exceed base return false
			if (row+target.getWidth()>10||column+target.getLength()>10) {
				return false;
			}
			/* set i and j to -1 to inspect surrounding coordinates
			 * i and j ends at target length, when added to row,column is one more than 
			 * where target ends, because target starts at 0
			 */
			for (int i=-1;i<=target.getWidth();i++) {
				for (int j=-1;j<=target.getLength();j++) {
					 if(row+i<10&&row+i>=0&&column+j>=0&&column+j<10) {
						 /*
						  * if any of the coordinates inspected are occupied
						  * (whether surrounding or target position)
						  */
						if (isOccupied(row+i, column+j)) {
							//return false
							return false;
						}
					}
				}
			}
		 }
		 //return true if none are occupied
		 return true;
	 }
	 /**
	  * "Put" the Target in the Base. This involves giving values to the 
		coordinate and horizontal instance variables in the Target, and it also 
		involves putting a reference to the Target in each of 1 or more 
		locations in the targets array in the Base object. (Note: There will be 
		many identical references; you can't refer to a "part" of a Target, only 
		to the whole Target. For example, an Armory occupies a 2x3 area in 
		the base, and all of these 6 squares reference to the same instance of 
		an Armory)
	  * @param target
	  * @param row
	  * @param column
	  * @param horizontal
	  */
	 public void placeTargetAt(Target target, int row, int column, boolean 
			 horizontal) {
		//set coordinates for target
		int [] coord= {row,column};
		//set head of target
		target.setCoordinate(coord);
		//if object is horizontal, length added to row and width to column
		if (horizontal) {
			 //set hit array for target
			 int [][] hit=new int[target.getWidth()][target.getLength()];
			 for (int hitRow=0;hitRow<hit.length;hitRow++) {
				 for (int hitCol=0;hitCol<hit[hitRow].length;hitCol++) {
					 hit[hitRow][hitCol]=0;
				 }
			 }
			 target.setHit(hit);
			 //set i and j from 0 to target size, and add i,j to row,column to change object
			 for (int i=0;i<target.getWidth();i++) {
				 for (int j=0;j<target.getLength();j++) {
					 //set specific coordinates to target(i.e. tank, armory etc)
					 targets[row+i][column+j]=target; 
				 }
			 }
		 }else {
			//set hit array for target
			int [][] hit=new int[target.getLength()][target.getWidth()];
			//for each element in array, value is 0
			for (int hitRow=0;hitRow<hit.length;hitRow++) {
				for (int hitCol=0;hitCol<hit[hitRow].length;hitCol++) {
					hit[hitRow][hitCol]=0;
				}
			}
			//set array for target
			target.setHit(hit);
			//set i and j from 0 to target size, and add i,j to row,column to change object
			for (int i=0;i<target.getLength();i++) {
				for (int j=0;j<target.getWidth();j++) {
					//set specific coordinates to target(i.e. tank, armory etc)
					targets[row+i][column+j]=target;
				}
			}
		 }
	 }
	 /**
	  *  Returns true if the given location contains a Target(not a Ground), 
		 false if it does not.
	  * @param row
	  * @param column
	  * @return
	  */
	 public boolean isOccupied(int row, int column) {
		 //if specific coordinate is an instance of ground
		 if (targets[row][column] instanceof Ground) {
			 //return not occupied
			 return false;
		 }
		 //else return occupied
		 return true;
	 }
	 /**
	  * Attack the position specified by the row and the column.
	  * @param row
	  * @param column
	  */
	 public void shootAt(int row, int column) {
		 //target in targets coordinate get shot
		 targets[row][column].getShot(row, column);
	 }
	 /**
	  * Returns true if run out of ammunition or if all targets have been
		destroyed. Otherwise return false.
	  * @param weapon1
	  * @param weapon2
	  * @return true if game is over, false otherwise
	  */
	 public boolean isGameOver(Weapon weapon1, Weapon weapon2) {
		 //if all shots are fired or if player wins
		 if (weapon1.getShotLeft()+weapon2.getShotLeft() == 0 || win()) {
			 System.out.println("Game over!");
			 //game over
			 return true;
		 }
		 else {
			 //not game over
			 return false;
		 }
	 }
	 /**
	  * Returns true if all targets have been destroyed.
	  * @return
	  */
	 public Boolean win() {
		 if (getDestroyedTargetCount()==18) {
			 //if all target destroyed print array and say player wins
			 print();
			 System.out.println("You won!");
			 //only when all objects are destroyed, return true
			 return true;
		 }else {
			 //return false otherwise
			 return false;
		 }
		 
	 }
	 /**
	  * Prints the Base. To aid the user, row numbers should be displayed 
		along the left edge of the array, and column numbers should be 
		displayed along the top. Numbers should be (0 to 9), not 1 to 10.
	  */
	 public void print() {
		 //print first line labeling columns
		 System.out.print("  ");
		 for(int i=0;i<10;i++) {
			 System.out.print(Integer.toString(i)+" ");
		 }
		 //print targets array
		 for(int row=0;row<10;row++) {
			 //change line
			 System.out.println("");
			 //print row name
			 System.out.print(Integer.toString(row)+" ");
			 //for each item in column
			 for(int col=0;col<10;col++) {
				 //if the target is hit
				 if (targets[row][col].isHitAt(row, col)) {
					 //toString the target
					 System.out.print(targets[row][col].toString()+" ");
				 }else {
					 //if not hit print .
					 System.out.print(". ");
				 }
			 }
		 }
	 }
	 /**
	  * Returns the number of shots fired
	  * @return
	  */
	 public int getShotsCount() {
		 return shotsCounts;
	 }
	 /**
	  * Returns the targets array
	  * @return
	  */
	 public Target[][] getTargetsArray(){
		 return targets;
	 }
	 /**
	  * This method will be called from shootAt(int row, int column) from Weapon class.
	  */
	 public void incrementShotsCount() {
		 shotsCounts += 1;
	 }
	 /**
	  * Returns the count of destroyed targets
	  */
	 public int getDestroyedTargetCount() {
		 return destroyedTargetCount;
	 }
	 /**
	  * set the count of destroyed targets
	  * @param i
	  */
	 public void setDestroyedTargetCount(int i) {
		 destroyedTargetCount = i;
	 }

}
