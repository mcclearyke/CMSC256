package cmsc256;

public class MinHeap <E extends Comparable<? super E>> {

	//instance variables
	private E[] heap;
	private int size = 0;
	private static final int DEFAULT_CAPACITY = 8;

	//Parameterized Constructor
	@SuppressWarnings("unchecked")
	public MinHeap(int capacity) {
		 heap = (E[]) new Comparable[capacity];
	}
	
	//Constructor
	public MinHeap() {
		this(DEFAULT_CAPACITY);
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	//Expand the array if necessary
	private void expand() {
		
		@SuppressWarnings("unchecked")
		E[] newHeap = (E[]) new Comparable[heap.length * 2];
			
			for(int i = 0; i < size(); i++) {
				newHeap[i] = heap[i];
			}
			
		heap = newHeap;
	}
	
	//Swap two elements
	private void swapElements(int index1, int index2) {
		
		E current = heap[index1];
		heap[index1] = heap[index2];
		heap[index2]=current;
		
	}
	
	//Index of nodes parents
	private int getParentIndex(int childIndex) { 
		
		// if odd, child is a left node
		if (childIndex % 2 != 0) {
		   return childIndex / 2;
		}
		// if even, child is a right node
		else {
			return childIndex / 2 - 1;
		} 
	}

	//find the smaller child index
	private int smallerChildIndex(int parentIndex) {
		
		int smaller = parentIndex;
		
		int leftChild = (2 * parentIndex) + 1; // get left child index 
		
		// if the left child index is in bounds of the heap...
		if (leftChild < size() - 1) {
		    
			// set smaller to left if left is smaller than parent
			if(heap[leftChild].compareTo(heap[smaller]) < 0) {
				smaller = leftChild;
			}
			
		int rightChild = (2 * parentIndex) + 2 ; // get right child index
		
			// if the right child index is in bounds of the heap...
			if (rightChild < size() - 1) {
		        
				// set smaller to right if right is smaller than parent
				if(heap[rightChild].compareTo(heap[smaller]) < 0) 
					smaller = rightChild;
				} 		
			}
		   
			return smaller;
		}
	
	public void insert(E element) {
		
		int position = size();
		
		//if the array needs to be resized
		if(position == heap.length) {
			expand();
		}
		
		size++;
		heap[position] = element;
		
		//To move the nod up the tree until its parent's value is lesser than its own
		int parentIndex = getParentIndex(position);

		while (position > 0 && heap[position].compareTo(heap[parentIndex]) < 0) { 
			
			// if parent is greater, swap parent and node
			swapElements(parentIndex, position);

			// update position of the new element and find next parent up
			position = parentIndex;
			parentIndex = getParentIndex(position);
		}
	}
	
	public static void main(String[] args) { 
		MinHeap<Integer> mh = new MinHeap<Integer>(); 
			mh.insert(2);
			mh.insert(4);
			mh.insert(1); 
			mh.insert(10); 
			mh.insert(3); 
			mh.insert(6);
			mh.insert(15); 
			mh.insert(12); 
			mh.insert(16);
			mh.insert(5);
			mh.insert(15);

		while(!mh.isEmpty()) {
			System.out.println(mh.remove());
			System.out.println();
		}
	}
	
   public E remove() {
		
	   if (isEmpty()) {
			return null;
		}
		
		// take out root and place last node at root position
		E min = heap[0];
		
		heap[0] = heap[size() - 1];
		
		heap[size() - 1] = null; // set the removed location to null size--;
		
		size--;
	
		// position of new root and its smaller child
		int position = 0;
		int smallerChildIndex = smallerChildIndex(position) ;
	
		// while there is a smaller child, swap parent and child
		while (smallerChildIndex != position) { 
			
			swapElements(position, smallerChildIndex);
			
			position = smallerChildIndex;
			
			// update position of node and get new smaller child position = smallerChild;
			smallerChildIndex = smallerChildIndex(position);
		}
	return min; 
	}

	
	
}
