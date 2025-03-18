package assignment2;

public class HDDAllocation {
    private final int[] hdds;
    private final int[] files;

    public HDDAllocation(int[] hdds, int[] files)
    {
        this.hdds = hdds;
        this.files = files;
    }

    public int[] generate_allocation() {
        // TODO: Task 1
        throw new RuntimeException("Not yet implemented!");
    }
    
    public static void main(String[] args) {
        int[] hdds = {1000, 1000, 2000};
        int[] files = {300, 200, 300, 1200, 400, 700, 700 };
        int[] allocation = new HDDAllocation(hdds, files).generate_allocation();
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
