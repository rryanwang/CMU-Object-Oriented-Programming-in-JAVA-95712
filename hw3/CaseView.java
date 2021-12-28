//Zhenxi Wang zhenxiw
package hw3;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class CaseView {
	
	final int CASE_WIDTH = 800;
	final int CASE_HEIGHT = 500;
	
	Button updateButton = new Button ("Update");
	Button clearButton = new Button ("Clear");
	Button closeButton = new Button ("Close");
	
	TextField titleTextField = new TextField();
	TextField caseTypeTextField = new TextField();
	DatePicker caseDatePicker = new DatePicker(LocalDate.now());
	TextField caseNumberTextField = new TextField();
	TextField categoryTextField = new TextField();
	TextField caseLinkTextField = new TextField();
	TextArea caseNotesTextArea = new TextArea();
	
	GridPane updateCaseGridPane = new GridPane();
	Stage stage = new Stage();
	
	CaseView(String header) {
		
		stage.setTitle(header);
		
		updateCaseGridPane.setVgap(5);
		updateCaseGridPane.setHgap(5);
		updateCaseGridPane.setPadding(new Insets(10, 10, 10, 10));
		
		Label titleLabel = new Label("Title");
		Label caseTypeLabel = new Label("Case type");
		Label caseDateLabel = new Label("Date");
		Label caseNumberLabel = new Label("Case number");
		Label categoryLabel = new Label("Category");
		Label caseLinkLabel = new Label("Case link");
		Label caseNotesLabel = new Label("Case notes");
		
		caseNotesTextArea.setWrapText(true);
		
		titleTextField.setPrefWidth(700);
		
		updateCaseGridPane.add(titleLabel, 0, 0);
		updateCaseGridPane.add(titleTextField, 0, 1, 4, 1);
		
		updateCaseGridPane.add(caseDateLabel, 0, 2);
		updateCaseGridPane.add(caseDatePicker, 0, 3);
		
		updateCaseGridPane.add(caseTypeLabel, 1, 2);
		updateCaseGridPane.add(caseTypeTextField, 1, 3);
		caseTypeTextField.setPrefWidth(200);

		updateCaseGridPane.add(caseNumberLabel, 2, 2);
		updateCaseGridPane.add(caseNumberTextField, 2, 3);
		caseNumberTextField.setPrefWidth(200);
		
		updateCaseGridPane.add(categoryLabel, 3, 2);
		updateCaseGridPane.add(categoryTextField, 3, 3);
		categoryTextField.setPrefWidth(200);
		
		updateCaseGridPane.add(caseLinkLabel, 0, 4);
		updateCaseGridPane.add(caseLinkTextField, 0, 5, 4, 1);
		
		updateCaseGridPane.add(caseNotesLabel, 0, 6);
		updateCaseGridPane.add(caseNotesTextArea, 0, 7, 4, 1);

		updateButton.setPrefWidth(400);
		closeButton.setPrefWidth(400);
		clearButton.setPrefWidth(800);
		
		updateCaseGridPane.add(updateButton, 0, 15, 2, 1);
		updateCaseGridPane.add(closeButton, 2, 15, 2, 1);
		updateCaseGridPane.add(clearButton, 0, 16, 4, 1);
	}
	

	abstract Stage buildView();
}
