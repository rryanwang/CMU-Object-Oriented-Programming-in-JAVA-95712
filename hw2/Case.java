// Zhenxi Wang zhenxiw
package hw2;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Case implements Comparable<Case>{
	private final StringProperty caseDate = new SimpleStringProperty();
	private final StringProperty caseTitle = new SimpleStringProperty();
	private final StringProperty caseType = new SimpleStringProperty();
	private final StringProperty caseNumber = new SimpleStringProperty();
	private final StringProperty caseLink = new SimpleStringProperty();
	private final StringProperty caseCategory = new SimpleStringProperty();
	private final StringProperty caseNotes = new SimpleStringProperty();

	public Case(String caseDate,
			String caseTitle,
			String caseType,
			String caseNumber,
			String caseLink,
			String caseCategory,
			String caseNotes){

		setCaseDate(caseDate);
		setCaseTitle(caseTitle);
		setCaseType(caseType);
		setCaseNumber(caseNumber);
		setCaseLink(caseLink);
		setCaseCategory(caseCategory);
		setCaseNotes(caseNotes);

	}

	public String getCaseDate(){
		return caseDate.get();
	}

	public void setCaseDate(String caseDate) {
		this.caseDate.set(caseDate);
	}

	public StringProperty caseDateProperty() {
		return caseDate;
	}

	public String getCaseTitle() {
		return caseTitle.get();
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle.set(caseTitle);
	}

	public StringProperty caseTitleProperty() {return caseTitle;}

	public String getCaseType() {
		return caseType.get();
	}

	public void setCaseType(String caseType) {
		this.caseType.set(caseType);
	}

	public StringProperty caseTypeProperty() {
		return caseType;
	}

	public String getCaseNumber() {
		return caseNumber.get();
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber.set(caseNumber);
	}

	public StringProperty caseNumberProperty() {
		return caseNumber;
	}

	public String getCaseLink() {
		return caseLink.get();
	}

	public void setCaseLink(String caseLink) {
		this.caseLink.set(caseLink);
	}

	public StringProperty caseLinkProperty() {
		return caseLink;
	}

	public String getCaseCategory() {
		return caseCategory.get();
	}

	public void setCaseCategory(String caseCategory) {
		this.caseCategory.set(caseCategory);
	}

	public StringProperty caseCategoryProperty() {
		return caseCategory;
	}

	public String getCaseNotes() {
		return caseNotes.get();
	}

	public void setCaseNotes(String caseNotes) {
		this.caseNotes.set(caseNotes);
	}

	public StringProperty caseNotesProperty() {
		return caseNotes;
	}

	@Override
	public int compareTo(Case o) {
		return getCaseDate().compareTo(o.getCaseDate());
	}

	@Override
	public String toString() {
		return getCaseNumber();
	}
}