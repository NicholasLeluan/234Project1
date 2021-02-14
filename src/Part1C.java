import java.util.Random;

public class Part1C {

	public static void main(String[] args) {
		// cost model is number of array access
		int N = Integer.parseInt(args[0]);
		
		//to get plot points for graph
		//uncomment to use!!
		getPlotPoints();
		
		
		
		int[] bestCase = bestCase(N);
		int[] worstCase = worstCase(N);
		int[] randomCase = randomCase(N);
		System.out.print("BEST: ");
		bringLargestUp(bestCase);
		System.out.print("WORST: ");
		bringLargestUp(worstCase);
		System.out.print("RANDOM: ");
		bringLargestUp(randomCase);
		
		
	}
	
	public static void bringLargestUp(int[] A) {
		int count = 0;
		int x = A[0];
		int p = 0;
		int f = 1;
		for (int i = 1; i < A.length; i++) {
			int y = A[i];
			count++;
			if (y < x) {
				//first swap
				A[i] = A[f];
				A[f] = y;

				//second swap
				int temp = A[p];
				A[p] = A[f];
				A[f] = temp;

				//increase  p & f
				p++;
				f++;
				//there were 4 total index accesses
				count += 4;
			}
			else if(y == x) {
				A[i] = A[f];
				A[f] = y;
				f++;
				count += 2;
			}
		}
		System.out.println(count);
		
		
		
	}
	/*
	 * sorted in ascending order
	 */
	public static int[] bestCase(int N) {
		int[] array = new int[N];
		for (int x = 0; x < N; x++) {
			array[x] = x;
		}
		return array;
	}
	
	/*
	 * sorted in descending order
	 */
	public static int[] worstCase(int N) {
		int[] array = new int[N];
		int y = 0;
		for(int x = N; x > 0; x--) {
			array[y] = x;
			y++;
		}
		
		
		return array;
	}
	/*
	 * int[] array of length N with random numbers 
	 * generated for the indices of array
	 */
	public static int[] randomCase(int N) {
		int[] array = new int[N];
		Random random = new Random();
		for (int x = 0; x < N; x++) {
			int num = random.nextInt();
			array[x] = num;
		}
		return array;
	}
	
	public static void getPlotPoints() {
		int[] plot = {10,20,30,50,75,100,150,200,250,300,350,400,500,1250};
		
		
		for (int run : plot) {
			
			System.out.println(String.format("N = %s", run));
			
			int[] bestCase = bestCase(run);
			System.out.print(String.format("Best case for %s: ",run));
			bringLargestUp(bestCase);
			
			//Worst Case Run:
			int[] worstCase = worstCase(run);
			System.out.print(String.format("Worst case for %s: ",run));
			bringLargestUp(worstCase);
			
			//Random Case Run:
			int[] randomCase = randomCase(run);
			System.out.print(String.format("Random case for %s: ",run));
			bringLargestUp(randomCase);
			System.out.println();
			
		}
	}
	
	

}
