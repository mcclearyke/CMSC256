package cmsc256;

/**
 * Kendall McCleary
 * Project 4 
 * CMSC 256 Fall 2019
 * This project uses the BRIDGES API to implement a generic singly linked stack.
 */


import java.util.EmptyStackException;
import java.util.Stack;

import bridges.base.DataStruct;
import bridges.base.SLelement;
import bridges.connect.Bridges;

//implements StackInterface<E>
public class CustomStack<E> implements StackInterface<E> {
	
	//instance variables
	private SLelement<E> stackHead = null; //Top of stack
	private SLelement<E> stackTail = null; //Bottom of stack
	
	/** Adds a new entry to the top of this stack.
    @param newEntry  An object to be added to the stack. */
	@Override
	public void push(E newEntry) {
		 
		//if the user tries to push nothing
		if (newEntry == null) {
			
			throw new IllegalArgumentException(); //throw an exception
		}
		
		//Creating the new node
		SLelement<E> bridgesNode = new SLelement<E>(newEntry); 
			
			//LINK the element to the head
			bridgesNode.setNext(stackHead);
			
			//make the head point to the new element
			stackHead = bridgesNode;
			
		//if the new element is the first one
		if (stackTail == null) {
			
			stackTail = stackHead; //set the tail to the first node so that it gets a value	
		}		
	}

	 /** Removes and returns this stack's top entry.
    @return  The object at the top of the stack. 
    @throws  EmptyStackException if the stack is empty before the operation. */
	@Override
	public E pop() {
		
		//If the list is empty
		if (this.isEmpty()) {
					
			throw new EmptyStackException(); //throw exception	
		}
				
		//Temporarily assigning the heads element to tempValue
		E tempValue = stackHead.getValue(); //use .getValue() to get the element at the top of the stack
			
			//updating stackHead to the element next to the one that was assigned to tempVal 
			stackHead = stackHead.getNext();
		
		//return the element at the top of the stack
		return tempValue;
		
	}

	/** Retrieves this stack's top entry.
    @return  The object at the top of the stack.
    @throws  EmptyStackException if the stack is empty. */
	@Override
	public E peek() {
		
		//If the stack is empty
		if (this.isEmpty()) {
			
			throw new EmptyStackException(); //throw exception
		}
		
		//return the element at the top of the stack
		return stackHead.getValue(); //use .getValue() to get the element at the top of the stack
	}

	 /** Detects whether this stack is empty.
    @return  True if the stack is empty. */
	@Override
	public boolean isEmpty() {
		
		//if it has no elements
		if (stackHead == null) {
				
			return true; //return true
		}
		
		//if it has elements
		return false; //return false
	}

	 /** Removes all entries from this stack. */
	@Override
	public void clear() {
		
		//To clear the stack:
		stackHead = null; //set head to null
		
		stackTail = null; //set tail to null
		
	}
	
	public void display() { 
		
		//If the list is empty
		if (isEmpty()) {
	
			System.out.println("The stack is empty"); 
		}
		
		else {
			
			//Set the top node to the current place in the list
			SLelement<E> current = stackHead;
			
			StringBuffer output = new StringBuffer();
			
			output.append("Top of stack: " + current.getValue() + "\n");
		
			while (current.getNext() != null) { 
				
				current = current.getNext();
		
				if (current.getNext() == null)  {
					
					output.append("Stack bottom: ");
				}
				
				else {
		            
					output.append("              ");     
				}
				
				output.append(current.getValue() + "\n");
			}
			
		System.out.println(output.toString()); 
		
		}
	}
	
	//Main method to demonstrate implementation
	@SuppressWarnings("unchecked")
	public static <E> void main(String[] args) throws Exception {
		
		//Making a bridges object
		Bridges bridges = new Bridges(3, "mcclearyke", "67178589394");
		bridges.setTitle("Kendall's List xD: ");
		
		//Creating a stack
		CustomStack<E> stack = new CustomStack<>();
			stack.push((E) "123");
			stack.push((E) "456");
			stack.push((E) "hi");
			stack.pop();
		
			//Displaying the stack
			stack.display();
		
		//Passing stackHead to bridges object to visualize the stack	
		bridges.setDataStructure(stack.stackHead);
		bridges.visualize();
		
	}
}
