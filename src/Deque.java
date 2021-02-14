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
    	if (back == front - 1 || back == this.deque.length) {
    		Item[] newA = extendArray();
    		newA[front] = item;
    		this.deque = newA;
    	}
    	else if (this.size() == 0) {
    		this.deque[0] = item;
    	}
    	//if the front of the deque is zero; we need to shift everything down
    	else if (front == 0 && this.size() > 0) {
    		Item[] newA = (Item[]) new Object[this.deque.length];
    		newA[0] = item;
    		for (int i = 0; i < this.size(); i++) {
    			newA[i+1] = this.deque[i];
    		}
    		this.deque = newA;
    	}
    	//index behind the front is not out of range and does not equal back
    	//if front - 1 == back; array is full
    	else if (front - 1 >= 0 && front - 1 != back) {
    		this.deque[front-1] = item;
    		front--;
    	}
    	this.size++;
    	this.back++;
    	
    }

    /***
     *add an item to the back of the Deque;
     *double the array capacity if the Deque is full
     ***/
    public void addToBack(Item item) {
	//TODO: Implement this!
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
    		front++;
    		if(this.size < (this.getArray().length/4)) {
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
    	if (this.isEmpty()) {
    		throw new EmptyDequeException();
    	}
    	return null;

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
    	// -1 because in my implementation , back == next free space at end
    	return this.getArray()[back-1];
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
    	for (int i = 0; i < 11; i++) {
    		deque.addToFront(i);
    		System.out.println(Arrays.toString(deque.getArray()));
    	}
    	System.out.println(deque.peekLast());
    	for(int u = 0; u < 6; u ++) {
    		deque.getFirst();
    	}
    	System.out.println(Arrays.toString(deque.getArray()));
    	
    	System.out.println(deque.peekLast());
    	System.out.println(deque.peekFirst());
    }	
    
    
    /*
     * MY PRIVATE METHODS:
     */
    //Makes a new array; leaves the 0 index empty for easy insert for addToFront()
    private Item[] extendArray() {
    	Item[] newA = (Item[]) new Object[size*2];
		int newBack = 0;
		for (int f = 1; f <= this.size() - front; f++) {
			newA[f] = this.deque[(front+f)-1];
			newBack++;
		}
		for (int b = 0; b > this.size() - back; b++) {
			newA[newBack] = this.deque[back-b];
			newBack++;
		}
		this.front = 0;
		this.back = newBack;
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
    	int newBack = 0;
		for (int f = 0; f < this.size(); f++) {
			newA[f] = this.deque[front+f];
			newBack++;
		}
		this.front = 0;
		this.back = newBack;
		return newA;
    }
}
