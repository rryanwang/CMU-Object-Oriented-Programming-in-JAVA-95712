package exam3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
	public final static String DICTIONARY = "SampleDictionary.txt";
	List<Word> wordList = new ArrayList<>();
	Map<String, Word> singleMap = new HashMap<>();
	Map<String, List<Word>> multiMap = new HashMap<>();

	//DO NOT CHANGE MAIN METHOD
	public static void main(String[] args) {
		Dictionary dictionary = new Dictionary();
		Scanner input = new Scanner(System.in);
		dictionary.loadWordList();
		dictionary.loadSingleMap();
		dictionary.loadMultiMap();

		System.out.println("Enter search word");
		String searchWord = input.nextLine();

		System.out.println("------------WordList Search-----------");
		dictionary.searchWordList(searchWord);
		System.out.println("\n------------SingleMap Search-----------");
		dictionary.searchSingleMap(searchWord);
		System.out.println("\n------------MultiMap Search-----------");
		dictionary.searchMultiMap(searchWord);
		input.close();
	}

	//DO NOT CHANGE THIS METHOD
	/**loadWordList() reads the txt file. For each line, it invokes 
	 * getWord() method that returns a Word object. This object is then
	 * added to the arrayList wordList
	 */
	void loadWordList() {
		String wordString = null;
		try {
			Scanner input = new Scanner(new File(DICTIONARY));
			while (input.hasNextLine()) {
				wordString = input.nextLine();
				if (wordString.length() == 0) continue;
				wordList.add(getWord(wordString));
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	//DO NOT CHANGE THIS METHOD
	/** getWord() is a helper method to extract a word and its meaning from 
	 * a line of text. 
	 * It takes a wordString and splits it on "(". The first
	 * element after split is the word, and rest are elements of its meaning. 
	 * So it uses first element to initialize 'word' of Word, and rest to 
	 * initialize 'meaning' of Word. As '(' may occur anywhere in the 
	 * 'meaning', the split string is put back together by putting
	 * '(' in front of each piece.  
	 * @param wordString
	 * @return
	 */
	Word getWord(String wordString) {
		String[] splits = wordString.split("\\(");  //split on (
		String word = null;
		StringBuilder wordMeaningString = new StringBuilder();
		if (splits[0].length() >0) 
			word = splits[0].trim();  //get the first string as it is the word
		for (int i = 1; i < splits.length; i++) {
			wordMeaningString.append("(" + splits[i]); //put back rest of the string together
		}
		return new Word(word, wordMeaningString.toString());
	}


	/** loadSingleMap() takes each word from
	 * wordList and loads it into singleMap with key being
	 * the Word's word in lowercase, and its value being the whole 
	 * Word object.
	 */
	void loadSingleMap() {
		//write your code here
		for (Word w : wordList) {
			singleMap.put(w.word.toLowerCase(), w);
		}
	}

	/**loadMultiMap() takes each word from wordList and loads it 
	 * into multiMap with key being the Word's word in lowercase, and 
	 * its value being a list of all Word objects for that word. 
	 */
	void loadMultiMap() {
		//write your code here
		for (Word w : wordList) {
			if (!multiMap.containsKey(w.word.toLowerCase())) multiMap.put(w.word.toLowerCase(), new ArrayList<>());
			multiMap.get(w.word.toLowerCase()).add(w);
		}
	}

	/** searchWordList() takes a searchWord String and and searches for it in wordList.
	 * If found, it prints all its meanings. Else it prints 'Sorry! word not found!'
	 * It also returns a list of meanings, if found. Else it returns null.
	 * @param searchWord
	 */
	List<String> searchWordList(String searchWord) {
		//write your code here
		List<String> strings = new ArrayList<>();
		for (Word w : wordList) {
			if (w.word.toLowerCase().equals(searchWord.toLowerCase())) {
				System.out.println(String.format("%s:%s", w.word, w.meaning));
				strings.add(w.meaning);
			}
		}
		if (strings.isEmpty()) {
			System.out.println(String.format("Sorry! %s not found!", searchWord));
			return null;
		} else {
			return strings;
		}
	}

	/** searchSingleMap() takes a searchWord String and searches for it in singleMap.
	 * If found, it prints its meaning. Else it prints 'Sorry! word not found!'
	 * It also returns the meaning string, if found, or else it returns null. 
	 * @param searchWord
	 */
	String searchSingleMap(String searchWord) {
		//write your code here
		String meaning = null;
		for (String key : singleMap.keySet()) {
			if (key.equals(searchWord.toLowerCase())) {
				System.out.println(String.format("%s:%s", singleMap.get(key).word, singleMap.get(key).meaning));
				meaning = singleMap.get(key).meaning;
			}
		}
		if (meaning == null) System.out.println(String.format("Sorry! %s not found!", searchWord));
		return meaning;
	}

	/** searchMultiMap() takes a searchWord String and searches for it in multiMap. 
	 * If found, it prints all its meanings. Else it prints 'Sorry! word not found!'
	 * It also returns a list of meanings, if found. Else it returns null.
	 * @param searchWord
	 */
	List<String> searchMultiMap(String searchWord) {
		//write your code here
		List<String> strings = new ArrayList<>();
		for (String key : multiMap.keySet()) {
			if (key.equals(searchWord.toLowerCase())) {
				for (Word w : multiMap.get(key)) {
					System.out.println(String.format("%s:%s", w.word, w.meaning));
					strings.add(w.meaning);
				}
			}
		}
		if (strings.isEmpty()) {
			System.out.println(String.format("Sorry! %s not found!", searchWord));
			return null;
		} else {
			return strings;
		}
	}
}
