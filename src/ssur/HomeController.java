package ssur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class HomeController {

    @FXML
    private Label label;

    public void initialize() {
        label.setText("Hello World.");
        
        RadioButton radioButton=new RadioButton("Radio");
        radioButton.getStyleClass().remove("radio-button");
        radioButton.getStyleClass().add("toggle-button");
    }
}