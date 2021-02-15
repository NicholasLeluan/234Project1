import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class DequeTest {
    public static void main(String[] args) {
	//Test 1: Basic Operations
	double score = test1();
	//Test 2: Underlying Array
	score += test2();

	System.out.println("Total Score: " + score);
    }

    private static double test1() {
	System.out.println("+++++ Begin Test 1 +++++");
	double score = 0.0;
	Deque<Integer> deque = new Deque<Integer>();
	ArrayList<Integer> list = new ArrayList<Integer>();
	Random gen = new Random();
	
	//add items to front
	System.out.println("adding items to the front...");
	int num = 6;
	//int num = gen.nextInt(25)+25;
	for(int i = 0; i < num; i++) {
	    int x = gen.nextInt(100);
	    deque.addToFront(x);
	    list.add(0, x);
	}
	//test size
	if(testSize(list, deque))
	    score += 0.5;//0.5

	//test isEmpty
	if(testIsEmpty(list, deque))
	    score += 0.5;//1.0
	//add items to the back
	System.out.println("adding items to the back...");
	num = 6;
	//num = gen.nextInt(25) + 25;
	for(int i = 0; i < num; i++) {
	    int x = gen.nextInt(100);
	    deque.addToBack(x);
	    list.add(x);
	}

	//test size
	if(testSize(list, deque))
	    score += 0.5;//1.5

	//test isEmpty
	if(testIsEmpty(list, deque))
	    score += 0.5;//2.0

	//test peekFirst
	int exp = list.get(0);
	int act = -1;
	try {
	    act = deque.peekFirst();
	} catch (EmptyDequeException e) {
	    System.out.println("Deque should not be empty here!");
	    e.printStackTrace();
	}
	if(exp == act)
	    score += 1.0;//3.0
	else
	    printMsg("peekFirst mismatch", ""+exp+"", ""+act+"");

	//test peekLast
	exp = list.get(list.size()-1);
	act = -1;
	try {
	    act = deque.peekLast();
	} catch (EmptyDequeException e) {
	    System.out.println("Deque should not be empty here!");
	    e.printStackTrace();
	}
	if(exp == act)
	    score += 1.0;//4.0
	else
	    printMsg("peekLast mismatch", ""+exp+"", ""+act+"");
	
	//testing getFirst
	System.out.println("getting items from the front...");
	num = 4;
	//num = gen.nextInt(list.size()/4);
	for(int i = 0; i < num; i++) {
	    try {
		deque.getFirst();
	    } catch (EmptyDequeException e) {
		System.out.println("Deque should not be empty here!");
		e.printStackTrace();
	    }
	    list.remove(0);
	}
	//test size
	if(testSize(list, deque))
	    score += 0.5;//4.5
	//test isEmpty
	if(testIsEmpty(list, deque))
	    score += 0.5;//5.0

	//testing getLast
	System.out.println("getting items from the back...");
	num = 4;
	//num = gen.nextInt(list.size()/4);
	for(int i = 0; i < num; i++) {
	    try {
		deque.getLast();
	    } catch (EmptyDequeException e) {
		System.out.println("Deque should not be empty here!");
		e.printStackTrace();
	    }
	    list.remove(list.size()-1);
	}
	//test size
	if(testSize(list, deque))
	    score += 0.5;//5.5
	//test isEmpty
	if(testIsEmpty(list, deque))
	    score += 0.5;//6.0

	//add more items
	System.out.println("adding more items...");
	num = 10;
	//num = gen.nextInt(100);
	for(int i = 0; i < num; i++) {
	    int x = gen.nextInt(100);
	    int side = gen.nextInt(2);
	    if(side == 0) {
		list.add(0, x);
		deque.addToFront(x);
	    } else {
		list.add(x);
		deque.addToBack(x);
	    }
	}
	//test size
	if(testSize(list, deque))
	    score += 0.5;//6.5

	//test getFront
	int total = list.size();
	int count = 0;
	while(!list.isEmpty()) {
	    int side = gen.nextInt(2);
	    if(side == 0) {
		exp = list.remove(0);
		act = -1;
		try {
		    act = deque.getFirst();
		} catch (EmptyDequeException e) {
		System.out.println("Error: Deque should not be empty here!");
		e.printStackTrace();
		}
	    } else {
		exp = list.remove(list.size()-1);
		act = -1;
		try {
		    act = deque.getLast();
		} catch (EmptyDequeException e) {
		    System.out.println("Error: Deque should not be empty here!");
		    e.printStackTrace();
		}
	    }
	    if(exp != act)
		printMsg("item mismatch", ""+exp+"", ""+act+"");
	    else
		count++;
	}
	if(total == count)
	    score += 6.0;//12.5
	if(testIsEmpty(list, deque) && deque.isEmpty())
	    score += 0.5;//13.0
	//test peek and get on empty Stack
	try {
	    deque.peekFirst();
	} catch (EmptyDequeException e) {
	    score += 1.0;//14.0
	}
	try {
	    deque.peekLast();
	} catch (EmptyDequeException e) {
	    score += 1.0;//15.0
	}
	try {
	    deque.getFirst();
	} catch (EmptyDequeException e) {
	    score += 1.0;//16.0
	}
	try {
	    deque.getLast();
	} catch (EmptyDequeException e) {
	    score += 1.0;//17.0
	}
	System.out.println("Test 1 Total: " + score);
	return score;
    }

    private static double test2() {
	System.out.println("+++++ Begin Test 2 +++++");
	Random gen = new Random();
	double score = 0.0;
	Deque<String> deque = new Deque<String>();
	ArrayList<String> list = new ArrayList<String>();
	int c = 0;
	while(c < 11) {
	    int y = gen.nextInt(2);
	    String x = String.valueOf(gen.nextInt(1000));
	    if(y == 0) {
		deque.addToFront(x);
		list.add(0, x);
	    } else {
		deque.addToBack(x);
		list.add(x);
	    }
	    c++;
	}
	//check size
	if(testSize(list, deque))
	    score += 0.5;//0.5
	Object[] array = deque.getArray();
	//check array capacity
	if(testCap(array, 20))
	    score += 2.0;//2.5
	//check array access count
	if(testCount(31, deque.getAccessCount()))
	    score += 2.0;//4.5
	//add 100 more items
	for(int i = 0; i < 100; i++) {
	    String x = String.valueOf(gen.nextInt(1000));
	    int y = gen.nextInt(2);
	    if(y == 0) {
		deque.addToFront(x);
		list.add(0, x);
	    } else {
		deque.addToBack(x);
		list.add(x);
	    }
	}
	array = deque.getArray();
	//check array capacity
	if(testCap(array, 160))
	    score += 2.0;//6.5
	//check array access count
	if(testCount(411, deque.getAccessCount()))
	    score += 2.0;//8.5
	//removing 72 items
	for(int i = 0; i < 72; i++) {
	    int y = gen.nextInt(2);
	    try {
		if(y == 0) {
		    deque.getFirst();
		} else {
		    deque.getLast();
		}
	    } catch (EmptyDequeException e) {
		System.err.println("Deque should not be empty here!");
		e.printStackTrace();
	    }
	    if(y == 0) 
		list.remove(0);
	    else
		list.remove(list.size()-1);
	}
	//check array capacity
	array = deque.getArray();
	if(testCap(array, 80))
	    score += 2.0;//10.5
	//remove elements until empty
	int total = deque.size();
	int count = 0;
	while(!deque.isEmpty()) {
	    int y = gen.nextInt(2);
	    String exp = "";
	    String act = "";
	    if(y == 0) {
		exp = list.remove(0);
		try {
		    act = deque.getFirst();
		} catch (EmptyDequeException e) {
		    System.err.println("Deque should not be empty here!");
		    e.printStackTrace();
		}
	    } else {
		exp = list.remove(list.size()-1);
		try {
		    act = deque.getLast();
		} catch (EmptyDequeException e) {
		    System.err.println("Deque should not be empty here!");
		    e.printStackTrace();
		}
	    }
	    if(exp.equals(act)) 
		count++;
	    else
		printMsg("item mismatch", exp, act);
	}
	if(count == total)
	    score += 4.5;//15.0
	System.out.println("Test 2 Total: " + score);
	return score;
    }

    private static boolean testCount(int exp, int act) {
	if(exp == act)
	    return true;
	printMsg("error in array access count", ""+exp+"", ""+act+"");
	return false;
    }

    private static boolean testCap(Object[] array, int exp) {
	if(array.length == exp)
	    return true;
	printMsg("error in array capacity", ""+exp+"", ""+array.length+"");
	return false;
    }
    private static boolean testSize(ArrayList list, Deque deque) {
	if(list.size() == deque.size())
	    return true;
	else {
	    printMsg("size mismatch", ""+list.size()+"", ""+deque.size()+"");
	    return false;
	}
    }

    private static boolean testIsEmpty(ArrayList list, Deque deque) {
	if(list.isEmpty() == deque.isEmpty())
	    return true;
	else {
	    printMsg("isEmpty mismatch", ""+list.isEmpty()+"", ""+deque.isEmpty()+"");
	    return false;
	}
    }
    
    private static void printMsg(String msg, String exp, String act) {
	System.out.println(msg);
	System.out.println("Expected: " + exp);
	System.out.println("Actual: " + act);
    }

    private static void printArray(Deque deque) {
	Object[] array = deque.getArray();
	System.out.println(Arrays.toString(array));
    }
}
	
