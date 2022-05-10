import java.io.*;
import java.util.Arrays;
public class Project3 {
public static void main(String[] args) throws FileNotFoundException {
		int bulbs = 8; //column 
		int windows = 15; //row
		//getting the worst case
		int worst_case = drop(bulbs,windows);
		System.out.println("The Answer is: " + worst_case);
	}
	//Bulbs = columns
	//windows = rows
	//function initializes the array that the values will be saved to.
	public static int drop(int bulbs, int windows){
		//checking for the base cases
		if(bulbs == 1) {
			return windows;
		}
		if(windows == 1 || windows == 0) {
			return windows;
		}
		//initializing the array.
		//the first column will be  
		int row = ++windows; //getting the actual number of windows + the ground level.
		int col = bulbs;
		int[][] arr = new int[row][col];
		//filling the first 2 layers of the array. 
		for(int i = 0; i < col; i++){
			arr[0][i] = 0;
			arr[1][i] = 1;
		}
		//when you have only 1 egg to drop -> base case which is # windows
		for(int i = 1; i < row; i++){
			arr[i][0] = i;
		}
		for(int bulb = 1; bulb < col; bulb++){
			for(int win = 2; win < row; win++){
				int temp = drophlp(arr, bulb, win); //calling helper function to evaluate 1<= L <= f and return the min value 
				arr[win][bulb] = temp;
			}
		}
		//to print the array for debug
		//print(arr, row, col);
		
		//returning worst case max eggs at top floor
		return arr[row-1][col-1];
	}
	
	//row are windows
	//col are bulbs
	private static int drophlp(int[][] arr, int bulbs, int wind){
		int drops = Integer.MIN_VALUE; //to know that there was error
		int[] values = new int[wind]; //creating an array to store 1<= L <= f values and to find the min
		int valIndex = 0;
		for(int L = 1; L <= wind; L++){
			//System.out.println("L: " + L);
			int val1 = arr[L - 1][bulbs - 1]; //bulb broke
			int val2 = arr[wind - L][bulbs]; //bulb does not break
			values[valIndex] = Math.max(val1, val2); //put the max of into array
			valIndex++;
		}
		//print 1<= L <= f (values array) items for debug
//		for(int i = 0; i < valIndex;i++) {
//			System.out.print(values[i]);
//		}
//		System.out.println();
		drops = 1 + Arrays.stream(values).min().getAsInt();
		//System.out.println("Wind:  " + wind + " Drops: " + drops);
		return  drops;

	}

	//to debug the algorithm
	public static void print(int arr[][], int row, int col){
		System.out.print("bulbs->");
		for(int i = 1; i <= col; i++) {
			System.out.print("	" + i + " ");
		}
		System.out.println("\n-------------------------");
		for(int i = 0; i < row; i++){
			System.out.print("floor:");
			for(int j = 0; j < col; j++){
				System.out.print("	" + arr[i][j] + " "); 
			}
			System.out.println();
		}
		//System.out.println("floors /\\");
	}
}
