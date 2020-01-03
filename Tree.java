package cmsc256;

/**
 * Kendall McCleary
 * Project 5
 * CMSC 256 Fall 2019
 * For this project I will add functionality the partial implementation of a Binary Search Tree.
 */

import java.util.Collection;
import java.util.Iterator;

public interface Tree<E> extends java.util.Collection<E> {

/** Return true if the element is in the tree */
  public boolean search(E e);
  
  
  /** Insert element o into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E e);
	
  /** Delete the specified element from the tree
   * Return true if the element is deleted successfully */
  public boolean delete(E e);

  /** Get the number of nodes in the tree */
  public int getSize();

  /** Inorder traversal from the root*/
  public default void inorder() {
  }

  /** Postorder traversal from the root */
  public default void postorder() {
  }

  /** Preorder traversal from the root */
  public default void preorder() {
  }

  @Override /** Return true if the tree is empty */
  public default boolean isEmpty() {
    return size() == 0;
  };

  @SuppressWarnings("unchecked")
@Override
  public default boolean contains(Object e) {
    return search((E)e);
  }

  @Override
  public default boolean add(E e) {
    return insert(e);
  }

  @SuppressWarnings("unchecked")
@Override
  public default boolean remove(Object e) {
    return delete((E)e);
  }

  @Override
  public default int size() {
    return getSize();
  }

  /** Returns true if this collection contains all the elements in the
  specified collection. */
  @SuppressWarnings("unchecked")
@Override
  public default boolean containsAll(Collection<?> c) {
	 boolean found = true; //assume its found
	  
	  //for each element in c
	  for(Object obj : c) { //: means in
		
		//check if it is in this tree
		found = this.search((E) obj);
			
			//if the object is NOT found
			if(!found) { 
				return false;
			}
	  }
	  
	//if the object IS found
	return true;
  }

  /** Adds all the elements in the specified collection to this collection 
   * returns true if the collection is changed*/
  @SuppressWarnings("unchecked")
@Override
  public default boolean addAll(Collection<? extends E> c) {
	  
	  boolean isChanged = false; //assume it won't be changed
	  
	  //for each element in c
	  for(Object obj : c) {
		  
		  //check to see if it is in the tree
		  isChanged = this.search((E) obj);
		  
		  	//if the object is NOT in the tree
		  	if(!isChanged) {
		  		
		  		//add the element
		  		add((E) obj);
		  		
		  		//The tree is now changed
		  		isChanged = true;
		  	}
	  }
	  
	  return isChanged;
  }

  /** Removes all this collection's elements that are also contained in the specified collection 
   * return true if the collection changed as a result of the call*/
  @SuppressWarnings("unchecked")
@Override
  public default boolean removeAll(Collection<?> c) {
   
	  boolean isChanged = false; //assume it won't be changed
	  
	  //for each element in c
	  for(Object obj : c) {
		  
		  //check to see if it is in the tree
		  isChanged = this.search((E) obj);
		  
		  	//if the object IS in the tree
		  	if(isChanged) {
		  	   
		  		//remove that element
		  		remove((E) obj);
		  		
		  		//The tree is now changed
		  		isChanged = true;
		  	}
	  }
	  
	  return isChanged;
  } 
	  
  /** removes from this collection all of its elements that are not contained in the specified collection
   * return true if the collection is changed */
  @SuppressWarnings({ "unchecked" })
  @Override
  public default boolean retainAll(Collection<?> c) {
	  boolean isChanged = true; //assume it will be changed
	  
	  //Iterator to go through the collection
	  Iterator<? extends E> retains = (Iterator<? extends E>) c.iterator();
	  	
	  //while loop to go through the collection element by element
		while(retains.hasNext()){
			
			Object i = retains.next();
			
			//if element i is not in c AND if an element in c is not in i
	  		if (!c.contains(i) && !((Collection<? extends E>) i).contains(c)) {
	  		
	  			//remove the values
	  			retains.remove();
	  		}
	  		else {
	  			
	  			isChanged = false;
	  		}
		}
		
	  return isChanged;  
  }


  /** Returns an array containing all the elements */
  @Override
  public default Object[] toArray() {
   //Create the array
   Object[] array = new Object[this.getSize()]; //set the size of the array to the size of the collection
 
   //Create the iterator
   Iterator iter = this.iterator();
   
   //Variable to keep the while loop going
   int i = 0;
  
   //While the iterator has another element
   while(iter.hasNext()) {
	   
	   //add the element to the array
	   array[i] = iter.next();
		
	   i++; //increment the variable that keeps the loop going
   }
   
   return array; //return the array
  }

  /** Returns an array containing all the elements the runtime type of the returned array is that of */
  @SuppressWarnings("unchecked")
  @Override
  public default <T> T[] toArray(T[] array) {
	 //variable to keep track of the index in the array
	 int index = 0;
	 	
	 	//For every object in the passed in array
	    for(E obj : this) {
		   
	       //Add it to the array 
	       array[index] = (T) obj; //cast the obj since it is a generic array
	       
	       //increment index
	       index++;   
	   }
	   
	   //return the filled generic array
	   return array;
  }
}
