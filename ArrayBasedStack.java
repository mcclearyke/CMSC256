package cmsc256;

import java.util.Arrays;

public class ArrayBasedStack<T> implements StackInterface<T> {

	private T[] data;
	private int topOfStack;
	private static final int INITIAL_CAPACITY = 5;
	
	@SuppressWarnings("unchecked")
	public ArrayBasedStack(int capacity) {
		
		if (capacity <= 0) {
	 
			throw new IllegalArgumentException("Array initial size error.");
		}
		
		this.data = (T[])new Object[capacity];
	
	
	}

	public ArrayBasedStack() {
		
		this.clear();
	}
	
	private void expandArray() {
		
		this.data = Arrays.copyOf(this.data, this.data.length * 2);
		
	}
	
	private boolean isArrayFull() { 
		
		if(topOfStack >= data.length -1) {
			
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public void push(T newEntry) {
		
	if (this.isArrayFull()) {
		
		this.expandArray();	
	}
	
	this.topOfStack++;
	
	data[topOfStack] = newEntry;
	
	}

	@Override
	public T pop() {
		//If it's empty
		if(this.isEmpty()) {
			
			throw new cmsc256.EmptyStackException();
		}
		
		T temp = data[topOfStack];
		
		data[topOfStack] = null;
		this.topOfStack--;
		return temp;
	}

	@Override
	public T peek() {
		
		//If it's empty
		if(topOfStack == -1) {
					
			throw new cmsc256.EmptyStackException();
		}
				
		return data[topOfStack];
	}

	@Override
	public boolean isEmpty() {
		
		//if it has no elements
		if (topOfStack == -1) {
		
			return true;
		}
		
		return false;
	}

	@Override
	public void clear() {
	
	@SuppressWarnings("unchecked")
	T[] tempStack = (T[])new Object[INITIAL_CAPACITY]; 
	data = tempStack;
	topOfStack = -1;
	}

}
