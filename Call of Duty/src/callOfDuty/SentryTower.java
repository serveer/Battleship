package callOfDuty;

public class SentryTower extends Target {
	static final String name="sentryTower";
	public SentryTower(Base base) {
		super(1, 1, base);
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
