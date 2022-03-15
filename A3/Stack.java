interface Stack {
	
	/*
	 * Purpose: Insert an item onto the top of the stack
	 * Parameters: int newValue - the item to insert 
	 * Returns: Nothing
	 */
	public void push(int newValue); 
	
	/*
	 * Purpose: Removes and returns the top item from the stack
	 * Parameters: None
	 * Returns: int - the data value of the element removed
	 */
	public int pop();
	
	/*
	 * Purpose: Determines whether the stack is empty
	 * Parameters: None
	 * Returns: boolean - true if the stack is empty, false otherwise
	 */
	public boolean isEmpty();
		
	/*
	 * Purpose: Accesses the top item on the stack
	 * Parameters: None
	 * Returns: int - the data value of the top element
	 */
	public int top();
	
	/*
	 * Purpose: Removes all elements from the stack 
	 * Parameters: None
	 * Returns: Nothing
	 */
	public void popAll();

}