import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevenshtienDistanceTest {
    @Test
    void assertGetIndexClosestWord() {

        LevenshteinDistance ld = new LevenshteinDistance();
        String[] words = new String[]{"bananagrams", "bananas", "rabananan", "banana pudding", "cream o' banana"};
        List<String> wordList = new ArrayList<>(Arrays.asList(words));
        assertEquals(ld.getIndexClosestWord("banana", wordList), 1);
    }
}
