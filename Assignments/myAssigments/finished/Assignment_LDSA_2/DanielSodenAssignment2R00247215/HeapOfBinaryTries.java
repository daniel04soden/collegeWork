public class HeapOfBinaryTries {

    private BinaryTrie[] A;
    private int heapSize;

    private void heapify(int i) {

		/*
			This method takes in a node i and from here it gets the index of the
			left and right values in order to ensure that the data structure follows
			the rules of a heap

			params: int i; node i representing the parent, left and right values

			returns:
						None;
		*/
        int n = heapSize; // Reassigning n to heap size so it isn't going outside of scope
        while (true) {
            int l = (i * 2) + 1; // Left index
            int r = (i * 2) + 2; // Right index

            int smallest = i; // i itself is the smallest value

            if (l < n && A[l].compare(A[i])) { // if l is less than the size and the left value frequency is less than the i frequency
                smallest = l; // Means left is the smallest 
            }
							// Same logic as previously just for the right
            if (r < n && A[r].compare(A[smallest])) { 
                smallest = r;
            }
						
            if (smallest != i) { // If i isnt the smallest value
                BinaryTrie temp = A[i]; // Swap the binary tries of i and smallest
                A[i] = A[smallest];
                A[smallest] = temp;
                i = smallest;
            } else {
                break;
            }
        }
    }

    public HeapOfBinaryTries(BinaryTrie[] A) {  
				/*
					Constructor which makes up a heap of binary tries

					fields:
								Array A
								heapSize being the length of this array
				 */
        // TASK 3.A.b

        this.A = A; // Array A
        this.heapSize = A.length; // length of the array is the size of this heap
        for (int i = (heapSize / 2) - 1; i >= 0; i--) { // In descending order, ensure each value maintains the heap property 
            heapify(i); 
        }
    }

    public BinaryTrie extractMin() {
					/*
					 extractMin takes out the minimum binary trie in a Heap of binaryTries

					 params: None;

					 returns:
								BinaryTrie min; minimum value binary trie
					  */

				if (heapSize == 0) {
					throw new RuntimeException("Empty heap");
				}
        BinaryTrie min = A[0]; // The min binartrie is at the index 0 of A
        A[0] = A[heapSize - 1]; // A[0] is then assigned to the value at the top of the array
        heapSize--; // Reduce the heapSize (Essentially take it out)
        heapify(0);

        return min; // Returning this minimum value
    }

    public void insert(BinaryTrie x) {
        // TASK 3.A.d
				/*
				 This method inserts a new binary trie into the heap of binary tries
				

				params:
							BinaryTrie x; the binary trie being added to the heap

				 returns:
								None
				 * */


        heapSize++; // Increment the size of the heap 
        int i = heapSize - 1;
        while (i > 0 && x.compare(A[i / 2])) { // While i is greater than one and x is different to the  parent element
            A[i] = A[i / 2];
            i = i / 2;
        }
        A[i] = x; // A at the index i becomes the new binary trie x
    }

    public int size() {
		/*
			This method returns the size of the HeapOfBinaryTries

			params: None

			returns:
						heapSize; Current size of heap
		 */
        return heapSize;
    }
}
