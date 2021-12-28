package exam3;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDictionary {
	static Dictionary dictionary;
	@BeforeClass 
	public static void setupClass() {
		dictionary = new Dictionary();
		dictionary.loadWordList();
		dictionary.loadSingleMap();
		dictionary.loadMultiMap();
	}

	//test searchWordList() method
	@Test
	public void test1_searchWordListExistingWord() {
		assertEquals(2, dictionary.searchWordList("aband").size());
	}
	@Test
	public void test2_searchWordListNonExistingWord() {
		assertEquals(null, dictionary.searchWordList("xyz"));
	}

	//test singleMap data structure
	@Test
	public void test3_SingleMapContainsKey() {
		assertEquals(true, dictionary.singleMap.containsKey("abaft") );
	}
	@Test
	public void test4_SingleMapMeaning() {
		assertEquals("(adv.) Toward the stern; aft; as, to go abaft.", dictionary.singleMap.get("abaft").meaning );
	}

	//test searchSingleMap() method
	@Test
	public void test5_searchSingleMapExistingWord() {
		String result = dictionary.searchSingleMap("abandon");
		assertEquals(false, (result == null || result.isEmpty()) );
	}
	@Test
	public void test6_searchSingleMapNonExistingWord() {
		assertEquals(null, dictionary.searchSingleMap("xyz") );
	}
	
	//test multiMap data structure
	@Test
	public void test7_MultiMapContainsKey() {
		assertEquals(true, dictionary.multiMap.containsKey("abaft") );
	}
	@Test
	public void test8_MultiMapMeaningSize() {
		assertEquals(2, dictionary.multiMap.get("abaft").size() );
	}

	//test searchMultiMap() method
	@Test
	public void test9_searchMultiMapExistingKey() {
		assertEquals(2, dictionary.searchMultiMap("abaft").size() );
		
	}
	@Test
	public void test10_searchMultiMapNonExistingKey() {
		assertEquals(null, dictionary.searchMultiMap("xyz") );
	}
}
