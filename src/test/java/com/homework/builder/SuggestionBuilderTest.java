package com.homework.builder;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SuggestionBuilderTest {


    public static String[] STOP_WORDS = {"is", "can", "the"};

    @Test
    public void testBasicCaseFromExample() {
        String[] TOKENS = {"The", "beautiful", "girl", "from", "the", "farmers", "market", ".", "I", "like", "chewing", "gum", "."};

        List<String> listOfTokens = new ArrayList<String>(Arrays.asList(TOKENS));
        Set<String> stopWordsSet = new HashSet<String>(Arrays.asList(STOP_WORDS));

        //Just an output to terminal, for a visual representation
        System.out.println(SuggestionBuilder.buildSuggestionsFromTokenFromString(listOfTokens.iterator(), stopWordsSet));
        assertEquals(SuggestionBuilder.buildSuggestionsFromTokenFromString(listOfTokens.iterator(), stopWordsSet).size(), 15);

    }

    @Test
    public void testWithAdditionalWord() {
        String[] TOKENS = {"The", "absolutely", "beautiful", "girl", "from", "the", "farmers", "market", ".", "I", "like", "chewing", "gum", "."};

        List<String> listOfTokens = new ArrayList<String>(Arrays.asList(TOKENS));
        Set<String> stopWordsSet = new HashSet<String>(Arrays.asList(STOP_WORDS));

        System.out.println(SuggestionBuilder.buildSuggestionsFromTokenFromString(listOfTokens.iterator(), stopWordsSet));
        assertEquals(SuggestionBuilder.buildSuggestionsFromTokenFromString(listOfTokens.iterator(), stopWordsSet).size(), 18);
    }

    @Test
    public void testWhenAllWordsAreValidTokens() {
        String[] TOKENS = {"beautiful", "girl", "from", "farmers", "market", "like", "chewing", "gum"};

        List<String> listOfTokens = new ArrayList<String>(Arrays.asList(TOKENS));
        Set<String> stopWordsSet = new HashSet<String>(Arrays.asList(STOP_WORDS));

        System.out.println(SuggestionBuilder.buildSuggestionsFromTokenFromString(listOfTokens.iterator(), stopWordsSet));
        assertEquals(SuggestionBuilder.buildSuggestionsFromTokenFromString(listOfTokens.iterator(), stopWordsSet).size(), 21);
    }

    @Test
    public void testOnlyFourWords() {
        String[] TOKENS = {"beautiful", "girl", "from", "farmers"};

        List<String> listOfTokens = new ArrayList<String>(Arrays.asList(TOKENS));
        Set<String> stopWordsSet = new HashSet<String>(Arrays.asList(STOP_WORDS));

        System.out.println(SuggestionBuilder.buildSuggestionsFromTokenFromString(listOfTokens.iterator(), stopWordsSet));
        assertEquals(SuggestionBuilder.buildSuggestionsFromTokenFromString(listOfTokens.iterator(), stopWordsSet).size(), 9);
    }

    @Test
    public void testWithNullElement() {
        String[] TOKENS = {"beautiful", "girl", null, "from", "farmers"};

        List<String> listOfTokens = new ArrayList<String>(Arrays.asList(TOKENS));
        Set<String> stopWordsSet = new HashSet<String>(Arrays.asList(STOP_WORDS));

        System.out.println(SuggestionBuilder.buildSuggestionsFromTokenFromString(listOfTokens.iterator(), stopWordsSet));
        assertEquals(SuggestionBuilder.buildSuggestionsFromTokenFromString(listOfTokens.iterator(), stopWordsSet).size(), 6);
    }
}
