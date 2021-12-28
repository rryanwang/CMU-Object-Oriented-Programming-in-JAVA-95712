//Ryan Wang zhenxiw
package hw1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CCModel {
	Case[] cases;
	String[] fileData;

	/**loadData() takes filename as a parameter,
	 * reads the file and loads all 
	 * data as a String for each row in 
	 * fileData[] array
	 * @param filename
	 */
	void loadData(String filename) {
		try {
			fileData = Files.readAllLines(Paths.get(filename)).toArray(new String[0]);
		} catch (IOException e) {
			System.err.println("Error reading file " + filename + ": " + e);
		}
	}

	/**loadCases() uses the data stored in fileData array
	 * and creates Case objects for each row.
	 * These cases are loaded into the cases array.
	 * Note that you may have to traverse the fileData array twice
	 * to be able to initialize the cases array's size.
	 */
	void loadCases() {
		int size = 0;  //size of case
		
		if (fileData == null || fileData.length == 0) {
			return;
		}
		
		for (String data : fileData) {
			String[] line = data.split("\t");
			if (line.length < 2) {
				continue;
			}
			
			size++;
		}
		
		cases = new Case[size];
		for (int i=0; i<fileData.length; i++) {
			String[] line = fileData[i].split("\t");
			if (line.length < 2) {
				System.out.println("error line: " + line);
				continue;
			}
			
			//parse title and case type
			String title = "";
			String castType = "";
			if (line[1].endsWith("(Federal)")) {
				castType = "Federal";
				title = line[1].substring(0, line[1].indexOf("(Federal)")).trim();
			} else if (line[1].endsWith("(Administrative)")) {
				castType = "Administrative";
				title = line[1].substring(0, line[1].indexOf("(Administrative)")).trim();
			} else {
				title = line[1];
			}
			
			//parse caseNumber
			String caseNumber = "";
			if (line.length == 3) {
				caseNumber = line[2];
			}
			
			//constructor
			Case cs = new Case(line[0], title, castType, caseNumber);
			cases[i] = cs;
		}
	}
}