package callOfDuty;

public class Missile extends Weapon {

	public Missile() {
		   super(3);
	   }
	public String getWeaponType() {
		   return "missile";
	}
	@Override
	public void shootAt(int row, int column, Base base) {
		// set i,j to missile radius so we can add i to coordinate and shoot all surrounding target
		for (int i=-1;i<=1;i++) {
			for (int j=-1;j<=1;j++) {
				//set base row and column to be shot, overwrite with each loop
				int baseRow=row+i;
				int baseCol=column+j;
				//if base row and column within base area
				if (baseRow<10 && baseRow>=0 && baseCol<10 && baseCol>=0) {
					//act as if the coordinate is shot
					base.shootAt(baseRow,baseCol);
				}		
			}
		}
		decrementShotLeft();
		base.incrementShotsCount();
	}
}
