package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Binary search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class BinarySearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> terms;

    /**
     * Constructs an empty instance.
     */
    public BinarySearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Replace with your code
        this.terms.addAll(terms);
        Collections.sort(this.terms, CharSequence::compare);
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        // TODO: Replace with your code
        List<CharSequence> matches = new ArrayList<>();
        int i = Collections.binarySearch(this.terms, prefix, CharSequence::compare);
        if(i >= 0) {
            for(int j = i; j < terms.size(); j++) {
                if(terms.get(i).toString().startsWith(prefix.toString())) {
                    matches.add(terms.get(i));
                }
            }
        }
        return matches;
        //throw new UnsupportedOperationException("Not implemented yet");
    }
}
