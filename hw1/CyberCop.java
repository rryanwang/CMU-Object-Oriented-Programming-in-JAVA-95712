//Ryan Wang zhenxiw
package hw1;

import java.util.Scanner;

public class CyberCop {

	public static final String DATAFILE = "data/FTC-cases-TSV.txt";
	CCModel ccModel = new CCModel();
	SearchEngine searchEngine = new SearchEngine();

	Scanner input = new Scanner(System.in);

	/**main() instantiates CyberCop and then invokes dataManager's loadData
	 * and loadCases() methods
	 * It then invokes showMenu to get user input
	 * @param args
	 */
	//Do not change this method
	public static void main(String[] args) {
		CyberCop cyberCop = new CyberCop();
		cyberCop.ccModel.loadData(DATAFILE);
		cyberCop.ccModel.loadCases();
		cyberCop.showMenu();
	}

	/**showMenu() shows the menu. 
	 * Based on the user choice, it invokes one of the methods:
	 * printSearchResults(), printCaseTypeSummary(), or printYearwiseSummary()
	 * The program exits when user selects Exit option. 
	 * See the hand-out for the expected layout of menu-UI
	 */
	void showMenu() {
		//write your code here
		while(true) {
			System.out.println();
			System.out.println("*** Welcome to CyberCop! ***");
			System.out.println("1. Search cases for a company");
			System.out.println("2. Search cases in a year");
			System.out.println("3. Search case number");
			System.out.println("4. Print Case-type Summary");
			System.out.println("5. Print Year-wise Summary");
			System.out.println("6. Exit");
			
			int choice = Integer.parseInt(input.nextLine());
			switch(choice) {
			case 1: {
				System.out.println("---------------------------------------------------------------------");
				System.out.println("Enter search string");
				String searchString = input.nextLine();
				System.out.println("---------------------------------------------------------------------");
				
				Case[] cases = searchEngine.searchTitle(searchString, ccModel.cases);
				printSearchResults(searchString, cases);
			}
				break;
			case 2: {
				System.out.println("---------------------------------------------------------------------");
				System.out.println("Enter search year as YYYY");
				System.out.println("---------------------------------------------------------------------");
				String searchString = input.nextLine();
				
				Case[] cases = searchEngine.searchYear(searchString, ccModel.cases);
				printSearchResults(searchString, cases);
			}
				break;
			case 3: {
				System.out.println("---------------------------------------------------------------------");
				System.out.println("Enter case number");
				System.out.println("---------------------------------------------------------------------");
				String searchString = input.nextLine();
				
				Case[] cases = searchEngine.searchCaseNumber(searchString, ccModel.cases);
				printSearchResults(searchString, cases);
			}
				break;
			case 4:
				printCaseTypeSummary();
				break;
			case 5:
				printYearwiseSummary();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("error input.");
			}
		}
	}

	/**printSearcjResults() takes the searchString and array of cases as input
	 * and prints them out as per the format provided in the handout
	 * @param searchString
	 * @param cases
	 */
	void printSearchResults(String searchString, Case[] cases) {
		//write your code here
		if (cases == null || cases.length == 0) {
			System.out.println("Sorry, no search results found for " + searchString);
		} else {
			System.out.println(cases.length + " case(s) found for " + searchString);
			System.out.println("---------------------------------------------------------------------");
			System.out.println(String.format("%-80s%-40s%-50s", " #. Last " +
					"update Case Title", "Case Type", "Case/File Number"));
			System.out.println("---------------------------------------------------------------------");
			
			for (int i=0; i<cases.length; i++) {
				System.out.println((i+1) + ". " + String.format("%-75s%-40s" +
						"%-50s",
						cases[i].caseDate + "\t" + cases[i].caseTitle, cases[i].caseType, cases[i].caseNumber));
			}
		}
		
		System.out.println("---------------------------------------------------------------------");
		return;
	}

	/**printCaseTypeSummary() prints a summary of
	 * number of cases of different types as per the 
	 * format given in the handout.
	 */
	void printCaseTypeSummary() {
		//write your code here
		int administrativeCaseNum = 0;
		int federalCaseNum = 0;
		int unknownNum = 0;
		
		for (int i=0; i<ccModel.cases.length; i++) {
			if ("Administrative".equals(ccModel.cases[i].caseType)) {
				administrativeCaseNum++;
			} else if ("Federal".equals(ccModel.cases[i].caseType)) {
				federalCaseNum++;
			} else {
				unknownNum++;
			}
		}
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("*** Case Type Summary Report ***");
		System.out.println("No. of Administrative cases: " + administrativeCaseNum);
		System.out.println("No. of Federal cases: " + federalCaseNum);
		System.out.println("No. of unknown case types: " + unknownNum);
		System.out.println("---------------------------------------------------------------------");
	}
	
	/**printYearWiseSummary() prints number of cases in each year
	 * as per the format given in the handout
	 */
	void printYearwiseSummary() {
		//write your code here
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("                            *** Year-wise Summary Report ***                          ");
		System.out.println("                            *** Number of FTC cases per year ***                      ");
		System.out.println();
		
		//5 in one line
		for (int i=2021; i>1997-1; i=i-5) {
			for (int index = 0; index < 5; index++) {
				Case[] cases = searchEngine.searchYear(String.valueOf(i-index), ccModel.cases);
				
				System.out.print(String.format("%15s", String.valueOf(i - index) + ": "));
				System.out.print(String.format("%5s", (cases==null?0:cases.length)));
			}
			
			System.out.println();
		}
		System.out.println("--------------------------------------------------------------------------------------");
	}
}
