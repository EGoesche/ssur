package ssur;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class HomeController
{
	//Items in der Menueleiste
    @FXML private Label lb_programmname;
    @FXML private RadioButton rbtn_simulation;
    @FXML private RadioButton rbtn_rekonstruktion;
    @FXML private Button btn_dateneingabe;
    @FXML private Button btn_einstellungen;
    @FXML private Label lb_version;
    
    //Items in der Parameterleiste
    @FXML private Label lb_parametertitel;
    @FXML private TextField tf_gewicht;
    @FXML private Label lb_geschwindigkeit;
    @FXML private TextField tf_geschwindigkeit;
    @FXML private Label lb_impuls;
    @FXML private TextField tf_impuls;
    @FXML private Label lb_energie;
    @FXML private TextField tf_energie;
    @FXML private Label lb_fahrzeugtyp;
    @FXML private ChoiceBox<String> cb_fahrzeugtyp;
    @FXML private Label lb_fahrzeugfarbe;
    @FXML private ChoiceBox<String> cb_fahrzeugfarbe;
    
    //ObservableListen fuer ChoiceBoxen
    ObservableList<String> fahrzeugtypen = FXCollections.observableArrayList("PKW", "LKW");
    ObservableList<String> fahrzeugfarben = FXCollections.observableArrayList("rot", "blau", "grün", "schwarz");
    
    
    /**
     * Initialisierungsmethode
     */
    @FXML
    public void initialize()
    {
    	//ChoiceBoxen in der Parameterleiste werden gefuellt
    	cb_fahrzeugtyp.setItems(fahrzeugtypen);
    	cb_fahrzeugfarbe.setItems(fahrzeugfarben);
    	
    	//cb_fahrzeugtyp.setValue("Bitte .");
    	
    	//Tooltipps werden erstellt
    	cb_fahrzeugtyp.setTooltip(new Tooltip("Wählen Sie den Typ des aktuellen Fahrzeuges."));
    	cb_fahrzeugfarbe.setTooltip(new Tooltip("Wählen Sie eine Farbe für das aktuelle Fahrzeug."));
    }
    
    
    /**
     * Methode fuer den Wechsel zur Scene Einstellungen
     * @throws IOException 
     */
    @FXML
    public void goToSettings(ActionEvent event) throws IOException
    {
    	Parent settingsParent = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        settingsParent.getStylesheets().add(getClass().getResource("Style.css").toExternalForm()); 
        Scene settings = new Scene(settingsParent);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(settings);
    	window.show();
    }
    
    
    /**
     * Methode fuer die Abfrage des aktuellen Inhaltes einer ChoiceBox
     * @param choiceBox
     * @return
     */
    @FXML
    public String getChoiceBox(ChoiceBox<String> choiceBox)
    {
    	String value = choiceBox.getValue();
    	return value;
    }
    
    
    /**
     * Methode um Inhalt fuer eine ChoiceBox setzen zu koennen
     * @param choiceBox
     * @param value
     */
    @FXML
    public void setChoiceBox(ChoiceBox<String> choiceBox, String value)
    {
    	choiceBox.setValue(value);
    }
    
    
    /**
     * Methode fuer die Abfrage des aktuellen Inhaltes eines TextFields
     * @param textField
     * @return
     */
    @FXML
    public String getTextField(TextField textField)
    {
    	String value = textField.getText();
    	return value;
    }
    
    
    /**
     * Methode um Inhalt fuer ein TextField setzen zu koennen
     * @param textField
     * @param value
     */
    @FXML
    public void setTextField(TextField textField, String value)
    {
    	textField.setText(value);
    }
    
    
 

    
        
}