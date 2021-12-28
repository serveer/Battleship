package callOfDuty;

public class Barrack extends Target {
	static final String name="barrack";
	public Barrack(Base base) {
		super(3, 1, base);
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
