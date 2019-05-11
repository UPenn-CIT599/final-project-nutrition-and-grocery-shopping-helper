import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevenshteinDistance {
    public HashMap<Set<String>, Integer> previouslyComparedStings;

    public LevenshteinDistance() {
        previouslyComparedStings = new HashMap<>();
    }

    public int getIndexClosestWord(String targetWord, List<String> comparisonWords) {
        int minDistance = getDistance(comparisonWords.get(0), targetWord);
        int minWordIdx = 0;
        for (int i = 1; i < comparisonWords.size(); i++) {
            System.out.println("comparing " + comparisonWords.get(minWordIdx) + " with " + comparisonWords.get(i));
            int distance = getDistance(targetWord, comparisonWords.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                minWordIdx = i;
                System.out.println(comparisonWords.get(i) + " wins");
            } else {
                System.out.println(comparisonWords.get(minWordIdx) + " wins");
            }
        }
        return minWordIdx;
    }

    public int getDistance(String a, String b) {
        return calculate(a, b);
    }

    /** internal recursive implementation */
    private int calculate(String a, String b) {
        Set<String> hashedComparisonWords = new HashSet<>();
        hashedComparisonWords.add(a);
        hashedComparisonWords.add(b);
        // if memoized already, just return cached value
        if (previouslyComparedStings.containsKey(hashedComparisonWords)) {
            return previouslyComparedStings.get(hashedComparisonWords);
        }

        // else we have to compute distance
        // step 1, handle case where either are empty:
        // cost is remaining unprocessed letters
        if (a.isEmpty()) { return b.length(); }
        if (b.isEmpty()) { return a.length(); }

        // recursively try all three strategies
        // 1. change first letter in a
        // cost is 1 if you have to change a letter, else it's 0
        int cost = a.charAt(0) == b.charAt(0) ? 0 : 1;
        int substitutionStrategy = cost + calculate(a.substring(1), b.substring(1));

        // 2. insert character in a to match the first letter in b
        // a's length stays the same while we look at the next letter in b
        int insertionStrategy = 1 + calculate(a, b.substring(1));

        // 3. delete first character in a
        // b's length stays the same while we look at the next letter in a
        int deletionStrategy = 1 + calculate(a.substring(1), b);

        // return minimum of recursive costs
        int totalCost = Math.min(substitutionStrategy, Math.min(insertionStrategy, deletionStrategy));
        previouslyComparedStings.put(hashedComparisonWords, totalCost);
        return totalCost;
    }
}
