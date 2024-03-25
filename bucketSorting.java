import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class bucketSorting {

	public static void main(String[] args) {
		
		// integer arrays to pass into the bucket sort function
		int[] arr  = new int[] {4,55,22,12,13,90,85,81,3,44,22,99,5,33,42,49};
		int[] arr1 = new int[] {14,1,94, 9595, 23,4,5,6,7,88,120,11,1345,44,3094,50,6};
		int[] arr2 = new int[] {120, 11, 1345, 50, 6};
		int[] arr3 = new int[] {59,2,56,1,494,94,43,22,3,55,66,77,55,44,399,400,11,99};
		int[] arr4 = new int[] {199,90,88,85,83,77,78,73,72,71,44,55,21,12,88,33};
		int[] arr5 = new int[] {99,1,2,3,4,9,11,12,13,104,15,16,17,1,8,102,100,112,114,90,88,85,83,77,78,73,72,71,44,55,21,12,88,33,150,145,122,121,119,155};
		
		// Set bucket size
		int bucketSz = 10;
		
		// print unsorted array
		System.out.println("Unsorted Array: " + Arrays.toString(arr));

		//NOTE: the bucket size can be changed but 10 will work very well as a default number but it can be changed if needed.
		sortArray(arr, bucketSz);
		
		// print sorted array
		System.out.println("Sorted Array:   " + Arrays.toString(arr));
	}
	
	public static void sortArray(int[] arr, int bucketSize) {
//		check if there are any integers in the array and check bucket size
//		if so exit the function
		if(arr == null || arr.length == 0 || bucketSize < 0) {
			System.out.println("Array is empty or bucket size is negative.");
			return;
		}
		
//		determine the min and max value of the array
		int minVal = arr[0]; // set min value to an initial value
		int maxVal = arr[0]; // set max value to an initial value
		
		// use for-loop to pick out min and max values
		for(int i = 0; i <= arr.length - 1; i++) {
			if (arr[i] < minVal) {
				minVal = arr[i];
			} else if (arr[i] > maxVal) {
				maxVal = arr[i];
			}
		}
		// Calculate the range of the array to ensure that the array values go into
		// the correct bucket.
		
		// Explicitly type cast equation in parenthesis to double.
		// Adding one to the range to include all the values in the range, that is,
		// if the range of values were 1,2,3,4,5, and you wanted to know every 
		// number in the range as well as the range, then you would have to add 
		// 1 to the result of subtracting the top of the range with the bottom of 
		// the range: (5-1) = 4 + 1 = 5. 
		// The result will be the size of each bucket in terms of what numbers can
		// fit in each bucket. If the arr_range is 5 and there are 5 buckets, 
		// then the first bucket would hold numbers 0 to 5, the second bucket would 
		// hold 6 to 10, and so on and the last bucket would hold numbers between
		// 20 and 25.
		double arr_range = Math.ceil((double) (maxVal - minVal) + 1) / bucketSize;
		
		// Declare an array of integer lists. The List is an interface and needs 
		// to be instantiated with a class, which is the LinkedList class. The list is
		// instantiated as a LinkedList object that is an array, so the List, 'buckets'
		// is an array of  linked lists. 
		List<Integer>[] buckets = new LinkedList[bucketSize];
		
		// Create a new linked list for each bucket in the array 
		for(int i = 0; i < bucketSize; i++) {
			buckets[i] = new LinkedList<>();
		}
		// Iterate through the array to determine which bucket the value will go into.
		// Do this by creating an index value and then use that value 
		// to add the number into that index of the bucket.
		int bucketIndex;
		for(int num : arr) {
			bucketIndex = (int)((num - minVal) / arr_range);
			buckets[bucketIndex].add(num);
		}
		
		// Iterate through each linked list array and call an insertion sort 
		// function to sort each linked list array, but only pass non-empty arrays.
		
		for(List<Integer> bucket : buckets) {
			if(!bucket.isEmpty()) {
				insertionSort(bucket);
			}
		}
		
		// Once all linked list arrays have been sorted, iterated through all 
		// of the linked list arrays, assign each array value to 'value', 
		// and then insert this value into the original array. 
		int index = 0;
		for(List<Integer> bucket : buckets) {
			// 'bucket' is a linked list array. 'value' is assigned the integer
			// of the array as it is iterated through.
			for(int value : bucket) {
				arr[index] = value;
				index++;
			}
		}
}
	// Use insertion sort to sort the linked list arrays that are 
	// passed to the function.
	public static void insertionSort(List<Integer> ll_arr) {
		for(int i = 1; i < ll_arr.size(); i++) {
			int currentVal = ll_arr.get(i);
			int j = i - 1;
			
			// while the index, j, is not 0 AND the value at the linked list 
			// index of j is greater than the currentVal, change the value 
			// at the linked list array index of j+1 to the value at linked list
			// array index of j, then decrement j.
			while(j >= 0 && ll_arr.get(j) > currentVal) {
				// the linked list 'set' method inserts a value at a given
				// index of the linked list.
				ll_arr.set(j + 1, ll_arr.get(j));
				j--;
			}
			// change the value at linked list array index of j+1 to the currentVal
			ll_arr.set(j + 1, currentVal);
		}
	}

}
