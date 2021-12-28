package callOfDuty;

public class HeadQuarter extends Target {
	static final String name="headQuarter";
	public HeadQuarter(Base base) {
		super(6, 1, base);
		// TODO Auto-generated constructor stub
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

}
