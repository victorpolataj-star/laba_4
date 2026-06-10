package core;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    public String[] processPipeline(String[] inputLines) {
        List<Integer> numbers = new ArrayList<>();

        for (String s : inputLines) {
            try {
                numbers.add(Integer.parseInt(s.trim()));
            } catch (Exception e) {
                // Skip invalid numbers
            }
        }

        List<Integer> negative = new ArrayList<>();
        List<Integer> positive = new ArrayList<>();

        for (int n : numbers) {
            if (n < 0) {
                negative.add(n);
            } else {
                positive.add(n);
            }
        }

        negative.addAll(positive);

        String[] result = new String[negative.size()];
        for (int i = 0; i < negative.size(); i++) {
            result[i] = String.valueOf(negative.get(i));
        }

        return result;
    }
}