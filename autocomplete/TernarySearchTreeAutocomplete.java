package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Ternary search tree (TST) implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TernarySearchTreeAutocomplete implements Autocomplete {
    /**
     * The overall root of the tree: the first character of the first autocompletion term added to this tree.
     */
    private Node overallRoot;

    /**
     * Constructs an empty instance.
     */
    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        List<CharSequence> term = new ArrayList<>();
        term.addAll(terms);
        overallRoot = insert(this.overallRoot, term, 0, 0);
    }

    private Node insert(Node overallRoot, List<CharSequence> term, int index, int index2) {
        char currentChar = term.get(index).toString().charAt(index2);
        int size = term.get(index).toString().length();

        // Insert parent node if tree is empty.
        if(overallRoot == null) {
            overallRoot = new Node(currentChar);
        }
        // Enters an entire word into tree.
        if(index2 < size) {
            if (currentChar < overallRoot.data) {
                overallRoot.left = insert(overallRoot.left, term, index, index2 + 1);
            } else if (currentChar > overallRoot.data) {
                overallRoot.right = insert(overallRoot.right, term, index, index2 + 1);
            }
        }
        else {
            // Enters the last character of word into tree.
            if(index + 1 < term.size()) {
                overallRoot.mid = insert(overallRoot.mid, term, index + 1, 0);
            }
            else {
                return overallRoot;
            }
        }
        return overallRoot;
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> matches = new ArrayList<>();
        return allMatches(overallRoot, matches, prefix, 0);
    }

    private List<CharSequence> allMatches(Node overallRoot, List<CharSequence> matches, CharSequence prefix, int index) {
        char currentChar = prefix.charAt(index);
        if(currentChar < overallRoot.data) {
            return allMatches(overallRoot.left,matches, prefix, index);
        }
        else if (currentChar > overallRoot.data){
            return allMatches(overallRoot.right, matches, prefix, index);
        }
        else {
            /*if(!overallRoot.isTerm) {
                // to do: add characters to a CharSequence arraylist using StringBuilder
                matches.add(overallRoot.data);
                return allMatches(overallRoot.mid, matches, prefix, index + 1);
            }
            else {*/
                return matches;
           //}
        }
    }

    /**
     * A search tree node representing a single character in an autocompletion term.
     */
    private static class Node {
        private final char data;
        private boolean isTerm;
        private Node left;
        private Node mid;
        private Node right;

        public Node(char data) {
            this.data = data;
            this.isTerm = false;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}
