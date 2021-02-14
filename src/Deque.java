@SuppressWarnings("unchecked")
public class Deque<Item> {
    private Item[] deque;
    private int front;
    private int back;
    private int size;

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
    }
    
    /***
     *add an item to the front of the Deque
     *double the array capacity if Deque is full
     */
    public void addToFront(Item item) {
	//TO BE IMPLEMENTED
    	//no more space in array; need to expand
    	if (back == front - 1) {
    		Item[] newA = extendArray();
    		newA[front] = item;
    	}
    	//if the front of the deque is zero; we need to shift everything down
    	else if (size == 0) {
    		this.deque[0] = item;
    	}
    	//index behind the front is not out of range and does not equal back
    	//if front - 1 == back; array is full
    	else if (front - 1 >= 0 && front - 1 != back) {
    		this.deque[front-1] = item;
    	}
    	this.size++;
    	
    }

    /***
     *add an item to the back of the Deque;
     *double the array capacity if the Deque is full
     ***/
    public void addToBack(Item item) {
	//TO BE IMPLEMENTED
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
	//TO BE IMPLEMENTED
    	return null;
    }

     /***
     *remove and return the back item from the Deque;
     *throw EmptyDequeException if the Deque is empty;
     *reduce the array capacity by half if the size 
     *of the Deque size falls below floor(N/4) where
     *N is the array capacity, but do not resize it 
     *to be below the default capacity, which is 10
     ***/
    public Item getLast() throws EmptyDequeException {
	//TO BE IMPLEMENTED
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
    	return this.size();
    }

    /***
     *return but do not remove the front item in the Deque;
     *throw an EmptyDequeException if the Deque is empty
     */
    public Item peekFirst() throws EmptyDequeException {
	//TO BE IMPLEMENTED
    }

    /***
     *return but do not remove the back item in the Deque;
     *throw an EmptyDequeException if the Deque is empty
     */
    public Item peekLast() throws EmptyDequeException {
	//TO BE IMPLEMENTED
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
    }
    
    /***
     *reset the array access count to 0
     ***/
    public void resetAccessCount() {
	//TO BE IMPLEMENTED
    }
    
    
    public static void main(String[] args) {
	//TO BE IMPLEMENTED
    }	
    
    
    /*
     * MY PRIVATE METHODS:
     */
    //Makes a new array; leaves the 0 index empty for easy insert for addToFront()
    private Item[] extendArray() {
    	Item[] newA = (Item[]) new Object[size*2];
		int newBack = 2;
		for (int f = 1; f <= this.size() - front; f++) {
			newA[f] = this.deque[(front+f)-1];
			newBack++;
		}
		//back = newBack;
		for (int b = 0; b > this.size() - back; b++) {
			newA[newBack] = this.deque[back-b];
			newBack++;
		}
		
		this.front = 0;
		this.back = newBack;
		return newA;
    }
}
