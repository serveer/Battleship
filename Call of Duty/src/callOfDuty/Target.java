package callOfDuty;
/**
 * This abstract class describes the characteristics common to all targets. 
 *
 */
public abstract class Target {
	//An instance of Base that the target is placed in. 
	private Base base;
	//Indicates whether the Target is horizontal or not 
	private boolean horizontal;
	//The width of the Target 
	private int width;
	//The length of the Target 
	private int length;
	/*
	 * An array of length 2 that specifies the coordinate of the head of a 
	 * target. Head means the upper left part of a Target. 
	 */
	private int[] coordinate;
	/*
	 * An array of the same size as the target, indicating the number of 
	 * times a part of the target has been hit. 
	 */
	private int[][] hit;
	//initialize count for explosion to avoid double explode
	private int explosionCount=0;
	/**
	 * 
	 * @param length
	 * @param width
	 * @param base
	 */
	public Target(int length, int width, Base base) {
		this.length=length;
		this.width=width;
		this.base=base;
	}
	/**
	 * coordinate getter
	 * @return the coordinate array 
	 */
	public int[] getCoordinate() {
		return this.coordinate;
	}
	/**
	 * horizontal getter
	 * @return whether the target is horizontal or not 
	 */
	public boolean getHorizontal() {
		return this.horizontal;
	}
	/**
	 * hit getter
	 * @return the hit array 
	 */
	public int[][] getHit() {
		return this.hit;
	}
	/**
	 * base getter
	 * @return the base
	 */
	public Base getBase() {
		return this.base;
	}
	/**
	 * length getter
	 * @return the length of this target 
	 */
	public int getLength() {
		return this.length;
	}
	/**
	 * width getter
	 * @return the width of this target 
	 */
	public int getWidth () {
		return this.width;
	}
	/**
	 * set coordinates
	 * @param new coordinate to be set to
	 */
	public void setCoordinate(int[] coordinate) {
		this.coordinate=coordinate;
	}
	/**
	 * set horizontal
	 * @param new horizontal to be set to
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal=horizontal;
	}
	/**
	 * set hit
	 * @param new hit to be set to
	 */
	public void setHit(int[][] hit) {
		this.hit=hit;
	}
	/**
	 * Defines the behavior when a target is destroyed.
	 */
	public abstract void explode();
	/**
	 * Returns the type of Target as a String. Every specific type of Target 
	 * (e.g. Armory, Tank, etc.) has to override and implement this method 
	 * and return the corresponding Target type.
	 * @return
	 */
	public abstract String getTargetName();
	/**
	 * If a part of the Target occupies the given row and column 
	 * and it is not destroyed, mark that part of the Target as “hit” 
	 * @param row of base
	 * @param column of base
	 */
	public void getShot (int row, int column) {
		//determine distance from head to input coordinates
		int horDist=row-coordinate[0];
		int verDist=column-coordinate[1];
		//if target not destroyed add 1 to hit array
		if(!isDestroyed()) {
			hit[horDist][verDist]+=1;
			//if target is destroyed after hit array, and target hasn't exploded
			if(isDestroyed()&&explosionCount==0) {
				//add one to destroyed target count
				base.setDestroyedTargetCount(base.getDestroyedTargetCount()+1);
				//print target destroyed
				System.out.println("You destroyed a "+getTargetName());
				//count explosion
				explosionCount+=1;
				//explode that item
				explode();
			}
		}	
	}
	/**
	 * Returns true if every part of the Target has been hit, false otherwise. 
	 * For Tank, every part of it should be hit twice. So override this method 
	 * in the Tank Class. 
	 * @return
	 */
	public boolean isDestroyed() {
		//iterate through hit array
		for (int row = 0; row < hit.length; row++) {
			 for (int col = 0; col < hit[row].length; col++) {
				 //if any one is not hit target is not destroyed
			     if (hit[row][col]==0) {
			    	 return false;
			     }
			 }
		}
		//if all are 1 destroyed
		return true;
	}
	
	/**
	 * Returns true if the target has been hit at the given coordinate. This 
	   method is used to print the Base. 
	 * @param row to hit
	 * @param column to hit
	 * @return
	 */
	public boolean isHitAt(int row, int column) {
		//determine distance from head to input coordinates
		int horDist=row-coordinate[0];
		int verDist=column-coordinate[1];
		//check the distance on hit array
		if (hit[horDist][verDist]==0) {
			//if hit array shows coordinate isn't hit return false
			return false;
		}
		return true;
	}
	/**
	 * Returns a single-character String to use in the Base�s print method. 
	   This method should return �X� if the Target has been destroyed 
	   and �O� (capital letter O) if it has not been destroyed. But for an
	   undestroyed Tank, it returns �T�.
	 */
	public String toString() {
		//if object is destroyed return X
		if (isDestroyed()) {
			return "X";
		}else {
			//else return O
			return "O";
		}
	}
}
