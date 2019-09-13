package ssur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SettingsController 
{
	//Items
	@FXML private Label lb1;
	@FXML private Button btn1;
	
	/**
     * Methode fuer den Wechsel zur Scene Home
     * @throws IOException 
     */
    @FXML
    public void goToHome(ActionEvent event) throws IOException
    {
    	Parent homeParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        homeParent.getStylesheets().add(getClass().getResource("Style.css").toExternalForm()); 
        Scene home = new Scene(homeParent);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(home);
    	//window.getIcons().add(new Image(this.getClass().getResource("login.png").toString()));
    	window.setResizable(false);
    	window.show();
    }
}
