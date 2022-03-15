import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;

public class A3Tester {

	private static int testPassCount = 0;
	private static int testCount = 0;
	
	public static void main(String[] args) {
		testStackOperations();
		testSumStack();
		testIsSorted();
		testMerge();
		testCountWithinThreshold();
		
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}
	
	public static void populateStack(IntegerStack s, int[] values) {
		for (int i = 0; i < values.length; i++) {
			s.push(values[i]);
		}
	}
	
	public static void testStackOperations() {
		System.out.println("Stack Operations Tests:");
		
		int result = 0;
		int smallStackSize = 2;
		int bigStackSize = 100;
		IntegerStack s1 = new IntegerStack(smallStackSize); 
		IntegerStack s2 = new IntegerStack(bigStackSize);
			
		displayResults(s1.isEmpty(), "stack initially empty");
		
		s1.push(2);
		displayResults(!s1.isEmpty(), "stack no longer empty");
		
		result = s1.top();
		displayResults(result==2, "top works after initial push");
		
		s1.push(5);
		result = s1.top();
		displayResults(!s1.isEmpty(), "stack still not empty");
		displayResults(result==5, "top works after second push");
		
		result = s1.pop();
		displayResults(!s1.isEmpty(), "stack still not empty");
		displayResults(result==5, "correct value returned from pop");
		result = s1.top();
		displayResults(result==2, "top works after pop");
		
		result = s1.pop();
		displayResults(s1.isEmpty(), "last element removed from stack, now empty");
		displayResults(result==2, "correct value returned from pop");
		
		s1.push(9);
		result = s1.top();
		displayResults(!s1.isEmpty(), "stack is no longer empty");
		displayResults(result==9, "correct value returned from top");		
		
		
		for (int i = 1; i <= bigStackSize; i++) {
			try{
				s2.push(i);
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		displayResults(!s2.isEmpty(), "stack is no longer empty");
		displayResults(s2.pop()==bigStackSize, "checking popped element values");
		
		boolean correctValuesPopped = true;
		for (int i=bigStackSize-1; i>0; i--) {
			if (s2.pop() != i) {
				correctValuesPopped = false;
			}
		}
		displayResults(correctValuesPopped, "popped all "+bigStackSize+" values correctly from stack");
		displayResults(s2.isEmpty(), "stack is now empty");
		
		
		int[] nums = {8, 1, 6, 3, 2, 4};
		IntegerStack s3 = new IntegerStack(nums);
		
		displayResults(!s3.isEmpty(), "stack is not empty");
		
		correctValuesPopped = true;
		for (int i = nums.length-1; i >= 0; i--) {
			if (s3.pop() != nums[i]) {
				correctValuesPopped = false;
			}
		}
		displayResults(correctValuesPopped, "popped all "+nums.length+" values correctly from stack");
		displayResults(s3.isEmpty(), "stack is now empty");
		
		System.out.println("Finished stack operations tests\n");
	}
	
	public static void testSumStack() {
		System.out.println("sumStack Tests:");
		int[] s2Values = {1, 2, 3, 4};
		int[] s3Values = {11, 12, 13, 14, 15, 16, 17, 18};
		IntegerStack s1 = new IntegerStack(0);
		IntegerStack s2 = new IntegerStack(s2Values.length);
		IntegerStack s3 = new IntegerStack(s3Values.length);

		int result = 0;
		int expected = 0;
		
		result = A3Exercises.sumStack(s1, 0);
		displayResults(result==0, "sum empty stack");
		
		populateStack(s2, s2Values);
		result = A3Exercises.sumStack(s2, s2Values.length);
		expected = 10;
		displayResults(result==expected, "sumStack with elements 1 to 4, calculate sum");
		displayResults(s2.equals(s2Values), "sumStack with elements 1 to 4, stack unchanged");
		
		populateStack(s3, s3Values);
		result = A3Exercises.sumStack(s3, s3Values.length);
		expected = 11+12+13+14+15+16+17+18;
		displayResults(result==expected, "sumStack with elements 11 to 18, calculate sum");
		displayResults(s3.equals(s3Values), "sumStack with elements 11 to 18, stack unchanged");
		
		System.out.println("Finished sumStack tests\n");
	}
	
	
	public static void testIsSorted() {
		System.out.println("isSorted Tests:");
		int[] s2Values = {4, 3, 2, 1};
		int[] s3Values = {6, 5, 10, 3, 2};

		IntegerStack s1 = new IntegerStack(0);
		IntegerStack s2 = new IntegerStack(s2Values.length);
		IntegerStack s3 = new IntegerStack(s3Values.length);
		
		boolean result = false;
		
		result = A3Exercises.isSorted(s1, 0);
		displayResults(result==true, "isSorted - empty stack");
		
		populateStack(s2, s2Values);
		result = A3Exercises.isSorted(s2, s2Values.length);
		displayResults(result==true, "isSorted - {1, 2, 3, 4}");
		displayResults(s2.equals(s2Values), "isSorted - stack unchanged");
		
		populateStack(s3, s3Values);
		result = A3Exercises.isSorted(s3, s3Values.length);
		displayResults(result==false, "isSorted - {6, 5, 10, 3, 2}");
		displayResults(s2.equals(s2Values), "isSorted - stack unchanged");
		
		System.out.println("Finished isSorted tests\n");		
	}
	
	public static void testMerge() {
		System.out.println("merge Tests:");
		int[] s3Values = {14, 8, 7, 3};
		int[] s4Values = {17, 13, 12, 11, 10, 4, 2, 1};
		int[] merged = {17, 14, 13, 12, 11, 10, 8, 7, 4, 3, 2, 1};
		IntegerStack s1 = new IntegerStack(0);
		IntegerStack s2 = new IntegerStack(0);
		IntegerStack s3 = new IntegerStack(s3Values.length);
		IntegerStack s4 = new IntegerStack(s4Values.length);
		populateStack(s3, s3Values);
		populateStack(s4, s4Values);
		
		IntegerStack result = A3Exercises.merge(s1, s2, s3Values.length);
		displayResults(result.isEmpty(), "merge empty stack");
		
		result = A3Exercises.merge(s1, s3, s3Values.length);
		displayResults(result.equals(s3Values), "merge s3 with empty stack");
		
		populateStack(s3, s3Values);
		result = A3Exercises.merge(s3, s2, s3Values.length);
		displayResults(result.equals(s3Values), "merge empty stack with s3");
		
		populateStack(s3, s3Values);
		result = A3Exercises.merge(s3, s4, (s3Values.length+s4Values.length));
		displayResults(result.equals(merged), "merge two populated stacks");
		
		System.out.println("Finished merge tests\n");
	}
	
	public static void testCountWithinThreshold() {
		System.out.println("countWithinThreshold Tests:");
		
		int[] test1 = {15, 13, 3, 4, 8};
		int test1Max = 15;
		int test1Expected = 3;

		int[] test2A = {1, 6, 4, 2, 4};
		int[] test2B = {5, 8, 1, 2};
		int test2Max = 10;
		int test2Expected = 4;
		
		int[] test3A = {3, 1, 1, 1, 1, 5, 1, 2, 4, 1, 1, 2, 1, 3};
		int[] test3B = {1, 1, 1, 2, 2, 3, 1, 1, 1, 1, 2};
		int test3Max1 = 10;
		int test3Max2 = 15;
		int test3Max3 = 20;
		int test3Expected1 = 7;
		int test3Expected2 = 10;
		int test3Expected3 = 13;
			
		IntegerStack s0 = new IntegerStack(0);
		IntegerStack s1 = new IntegerStack(test1.length);
		IntegerStack s2A = new IntegerStack(test2A.length);
		IntegerStack s2B = new IntegerStack(test2B.length);
		IntegerStack s3A = new IntegerStack(test3A.length);
		IntegerStack s3B = new IntegerStack(test3B.length);
		
		populateStack(s1, test1);
		populateStack(s2A, test2A);
		populateStack(s2B, test2B);
				
		int result = 0;
		
		result = A3Exercises.countWithinThreshold(test1Max, s0, s1, 0, test1.length);
		// System.out.println(result);	// for debugging
		displayResults(result==test1Expected, "testing with an empty stack and s1");
		
		s1 = new IntegerStack(test1.length);
		populateStack(s1, test1);
		result = A3Exercises.countWithinThreshold(test1Max, s1, s0, test1.length, 0);
		// System.out.println(result);	// for debugging
		displayResults(result==test1Expected, "testing with s1 and an empty stack");

		result = A3Exercises.countWithinThreshold(test2Max, s2A, s2B, test2A.length, test2B.length);
		// System.out.println(result);	// for debugging
		displayResults(result==test2Expected, "testing with s2A and s2B");

		s3A = new IntegerStack(test3A.length);
		populateStack(s3A, test3A);
		s3B = new IntegerStack(test3B.length);
		populateStack(s3B, test3B);
		result = A3Exercises.countWithinThreshold(test3Max1, s3A, s3B, test3A.length, test3B.length);
		// System.out.println(result);	// for debugging
		displayResults(result==test3Expected1, "testing with s3A and s3B with maxValue " + test3Max1);

		s3A = new IntegerStack(test3A.length);
		populateStack(s3A, test3A);
		s3B = new IntegerStack(test3B.length);
		populateStack(s3B, test3B);
		result = A3Exercises.countWithinThreshold(test3Max2, s3A, s3B, test3A.length, test3B.length);
		// System.out.println(result);	// for debugging
		displayResults(result==test3Expected2, "testing with s3A and s3B with maxValue " + test3Max2);
		
		s3A = new IntegerStack(test3A.length);
		populateStack(s3A, test3A);
		s3B = new IntegerStack(test3B.length);
		populateStack(s3B, test3B);
		result = A3Exercises.countWithinThreshold(test3Max3, s3A, s3B, test3A.length, test3B.length);
		System.out.println(result);	// for debugging
		displayResults(result==test3Expected3, "testing with s3A and s3B with maxValue " + test3Max3);


		// custom tests

		int[] test4A = {1,1,1,1,1,1,1,1,1};
		int[] test4B = {2,3};
		int test4Max = 5;
		int test4Expected = 5;


		IntegerStack s4A = new IntegerStack(test4A.length);
		IntegerStack s4B = new IntegerStack(test4B.length);
		populateStack(s4A, test4A);
		populateStack(s4B, test4B);
		result = A3Exercises.countWithinThreshold(test4Max, s4A, s4B, test4A.length, test4B.length);
		displayResults(result==test4Expected, "testing with s4A and s4B with maxValue " + test4Max);
		System.out.println(result);	// for debugging

		
		System.out.println("Finished countWithinThreshold tests\n");
	}
		
	public static void displayResults (boolean passed, String testName) {
       /* There is some magic going on here getting the line number
        * Borrowed from:
        * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
        */
        
        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testName);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
	
}