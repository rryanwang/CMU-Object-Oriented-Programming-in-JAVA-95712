package hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestCyberCop {

	public static final String DATAFILE = "data/FTC-cases-TSV.txt";
	static CyberCop cyberCop = new CyberCop();

	@BeforeAll
	static void setUpCyberCop() {
		cyberCop.ccModel.loadData(DATAFILE);
		cyberCop.ccModel.loadCases();
	}

	@Test
	void testLoadData() {
		assertEquals(282, cyberCop.ccModel.fileData.length);
	}

	@Test
	void testLoadCases() {
		assertEquals(282, cyberCop.ccModel.cases.length);
	}

	@Test
	void testCaseContent() {
		for (Case c : cyberCop.ccModel.cases) {
			if (c.caseTitle.contains("Facebook")) {
				assertEquals("2020-04-28", c.caseDate);
				assertTrue(c.caseType.equals("Administrative"));
				assertTrue(c.caseNumber.contains("C-4365"));
				break;
			}
		}
	}

	@Test
	void testSearchTitleCount() {
		Case[] cases = cyberCop.searchEngine.searchTitle("google", cyberCop.ccModel.cases);
		assertEquals(3, cases.length);
	}

	@Test
	void testSearchTitleContent() {
		Case[] cases = cyberCop.searchEngine.searchTitle("google", cyberCop.ccModel.cases);
		for (Case c : cases) {
			assertTrue(c.caseTitle.toLowerCase().contains("google"));
		}
	}

	@Test
	void testSearchYearLength() {
		Case[] cases = cyberCop.searchEngine.searchYear("2021", cyberCop.ccModel.cases);
		assertEquals(5, cases.length);
	}

	@Test
	void testSearchCaseNumberLength() {
		Case[] cases = cyberCop.searchEngine.searchCaseNumber("192 3090", cyberCop.ccModel.cases);
		assertEquals(1, cases.length);
	}

	@Test
	void testSearchCaseNumberContent() {
		Case[] cases = cyberCop.searchEngine.searchCaseNumber("192 3090", cyberCop.ccModel.cases);
		assertTrue(cases[0].caseTitle.contains("Click Labs"));
	}

}
