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
		int arr3[] = {2, 6, 8, 4, 11, 16, 0, 20, 1, 100, 5, 12, 99, 7, 13, 44, 46, 45};
		int index = 17;
		//Quick Sort Version
		QuickSortFindkth(arr, index);
		System.out.println("\n");
		print(arr, 0 ,arr.length);
		//Medians of Medians
		int val = medianOfMed(arr3 ,0, arr3.length-1,index+1);
		//int val = kthSmallest(arr3 ,0, arr3.length-1,index+1);
		System.out.println("The Value is: " + val + "\nAt index: " + index);
		

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
				break;
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
		if(k > 0 && k <= (end - start)+1){
		int arrSize = end - start + 1;
		int []medians = new int[(4+arrSize) / 5];
		int medIndex;
		for(medIndex = 0; medIndex < arrSize/5; medIndex++) {
			medians[medIndex] = getMed(arr, start+medIndex*5, start+medIndex*5+5);
		}
		if(medIndex*5 < arrSize) {
			medians[medIndex] = getMed(arr, start + medIndex*5,  (start + medIndex * 5 + arrSize%5));
			medIndex++;
		}
		int mm;
		if(medIndex == 1){
			mm = medians[medIndex-1];
		}
		else{
			mm = medianOfMed(medians, 0, medIndex-1, medIndex/2); 
		}
			int pivot = partition(arr, start, end, mm);
			if(k-1 == pivot - start){
				return arr[pivot];
			}
			if(k-1 < pivot - start){
				return medianOfMed(arr, start, pivot - 1, k);
			}
			else{
				return medianOfMed(arr, pivot + 1, end, (k - pivot + start -1) );
			}
		}
		System.out.println("FAILED");
		return Integer.MAX_VALUE;
	}
//	-----------------------------------------------------------------------------------------------
	
	
	
	public static int getMed(int arr[],int s,int f) {
		 Arrays.sort(arr, s, f);
	        return arr[s+(f-s)/2];    
	}
	
	//A print method to help debug the algorithm
	public static void print(int arr[], int i, int j) {
		for (int a = i; a < j; a++) {
			System.out.print(arr[a] + " ");
		}
		System.out.println();
		for (int a = i; a < j; a++) {
			System.out.print(a + " ");
		}
		System.out.println("\n");
	}
}
