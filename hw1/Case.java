
package hw1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Case {
	String caseDate; //date in YYYY-mm-dd format
	String caseTitle;
	String caseType;
	String caseNumber;

	Case(String caseDate, String caseTitle, String caseType, String caseNumber) {
		this.caseDate = caseDate;
		this.caseTitle = caseTitle;
		this.caseType = caseType;
		this.caseNumber = caseNumber;
	}

	/** getYear() is an optional method to extract year
	 * from the caseDate. It can be useful 
	 * for printing yearWise summary. 
	 * @return
	 */
	
	int getYear() {
		String fromFormat = "yyyy-MM-dd";
		SimpleDateFormat format = new SimpleDateFormat(fromFormat);
		
		
		try {
			Date time = format.parse(caseDate);
			String now = time.toString();
			
			/*public int getYear() {
		        return normalize().getYear() - 1900;
		    }*/
			return time.getYear() + 1900;
		} catch (ParseException e) {
			//error
		}
		
		return 0;
	}
}
