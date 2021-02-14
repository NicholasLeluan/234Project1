import java.util.Random;

public class Part1D {

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		
		
		//to get plot ponts for graph
		//uncomment to use!!
		//getPlotPoints();
		
		System.out.println("Best Case: ");
		int[] ascending = bestCase(N);
		sortAscending(ascending);
		System.out.println("Worst Case: ");
		int[] descending = worstCase(N);
		sortAscending(descending);
		System.out.println("Random Case: ");
		int[] random = randomCase(N);
		sortAscending(random);
		
		
		
		

	}
	
	
	public static void sortAscending(int[] A) {
		int count = 0;
		for (int i = 1; i < A.length; i++) {
			int j = i;
			int k = j-1;
			int itters = 0;
			count+=2;
			while (k >= 0 && A[j] < A[k]) {
				itters += 1;
				int temp = A[j];
				A[j] = A[k];
				A[k] = temp;
				count += 2;
				j--;
				k--;
			}
			count += (itters*2);
		}
		System.out.println("count: " + count);
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
			sortAscending(bestCase);
			
			//Worst Case Run:
			int[] worstCase = worstCase(run);
			System.out.print(String.format("Worst case for %s: ",run));
			sortAscending(worstCase);
			
			//Random Case Run:
			int[] randomCase = randomCase(run);
			System.out.print(String.format("Random case for %s: ",run));
			sortAscending(randomCase);
			System.out.println();
			
		}
	}
	
}
