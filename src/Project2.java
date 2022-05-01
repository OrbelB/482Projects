import java.io.*;
import java.util.Arrays;
public class Project2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * How the .txt works
		 * 2 5 -> the two is the ith position desired
		 * 		-> 5 is the size of the array getting put into the algorithm
		 */
		int arr[] = { 2, 6, 8, 4, 11, 16, 0, 20, 1, 100, 5, 12, 99, 7, 13, 44, 46, 45};
		int arr2[] = {0, 1, 2, 3, 4, 0, 1, 2, 3, 4 , 0, 1, 7, 8, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4};
//		medianOfMed(arr, 0, arr.length, 0);
		int arr3[] = { 2, 6, 8, 4, 11, 16, 0, 20, 1, 100, 5, 12, 99, 7, 13, 44, 46, 45};
		Arrays.sort(arr);
		System.out.println("The Target Value is: " + arr[5]);
		QuickSortFindkth(arr, 5);
		print(arr, 0 ,arr.length);
	}
	public static int QuickSortFindkth(int arr[], int target) {
		QuickSort(arr, 0, arr.length-1);
		int value = arr[target];
		print(arr, 0, arr.length);
		System.out.println("The Target is: " + target);
		System.out.println("The Target Value is: " + value);
		return value;
	}
	/*
	 * A recursive function with 3 argumetns -> arr[], low, and high
	 */
	public static void QuickSort(int arr[], int low, int high) {
		if(low <= high) {
			int pivot = partition(arr, low, high, arr[high]);
			QuickSort(arr, low, pivot-1);
			QuickSort(arr, pivot+1, high);
		}
	}

	private static void swap(int[] arr, int i, int j){
		// Hello World
	    int temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
	}
	
	//swaps the elements around the pivot value passed in and returns the index of the pivot in the array

	private static int partition(int arr[], int floor, int ceiling, int target)
	{
		//the index of the pivot in the array
		int pivPos;
		//find the actual index of the target
		for(int i = floor; i < ceiling; i++) {
			if(target == arr[i]) {
				//place target at the end of the array.
				swap(arr, i, ceiling);
			}
		}
		//now we partition the array based on our pivot
		pivPos = floor;
		for(int i = floor; i <= ceiling; i++) {
			if(arr[i] < target) {
//				if arr[i] is less that our target value, we swap it with pivPos (a higher value), then increase pivPos.
				swap(arr, pivPos, i); 
				pivPos++;
			}
		}
		//now we put target at the middle of the array and return its position.
		swap(arr, pivPos, ceiling);
		return pivPos;
	}
	
//	write your code to select 3th element as median if you have 5 elements, 2nd element as median if you have 4 elements 
//	or 3 elements, 1st element as median if you have 2 elements or 1 element.
//	Ms. Lord email
	
	public static int medianOfMed(int arr[], int start, int end, int k) {
		if(end - start > 1 && k <= (end - start)-1){
			//to get the number of values in the array
		System.out.println(start);
		System.out.println(end);
		int arrSize = end - start;
		//creating a separate array to hold the medians of arr. 
		//median arr is given extra + 1 capacity for when there is a non 5 group of numbers. \int []medians
		int []medians;
		if(arr.length%5 != 0){
			medians = new int[(1+end / 5)];
		}
		else{
			medians = new int[(end / 5)];
		}
		
		int medIndex;
		//filling the medians array with medians of arr
		for(medIndex = 0; medIndex < arrSize/5; medIndex++) {
			// System.out.println("The Start is: " + (start+medIndex*5) + " --> " + "Ending at " + (start+medIndex*5+5));
			medians[medIndex] = getMed(arr, start+medIndex*5, start+medIndex*5+5);
		}
		//to get the median of the last remaining 1 - 4 elements.
		if(medIndex*5 < arrSize) {
			// System.out.println("The Starting index is: " + (start + medIndex)*5);
			// System.out.println("arrSize is = " + arrSize); 
			// System.out.println("arrSize%5 = " + (arrSize%5)); 
			// System.out.println("The Ending index is: " + ((start + medIndex * 5 + arrSize%5)));		
			medians[medIndex] = getMed(arr, (start + medIndex)*5,  (start + medIndex * 5 + arrSize%5));
			medIndex++;
		}

		//finding the median of medians
		//print(arr);
		System.out.println("the Median arr is: ");
//		print(medians);
		int mm;
		if(medians.length == 1){
			mm = medians[medIndex-1];
		}
		else{
			mm = medianOfMed(medians, 0, medIndex, medIndex/2); 
		}
			int pivot = partition(medians, 0, medians.length, mm);
		}
		return 0;
	}
	
	public static int getMed(int arr[],int s,int f) {
		Arrays.sort(arr, s, f);
		//If s -> f have less than 5 variables getMid get the second element of a group of 4s 
		// Or first element of a group of 2
		if(arr.length - (s) == 2 || arr.length - (s) == 4) {
			// System.out.println("Less than 5 values " + (arr.length - (s)));
			return arr[(s+(f-s)/2)- 1];
		}
		else {
			// System.out.println("The getMed is: "+ arr[s+(f-s)/2] + "\n");
			return arr[s+(f-s)/2];	
		}
	}
	
	//A print method to help debug the algorithm
	public static void print(int arr[], int i, int j) {
		for (int a = i; a < j; a++) {
			System.out.print(arr[a] + " ");
		}
		System.out.println("\n");
	}
}
