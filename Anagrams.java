/**
 * Created by keshavberiwala on 11/29/17.
 */
import java.io.*;
import java.util.*;


public class Anagrams {
    String pathToDict;
    HashMap<String, HashMap<Character, Integer>> frequencies;
    List<String> allWords;

    public Anagrams(String path) {
        pathToDict = path;
        frequencies = new HashMap<>();
        allWords = new ArrayList<>();
        initializeOffline();

    }


    //offline learning from the dictionary, updating the HashMap frequencies and List allWords.
    public void initializeOffline() {
        String word = "";
        try {
            FileReader fr = new FileReader(pathToDict);
            BufferedReader br = new BufferedReader(fr);
            while ((word = br.readLine()) != null) {
                HashMap<Character, Integer> wordFrequencies = wordFrequency(word);
                frequencies.put(word, wordFrequencies);
                allWords.add(word);
            }
        }

        catch (Exception e) {
        System.out.println(e);
        }

    }

    //given a word, this function returns a mapping from each character in the word to the frequency of that character.
    private HashMap<Character, Integer> wordFrequency (String word) {
        HashMap<Character, Integer> wordFrequencies = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c != 39) {
                c = Character.toLowerCase(c); //only letters (not apostrophe)
            }
            if (wordFrequencies.containsKey(c)) {
                wordFrequencies.put(c, wordFrequencies.get(c) + 1);
            }
            else {
                wordFrequencies.put(c, 1);
            }
        }
        return wordFrequencies;
    }

    // Given the entered word 'input', this function returns a sorted list of all dictionary words that are the input's anagrams
    private List<String> checkMatches(String input, HashMap<Character, Integer> inputFrequency, HashSet<Character> uniqueChars) {
        List<String> matchedWords = new ArrayList<>();
        int length = input.length();
        outer: for (int i = 0; i < allWords.size(); i++) {
            String dictWord = allWords.get(i);
            Iterator<Character> characters = uniqueChars.iterator();
            boolean flag = true;
            if (dictWord.length() != length) {
                continue;
            }
            inner: while (characters.hasNext()) {
                char c = characters.next();
                int frequency = inputFrequency.get(c);
                HashMap<Character, Integer> temp = frequencies.get(dictWord);

                if (!temp.containsKey(c) || frequency != temp.get(c)) {
                    flag = false;
                    break inner;
                }
            }
            if (flag) {
                matchedWords.add(dictWord);
            }

        }

        Collections.sort(matchedWords);
        return matchedWords;

    }


    //prints output, given a list of anagrams
    private void printOutput(List<String> outputs) {
        if (outputs.size() == 0) {
            System.out.println("-");
            return;
        }


        for (String i: outputs) {
            System.out.print(i + " ");
        }
        System.out.println();
    }



    public static void main(String[] args) throws IOException {

        String pathToDict = args[0];
        //String pathToDict = "text.txt";
        Anagrams obj = new Anagrams(pathToDict);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Enter word: ");
        String input = br.readLine();
        while (input != null) {
            if (input.length() == 0) {
                break;
            }
            HashMap<Character, Integer> wordFrequency = new HashMap<>();
            HashSet<Character> uniqueChars = new HashSet<>();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c != 39) {
                    c = Character.toLowerCase(c); //only letters (not apostrophe)
                }
                if (wordFrequency.containsKey(c)) {
                    wordFrequency.put(c, wordFrequency.get(c) + 1);
                }
                else {
                    wordFrequency.put(c, 1);
                    uniqueChars.add(c);
                }
            }
            List<String> outputWords = obj.checkMatches(input, wordFrequency, uniqueChars);
            obj.printOutput(outputWords);
            input = br.readLine();
        }

    }




}
