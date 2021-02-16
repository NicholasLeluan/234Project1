/*
 * Nicholas Leluan
 * CSC 345 Spring 2021
 */

@SuppressWarnings("unchecked")
public class Deque<Item> {
    private Item[] deque;
    private int front;
    private int back;
    private int size;
    private int arrayAccesses;

    /***
     *constructor: create an empty Deque with initial
     *capacity of 10
     */
    public Deque() {
		this(10);
    }

    /***
     *constructor: create an empty Deque with initial
     *capacity of n
     */
    public Deque(int n) {
		this.deque = (Item[]) new Object[n];
		front = 0;
		back = 0;
		size = 0;
		arrayAccesses = 0;
    }
    
    /***
     *add an item to the front of the Deque
     *double the array capacity if Deque is full
     */
    public void addToFront(Item item) {
    	//no more space in array; need to expand
    	if (this.size == this.getArray().length) {
    		Item[] newA = extendArray(item,"front");
    		this.deque = newA;
    	}else if(this.size() == 0) {
    		this.arrayAccesses++;
    		this.deque[0] = item;
    	}
    	else if (front == 0) {
    		this.arrayAccesses++;
    		this.deque[this.deque.length-1] = item;
    		front = this.deque.length-1;
    		
    	}
    	else if(this.deque[front-1] == null) {
    		this.arrayAccesses++;
    		this.deque[front-1] = item;
    		front--;
    	}
    	this.size++;
    	
    }

    /***
     *add an item to the back of the Deque;
     *double the array capacity if the Deque is full
     ***/
    public void addToBack(Item item) {
    	if (this.size() == this.deque.length) {
    		Item[] newA = extendArray(item,"back");
    		this.deque = newA;
    	}
    	else if(this.isEmpty()) {
    		this.deque[back] = item;
    		this.arrayAccesses++;
    		back++;
    	}
    	else if (back > front) {
    		if (back == this.deque.length - 1) {
    			this.deque[0] = item;
    			this.back = 0;
    			this.arrayAccesses++;
    		}else {
    			this.deque[back+1] = item;
    			this.arrayAccesses++;
    			this.back++;
    		}
    	}else  {//back < front
    		this.deque[back+1] = item;
    		this.arrayAccesses++;
    		this.back++;
    	}
    	size++;
    }

    /***
     *remove and return the front item from the Deque;
     *throw EmptyDequeException if the Deque is empty;
     *reduce the array capacity by half if the size 
     *of the Deque size falls below floor(N/4) where
     *N is the array capacity, but do not resize it 
     *to be below the default capacity, which is 10
     ***/
    public Item getFirst() throws EmptyDequeException {
    	Item retItem;
    	if (this.isEmpty()) {
    		throw new EmptyDequeException();
    	}else {
    		// do the <front < back) && (front > back) checks here
    		retItem = this.deque[front];
    		this.arrayAccesses += 2;
    		this.deque[front] = null;
    		size--; 
    		if (front > back) {
    			if (front == this.deque.length-1) {
    				front = 0;
    			}else {
    				front++;
    			}
    		}else if(front < back) {
    			front++;
    		}
    		if(this.size < (this.getArray().length/4) && this.size() > 1) {
    			this.deque = reduceArray();
    		}
    	}
    	if (this.isEmpty()) {
			this.back = 0;
			this.front = 0;
		}
    	return retItem;
    }

     /***
     *remove and return the back item from the Deque;
     *throw EmptyDequeException if the Deque is empty;
     *reduce the array capacity by half if the size 
     *of the Deque size falls below floor(N/4) where
     *N is the array capacity, but do not resize it 
     *to be below the default capacity, which is 10
     ***/
    //TODO: Implement this!
    public Item getLast() throws EmptyDequeException {
    	Item retItem;
    	if (this.isEmpty()) {
    		throw new EmptyDequeException();
    	}
    	else {
    		retItem = this.deque[back];
    		this.deque[back] = null;
//    		this.arrayAccesses++;
    		this.arrayAccesses += 2;
    		size--;
    		if (back < front) {
	    		if(back == 0) {
	    			this.back = this.getArray().length-1;
	    		}else {
	    			this.back--;
	    		}
    		}else {
    			back--;
    		}
    		if(this.size < (this.getArray().length/4) && this.size() > 1) {
    			this.deque = reduceArray();
    		}
    	}
    	if (this.isEmpty()) {
			this.back = 0;
			this.front = 0;
		}
    	return retItem;

    }
    
    /***
     *return true if the Deque is empty;
     *return false if the Deque is not empty
     ***/
    public boolean isEmpty() {
		return this.size() == 0;
    }

    /***
     *return the size of the Deque (i.e. the 
     *number of elements currently in the Deque)
     ***/
    public int size() {
    	return this.size;
    }

    /***
     *return but do not remove the front item in the Deque;
     *throw an EmptyDequeException if the Deque is empty
     */
    public Item peekFirst() throws EmptyDequeException {
    	if (this.isEmpty()) {
    		throw new EmptyDequeException();
    	}
    	//System.out.println("from peekFront--> front: "+ this.front);
    	this.arrayAccesses++;
    	return this.getArray()[front];
    }

    /***
     *return but do not remove the back item in the Deque;
     *throw an EmptyDequeException if the Deque is empty
     */
    public Item peekLast() throws EmptyDequeException {
    	if (this.isEmpty()) {
    		throw new EmptyDequeException();
    	}
    	this.arrayAccesses++;
    	return this.getArray()[back];
    }
    
    /***
     *return the underlying array
     ***/
    public Item[] getArray() {
    	return this.deque;
    }

    /***
     *return the array accesses count
     ***/
    public int getAccessCount() {
	//TO BE IMPLEMENTED
    	return this.arrayAccesses;
    }
    
    /***
     *reset the array access count to 0
     ***/
    public void resetAccessCount() {
		this.arrayAccesses = 0;
    }
    
    
    public static void main(String[] args) throws EmptyDequeException {
    	// c = capacity of the initial array
    	// N = number of addFront/addBacks
    	int N = Integer.parseInt(args[0]); 
    	int c = Integer.parseInt(args[1]);
    	Deque<Integer> deque = new Deque<Integer>(c);
    	testCounts(deque,c,N);
    }	
    
    
    /*
     * MY PRIVATE METHODS:
     */
    /*
     * this is to test the N input and the c input on the Deque class
     */
    private static void testCounts(Deque<Integer> deque, int c,int N) throws EmptyDequeException {
    	System.out.println("For capacity of: "+ c);
		deque.resetAccessCount();
		System.out.println(String.format("Beginning test for range(N) %s: ", N));
    	for(int x = 0; x < N; x++) {
    		deque.addToFront(x);
    	}
    	for(int y = 0; y < N; y++) {
    		deque.addToBack(y);
    	}
    	System.out.println("After Adding: "+deque.getAccessCount());
    	deque.resetAccessCount();
    	for (int g = 0; g < N; g++) {
    		deque.getFirst();
    	}
    	for (int k = 0; k < N; k++) {
    		deque.getLast();
    	}
    	System.out.println("After Removing: " + deque.getAccessCount());
    	System.out.println();
    	
    }
    /*
     * this private method is used when the array needs to be extended;
     * this is the case when the deque.size() == deque.length
     */
    private Item[] extendArray(Item item,String mode) {
    	//ret array 
    	Item[] newA = (Item[]) new Object[size*2];
    	//keeps track of the 
    	int newIndex;
    	//mode helps me add to the right index without having to 
    	//create another function to do adds
    	//if mode is front; add the item to the zero index
    	if (mode.toLowerCase() == "front") {
    		newA[0] = item;
    		this.arrayAccesses++;
    		newIndex = 1;
    	}else {
    		newIndex = 0;
    	}
    	//if there is a wrap around
    	if (front > back) {
    		//get all the fronts
    		for (int f = front; f < this.deque.length; f++) {
    			this.arrayAccesses+=2;
    			newA[newIndex] = this.deque[f];
    			newIndex++;
    		}
    		//get all the backs
    		for (int b = 0; b <= back; b++) {
    			this.arrayAccesses+=2;
    			newA[newIndex] = this.deque[b];
    			newIndex++;
    		}
    	//no wrap around
    	}else if (back > front) {
    		for (int f = front; f < back; f++) {
    			this.arrayAccesses+=2;
    			newA[newIndex] = this.deque[f];
    			newIndex++;
    		}
    		for (int b = back; b < this.deque.length; b++) {
    			this.arrayAccesses+=2;
    			newA[newIndex] = this.deque[b];
    			newIndex++;
    		}
    	}
    	//no matter what, set the new front to zero
    	this.front = 0;
    	//if the mode is back; add the item at the next available
    	//index
    	if (mode.toLowerCase().equals("back")) {
    		this.arrayAccesses+=1;
    		newA[newIndex] = item;
    		newIndex++;
    	}
    	//set the back to be current position
    	this.back = newIndex -1;
    	return newA;
    }
    /*
     * called when an the deque size has gone below array.length/4
     * if arra.length / 4 < 10 ; we set the capacity to 10
     * the return array will always have the front as 0 and any subsequent 
     * nodes leading after it in the right order.
     */
    private Item[] reduceArray() {
    	int newSize;
    	if (this.getArray().length / 4 > 10) {
    		newSize = this.getArray().length / 2;	
    	}else {
    		newSize = 10;
    	}
    	//ret array
    	Item[] newA = (Item[]) new Object[newSize];
    	//our current deque has a wrap around
    	if (front < back) {
    		for (int x = 0; x < newSize; x++) {
    			this.arrayAccesses+=2;
    			newA[x] = this.deque[front+x];
    		}
    	//current deque doesnt have a wrap around
    	}else if (front > back) {
    		int newIndex = 0;
    		for (int f = front; f < this.deque.length; f++) {
    			this.arrayAccesses+=2;
    			newA[newIndex] = this.deque[front+newIndex];
    			newIndex++;
    		}
    		for (int b = 0; b <= back; b++) {
    			this.arrayAccesses+=2;
    			newA[newIndex] = this.deque[b];
    			newIndex++;
    		}
    	}
    	//set the values of the new array
    	this.front = 0;
    	this.back = this.size()-1;
    	return newA;
    }
}
