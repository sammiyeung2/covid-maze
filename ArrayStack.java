/**
 * This class represents a Stack implemented with an array data structure.
 * Each ArrayStack has its first element added to the end of the array and new items added to the left of existing items.
 * @author sammi
 *
 * @param <T>
 */
public class ArrayStack<T> implements StackADT<T> {
	
	// create instance variable of generic type to hold the arraystack
	private T[] array; 
	// create instance variable to remember the index of top (the first space available to put the next item)
	private int top;
	
	/**
	 * Constructor creates an ArrayStack with a default capacity of 10 slots.
	 */
	public ArrayStack() {
		// initialize the array with 10 slots
		array = (T[])(new Object[10]);
		// set the top index value to the end of the array
		top = 9;
		
	}

	/**
	 * Constructor overloads the one above and allows user to enter the array's capacity.
	 * @param N the number of slots the array should have
	 */
	public ArrayStack (int N) {
		// initialize the array with the desired number of slots (N)
		array = (T[])(new Object[N]);
		// set the top index value to the end of the array
		top = N-1;
	}
	
	/**
	 * Method to add an element to the top of the stack and update top.
	 * @param element the element to add to the top
	 */
	public void push(T element) {
		// if the stack is full
		if (top < 0) {
			// add 5 new slots to the stack
			expandCapacity();
		}
		
		// add the given element to the top of the stack
		array[top] = element;
		// decrement top (move the index one to the left)
		top--;
	}
	
	/**
	 * Private helper method to expand the capacity of the array if it is full
	 */
	private void expandCapacity() {
		// create a temporary array that has 5 more spaces than the original array
		T[] larger = (T[])(new Object[array.length + 5]);

		// loop through all the elements in the existing array
		for (int index = array.length - 1; index >= 0; index--)
			// copy over elements from the original array to the right end of the temporary, larger array
			larger[index + 5] = array[index];

		// update top 
		top += 5; 
		
		// make the original array equal to the temporary, larger array
		array = larger;
	}
	
	/**
	 * Method to remove and return the element from the top of the stack and update top.
	 */
	public T pop() throws StackException {
		
		// if we are trying to pop from an empty stack, throw a StackException
		if (isEmpty()) {
			throw new StackException("Empty stack");
		}
		
		// update the top value to be one to the right (because we have one more available slot now)
		top++;
		// hold the value of the element at top
		T topItem = array[top];
		// remove the element
		array[top] = null;
		// return the value of the element from the top of the stack
		return topItem;
	}
	
	/**
	 * Method to return (without removing) the element from the top of the stack and update top
	 */
	public T peek() throws StackException {
		
		// throw a StackException if trying to peek from an empty stack
		if (isEmpty()) {
			throw new StackException("Empty stack");
		}
		
		// return the element from the top of the stack
		return array[top+1];
	}
	
	/**
	 * Boolean method to check if the stack is empty
	 */
	public boolean isEmpty() {
		// Return true if the stack contains 0 elements (is empty); otherwise return false
		return (top == array.length - 1);
	}
	
	/**
	 * Method to return the number of elements in the stack (not necessarily the same as capacity of the stack)
	 */
	public int size() {
		// create a counter and set it to 0
		int count = 0;
		
		// loop through the array 
		for (int i=0; i < array.length; i++) {
			if (array[i] != null) {
				// count any filled spots
				count++;
			}
		}
		
		// return the counter of number of elements in the stack
		return count;
	}
	
	/**
	 * Accessor method to get the length/capacity of the array
	 * @return
	 */
	public int getLength() {
		// return the array's capacity
		return array.length;
	}
	
	/**
	 * Accessor method to get the top index of the array
	 * @return
	 */
	public int getTop() {
		// return the index of top (first space to put the next item)
		return top;
	}
	
	/**
	 * Method to build a string with the stack's elements
	 */
	public String toString() {
		
		// if the stack is empty, return the message that "The stack is empty."
		if (isEmpty()) {
			return "The stack is empty.";
		}
		// otherwise if the stack isn't empty
		else {
			// make a new string that starts with "Stack: "
			String s = "Stack: ";
			// loop through the array
			for (int i = top + 1; i < array.length; i++) {
				// add each element to the string
				s += array[i];
				// if it's not the last element, add a comma and space
				if (i < array.length - 1) {
					s += ", ";
				}
				// if it's the last element, add a period instead
				else {
					s += ".";
				}
			}
			
			// return the string
			return s;
			
		}
	}
	
}
