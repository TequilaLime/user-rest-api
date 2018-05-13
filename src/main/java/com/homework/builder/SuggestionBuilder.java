package com.homework.builder;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class SuggestionBuilder {

    /**
     * The maximum amount of words which can be combined to a suggestion, can be changed to any positive value
     */
    private final static int MAX_COMBINED_TOKENS = 3;

    private static final String[] TOKENS = {"The", "beautiful", "girl", "from", "the", "farmers", "market", ".", "I", "like", "chewing", "gum", "."};

    private static final String[] ALL_VALID_TOKENS = {"beautiful", "girl", "from", "farmers", "market", "like", "chewing", "gum"};

    private static final String[] STOP_WORDS = {"is", "can", "the"};

    private static final class Suggestion {

        private final String text;

        public Suggestion(String text) {
            this.text = text;
        }

        public String toString() {
            return text;
        }
    }

    // Changed modifier to public for testing.
    public static Set<Suggestion> buildSuggestionsFromTokenFromString(Iterator<String> tokens, Set<String> stopWords) {

        // Implementation comes here
        // Please don't change the signature of this method
        Set<Suggestion> suggestions = new LinkedHashSet<>();
        String[] tokensForWordsCombo = new String[MAX_COMBINED_TOKENS];
        int wordsCounter = 0;

        while (tokens.hasNext()) {
            String token = tokens.next();
            // considered 'null' element as a stop word
            // if null or a single char or contains stop words.
            if (token == null || isSingleCharacter(token) || containsIgnoreCase(stopWords, token)) {
                //all elements in an array have to be combined(Combination) before clearing an array
                combineLeftOutWords(tokensForWordsCombo, suggestions);
                wordsCounter = 0;
                continue;
            }

            if (wordsCounter < MAX_COMBINED_TOKENS) {
                tokensForWordsCombo[wordsCounter] = token;
                combineWordsToSuggestions(tokensForWordsCombo, suggestions, wordsCounter);
            }
            if (wordsCounter >= MAX_COMBINED_TOKENS) {
                shiftToLeftByOneAndAddToken(tokensForWordsCombo, token);
                combineWordsToSuggestions(tokensForWordsCombo, suggestions, 0);
            }
            wordsCounter++;
        }

        //if any elements still left, the last actions of combinations has to be performed
        combineLeftOutWords(tokensForWordsCombo, suggestions);

        return suggestions;
    }

    /**
     * Takes an array and adds combinations with existings objects to set suggestions, and shifts array to empty state.
     *
     * @param array
     * @param suggestions
     */
    private static void combineLeftOutWords(String[] array, Set<Suggestion> suggestions) {
        while (!arrayIsEmpty(array)) {
            shiftToLeftByOneAndAddToken(array, null);
            combineWordsToSuggestions(array, suggestions, 0);
        }
    }

    /**
     * Checks if array has objects
     *
     * @param array
     * @return
     */
    private static boolean arrayIsEmpty(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Shifts elements to left by 1, drops out the element at index 0,
     * adds token to the end of the array.
     *
     * @param array
     * @param token
     */
    private static void shiftToLeftByOneAndAddToken(String[] array, String token) {
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = token;
    }

    /**
     * Adds recursively words combinations to set - suggestions. Element at index 0 is leading in combinations.
     */
    private static void combineWordsToSuggestions(String[] array, Set<Suggestion> suggestions, int lastIndex) {
        if (lastIndex == array.length) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= lastIndex; i++) {
            if (array[i] == null) {
                return;
            }
            sb.append(array[i]).append(" ");
        }
        if (!sb.toString().isEmpty()) {
            suggestions.add(new Suggestion(sb.toString().trim()));
        }

        combineWordsToSuggestions(array, suggestions, lastIndex + 1);
    }

    private static boolean isSingleCharacter(String str) {
        return str.length() == 1;
    }

    private static boolean containsIgnoreCase(Set<String> set, String str) {
        for (String stopWord : set) {
            if (str.equalsIgnoreCase(stopWord)) {
                return true;
            }
        }
        return false;
    }
}
