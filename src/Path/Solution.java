package Path;

public class Solution {
	String path;
	int profit;	
	public Solution(String path, int profit) {
		this.path = path;
		this.profit = profit;
	}
	public String toString() {
		return "----------- Exact - Profit: " + this.profit + " -----------\n" + path;
	}
}
