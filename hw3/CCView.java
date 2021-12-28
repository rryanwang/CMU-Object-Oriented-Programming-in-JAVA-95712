//Zhenxi Wang zhenxiw
package hw3;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CCView {

	WebEngine webEngine;
	double ccWidth, ccHeight;
	BooleanProperty isFileOpen = new SimpleBooleanProperty(false);

	
	/**Menu components */
	MenuItem openFileMenuItem = new MenuItem("Open");
	MenuItem saveFileMenuItem = new MenuItem("Save");  //new in HW3
	MenuItem closeFileMenuItem = new MenuItem("Close");
	MenuItem exitMenuItem = new MenuItem("Exit");
	MenuItem addCaseMenuItem = new MenuItem("Add case");
	MenuItem modifyCaseMenuItem = new MenuItem("Modify case");
	MenuItem deleteCaseMenuItem = new MenuItem("Delete case");
	MenuItem caseCountChartMenuItem = new MenuItem("Case Count Chart"); //new in HW3

	/** Main screen components **/
	Label titleLabel = new Label("Title");
	Label caseTypeLabel = new Label("Case type");
	Label yearLabel = new Label("Year");
	Label caseNumberLabel = new Label("Case number");
	Label caseNotesLabel = new Label("Case notes");
	
	Button searchButton = new Button("Search");
	Button clearButton = new Button("Clear");
	
	Label messageLabel = new Label();
	
	TextField titleTextField = new TextField();
	TextField caseTypeTextField = new TextField();
	TextField caseNumberTextField = new TextField();
	TextArea caseNotesTextArea = new TextArea();
	
	ComboBox<String> yearComboBox = new ComboBox<>();
	
	TableView<Case> caseTableView = new TableView<>();
	TableColumn<Case, String> titleColumn = new TableColumn<>("Case title");
	TableColumn<Case, String> caseTypeColumn = new TableColumn<>("Case type");
	TableColumn<Case, String> caseDateColumn = new TableColumn<>("Case date");
	TableColumn<Case, String> caseNumberColumn = new TableColumn<>("Case number");
	TableColumn<Case, String> caseCategoryColumn = new TableColumn<>("Category");
	
		
	
	
	/** seupScreen() builds the overall GUI
	 * Important operation here is the caseTableView setup.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	GridPane setupScreen() {
		GridPane root = new GridPane();
		
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		ccWidth = screenBounds.getWidth();
		ccHeight = screenBounds.getHeight();
		
		GridPane mainGrid = new GridPane();
		
		mainGrid.setVgap(5);
		mainGrid.setHgap(5);
		GridPane.setMargin(mainGrid, new Insets(10,10,10,10));

		GridPane inputGrid = new GridPane();
		inputGrid.setVgap(10);
		inputGrid.setHgap(10);

		GridPane selectionGrid = new GridPane();
		selectionGrid.setVgap(5);
		selectionGrid.setHgap(5);

		/** bind caseTableView columns with Case's properties */
		titleColumn.setCellValueFactory(new PropertyValueFactory<Case, String>("caseTitle"));
		caseTypeColumn.setCellValueFactory(new PropertyValueFactory<Case, String>("caseType"));
		caseDateColumn.setCellValueFactory(new PropertyValueFactory<Case, String>("caseDate"));
		caseNumberColumn.setCellValueFactory(new PropertyValueFactory<Case, String>("caseNumber"));
		caseCategoryColumn.setCellValueFactory(new PropertyValueFactory<Case, String>("caseCategory"));
		
		/** bind columns to the table view */
		caseTableView.getColumns().clear();
		caseTableView.getColumns().addAll(caseDateColumn, titleColumn, caseTypeColumn, caseNumberColumn, caseCategoryColumn);
		for (int i = 0; i < caseTableView.getColumns().size(); i++) 	
			caseTableView.getColumns().get(i).setPrefWidth(100);

		inputGrid.add(titleLabel, 0, 0);
		inputGrid.add(caseTypeLabel, 1, 0);
		inputGrid.add(yearLabel, 2, 0);
		inputGrid.add(caseNumberLabel, 3, 0);
		
		inputGrid.add(titleTextField, 0, 1);
		inputGrid.add(caseTypeTextField, 1, 1);
		inputGrid.add(yearComboBox, 2, 1);
		inputGrid.add(caseNumberTextField, 3, 1);
		
		inputGrid.add(searchButton, 0, 4, 2, 1);
		inputGrid.add(clearButton, 2, 4, 2, 1);
		inputGrid.add(messageLabel, 0, 5, 4, 1);
		
		selectionGrid.add(caseTableView, 0, 3);
		selectionGrid.add(caseNotesLabel, 0, 4);
		selectionGrid.add(caseNotesTextArea, 0, 5);
		
		/** setup various components' sizes*/
		inputGrid.setPrefWidth(ccWidth/2);
		titleTextField.setPrefWidth(ccWidth/8);
		caseTypeTextField.setPrefWidth(ccWidth/8);
		yearComboBox.setPrefWidth(ccWidth/8);
		caseNumberTextField.setPrefWidth(ccWidth/8);
		
		searchButton.setPrefSize(ccWidth/2, 20);
		clearButton.setPrefSize(ccWidth/2, 20);
		
		selectionGrid.setPrefWidth(ccWidth/2);
		caseTableView.setPrefSize(ccWidth/2, ccHeight/3);
		caseNotesTextArea.setPrefWidth(ccWidth/2);
		
		caseNotesTextArea.setWrapText(true);
		
		// set tool tip for case-number columns
		Label tooltipLabel = new Label("Case number");
		Tooltip tooltip = new Tooltip("7-digit number\nFirst 2 digits: year\n3rd digit: 1-competiton; 2-consumer protection\n3-advisory opinions; 4-special investigaton\nLast 4 digits: sequence no.");
		tooltipLabel.setTooltip(tooltip);
		caseNumberColumn.setText(" ");
		caseNumberColumn.setGraphic(tooltipLabel);
		
		//add all to main grid
		mainGrid.add(setupMenus(), 0, 0, 2, 1);
		mainGrid.add(showWebView(), 1, 1, 1, 5);
		mainGrid.add(inputGrid, 0, 1);
		mainGrid.add(selectionGrid, 0, 5);
		
		Background b1 = new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY));
		inputGrid.setBackground(b1);
		Background b2 = new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY));
		root.setBackground(b2);
		root.add(mainGrid, 0, 0);
		return root;
	}
	
	/**setupMenus() creates Menu Bar,
	 * adds Menus and Menu Items to it and 
	 * returns the Menu Bar.
	 * @return
	 */
	MenuBar setupMenus() {
		MenuBar menuBar = new MenuBar();
		Menu ccMenu = new Menu("Cyber Cop");
		Menu caseMenu = new Menu("Cases");
		Menu chartMenu = new Menu("Charts");  //new in HW3

		ccMenu.getItems().addAll(openFileMenuItem, saveFileMenuItem, new SeparatorMenuItem(), closeFileMenuItem, exitMenuItem);
		caseMenu.getItems().addAll(addCaseMenuItem, modifyCaseMenuItem, deleteCaseMenuItem);
		chartMenu.getItems().add(caseCountChartMenuItem);
		menuBar.getMenus().addAll(ccMenu, caseMenu, chartMenu);
		return menuBar;
	}
	
	/**showWebView positions webView in a stack 
	 * with look-and-feel settings and returns the stackpane
	 * @return
	 */
	StackPane showWebView() {
		WebView webView = new WebView();
		webView.setPrefSize(ccWidth/2, ccHeight-300);
		webEngine = webView.getEngine();
		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(webView);
		StackPane.setMargin(stackPane, new Insets(10,10,10,10));
		return stackPane;
	}
	
	/**showChartView displays the bar-chart for
	 * number of cases across all years
	 * based on yearMap
	 * @param yearMap
	 */
	void showChartView(ObservableMap<String, List<Case>> yearMap) {
		StackPane stackPane = new StackPane();
		Stage stage = new Stage();
		CCChart ccChart = new CCChart();
		stackPane.getChildren().add(ccChart.updateChart(yearMap));
		Scene scene = new Scene (stackPane, 800, 600);
		stage.setScene(scene);
		stage.setTitle("FTC Case Count");
		
		//setup upper and lower bound of y-axis
		ccChart.yAxis.setLowerBound(0);
		
		//find the max count across all years to set up y-axis in chart
		int maxCount = 0;
		for (List<Case> list : yearMap.values()) {
			if (list.size() > maxCount) maxCount = list.size();
		}
		ccChart.yAxis.setUpperBound(maxCount+5);
		stage.show();
	}
}
