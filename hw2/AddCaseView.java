// Zhenxi Wang zhenxiw
package hw2;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;

public class AddCaseView extends CaseView {

	AddCaseView(String header) {
		super(header);

	}

	@Override
	Stage buildView() { // returns a Stage with empty GUI controls for a case
		caseDatePicker.setValue(LocalDate.now()); // show current date
		Scene sc = new Scene(updateCaseGridPane, CASE_WIDTH, CASE_HEIGHT);
		updateButton.setText("Add Case");
		stage.setScene(sc);

		return stage;
	}

}
