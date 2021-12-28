package callOfDuty;

public class Ground extends Target {
	static final String name="ground";
	public Ground(Base base) {
		super(1, 1, base);
	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTargetName() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public String toString() {
		return "-";
	}
	@Override
	public boolean isDestroyed() {
		//indicate nothing is destroyed
		return false;
	}
}
