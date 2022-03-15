public class IntegerStack implements Stack {
	
	private int[] data;
	private int top;

	public IntegerStack(int size) {
		data = new int[size];
		top = -1;
	}
	
	public IntegerStack(int[] contents) {
		data = contents;
		top = contents.length-1;
	}
	
	public void push(int newValue) {
		if (top + 1 < data.length) {
			data[++top] = newValue;
		}
	}
	
	public int pop() {
		return data[top--];
	}

	public int top() {
		return data[top];
	}	
	
	public void popAll() {
		top = -1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	
	
	
	
	/////////////////
	// FOR TESTING //
	/////////////////
	public boolean equals (int[] arr) {
		return java.util.Arrays.equals(this.data, arr);
	}
	
}