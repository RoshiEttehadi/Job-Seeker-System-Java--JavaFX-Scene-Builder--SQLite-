package com.groupi.groupi2;

public class JobSearch {
    // Uses Levenshtein Edit Distance algorithm to find a weighted score of similarity between two arbitrary strings.
    // Used for comparing job title and description to user input.
    public static double getSimilarity(String firstString, String secondString) {
        String longer = firstString, shorter = secondString;
        if (firstString.length() < secondString.length()) { // longer string should always have the greater length
            longer = secondString; shorter = firstString;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; } // both strings are empty
        return (longerLength - levenshteinEditDistanceCalculation(longer, shorter)) / (double) longerLength;
    }

    // Levenshtein Edit Distance algorithm
    public static int levenshteinEditDistanceCalculation(String firstString, String secondString) {
        firstString = firstString.toLowerCase();
        secondString = secondString.toLowerCase();

        int[] costs = new int[secondString.length() + 1];
        for (int i = 0; i <= firstString.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= secondString.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (firstString.charAt(i - 1) != secondString.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[secondString.length()] = lastValue;
        }
        return costs[secondString.length()];
    }
}
