package assignment2;

import java.util.Arrays;

public class HDDAllocation {
	private final int[] hdds;
	private final int[] files;
	private int[] res;

	public HDDAllocation(int[] hdds, int[] files)
{
		this.hdds = hdds; // Hdds with set amount of space
		this.files = files; // Files to be put in these hard drives
		this.res = new int[files.length]; // New Result array, needed in constr for recursion
		for (int i = 0; i < files.length; i++) {
			res[i] = -1; // -1 denotes an unallocated file
		}
	}

	/**
		@param hddIdx

		Index of hard drive we are currently evaluating, ie if the file at file idx fits
		on this hard drive.
		This is achieved via summing up our current allocation (could be optimised through caching of some sort??)
		And checking if after adding this new allocation, is it still less than or equal to the hdd size

		@return boolean of whether we can allocate or not (if the list with the newly allocated file works)
	 */
	public boolean verify(int hddIdx){

		int allocation = 0; // Alloc as 0 so we can sum it when hdd met
		for (int i = 0; i < res.length; i++) {
			if (res[i] == hddIdx) { // Hdd eval met
				allocation += files[i]; // add file size of that index
			}
		}
		return allocation <= hdds[hddIdx]; // Boolean if fits 
	}

	/**
		@param fileIdx
		This function takes in a fileIdx, generally starting at 0 and from there 
		we look for the solution which occurs when we've hit the last file.

		After checking this we loop over our hard drives and follow the backtracking 
		steps of making a choice, evaluating the constraints (fitting into the hard drive)
		and if it does not fit those constraints, we backtrack by setting the res at that file 
		index back to -1
		
		This function makes use of recursion to check between each file and their different hard drive 
		fittings. When verifying if it fits our constraints, we recurse onto the next file index. If we can't find any 
		solution for that file to a particular hard drive, we return null 
		@return res
		*/

	public int[] generate_allocation(int fileIdx) {
		// Checking if solution reached
		if (fileIdx == files.length) { // if we've gone through it to the point that we're at the last file and its allocated
			System.out.println(Arrays.toString(res)); 			
			return res; // Finish and return final res ie the solution
		}
		// Making our choice
		for (int i = 0; i < hdds.length; i++) { //  Loop over hard drives on each recursive call
			res[fileIdx] = i; // Making our choice 
			if (verify(i)) { // If this choice is valid
				int[] ans = generate_allocation(fileIdx+1); // recurse on with ans
				if (ans != null ) { // If its not null continue
					return ans; // Return for call stack
				}
			}else{
				res[fileIdx] = -1; //  Backtracking/constraints
				System.out.println("Backtrack occured for HDD " + i + " at file " + files[fileIdx]); // Displaying when backtracking needed
			System.out.println(Arrays.toString(res)); 			
			}
		}
		return null;
	}

	public static void main(String[] args) {
		int[] hdds = {1000, 1000, 2000};
		int[] files = {300, 200, 300, 1200, 400, 700, 700};
		int[] allocation = new HDDAllocation(hdds, files).generate_allocation(0);
		for (int i=0; i<allocation.length; i++) {
			System.out.println("File "+i+" has size " + files[i] + "MB and goes on HDD"+allocation[i] + ".");
		}
		for (int j=0; j<hdds.length; j++)
	{
			int space_used = 0;
			for (int i=0; i<allocation.length; i++) {
				if (allocation[i]==j) {
					space_used += files[i];
				}
			}
			System.out.println("HDD"+ j + " space used " + space_used + "MB / " + hdds[j] + "MB.");
		}
	}
}
