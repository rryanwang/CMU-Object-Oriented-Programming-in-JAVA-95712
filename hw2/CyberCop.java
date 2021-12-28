package hw2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CyberCop extends Application{

	public static final String DEFAULT_PATH = "data"; //folder name where data files are stored
	public static final String DEFAULT_HTML = "/CyberCop.html"; //local HTML
	public static final String APP_TITLE = "Cyber Cop"; //displayed on top of app

	CCView ccView = new CCView();
	CCModel ccModel = new CCModel();

	CaseView caseView; //UI for Add/Modify/Delete menu option

	GridPane cyberCopRoot;
	Stage stage;

	static Case currentCase; //points to the case selected in TableView.

	public static void main(String[] args) {
		launch(args);
	}

	/** start the application and show the opening scene */
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		primaryStage.setTitle("Cyber Cop");
		cyberCopRoot = ccView.setupScreen();  
		setupBindings();
		Scene scene = new Scene(cyberCopRoot, ccView.ccWidth, ccView.ccHeight);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		ccView.webEngine.load(getClass().getResource(DEFAULT_HTML).toExternalForm());
		primaryStage.show();
	}


	/** setupBindings() binds all GUI components to their handlers.
	 * It also binds disableProperty of menu items and text-fields 
	 * with ccView.isFileOpen so that they are enabled as needed
	 */
	void setupBindings() {

		// setup year combo box
		ccView.yearComboBox.setItems(ccModel.yearList);

		//  bind currentCase
		ccView.caseTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			currentCase = ccView.caseTableView.getSelectionModel().getSelectedItem();
			if (currentCase != null) {
				ccView.titleTextField.setText(currentCase.getCaseTitle());
				ccView.caseTypeTextField.setText(currentCase.getCaseType());
				ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
				ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
				String year = currentCase.getCaseDate().split("-")[0];
				ccView.yearComboBox.getSelectionModel().select(year);

				// show web link
				if (currentCase.getCaseLink() == null || currentCase.getCaseLink().isBlank()) {  //if no link in data
					URL url = getClass().getClassLoader().getResource(DEFAULT_HTML);  //default html
					if (url != null) ccView.webEngine.load(url.toExternalForm());
				} else if (currentCase.getCaseLink().toLowerCase().startsWith("http")){  //if external link
					ccView.webEngine.load(currentCase.getCaseLink());
				} else {
					URL url = getClass().getClassLoader().getResource(currentCase.getCaseLink().trim());  //local link
					if (url != null) ccView.webEngine.load(url.toExternalForm());
				}
			}
		});


		// bind disable property
		ccView.openFileMenuItem.disableProperty().bind(ccView.isFileOpen);
		ccView.closeFileMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.titleTextField.disableProperty().bind(ccView.isFileOpen.not());
		ccView.caseTypeTextField.disableProperty().bind(ccView.isFileOpen.not());
		ccView.yearComboBox.disableProperty().bind(ccView.isFileOpen.not());
		ccView.caseNumberTextField.disableProperty().bind(ccView.isFileOpen.not());
		ccView.searchButton.disableProperty().bind(ccView.isFileOpen.not());
		ccView.clearButton.disableProperty().bind(ccView.isFileOpen.not());
		ccView.addCaseMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.modifyCaseMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.deleteCaseMenuItem.disableProperty().bind(ccView.isFileOpen.not());


		// bind menuItem
		ccView.openFileMenuItem.setOnAction(new OpenFileMenuItemHandler());
		ccView.closeFileMenuItem.setOnAction(new CloseFileMenuItemHandler());
		ccView.exitMenuItem.setOnAction(new ExitMenuItemHandler());

		// bind search and clear
		ccView.searchButton.setOnAction(new SearchButtonHandler());
		ccView.clearButton.setOnAction(new ClearButtonHandler());

		// bind caseMenuItem
		ccView.addCaseMenuItem.setOnAction(new CaseMenuItemHandler());
		ccView.modifyCaseMenuItem.setOnAction(new CaseMenuItemHandler());
		ccView.deleteCaseMenuItem.setOnAction(new CaseMenuItemHandler());
	}


	class OpenFileMenuItemHandler implements EventHandler<ActionEvent>{
		// Opens dialog box for user to select a case file
		@Override
		public void handle(ActionEvent actionEvent) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select file");
			File fr = fileChooser.showOpenDialog(stage);
			String filePath = fr.getAbsolutePath();

			ccModel.readCases(filePath);
			ccModel.buildYearMapAndList();

			ccView.caseTableView.setItems(ccModel.caseList);
			ccView.caseTableView.getSelectionModel().selectFirst();

			ccView.messageLabel.setText(ccModel.caseList.size() + " cases.");

			stage.setTitle(APP_TITLE + ": " +  fr.getName());

			ccView.isFileOpen.set(true);
		}
	}

	class CloseFileMenuItemHandler implements EventHandler<ActionEvent>{
		//Clears all GUI elements and sets isFileOpen to false.
		@Override
		public void handle(ActionEvent actionEvent) {
			ccView.caseTableView.getItems().clear();
			ccView.caseNotesTextArea.clear();

			ccView.titleTextField.clear();
			ccView.caseTypeTextField.clear();
			ccView.yearComboBox.valueProperty().set(null);
			ccView.caseNumberTextField.clear();

			ccView.messageLabel.setText(null);

			ccView.isFileOpen.set(false);
			ccView.webEngine.load(getClass().getResource(DEFAULT_HTML).toExternalForm());
		}
	}

	class ExitMenuItemHandler implements EventHandler<ActionEvent>{
		// Exits the application 
		@Override
		public void handle(ActionEvent actionEvent) {
			Platform.exit();
		}
	}

	class SearchButtonHandler implements EventHandler<ActionEvent>{
		// Displays cases that contain data entered in titleTextField, caseTypeTextField, yearComboBox, and caseNumberTextField. 
		@Override
		public void handle(ActionEvent actionEvent) {
			String title = ccView.titleTextField.getText();
			String caseType = ccView.caseTypeTextField.getText();
			String year = ccView.yearComboBox.getValue();
			String caseNumber = ccView.caseNumberTextField.getText();
			ObservableList<Case> searchResult;

			if (title.isEmpty() && caseType.isEmpty() && year.isEmpty() && caseNumber.isEmpty()){
				searchResult = ccModel.caseList;
			} else {
				if (title.isEmpty()){
					title = null;
				}
				if (caseType.isEmpty()){
					caseType = null;
				}
				if (year.isEmpty()){
					year = null;
				}
				if (caseNumber.isEmpty()){
					caseNumber = null;
				}
				searchResult = ccModel.searchCases(title, caseType, year, caseNumber);
			}

			currentCase = searchResult.get(0);
			ccView.caseTableView.setItems(searchResult);
			ccView.caseTableView.getSelectionModel().selectFirst();

			ccView.messageLabel.setText(ccView.caseTableView.getItems().size() + " cases. "); // Updates messageLabel accordingly. 
		}
	}

	class ClearButtonHandler implements EventHandler<ActionEvent>{
		// Clears the data entered in titleTextField, caseTypeTextField, yearComboBox,and caseNumberTextField 
		@Override
		public void handle(ActionEvent actionEvent) {
			ccView.titleTextField.clear();
			ccView.caseTypeTextField.clear();
			ccView.yearComboBox.valueProperty().set("");
			ccView.caseNumberTextField.clear();
			ccView.caseNotesTextArea.clear();
			ccView.messageLabel.setText("");
		}
	}

	class CaseMenuItemHandler implements EventHandler<ActionEvent>{
		// A common handler for three menu items - Add case, Modify case, Delete case. 
		@Override
		public void handle(ActionEvent actionEvent) {
			MenuItem source = (MenuItem) actionEvent.getSource();            // creates an instance of AddView, ModifyView, or DeleteView respectively.
			if (source == ccView.addCaseMenuItem){
				caseView = new AddCaseView("Add Case");
				caseView.updateButton.setOnAction(new AddButtonHandler());
			} else if(source == ccView.modifyCaseMenuItem){
				caseView = new ModifyCaseView("Modify Case");
				caseView.updateButton.setOnAction(new ModifyButtonHandler());
			} else {
				caseView = new DeleteCaseView("Delete Case");
				caseView.updateButton.setOnAction(new DeleteButtonHandler());
			}

			// bind clear button and close button
			caseView.clearButton.setOnAction(new CaseViewClearButtonHandler());
			caseView.closeButton.setOnAction(new CaseViewCloseButtonHandler());

			Stage caseViewStage = caseView.buildView();
			caseViewStage.show();
		}
	}

	class CaseViewClearButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent actionEvent) {
			caseView.titleTextField.clear();
			caseView.caseDatePicker.setValue(LocalDate.now());
			caseView.caseTypeTextField.clear();
			caseView.caseNumberTextField.clear();
			caseView.categoryTextField.clear();
			caseView.caseLinkTextField.clear();
			caseView.caseNotesTextArea.clear();
		}
	}

	class CaseViewCloseButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent actionEvent) {
			caseView.stage.close();
		}
	}

	class AddButtonHandler implements EventHandler<ActionEvent>{
		// add case entry to main screen's view
		@Override
		public void handle(ActionEvent actionEvent) {
			String caseDate = caseView.caseDatePicker.getValue().format(
					DateTimeFormatter.ofPattern("yyyy-MM-dd")); // extract data from caseDatePicker control
			String caseTitle = caseView.titleTextField.getText();
			String caseType = caseView.caseTypeTextField.getText();
			String caseNumber = caseView.caseNumberTextField.getText();
			String caseLink = caseView.caseLinkTextField.getText();
			String caseCategory = caseView.categoryTextField.getText();
			String caseNotes = caseView.caseNotesTextArea.getText();

			Case newCase = new Case(caseDate, caseTitle, caseType, caseNumber, caseLink, caseCategory, caseNotes);

			ccModel.caseList.add(newCase);
			ccView.caseTableView.setItems(ccModel.caseList);

			ccView.messageLabel.setText(ccView.caseTableView.getItems().size() + " cases. "); // Updates messageLabel accordingly. 
		}
	}

	class ModifyButtonHandler implements EventHandler<ActionEvent>{
		// Takes the data from all GUI controls and updates currentCase's properties so that they are updated in the main screen's view.  
		@Override
		public void handle(ActionEvent actionEvent) {
			String caseDate = caseView.caseDatePicker.getValue().format(
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String caseTitle = caseView.titleTextField.getText();
			String caseType = caseView.caseTypeTextField.getText();
			String caseNumber = caseView.caseNumberTextField.getText();
			String caseLink = caseView.caseLinkTextField.getText();
			String caseCategory = caseView.categoryTextField.getText();
			String caseNotes = caseView.caseNotesTextArea.getText();

			currentCase.setCaseDate(caseDate);
			currentCase.setCaseTitle(caseTitle);
			currentCase.setCaseType(caseType);
			currentCase.setCaseNumber(caseNumber);
			currentCase.setCaseLink(caseLink);
			currentCase.setCaseCategory(caseCategory);
			currentCase.setCaseNotes(caseNotes);

			ccView.caseTableView.setItems(ccModel.caseList);

			// display modified case
			ccView.titleTextField.setText(currentCase.getCaseTitle());
			ccView.caseTypeTextField.setText(currentCase.getCaseType());
			ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
			ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
			String year = currentCase.getCaseDate().split("-")[0];
			ccView.yearComboBox.getSelectionModel().select(year);
		}
	}

	class DeleteButtonHandler implements EventHandler<ActionEvent>{
		// removes the currentCase from caseMap and caseList. 
		@Override
		public void handle(ActionEvent actionEvent) {
			ccModel.caseList.remove(currentCase);
			ccView.caseTableView.setItems(ccModel.caseList);
			ccView.messageLabel.setText(ccView.caseTableView.getItems().size() + " cases. "); // Updates messageLabel accordingly. 
		}
	}

}
