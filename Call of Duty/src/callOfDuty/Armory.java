package callOfDuty;

public class Armory extends Target {
	static final String name="armory";

	public Armory(Base base) {
		super(3, 2, base);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void explode() {
		if (getHorizontal()) {
			// set i,j to explode radius so we can add i to coordinate and shoot all surrounding target
			for (int i=-2;i<=4;i++) {
				for (int j=-2;j<=3;j++) {
					//set base row and column to be shot, overwrite with each loop
					int baseRow=getCoordinate()[0]+i;
					int baseCol=getCoordinate()[1]+j;
					//if base row and column within base area
					if (baseRow<10 && baseRow>=0 && baseCol<10 && baseCol>=0) {
						//act as if the coordinate is shot
						getBase().shootAt(getCoordinate()[0]+i,getCoordinate()[1]+j);
					}		
				}
			}
		}else {
			// set i,j to explode radius so we can add i to coordinate and shoot all surrounding target
			for (int i=-2;i<=3;i++) {
				for (int j=-2;j<=4;j++) {
					//set base row and column to be shot, overwrite with each loop
					int baseRow=getCoordinate()[0]+i;
					int baseCol=getCoordinate()[1]+j;
					//if base row and column within base area
					if (baseRow<10 && baseRow>=0 && baseCol<10 && baseCol>=0) {
						//act as if the coordinate is shot
						getBase().shootAt(getCoordinate()[0]+i,getCoordinate()[1]+j);
					}		
				}
			}
		}
		
	}

	@Override
	public String getTargetName() {
		// TODO Auto-generated method stub
		return name;
	}

}
