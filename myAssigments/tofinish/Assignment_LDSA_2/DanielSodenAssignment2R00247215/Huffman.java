import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Huffman {

    BinaryTrie codeTrie;
    Map<Character, String> codeTable;

    public Huffman(String s) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1); // count the frequencies of each character in s
        }

        Collection<BinaryTrie> L = new LinkedList<BinaryTrie>();
        freq.forEach((c, f) -> {
            L.add(new BinaryTrie(c, f));
        }); // create a binary trie for each character
        HeapOfBinaryTries H = new HeapOfBinaryTries(
            L.toArray(new BinaryTrie[L.size()])
        ); // store these tries in the heap data structure

        codeTrie = findOptimalCode(H); // calculate the optimal Huffman code as binary trie
        codeTable = codeTrie.createCodeTable(); // calculate the code table to facilitate encoding
    }

    private static BinaryTrie findOptimalCode(HeapOfBinaryTries H) {
        /*
        This funciton takes in a heap of binary tries H in containing tries representing a single
        character

        It takes a heap as input that contains all code tries representing a single character occurring
        in the input string. The output should be a prefix code trie for all characters, calculated using
        the greedy Huffman algorithm by consecutively combining the lowest frequency partial
        codes until only a single code trie is left in the heap [1.5 points]. The implementation should
        use the heap data structure developed in the previous subtask to efficiently manage and
        identify which tries to merge and use the constructor

        */
        // TASK 3.B
        while (H.size() > 1) { // Loop until there is one BinaryTrie
            BinaryTrie x = H.extractMin(); // Get the minimum value ie left
            BinaryTrie y = H.extractMin(); // Y is the next minimum value ie right

            BinaryTrie z = new BinaryTrie(x, y); // New individual binary trie with x and y
            H.insert(z); // Insert This trie into the heap of binary Tries
        }
        return H.extractMin(); // Return the final min value
    }

    public void printCodeTable() {
        codeTable.forEach((c, b) -> {
            System.out.println("'" + c + "' -> " + b);
        });
    }

    public String encode(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(codeTable.get(c));
        }
        return sb.toString();
    }

    public String decode(String s) {
        BinaryTrie n = codeTrie;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '0') {
                n = n.getLeft();
            } else if (c == '1') {
                n = n.getRight();
            }
            if (n.getCharacter() != null) {
                sb.append(n.getCharacter());
                n = codeTrie;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s =
            "this is a small sentence to find the optimal binary code for";
        System.out.println(s);
        Huffman code = new Huffman(s);
        code.printCodeTable();
        String b = code.encode(s);
        System.out.println(b);
        String t = code.decode(b);
        System.out.println(t);
    }
}
