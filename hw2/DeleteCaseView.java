package hw2;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;

public class DeleteCaseView extends CaseView {

	DeleteCaseView(String header) {
		super(header);
	}

	@Override
	Stage buildView() { // returns a Stage with 'currentCase' displayed
		Case currentCase = CyberCop.currentCase;
		titleTextField.setText(currentCase.getCaseTitle());
		caseTypeTextField.setText(currentCase.getCaseType());
		caseDatePicker.setValue(LocalDate.parse(currentCase.getCaseDate()));
		caseNumberTextField.setText(currentCase.getCaseNumber());
		categoryTextField.setText(currentCase.getCaseCategory());
		caseLinkTextField.setText(currentCase.getCaseLink());
		caseNotesTextArea.setText(currentCase.getCaseNotes());

		Scene sc = new Scene(updateCaseGridPane, CASE_WIDTH, CASE_HEIGHT);
		updateButton.setText("Delete Case");
		stage.setScene(sc);
		return stage;
	}

}
