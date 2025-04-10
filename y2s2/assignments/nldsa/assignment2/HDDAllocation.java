package assignment2;


public class HDDAllocation {
	private final int[] hdds;
	private final int[] files;
	private int[] res;

	public HDDAllocation(int[] hdds, int[] files)
{
		this.hdds = hdds;
		this.files = files;
		this.res = new int[files.length];
		for (int i = 0; i < files.length; i++) {
			res[i] = -1; // -1 denotes an unallocated file...
		}
	}

	public boolean verify(int hddIdx){
		int allocation = 0;
		for (int i = 0; i < res.length; i++) {
			if (res[i] == hddIdx) {
				allocation += files[i];
			}
		}
        return allocation <= hdds[hddIdx];
    }

	public int[] generate_allocation(int fileIdx) {
		// Checking if solution reached
		 if (fileIdx == files.length) {
			return res;
		}
		// Making our choice
        for (int i = 0; i < hdds.length; i++) {
                res[fileIdx] = i;
                if (verify( i)) {
					int[] penis = generate_allocation(fileIdx+1);
					if (penis != null ) {
						return penis;
					}
                }else{
					res[fileIdx] = -1; //  Backtracking/constraints
					System.out.println("Epic backtrack");
				}
        }
        return null;
	}

	public static void main(String[] args) {
		int[] hdds = {1000, 1000, 2000};
		int[] files = {300, 200, 300, 1200, 400, 700, 700 };
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
