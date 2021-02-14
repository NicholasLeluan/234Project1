import java.util.Arrays;

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
    		this.deque[front] = item;
    	}
    	else if (front == 0) {
    		this.deque[this.deque.length-1] = item;
    		front = this.deque.length-1;
    		
    	}
    	else if(this.deque[front-1] == null) {
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
    	
    	}else if (back + 1 == this.deque.length) {
    		this.back = 0;
    		this.deque[0] = item;
    	}
    	else {
    		this.deque[back+1] = item;
    		back++;
    	}
    	//back++;
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
    		retItem = this.deque[front];
    		this.deque[front] = null;
    		size--; 
    		if (front + 1 == this.deque.length) {
        		front = 0;
        	}else {
        		front++;
        	}
    		if(this.size < (this.getArray().length/4) && this.size() > 1) {
    			this.deque = reduceArray();
    		}
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
    		size--;
    		if(back == 0) {
    			this.back = 0;
    		}else {
    			this.back--;
    		}if(this.size < (this.getArray().length/4) && this.size() > 1) {
    			this.deque = reduceArray();
    		}
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
	//TO BE IMPLEMENTED
    	Deque<Integer> deque = new Deque<Integer>();
    	for (int i = 0; i < 12; i++) {
    		deque.addToFront(i);
    		//System.out.println(i + ": "+ Arrays.toString(deque.getArray()) + "SIZE: "+deque.size());
    	}
    	System.out.println(Arrays.toString(deque.getArray()));
    	System.out.println(deque.peekFirst());
    	System.out.println(deque.peekLast());
    	for (int i = 0; i < 11; i++) {
    		deque.getLast();
    	}
    	//deque.getLast();
//    	deque.addToBack(25);
//    	deque.addToFront(44);
    	System.out.println(Arrays.toString(deque.getArray()));
    	System.out.println(deque.peekFirst());
    	System.out.println(deque.peekLast());
    }	
    
    
    /*
     * MY PRIVATE METHODS:
     */
    //Makes a new array; leaves the 0 index empty for easy insert for addToFront()
    private Item[] extendArray(Item item,String mode) {
    	Item[] newA = (Item[]) new Object[size*2];
    	int newIndex;
    	if (mode.toLowerCase() == "front") {
    		newA[0] = item;
    		newIndex = 1;
    	}else {
    		newIndex = 0;
    	}
    	if (front > back) {
    		for (int f = front; f < this.deque.length; f++) {
    			newA[newIndex] = this.deque[f];
    			newIndex++;
    		}
    		for (int b = 0; b <= back; b++) {
    			newA[newIndex] = this.deque[b];
    			newIndex++;
    		}
    	}else if (back > front) {
    		for (int f = front; f < back; f++) {
    			newA[newIndex] = this.deque[f];
    			newIndex++;
    		}
    		for (int b = back; b < this.deque.length; b++) {
    			newA[newIndex] = this.deque[b];
    			newIndex++;
    		}
    	}
    	this.front = 0;
    	if (mode.toLowerCase().equals("back")) {
    		newA[newIndex] = item;
    		newIndex++;
    	}
    	this.back = newIndex -1;
    	return newA;
    }
    
    private Item[] reduceArray() {
    	int newSize;
    	if (this.getArray().length / 4 > 10) {
    		newSize = this.getArray().length / 2;	
    	}else {
    		newSize = 10;
    	}
    	Item[] newA = (Item[]) new Object[newSize];
    	if (front < back) {
    		for (int x = 0; x < newSize; x++) {
    			newA[x] = this.deque[front+x];
    		}
    	}else if (front > back) {
    		int newIndex = 0;
    		for (int f = front; f < this.deque.length; f++) {
    			newA[newIndex] = this.deque[front+newIndex];
    			newIndex++;
    		}
    		for (int b = 0; b <= back; b++) {
    			newA[newIndex] = this.deque[b];
    			newIndex++;
    		}
    	}
    	this.front = 0;
    	this.back = this.size()-1;
    	return newA;
    }
}
