//Ryan Wang zhenxiw
package hw1;

import java.util.Arrays;
import java.util.Comparator;

public class SearchEngine {
	
	/**searchTitle() takes a searchString and array of cases,
	 * searches for cases with searchString in their title,
	 * and if found, returns them in another array of cases.
	 * If no match is found, it returns null.
	 * Search is case-insensitive
	 * @param searchString
	 * @param cases
	 * @return
	 */
	Case[] searchTitle(String searchString, Case[] cases) {
		//write your code here
		if (searchString == null || searchString.length() == 0 || cases == null || cases.length == 0) {
			return null;
		}
		
		Case[] array = new Case[cases.length];
		int index = 0; //match number
		for (int i=0; i<cases.length; i++) {
			if (cases[i].caseTitle.toLowerCase().contains(searchString.toLowerCase())) { //case-insensitive 
				array[index++] = cases[i];
			}
		}
		
		if (index > 0) {
			Case[] result = new Case[index];
			System.arraycopy(array, 0, result, 0, index);
			return result;
		}
		
		return null;
	}
	
	/**searchYear() takes year in YYYY format as search string,
	 * searches for cases that have the same year in their date,
	 * and returns them in another array of cases.
	 * If not found, it returns null.
	 * @param year
	 * @param cases
	 * @return
	 */
	Case[] searchYear(String year, Case[] cases) {
		//write your code here
		if (year == null || year.length() == 0 || cases == null || cases.length == 0) {
			return null;
		}
		
		int intYear = Integer.parseInt(year.trim());
		Case[] array = new Case[cases.length];
		int index = 0; //match number
		for (int i=0; i<cases.length; i++) {
			int _year = cases[i].getYear();
			if (_year == intYear) {
				array[index++] = cases[i];
			}
		}
		
		if (index > 0) {
			Case[] result = new Case[index];
			System.arraycopy(array, 0, result, 0, index);
			
			//sort the result
			Arrays.sort(result, new Comparator<Case>() {
				@Override
				public int compare(Case cs1, Case cs2) {
					if (cs1.getYear() > cs2.getYear()) {return -1;}
					else if(cs1.getYear() < cs2.getYear()) {return 1;}
					else {return 0;}
				}
			});
			
			return result;
		}
		
		return null;
	}
	
	/**searchCaseNumber() takes a caseNumber,
	 * searches for those cases that contain that caseNumber, 
	 * and returns an array of cases that match the search.
	 * If not found, it returns null.
	 * Search is case-insensitive.
	 * @param caseNumber
	 * @param cases
	 * @return
	 */
	Case[] searchCaseNumber(String caseNumber, Case[] cases) {
		//write your code here
		if (caseNumber == null || caseNumber.length() == 0 || cases == null || cases.length == 0) {
			return null;
		}
		
		Case[] array = new Case[cases.length];
		int index = 0; //match number
		for (int i=0; i<cases.length; i++) {
			if (cases[i].caseNumber.contains(caseNumber)) { //
				array[index++] = cases[i];
			}
		}
		
		if (index > 0) {
			Case[] result = new Case[index];
			System.arraycopy(array, 0, result, 0, index);
			return result;
		}
		
		return null;
	}
}
