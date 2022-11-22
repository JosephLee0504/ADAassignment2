package Path;

public class PathTest {
	private Town[] towns;
	private Builder builder;	
	public PathTest(Town[] towns) {
		this.towns = towns;
		builder = new Builder1();
		printSolution();
	}
	public void printSolution() {
		Solution bruteForceSolution = builder.bruteForce(towns);
		System.out.println("----------- Brute Force - Profit: " + bruteForceSolution.profit + " -----------\n" + bruteForceSolution.path);
		Solution approximateSolution = builder.approximate(towns);
		System.out.println("----------- Approximate - Profit: " + approximateSolution.profit + " -----------\n" + approximateSolution.path);
		Solution exactSolution = builder.exact(towns);
		System.out.println("----------- Exact - Profit: " + exactSolution.profit + " -----------\n" + exactSolution.path);
	}
	public static void main(String[] args) {
		Town[] towns = new Town[8];
		towns[0] = new Town(1, 3, 3);
		towns[1] = new Town(2, 8, 4);
		towns[2] = new Town(3, 9, 5);
		towns[3] = new Town(4, 8, 4);
		towns[4] = new Town(5, 9, 9);
		towns[5] = new Town(6, 10, 10);
		towns[6] = new Town(7, 23, 22);
		towns[7] = new Town(8, 13, 10);
		new PathTest(towns);
	}
}
