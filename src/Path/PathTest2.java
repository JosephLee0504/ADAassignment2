package Path;

import java.util.Scanner;

public class PathTest2 {
	public static void main(String[] args) {
		System.out.println("The number of town:");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int name = 1;
		int[] c = new int[2000];
		int[] v = new int[2000];
		Town[] towns = new Town[n];
		for (int i = 0; i < n; i++) {
			c[i] = (int)(10+Math.random()*(10000-10+1));
			v[i] = (int)(10+Math.random()*(c[i]-10+1));
			towns[i] = new Town(name,c[i],v[i]);
			name++;
		}
		Builder builder = new Builder1();	
		long startTime1 = System.currentTimeMillis();
		Solution bruteForceSolution = builder.bruteForce(towns);
		long endTime1 = System.currentTimeMillis();
		System.out.println("----------- Brute Force - Time: " + (endTime1 - startTime1) + "ms -----------\n" + bruteForceSolution.path);
		long startTime2 = System.currentTimeMillis();
		Solution approximateSolution = builder.approximate(towns);
		long endTime2 = System.currentTimeMillis();
		System.out.println("----------- Approximate - Time: " + (endTime2 - startTime2) + "ms -----------\n" + approximateSolution.path);
		long startTime3 = System.currentTimeMillis();
		Solution exactSolution = builder.exact(towns);
		long endTime3 = System.currentTimeMillis();
		System.out.println("----------- Exact - Time: " + (endTime3 - startTime3) + "ms -----------\n" + exactSolution.path);		
	}
}
