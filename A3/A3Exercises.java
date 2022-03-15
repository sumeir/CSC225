// Sumeir Khinda -- V00933760

public class A3Exercises {

	/*
	 * Purpose: Returns the sum of all integers found in s
	 * Parameters: IntegerStack s - the stack of integers
	 *             int n - the number of elements in s
	 * Returns: int - the sum of all integers found in s
	 * Post-Condition: s is not modified
	 */
	public static int sumStack (IntegerStack s, int n) {
		int sum = 0;
		IntegerStack temp = new IntegerStack(n);
		while (!s.isEmpty()) {
			temp.push(s.pop());
			sum += temp.top();
		}
		while (!temp.isEmpty()) {
			s.push(temp.pop());
		}
		return sum;	
	}
	
	/*
	 * Purpose: Determines whether the elements in s are sorted in ascending order
	 * Parameters: IntegerStack s - the stack of integers
	 *             int n - the number of elements in s
	 * Returns: boolean - true if elements are sorted in ascending order
	 * Post-Condition: s is not modified
	 */
	public static boolean isSorted (IntegerStack s, int n) {
		boolean sorted = true;
		IntegerStack temp = new IntegerStack(n);
		int len = 0;

		if (s.isEmpty()) {
			return sorted;
		} // empty stack -- sorted 

		// stack size 1 -- sorted
		while (!s.isEmpty()) {
			temp.push(s.pop());
			len++;
		}
		while (!temp.isEmpty()) {
			s.push(temp.pop());
		}
		if (len==1) {
			return sorted;
		}

		// len = size of s
		int[] tempArray = new int[len];

		// general case, when stack size >= 2
		for (int i=0; i<len; i++) {
			tempArray[i] = s.pop();
		}
		for (int i = len-1; i>0; i--) {
			s.push(tempArray[i]);
		}
		for (int i=1; i<len; i++) {
			if (tempArray[i-1] > tempArray[i]) {
				return false;
			} 
		}
		// no unsorted pair found -- return true
		return sorted;
	}
		
	/*
	 * Purpose: Merges two sorted stacks into a single stack
	 * Parameters: IntegerStack s1, IntegerStack s2 - the stacks to merge
	 *             int n1 - total number of elements to merge
	 * Returns: IntegerStack s - sorted stack containing elements from s1 and s2
	 * Pre-Condition: the elements in s1 and s2 are sorted in ascending order
	 * Post-Condition: None - s1 and s2 can be empty as their elements have been merged
	 */
	public static IntegerStack merge (IntegerStack s1, IntegerStack s2, int n) {
		IntegerStack merged = new IntegerStack(n);
		int[] mergedArray = new int[n];
		IntegerStack s1Stack = new IntegerStack(n);
		IntegerStack s2Stack = new IntegerStack(n);
		int s1Size = 0;
		int s2Size = 0;
		int max = 0;
		int temp = 0;


		if (s1.isEmpty() && s2.isEmpty()) {
			return merged;
		} // both stacks empty -- return empty stack

		if (s1.isEmpty()) {
			merged = s2;
			return merged;
		} // s1 empty, s2 already sorted, so we return s2

		if (s2.isEmpty()) {
			merged = s1;
			return merged;
		} // s2 empty, s1 already sorted, so we return s1

		while (!s1.isEmpty()) {
			s1Stack.push(s1.pop());
			s1Size++;
		}
		while (!s2.isEmpty()) {
			s2Stack.push(s2.pop());
			s2Size++;
		}
		for (int i=0; i<s1Size; i++) {
			mergedArray[i] = s1Stack.pop();
		}
		for (int i = s1Size; i<s1Size+s2Size; i++) {
			mergedArray[i] = s2Stack.pop();
		}

		// selection sort mergedArray by descending order
		for (int i = 0; i < n-1; i++) {
			max = i;
			for (int j = i+1; j < n; j++) {
				if (mergedArray[j]>mergedArray[max]) {
					max = j;
				}
			}
			temp = mergedArray[i];
			mergedArray[i] = mergedArray[max];
			mergedArray[max] = temp;
		}

		// populate merged stack from the descending ordered mergedArray
		// resulting merged stack is in ascending order, as required.
		for (int i = 0; i < n; i++) {
			merged.push(mergedArray[i]);
		}

		return merged;
	}
	
	/*
	 * Purpose: Determines the maximum number of elements that can be popped off of
	 *          stacks s1 and s2 with a sum less than or equal to maxSum
	 * Parameters: int maxSum - the maximum sum the popped elements can sum to 
	 *             IntegerStack s1, IntegerStack s2 - the stacks with the elements
	 *             int s1Size, int s2Size - total number of elements in s1 and s2, respectively
	 * Returns: int - the maximum number of elements that can be popped
	 * Pre-Condition: maxSum >= 0, all elements in s1 and s2 >= 0
	 * Post-Condition: None - s1 and s2 can be empty as their elements have been merged
	 *
	 * NOTE: There is an explanation and visualization of how this method should work
	 *       provided for you in the Assignment3.pdf on page 2.
	 */
    public static int countWithinThreshold(int maxSum, IntegerStack a, IntegerStack b, int aSize, int bSize) {
		int count = 0;
		int sum = 0;

		if (a.isEmpty() && b.isEmpty()) {
			return count;
		} // if both stacks are empty, return 0

		if (a.isEmpty()) {
			while (!b.isEmpty() && sum+b.top()<=maxSum) {
			sum += b.pop();
			count++;
			}
			return count;
		}// if a is empty, we can only pop from b
		if (b.isEmpty()) {
			while (!a.isEmpty() && sum+a.top()<=maxSum) {
			sum += a.pop();
			count++;
			}
			return count;
		}// if b is empty, we can only pop from a

		while (!a.isEmpty() && !b.isEmpty() && sum+a.top()<=maxSum && sum+b.top()<=maxSum) {
			if (a.top()<=b.top()) {
				sum += a.pop();
			} else {
				sum += b.pop();
			}
			count++;
		}
		while (!a.isEmpty() && sum+a.top()<=maxSum) {
			sum += a.pop();
			count++;
		}
		while (!b.isEmpty() && sum+b.top()<=maxSum) {
			sum += b.pop();
			count++;
		}

		return count;
    }   

}