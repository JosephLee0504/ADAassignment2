package Path;

public abstract class Builder {
	public abstract Solution bruteForce(Town[] towns);
	public abstract Solution approximate(Town[] towns);
	public abstract Solution exact(Town[] towns);
}
