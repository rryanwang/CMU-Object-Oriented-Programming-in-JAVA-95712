package hw3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestCyberCop {
	
	static CCModel ccModel = new CCModel();
	
	@BeforeAll
	public static void setupCyberCop() {
		ccModel.readCases("data\\CyberCop-TSVData-Perfect.tsv");
		ccModel.buildYearMapAndList();
	}

	@Test
	void test1_readCases() {
		assertEquals(281, ccModel.caseList.size());
	}
	
	/************************* test cases for search ****************/
	@Test
	void test2_searchCases() {
		assertEquals(1, ccModel.searchCases("facebook", null, null, null).size());
	}
	
	@Test
	void test3_searchCases() {
		assertEquals(3, ccModel.searchCases("google", null, null, null).size());
	}

	@Test
	void test4_searchCases() {
		assertEquals(0, ccModel.searchCases("apple", null, null, null).size());
	}
	
	@Test
	void test5_searchCases() {
		assertEquals(1, ccModel.searchCases("google", null, "2019", null).size());
	}
	
	@Test
	void test6_searchCases() {
		assertEquals(1, ccModel.searchCases("zoom video", null, "2020", "192 3167").size());
	}
	
	@Test
	void test7_searchCases() {
		assertEquals(2, ccModel.searchCases("bonzi software", null, null, null).size());
	}
	
	@Test
	void test8_searchCases() {
		assertEquals(1, ccModel.searchCases("bonzi software", "federal", null, null).size());
	}
	
	@Test
	void test9_searchCases() {
		assertEquals(1, ccModel.searchCases("cambridge", "administrative", "2019", "182 3107").size());
	}
	
	/***************** tests for buildYearListAndMap *************************/
	
	//test there are 25 years on yearList
	@Test
	void test10_buildYearList() {
		assertEquals(25, ccModel.yearList.size());
	}
	
	//test some values in yearList
	@Test
	void test11_buildYearList() {  
		Collections.sort(ccModel.yearList);
		assertEquals("1997", ccModel.yearList.get(0));  
		assertEquals("1998", ccModel.yearList.get(1));
		assertEquals("2021", ccModel.yearList.get(24));
	}
	
	//test there are 25 years in yearMap
	@Test
	void test12_buildYearMap() {
		assertEquals(25, ccModel.yearMap.size());
	}
	
	
	//test year 2021 contains 4 cases in yearMap
	@Test
	void test13_buildYearMap() {
		assertEquals(4, ccModel.yearMap.get("2021").size());
	}
}
