package hw3;

public class CaseReaderFactory {
	public CaseReader createReader(String filename) { // returns an appropriate caseReader
		if (filename.contains(".tsv")) {
			return new TSVCaseReader(filename);
		} else {
			return new CSVCaseReader(filename);
		}
	}
}
