package ssur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Label label;

    public void initialize() {
        label.setText("Hello World.");
    }
}