package Path;

public class Finder {
    private static Finder finder = new Finder();	
	private Finder() {}	
	public static Finder getFinder() {
		return finder;
	}	
	public Solution bruteForce(Town[] towns, int start, int state, int current, String path) {
		/**
		 * start: the current town
		 * state: 	1 for the trunk is already picking up loads at previous town
		 * 			0 for empty trunk at current town
		 * current: current profit of the trunk
		 * path: current path of the trunk
		 */
		if (start >= towns.length) {
			return new Solution(path, current);
		}
		if (state == 1) { // drop off loads or pass by
			Solution solution1 = bruteForce(towns, start + 1, state, current, path.equals("") ? towns[start].toString() : path + " -> " + towns[start]);
			Solution solution2 = bruteForce(towns, start + 1, 0, current + towns[start].v, path.equals("") ? "+(" + towns[start].toString() + ")" : path + " -> +(" + towns[start] + ")");
			return solution1.profit > solution2.profit ? solution1 : solution2;
		} else { // pick up loads or pass by
			Solution solution1 = bruteForce(towns, start + 1, state, current, path.equals("") ? towns[start].toString() : path + " -> " + towns[start]);
			Solution solution2 = bruteForce(towns, start + 1, 1, current - towns[start].c, path.equals("") ? "-(" + towns[start].toString() + ")" : path + " -> -(" + towns[start] + ")");
			return solution1.profit > solution2.profit ? solution1 : solution2;
		}
	}	
	public Solution approximate(Town[] towns) {
		/**
		 * different from Brute-force approach, it is a approximate approach which is quicker but can't get the right answer every time
		 * method of approximate:
		 * 1. Find the minimum cost town in all towns and pick up a load at this town.
		 * 2. As soon as the truck arrives at the town that the profit is greater than the cost before(not necessarily the biggest profit town), drop off the load.
		 * 3. Then find the minimum cost town among the remaining towns and do the same thing as in step 1.
		 * 4. do the same thing as in step 2.
		 * and so on......
		 */
		int profit = 0;
		String path = null;
		for (int i = 0; i < towns.length; i++) {
			// find and travel to a town with minimum cost
			Town minTown = findMin(towns, i);
			while (minTown != towns[i]) {
				if (path == null) {
					path = towns[i].toString();
				} else {
					path += " -> " + towns[i].toString();
				}
				i++;
			}
			// pick up the load at the town
			if (path == null) {
				path = "-(" + minTown + ")";
			} else {
				path += "  -> -(" + minTown + ")";
			}
			profit -= minTown.c;
			// keep traveling until find a town to drop off the load
			for (int j = i + 1; j < towns.length; j++) {
				if (towns[j].v > minTown.c) {
					i = j;
					path += " -> +(" + towns[j] + ")";
					profit += towns[j].v;
					break;
				} else {
					path += " -> " + towns[i].toString();
				}
			}
		}
		return new Solution(path, profit);
	}	
	public Town findMin(Town[] towns, int start) {
		Town minTown = towns[start];
		for (int j = start + 1; j < towns.length; j++) {
			if (minTown.c > towns[j].c) {
				minTown = towns[j];
			}
		}
		return minTown;
	}	
	public Solution exact(Town[] towns) {
		/**
		 * use dynamic programming:
		 * 3xn matrix to store the dynamic mid result:
		 * matrix[0][i]: the maximum profit if the state of ith town is carrying the load (pick up at ith town or at before town without droping off yet;
		 * matrix[1][i]: the maximum profit if the state of ith town is droping off the load (the trunk pick up loads at previous town)
		 * matrix[2][i]: the maximum profit if the town do nothing at ith town.
		 */
		int[][] matrix = new int[3][towns.length];
		matrix[0][0] = -towns[0].c;
		matrix[1][0] = 0;
		matrix[2][0] = 0;
		// Fill in the matrix
		for (int i = 1; i < towns.length; i++) {
			matrix[0][i] = Math.max(Math.max(matrix[0][i - 1], matrix[1][i - 1] - towns[i].c), matrix[2][i - 1] - towns[i].c);
			matrix[1][i] = matrix[0][i - 1] + towns[i].v;
			matrix[2][i] = Math.max(Math.max(matrix[0][i - 1], matrix[1][i - 1]), matrix[2][i - 1]);
		}
		int profit = Math.max(Math.max(matrix[0][towns.length - 1], matrix[1][towns.length - 1]), matrix[2][towns.length - 1]);
		// Retrieve the path
		String path = "";
		int tmp = profit;
		for (int col = towns.length - 1; col >= 0; col--) {
			if (tmp == matrix[0][col]) {
				if (col > 0) {
					if (matrix[0][col] == matrix[0][col - 1]) {
						path = towns[col] + " -> " + path;
						continue;
					}
				}
				path = "-(" + towns[col] + ") -> " + path;
				tmp = tmp + towns[col].c;
			} else if (tmp == matrix[1][col]) {
				path = "+(" + towns[col] + ") -> " + path;
				tmp = tmp - towns[col].v;
			} else {
				path = towns[col] + " -> " + path;
			}
		}
		return new Solution(path, profit);
	}
}
