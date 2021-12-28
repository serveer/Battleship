package callOfDuty;

public class Tank extends Target {
	static final String name="tank";
	public Tank(Base base) {
		super(1, 1, base);
	}
	

	@Override
	public void explode() {
		// set i,j to explode radius so we can add i to coordinate and shoot all surrounding target
		for (int i=-2;i<=2;i++) {
			for (int j=-2;j<=2;j++) {
				//set base row and column to be shot, overwrite with each loop
				int baseRow=getCoordinate()[0]+i;
				int baseCol=getCoordinate()[1]+j;
				//if base row and column within base area
				if (baseRow<10 && baseRow>=0 && baseCol<10 && baseCol>=0) {
					//act as if the coordinate is shot
					getBase().shootAt(baseRow,baseCol);
				}		
			}
		}
	}

	@Override
	public String getTargetName() {
		return name;
	}
	@Override
	/**
	 * tank has to be hit twice
	 */
	public boolean isDestroyed() {
		//get hit array, tank size only 1,1 so if hit[0][0] not 2 it is not destroyed
		if (getHit()[0][0]<2) {
			return false;
		}else {
			//return true if not destroyed
			return true;
		}
	}
	
	/**
	 * return T if tank is only hit once
	 * override
	 */
	public String toString() {
		//return T if not destroyed
		if (!isDestroyed()) {
			return "T";
		}
		//return X if is destroyed
		else {
			return "X";
		}
	}
}
