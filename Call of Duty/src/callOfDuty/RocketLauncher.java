package callOfDuty;

public class RocketLauncher extends Weapon {
	   public RocketLauncher() {
		   super(20);
	   }
	   public String getWeaponType() {
		   return "rocketLauncher";
	   }
	@Override
	public void shootAt(int row, int column, Base base) {
		base.shootAt(row, column);
		decrementShotLeft();
		base.incrementShotsCount();
	}
}
