import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class Project2 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		/*
		 * How the .txt works
		 * 2 5 -> the two is the ith position desired
		 * 		-> 5 is the size of the array getting put into the algorithm
		 */
		//Test arrays, use to test algortithms
		int TestQS[] = { 2, 6, 8, 4, 11, 16, 0, 20, 1, 100, 5, 12, 99, 7, 13, 44, 46, 45};
		int testMM[] = {2, 6, 8, 4, 11, 16, 0, 20, 1, 100, 5, 12, 99, 7, 13};

		//retriving values from file
		Scanner file = new Scanner(new File("input2.txt")); //open the .txt file.
		int index = file.nextInt();
		int size = file.nextInt();
		int qarr[] = new int[size]; //Array used for the quicksort Algorithm
		int mmarr[] = new int[size]; //Array used for the Medians of Medians Algorthm
		//while loop to fill the men array with their preferences
		int i = 0;
		while(i < size && file.hasNext()){
			int temp = file.nextInt(); 
			qarr[i] = temp;
			mmarr[i] = temp;
			i++;
		}
		//testing if arrays loaded in correctly
		// print(qarr, 0 , size);
		// print(mmarr, 0 , size);

		int val = Integer.MAX_VALUE;
		//Quick Sort Version
		val = QuickSortFindkth(qarr, index);
		System.out.println("QuickSort finds " + val + " .");		
		//Medians of Medians
		val = medianOfMed(mmarr ,0, size-1,index+1);
		System.out.println("MedOfMed finds " + val + " .");

		

	}
	public static int QuickSortFindkth(int arr[], int target) {
		if(target < arr.length && target >= 0) {
			QuickSort(arr, 0, arr.length-1); //calling QuickSort to sort the array
			int value = arr[target]; 
			print(arr, 0, arr.length); //print array to double check that it was correctly sorted
			return value;	
		}
		System.out.println("FAILED TO FINED VALUE");
		return -1;
	}
	
	public static void QuickSort(int arr[], int low, int high) {
		if(low <= high) {
			int pivot = partition(arr, low, high, arr[high]);
			QuickSort(arr, low, pivot-1);
			QuickSort(arr, pivot+1, high);
		}
	}

	private static void swap(int[] arr, int i, int j){
	    int temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
	}
	
	//swaps the elements around the target value passed in and returns the index of the target in the array
	//left side are smaller than target, right side larger than target
	private static int partition(int arr[], int floor, int ceiling, int target)
	{
		//the index of the target in the array
		int pivPos;
		//find the actual index of the target
		for(int i = floor; i < ceiling; i++) {
			if(target == arr[i]) {
				//place target at the end of the array.
				swap(arr, i, ceiling);
				break; //to not waist time
			}
		}
		//now we partition the array based based on the target
		pivPos = floor;
		for(int i = floor; i <= ceiling; i++) {
			if(arr[i] < target) {
				// if arr[i] is less that our target value, we swap it with pivPos, then increase pivPos.
				swap(arr, pivPos, i); 
				pivPos++;
			}
		}
		//now we put target at the middle of the array and return its position.
		swap(arr, pivPos, ceiling);
		return pivPos;
	}
	
	// write your code to select 3th element as median if you have 5 elements, 2nd element as median if you have 4 elements 
	// or 3 elements, 1st element as median if you have 2 elements or 1 element.
	// Ms. Lord email
	//Mediands of Mediands Algortihm
	public static int medianOfMed(int arr[], int start, int end, int k) {
		if(k > 0 && k <= (end - start)+1){
		//get the array size that is between start to end
		int arrSize = end - start + 1;
		//creating the medians array
		int []medians;
		//arrSize has a uniform pairs of 5 the median array will not include extrea spaces for groups of 4 or less
		if(arrSize%5 == 0) {
			medians = new int[arrSize / 5];
		}
		else {
			//will inlcude an extra space to include the last group that has 4 or less values
			medians = new int[ 1 + (arrSize) / 5];
		}
		int medIndex;
		//getting the medians of arr
		for(medIndex = 0; medIndex < arrSize/5; medIndex++) {
			medians[medIndex] = getMed(arr, start+medIndex*5, start+medIndex*5+5);
		}
		//check the last group that has 4 or less values and to gets its median is well
		if(medIndex*5 < arrSize) {
			medians[medIndex] = getMed(arr, start + medIndex*5,  (start + medIndex * 5 + arrSize%5));
			medIndex++;
		}
		int mm;
		//we have found the median to use as a pivot
		if(medIndex == 1){
			mm = medians[medIndex-1];
		}
		else{
			//havnt found the median of medians to use as a pivot, must call MedOfMed to get pivot
			mm = medianOfMed(medians, 0, medIndex-1, medIndex/2); 
		}
			// partition the array from start to end from the pivot given by median of medians
			int pivot = partition(arr, start, end, mm);
			//we have found the pivot and can return the value.
			if(k-1 == pivot - start){
				return arr[pivot];
			}
			if(k-1 < pivot - start){ // k is less than pivot. Must search to the right of pivot
				return medianOfMed(arr, start, pivot - 1, k);
			}
			else{// k is greater than pivot. Must search to the left of pivot
			return medianOfMed(arr, pivot + 1, end, (k - pivot + start -1) );
			}
		}
		System.out.println("FAILED TO FIND VALUE");
		return -1;
	}
	//a simple function to get the median
	public static int getMed(int arr[],int s,int f) {
		
		Arrays.sort(arr, s, f);
	    if(f - s == 2 || f - s == 4){
			// print(arr, s,f);
			// System.out.println(arr[(s+(f-s)/2)-1]);
			return arr[(s+(f-s)/2)-1];
		}
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
