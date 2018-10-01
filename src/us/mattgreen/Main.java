package us.mattgreen;

import java.util.*;
import java.util.regex.Pattern;

public class Main
{

    private final static FileInput            indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map    = new HashMap<>();

    public static void main(String[] args)
    {
        String   line;
        String[] words;
        Object   wordFound;
        //   String[] fields;
        while ((line = indata.fileReadLine()) != null) {

            line = line.replace(",", "").replace(".", "")
                       .replace(";", "").replace(":", "")
                       .replace("'", "").replace("\"", "")
                       .replace("-", "").replace("!", "")
                       .replace("#", "").replace("(", "")
                       .replace(")", "").replace("?", "")
                       .replace("_", " ").replace("?", "")
                       .toLowerCase().trim();
            words = line.split(" ");
            for (String s : words) {
                wordFound = map.get(s);
                if (wordFound == null) {
                    map.put(s, 1);
                } else {
                    map.put(s, map.get(s) + 1);
                }

            }
        }

        int oneCount = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                oneCount++;
            }
            //System.out.println(entry.getKey() + " " + entry.getValue());
        }
        ArrayList<Map.Entry<String, Integer>> wordList = new ArrayList<>(map.entrySet());
        wordList.sort(Comparator.comparingInt(Map.Entry::getValue));
        Collections.reverse(wordList);

        //Collections.frequency(wordList, 1);
        List<Map.Entry<String, Integer>> top20 = wordList.subList(0, 20);
        for (int i = 0; i < top20.size(); i++) {
            Map.Entry<String, Integer> entry = top20.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());

        }

        System.out.println("Number of words with only one usage: " + oneCount);
    }

}