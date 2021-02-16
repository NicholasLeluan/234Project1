/*
 * Nicholas Leluan
 * CSC 345 Spring 2021
 */

public class Part1B {

	public static void main(String[] args) {
		
		Integer N = Integer.parseInt(args[0]);
		
		//for getting plots points to graph;
		// to use, uncomment!!
		//getPlots();
		
		doSomething(N);

	}
	
	public static void getPlots() {
		int[] plot = {1,9,18,27,36,81,162,324,648,729};
		for (int num : plot) {
			System.out.print(String.format("Part1B for %s: ", num));
			doSomething(num);
		}
	}
	
	public static void doSomething(int N) {
		int count = 0;
		int sum = 0;
		while (N > 0) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= j; k++) {
					sum += (j+k);
					count++;
				}
			}
			N/=3;
		}
		System.out.println(count);
		
	}

}
