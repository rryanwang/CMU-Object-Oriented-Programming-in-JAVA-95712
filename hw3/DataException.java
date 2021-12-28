//Zhenxi Wang zhenxiw
package hw3;

import java.lang.RuntimeException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("serial")
public class DataException extends RuntimeException{
    DataException(String message){
        Alert alert = new Alert(AlertType.ERROR);
     alert.setTitle("Data Error");
  alert.setContentText(message);
  alert.showAndWait();
    }
}