package Path;

public class Builder1 extends Builder {
	public Solution bruteForce(Town[] towns) {
		return Finder.getFinder().bruteForce(towns, 0, 0, 0, "");
	}	
	public Solution approximate(Town[] towns) {
		return Finder.getFinder().approximate(towns);
	}	
	public Solution exact(Town[] towns) {
		return Finder.getFinder().exact(towns);
	}
}
