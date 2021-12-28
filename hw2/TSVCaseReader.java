// Zhenxi Wang zhenxiw
package hw2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class TSVCaseReader extends CaseReader { // implements readCases method and returns caseList

	TSVCaseReader(String filename) {
		super(filename);
	}

	@Override
	List<Case> readCases() {
		List<Case> caseList = new ArrayList<>();
		try {
			File fr = new File(filename);
			Scanner s = new Scanner(fr);
			String line;
			String[] tokens;
			while (s.hasNext()) {
				line = s.nextLine();
				tokens = line.split("\t");
				Case c = new Case(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
				caseList.add(c);
			}
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return caseList;
	}
}