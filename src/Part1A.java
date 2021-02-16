/*
 * Nicholas Leluan
 * CSC 345 Spring 2021
 */

public class Part1A {

	public static void main(String[] args) {
		
		Integer N = Integer.parseInt(args[0]);
		//this is for getting the polots points for graph
		//to use, uncomment!!
		//getPlots();
		
		//Run N one time
		doSomething(N);

	}
	public static void doSomething(int N) {
		int count = 0;
		for (int i = 1; i <= N; i++) {
			int j = i;
			while (j >= 1) {
				int k = 1;
				while (k <= N) {
					k*=2;
					count++;
				}
				j-=1;
			}
		}
		System.out.println(count);
	}
	
	public static void getPlots() {
		int[] plots = {1,2,4,8,16,32,64,128,256};
		
		for (int num : plots) {
			System.out.print(String.format("Count for N = %s: ", num));
			doSomething(num);
		}
	}
}
