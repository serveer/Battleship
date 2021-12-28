package callOfDuty;

public abstract class Weapon {
	//The number of shots left. Initially, it’s 20 for RocketLauncher and 3 for Missile
	private int shotleft;
	//constructor
	public Weapon(int shotCount) {
		this.shotleft = shotCount;
	}
	
	public int getShotLeft() {
		 return shotleft;
	 }
	public abstract String getWeaponType();
	public abstract void shootAt(int row, int column, Base base);
	public void decrementShotLeft() {
		shotleft -= 1;
	}

	
}
