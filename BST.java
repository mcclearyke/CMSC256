package cmsc256;

/**
 * Kendall McCleary
 * Project 5
 * CMSC 256 Fall 2019
 * For this project I will add functionality the partial implementation of a Binary Search Tree.
 */


import java.util.LinkedList;
import java.util.Queue;

public class BST<E extends Comparable<E>> implements Tree<E> {
  protected TreeNode<E> root;
  protected int size = 0;

  /** Create a default binary tree */
  public BST() {
  }

  /** Create a binary tree from an array of objects */
  public BST(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      insert(objects[i]);
  }

  @Override /** Returns true if the element is in the tree */
  public boolean search(E e) {
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else // element matches current.element
        return true; // Element is found
    }

    return false;
  }

  @Override /** Insert element o into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E e) {
    if (root == null)
      root = createNewNode(e); // Create a new root
    else {
      // Locate the parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null)
        if (e.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        }
        else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        }
        else
          return false; // Duplicate node not inserted

      // Create the new node and attach it to the parent node
      if (e.compareTo(parent.element) < 0)
        parent.left = createNewNode(e);
      else
        parent.right = createNewNode(e);
    }

    size++;
    return true; // Element inserted successfully
  }

  protected TreeNode<E> createNewNode(E e) {
    return new TreeNode<>(e);
  }

  @Override /** Inorder traversal from the root */
  public void inorder() {
    inorder(root);
  }

  /** Inorder traversal from a subtree */
  protected void inorder(TreeNode<E> root) {
    if (root == null) return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  @Override /** Postorder traversal from the root */
  public void postorder() {
    postorder(root);
  }

  /** Postorder traversal from a subtree */
  protected void postorder(TreeNode<E> root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }

  @Override /** Preorder traversal from the root */
  public void preorder() {
    preorder(root);
  }

  /** Preorder traversal from a subtree */
  protected void preorder(TreeNode<E> root) {
    if (root == null) return;
    System.out.print(root.element + " ");
    preorder(root.left);
    preorder(root.right);
  }

  /** This inner class is static, because it does not access
      any instance members defined in its outer class */
  public static class TreeNode<E> {
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;

    public TreeNode(E e) {
      element = e;
    }
  }

  @Override /** Get the number of nodes in the tree */
  public int getSize() {
    return size;
  }

  /** Returns the root of the tree */
  public TreeNode<E> getRoot() {
    return root;
  }

  /** Returns a path from the root leading to the specified element */
  public java.util.ArrayList<TreeNode<E>> path(E e) {
    java.util.ArrayList<TreeNode<E>> list =
      new java.util.ArrayList<>();
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      list.add(current); // Add the node to the list
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else
        break;
    }

    return list; // Return an array list of nodes
  }

  @Override /** Delete an element from the binary tree.
   * Return true if the element is deleted successfully
   * Return false if the element is not in the tree */
  public boolean delete(E e) {
    // Locate the node to be deleted and also locate its parent node
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      }
      else
        break; // Element is in the tree pointed at by current
    }

    if (current == null)
      return false; // Element is not in the tree

    // Case 1: current has no left child
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
        root = current.right;
      }
      else {
        if (e.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    }
    else {
      // Case 2: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;

      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right; // Keep going to the right
      }

      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;

      // Eliminate rightmost node
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else
        // Special case: parentOfRightMost == current
        parentOfRightMost.left = rightMost.left;
    }

    size--;
    return true; // Element deleted successfully
  }

  @Override /** Obtain an iterator. Use inorder. */
  public java.util.Iterator<E> iterator() {
    return new InorderIterator();
  }

  // Inner class InorderIterator
  private class InorderIterator implements java.util.Iterator<E> {
    // Store the elements in a list
    private java.util.ArrayList<E> list =
      new java.util.ArrayList<>();
    private int current = 0; // Point to the current element in list

    public InorderIterator() {
      inorder(); // Traverse binary tree and store elements in list
    }

    /** Inorder traversal from the root*/
    private void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    private void inorder(TreeNode<E> root) {
      if (root == null)return;
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }

    @Override /** More elements for traversing? */
    public boolean hasNext() {
      if (current < list.size())
        return true;

      return false;
    }

    @Override /** Get the current element and move to the next */
    public E next() {
      return list.get(current++);
    }

    @Override /** Remove the current element */
    public void remove() {
      delete(list.get(current)); // Delete the current element
      list.clear(); // Clear the list
      inorder(); // Rebuild the list
    }
  }

  @Override /** Remove all elements from the tree */
  public void clear() {
    root = null;
    size = 0;
  }
   
  
  /** Methods to Write:
   * levelOrderTraversal()
   * height() -> DONE
   * isFullBST() -> REVISE	
   * getNumberOfLeaves() -> DONE
   * getNumberOfNonLeaves() -> DONE
   *  */ 
  
  /** Display the nodes in a level-order traversal */ 
  public void levelOrderTraversal() {
	  
	  levelOrderTraversal(root); //pass in the root value
	   
  }
 
  	//Level order traversal will print out the tree staring from left to right per level stating at 1
  	private void levelOrderTraversal(TreeNode<E> root) {
	 
  		/**************************************
  		 * Algorithm
  		 * Initialize queue Q to contain root()
  		 * while Q not empty do
  		 * p = Q.dequeue()
  		 * visit position p
  		 * for each child c in children (p) do 
  		 * Q.enqueue c
  		 **************************************/
	  
  		//base case
  		if(root == null) {
  			return; //exist the program
  		}
	  
  		//Creating the Generic Queue by implementing a linked list structure
  		Queue<TreeNode<E>> Q = new LinkedList<TreeNode<E>>();
	  	
  			//Initialize queue Q to contain the root
	  		Q.add(root);
	  	
	  	//while Q is not empty
	  	while (!Q.isEmpty()) {
	  		
	  		TreeNode<E> current = root;
	  		
	  		//remove the first element
	  		current =  Q.remove();
	  		
	  		//Display it
	  		System.out.println(current.element + " ");
	  		
	  		//Add the children to the queue
	  			//Check the left to see if it is null or not
	  			if(current.left != null) {
	  				
	  				//If true, add the left child node to the queue
	  				Q.add(current.left);
	  			}
	  		
	  			//Check the right to see if it is null or not
	  			if(current.right != null) {
	  				
	  				//If true, add the right child node to the queue
	  				Q.add(current.right);
	  			}		
	  	}  
  	}

  /** Return the height of this binary tree 
  * @param left */ 
  public int height() {
	
	  return height(root); //pass in the root
  }
 
    //helper method to be able to do recursion
  	private int height(TreeNode<E> root) {
  		int rootPos = 1; //root position to be added to the greater child depth
  		
  		//base case
  		if(root == null) {
  			return -1; //return a -1 to exist
  		}
	
		//finding the depth by going to the left
		int leftDepth = height(root.left);
		
		//finding the depth by going to the right
		int rightDepth = height(root.right);
		
		//If the right subtree depth is larger than the left
		if(leftDepth < rightDepth) {
			
			//return the depth of the right subtree and the root
			return rootPos + rightDepth;
		}
		
		//If the left subtree depth is larger than the right
		else {
			
			//return the depth of the left subtree and the root
			return rootPos + leftDepth; 
		}
}

  /** Returns true if the tree is a full binary tree */ 
  public boolean isFullBST() {
	  
	  return isFullBST(root);
  }
  
  	//helper method to do recursion
  	private boolean isFullBST(TreeNode<E> root) {
  		
  		//variable to check if the tree is full
  		boolean isFull = false; //assume that it is false
  		
  		/****************************************
  		 * A BST is full if all nodes have either
  		 * 1. No children -> Check for leafs
  		 * 2. Two Children 
  		 ****************************************/
  		
  		//base case
  		if (root == null) {
  			
  				return isFull; //return false
  		}
  		
  		//checking to see if the nodes children are BOTH null
  		if (root.left == null && root.right == null) {
  			
  			//Since a BST is full if a node has 0 children
  			isFull = true; //set isFull to true
  		}
  		
  			//If the left child AND the right child are NOT null
  			if (root.left != null && root.right != null) {
  				
  				//Move to the left AND the right
  				return isFullBST(root.left) && isFullBST(root.right); //recursive call
			}

  			//If the node dosen't have 0 children and the recursive calls don't work 
  			else {
  				
  				return isFull; 
  			}			
  	}	

  /** Return the number of leaf nodes */ 
  //Finding the EXTERNAL nodes
  public int getNumberOfLeaves() {
	 
	  return getNumberOfLeaves(root); //pass in the root node
  }
  	
  	  //helper method to be able to do recursion
	  private int getNumberOfLeaves(TreeNode<E> root) {
		  //counter for the number of leaves in a tree
		  int numLeaf = 0;
		 
		  //base case
		  if (root == null) {
			  return 0;
		  }
	  
		  //if the roots left AND right child is null -> IT'S A LEAF	
		  if(root.left == null && root.right == null) {
			 
			  //increment the number of leaves found
			  numLeaf++;
			 
			  //return the number of leaves
			  return numLeaf;  
		  }
		  //if both the roots children are NOT null -> 	IT'S NOT A LEAF
		  else {
			  
			  //make the recursive call
			  return getNumberOfLeaves(root.left) + getNumberOfLeaves(root.right);
		  }	  
	  }

/** Return the number of non-leaf nodes */
  //Finding the INTERNAL nodes
  public int getNumberOfNonLeaves() {
	 
	  return getNumberOfNonLeaves(root); //pass in the root
  }

  	//helper method to be able to do recursion
  	private int getNumberOfNonLeaves(TreeNode<E> root	) {
  		
  		//Counter for the number of non-leaves in a tree
  		int numNonLeaf = 0;
	 
  		//Base case 1 -> if the root/tree is empty
  		if (root == null) {
  			
  			return 0;
  		}
	  
  		//Base case 2 -> if the node is a LEAF
  		else if (root.left == null && root.right == null) {
  			
  			return 0;
  		}
	  
  		//If it is an internal node
  		else {
  			
  			//increment the number of non leafs found
  			numNonLeaf++;
		  
  			numNonLeaf += getNumberOfNonLeaves(root.left) + getNumberOfNonLeaves(root.right); //recursive call
  			
  			//return the number of non leaves 
  			return numNonLeaf; 
	  }

  	}
}
