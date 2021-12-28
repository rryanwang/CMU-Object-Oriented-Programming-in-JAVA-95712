// Zhenxi Wang zhenxiw
package hw3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVCaseReader extends CaseReader {

	CSVCaseReader(String filename) {
		super(filename);
	}

	/**readCases uses CSVParser library to parse data file */
	@Override
	List<Case> readCases() {
		List<Case> caseList = new ArrayList<>();
		CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();  //data file has heade row
		try {
			CSVParser csvParser = CSVParser.parse(new FileReader(filename), csvFormat);
			for (CSVRecord csvRecord : csvParser) {
				Case c = new Case(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3), csvRecord.get(4), csvRecord.get(5), csvRecord.get(6));
				caseList.add(c);
			}
		} catch (FileNotFoundException e1) { e1.printStackTrace(); 
		} catch (IOException e1) { e1.printStackTrace(); 
		}
		return caseList;
	}
}
