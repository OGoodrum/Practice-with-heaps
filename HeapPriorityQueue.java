/*
* HeapPriorityQueue.java
*
* An implementation of a minimum PriorityQueue using a heap.
* based on the implementation in "Data Structures and Algorithms
* in Java", by Goodrich and intamassia
*
* inthis implementation will throw a Runtime, HeapEmptyException
*	if the heap is empty and removeMin is called.
*
* inthis implementation will throw a Runtime, HeapFullException
*	if the heap is full and insert is called.
*
*/
public class HeapPriorityQueue implements PriorityQueue {

	protected final static int DEFAULT_SIZE = 10000;
	
	protected int[] storage;
	protected int currentSize;
	int rootIndex = 1; // should be 0 or 1 depending on implementation

	/*
	 * Constructor that initializes the array to hold DEFAULT_SIZE elements
	 */
	public HeapPriorityQueue() {
		// TODO: implement this
		
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 

		storage = new int[DEFAULT_SIZE + rootIndex];
		currentSize = 0;
	}
	
	/*
	 * Constructor that initializes the array to hold size elements
	 */
	public HeapPriorityQueue(int size) {
		// TODO: implement this
		
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used.
		storage = new int[size + rootIndex]; 
		currentSize = 0;
	}

	public void insert (int element) throws HeapFullException {
		// TODO: implement this
		
		// When inserting the first element, choose whether to use 
		// a 0-based on 1-based implementation. Whatever you choose,
		// make sure your implementation for the rest of the program
		// is consistent with this choice.	
		if(isFull()){
			throw new HeapFullException();
		}
		
		storage[currentSize + rootIndex] = element;
		bubbleUp(currentSize + rootIndex);
		currentSize++;
    }
	
	private void bubbleUp(int index) {
		// TODO: implement this
		if(storage[index/2] > storage[index]){
			int temp = storage[index/2];
			storage[index/2] = storage[index];
			storage[index] = temp;
			bubbleUp(index/2);
		}
	}
			
	public int removeMin() throws HeapEmptyException {
		// TODO: implement this
		if(isEmpty()){
			throw new HeapEmptyException();
		}
		int toReturn = storage[rootIndex];
		storage[rootIndex] = storage[currentSize];
		storage[currentSize] = 0;
		currentSize--;
		bubbleDown(rootIndex); 
		
		return toReturn; // so it compiles
	}
	
	private void bubbleDown(int index) {
		// TODO: implement this
		int temp = storage[index];
		if(index * 2 > currentSize){
			return;
		}

		//go into this if if 
		if((storage[index] > storage[index * 2] && (index * 2) <= currentSize) || (storage[index] > storage[(index * 2) + 1] && (index * 2) +1 <= currentSize)){
			if( (storage[(index * 2) + 1] > storage[index * 2]) || (index * 2) + 1 > currentSize){
				storage[index] = storage[index * 2];
				storage[index * 2] = temp;
				bubbleDown(index * 2);
			} else {
				storage[index] = storage[(index * 2) + 1];
				storage[(index * 2) + 1] = temp;
				bubbleDown((index * 2) + 1); 
			}

		}
		
		 
	}

	public boolean isEmpty(){
		// TODO: implement this
		if(currentSize == 0){
			return true;
		}
		
		return false; // so it compiles
	}
	
	public boolean isFull() {
		// TODO: implement this
		if(currentSize + rootIndex == storage.length){
			return true;
		}
		
		return false; // so it compiles
	}
	
	public int size () {
		// TODO: implement this
		
		return currentSize; // so it compiles
	}

	public String toString() {
		String s = "";
		String sep = "";
		if (rootIndex == 0) {
			for (int i = 0; i < currentSize; i++) {
				s += sep + storage[i];
				sep = " ";				
			}
		} else if (rootIndex == 1) {
			for(int i=1; i <= currentSize; i++) {
				s += sep + storage[i];
				sep = " ";
			}
		}
		return s;
	}
}
