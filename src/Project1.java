import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
public class Project1 {
	public static void main(String[] args) throws FileNotFoundException
	{	
		galeShapley_alg(false); //men proposing
		System.out.println();
		galeShapley_alg(true); //woman are proposing
	}
	/*
	 * initializes m and w by reading from input1.txt; 
	 */
	public static void galeShapley_alg(boolean set) throws FileNotFoundException
	{
		Scanner file = new Scanner(new File("input1.txt")); //open the .txt file.
		//System.out.println(file.hasNext());
		int size = file.nextInt();
		int x = 0, y = 0;
		//filling out the men and woman arrays
		
		int[][] m = new int[size][size];
		int[][] w = new int[size][size];
		
		//while loop to fill the men array with their preferences
		while(x < size && file.hasNext())
		{			
			y = 0;
			//System.out.print("m"+(x+1) + " "); 
			while(y < size)
			{
				m[x][y] = file.nextInt();
				//System.out.print(" "+ m[x][y] + " ");
				y++;
			}
			x++;
		}
		x = 0; y = 0;
		//while loop to fill woman array with their preferences
		while(x < size && file.hasNext())
		{			
			y = 0;
			//System.out.print("w"+(x+1) + " ");
			while(y < size)
			{
				w[x][y] = file.nextInt();
				//System.out.print(" " + w[x][y] + " ");
				y++;
			}
			x++;
		}
		
		
		if(set) { //if men or woman are proposing true == woman, false == men
			System.out.println("Output when woman propose");
			int[] pair = galeShapley_alg_helper(w,m, size);
			//print the pairs of couples
			System.out.print("{");
			for(int i = 0; i < size; i++)
			{
				System.out.print("(" + (i+1) + "," + (pair[i])+ ")");
			}
			System.out.print("}");
		}
		else
		{
			System.out.println("Output when men propose");
			System.out.print("{");
			int[] pair = galeShapley_alg_helper(m,w, size);
			//print the pairs of couples
			for(int i = 0; i < size; i++)
			{
				System.out.print("(" + (i+1) + "," + (pair[i]) + ")");
			}
			System.out.print("}");
		}
	}
	/*
	 * the allgorithm implemented here, badly...
	 */
	public static int[] galeShapley_alg_helper(int[][] man, int[][] woman, int size)
	{
		int batchlor_pool = 0;
		boolean[] batchlor = new boolean[size];
		int[] pair = new int[size];
		for(int i = 0; i < size; i++) {
			pair[i] = -1;
			batchlor[i] = true;
		}
		while(batchlor_pool < size)
		{
			int m;
			for(m = 0; m < size; m++)
			{
				if(batchlor[m])
					break;
			}
			for(int i = 0; i < size && batchlor[m]; i++)
			{
				if(man[m][i] > 0) {
					int love = man[m][i];
					if(is_free(pair, size, love)) {
						pair[m] = love;
						batchlor[m] = false;
						batchlor_pool++;
						man[m][i] = 0;
					}
					else {
						if(jump_ship(pair, m, woman, size, love))
						{
							int old_husband = get_husband(love, pair, size);
							pair[m] = love;
							batchlor[m] = false;
							batchlor[old_husband] = true;
							man[m][i] = 0;
						}
					}	
				}
			}
		}
		return pair;
	}
	/*
	 * looks to see if w prefers m or m' be looking at who comes first w's preference list
	 */
	static boolean jump_ship(int[] pair, int home_wrecker, int[][] woman_, int size, int woman)
	{
		int husband = get_husband(woman, pair,size);
		for(int i = 0; i < size; i++)
		{
			if(woman_[woman][i] == home_wrecker)
				return true;
			if(woman_[woman][i] == husband)
				return false;
		}
		return false;
	}
	/*
	 * Gets the husband of the current marriage
	 */
	static public int get_husband(int woman, int[] pair, int size)
	{
		for(int i = 0; i < size; i++)
		{
			if(pair[i] == woman) {
				return (i+1);
			}
		}	
		return -1;
	}
	/*
	 * goes through the pairs too see if woman is free
	 */
	static boolean is_free(int[] pair, int size, int woman)
	{
		for(int i = 0; i < size; i++)
		{
			if(pair[i] == woman) {
				return false;
				}
		}
		return true;
	}
	
}