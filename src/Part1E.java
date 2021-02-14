import java.util.Random;

public class Part1E {

	public static void main(String[] args) {
		
		int N = Integer.parseInt(args[0]);
		
		//to get plot points: UNCOMMENT TO USE
		//getPlotPoints();
		
		//Best Case Run:
		int[] bestCase = bestCase(N);
		System.out.print(String.format("Best case for %s: ",args[0]));
		buildMaxHeap(bestCase);
		
		//Worst Case Run:
		int[] worstCase = worstCase(N);
		System.out.print(String.format("Worst case for %s: ",args[0]));
		buildMaxHeap(worstCase);
		
		//Random Case Run:
		int[] randomCase = randomCase(N);
		System.out.print(String.format("Random case for %s: ",args[0]));
		buildMaxHeap(randomCase);
	}
	/*
	 * debugging purposes to see array if needed
	 */
	public static void printArray(int[] A) {
		String string = "[";
		for(int k : A) {
			string+= k;
			string+= ",";
		}
		string+="]";
		System.out.println(string);
	}
	
	/*
	 * this is the "foo()" function from the pseudocode
	 * takes in an array of size in as A; 
	 * builds a Max Heap using doMore() function
	 */
	public static void buildMaxHeap(int[] A) {
		int count = 0;
		int len = A.length;
		for (int i = (len/2); i >= 0;i--) {
			count += doMore(A,i);
		}
		System.out.println(count);
	}
	/*
	 * Takes in a list and sorts it into a Max Heap 
	 */
	public static int doMore(int[] A, int i) {
		int count = 0;
		int L = (2*i) + 1;
		int r = (2*i) + 2;
		if (L >= A.length && r >= A.length) {
			return count;
		}
		if (r >= A.length) {
			count += 2;
			if(A[i] < A[L]) {
			int temp = A[i];
				A[i] = A[L];
				A[L] = temp;
				count += 4;
			}
			return count;
		}
		//3 array accesses; left, right and A[i]
		int right = A[r];
		int left = A[L];
		count += 3;
		if(right > left && right > A[i]) {
			int temp = A[i];
			A[i] = A[r];
			A[r] = temp;
			count += 4;
			count += doMore(A,r);
			//return count;
		}
		//1 array access; left was already saved does not count
		//as an access; A[i] is counted 
		count += 1;
		if (left > A[i]) {
			int temp = A[i];
			A[i] = A[L];
			A[L] = temp;
			count += 4;
			count += doMore(A,L);
		}
		return count;
	}
	
	/*
	 * Ascending assorted list with size N 
	 */
	public static int[] worstCase(int N) {
		int[] array = new int[N];
		for (int x = 0; x < N; x++) {
			array[x] = x;
		}
		return array;
	}
	
	/*
	 * sorted in descending order of size N
	 */
	public static int[] bestCase(int N) {
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
		int[] plot = {10,20,30,50,75,100,150,200,250,300,350,400,500};
		
		
		for (int run : plot) {
			
			System.out.println(String.format("N = %s", run));
			
			int[] bestCase = bestCase(run);
			System.out.print(String.format("Best case for %s: ",run));
			buildMaxHeap(bestCase);
			
			//Worst Case Run:
			int[] worstCase = worstCase(run);
			System.out.print(String.format("Worst case for %s: ",run));
			buildMaxHeap(worstCase);
			
			//Random Case Run:
			int[] randomCase = randomCase(run);
			System.out.print(String.format("Random case for %s: ",run));
			buildMaxHeap(randomCase);
			System.out.println();
			
		}
	}
	

}
