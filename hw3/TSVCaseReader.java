package hw3;

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
		int missing_column_count = 0;
		try {
			File fr = new File(filename);
			Scanner s = new Scanner(fr);
			String line;
			String[] tokens;
			

			while (s.hasNext()) {
				line = s.nextLine();
				tokens = line.split("\t", -1);
				// 0: Date, 1: Title, 2: Type, 3: Number, 4: Link, 5: Category, 6: Notes
				if (tokens[0].isBlank() || tokens[1].isBlank() || tokens[2].isBlank() || tokens[3].isBlank()) {
					missing_column_count++;
					continue;
				}

				Case c = new Case(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
				caseList.add(c);
			}
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			if (missing_column_count != 0) {
				throw new DataException(String.format(
						"%d cases rejected.\nThe file must have cases with\ntab separated date, title, type, and case number!",
						missing_column_count));
			}
		} catch (DataException e) {

		}

		return caseList;
	}
}
