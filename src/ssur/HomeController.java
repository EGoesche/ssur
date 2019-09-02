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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    @FXML private Button btn_parameterspeichern;
    
    //Items im Pane
    @FXML private ToggleButton btn_fahrzeug1;
    @FXML private ToggleButton btn_fahrzeug2;
    @FXML private ImageView iv_fahrzeug1;
    @FXML private ImageView iv_fahrzeug2;
    
    //ObservableListen fuer ChoiceBoxen
    ObservableList<String> fahrzeugtypen = FXCollections.observableArrayList("PKW", "LKW");
    ObservableList<String> fahrzeugfarben = FXCollections.observableArrayList("rot", "blau", "grün", "schwarz");
    
	//Die zu kollidierenen Fahrzeuge werden erstellt
	Fahrzeug f1 = new Fahrzeug();
	Fahrzeug f2 = new Fahrzeug();
	
    /**
     * Initialisierungsmethode
     */
    @FXML
    public void initialize()
    {
    	//ChoiceBoxen in der Parameterleiste werden gefuellt
    	cb_fahrzeugtyp.setItems(fahrzeugtypen);
    	cb_fahrzeugfarbe.setItems(fahrzeugfarben);
    	
    	//Tooltipps werden erstellt
    	cb_fahrzeugtyp.setTooltip(new Tooltip("Wählen Sie den Typ des aktuellen Fahrzeuges."));
    	cb_fahrzeugfarbe.setTooltip(new Tooltip("Wählen Sie eine Farbe für das aktuelle Fahrzeug."));
    	
    	
    	
    	//Parameter des beim Programmstart ausgewaehlten Fahrzeug werden  geladen
    	ladeParameter(f1);
    	
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
    	window.setResizable(false);
    	window.show();
    }
    
    
    @FXML
    public void speichereParameter(ActionEvent event) throws IOException
    {
    	//if f1 angewaehlt ... if f2 angewaehlt ...
    }
    
    
    /**
     * Methode fuer die Abfrage des aktuellen Inhaltes einer ChoiceBox
     * @param choiceBox
     * @return
     */
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
    public void setChoiceBox(ChoiceBox<String> choiceBox, String value)
    {
    	choiceBox.setValue(value);
    }
    
    
    /**
     * Methode fuer die Abfrage des aktuellen Inhaltes eines TextFields
     * @param textField
     * @return
     */
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
    public void setTextField(TextField textField, String value)
    {
    	textField.setText(value);
    }
    
    @FXML
    public void ladeParameter(MouseEvent event)
    {
    	ToggleButton btn = (ToggleButton) event.getSource();
    	if (btn == btn_fahrzeug1) {ladeParameter(f1);}
    	if (btn == btn_fahrzeug2) {ladeParameter(f2);}
    }
    
    public void ladeParameter(Fahrzeug fahrzeug)
    {
 	   setTextField(tf_gewicht, Float.toString(fahrzeug.getGewicht()));
 	   setTextField(tf_geschwindigkeit, Float.toString(fahrzeug.getGeschwindigkeit()));
 	   setTextField(tf_impuls, Float.toString(fahrzeug.getImpuls()));
 	   setTextField(tf_energie, Float.toString(fahrzeug.getEkin()));
 	   setChoiceBox(cb_fahrzeugtyp, fahrzeug.getFahrzeugtyp());
 	   setChoiceBox(cb_fahrzeugfarbe, fahrzeug.getFarbcode());
    }
    
    
    /**
     * Methode zur Berechnung des Aufprallzeitpunktes zweier Fahrzeuge.
     * @param mitReibung
     * @param geschw1
     * @param geschw2
     * @param fahrtricht1
     * @param fahrtricht2
     * @param startp1
     * @param startp2
     * @return
     */
    public float berechneAuftreffzeitpunkt(boolean mitReibung, float geschw1, float geschw2, int fahrtricht1, int fahrtricht2,
    		float startp1, float startp2)
    {
    	if (mitReibung) //mit Reibung
    	{
    		if (geschw1 == geschw2) //Beide Fahrzeuge sind gleich schnell
    		{
    			System.out.println("Fehler!");
    			return -1;
    		}
    			
    		if (geschw1 == 0 ^ geschw2 == 0) //Eines der beiden Fahrzeuge steht still, aber nicht beide
    		{
    			return -1;
    		}
    		
    		if (fahrtricht1 != fahrtricht2) //Die Fahrzeuge fahren sich entgegen
    		{
    			return -1;
    		}
    		else //Die Fahrzeuge fahren in die selbe Richtung
    		{
    			return -1;
    		}
    	}
    	else //ohne Reibung
    	{
    		if (geschw1 == geschw2) //Beide Fahrzeuge sind gleich schnell
    		{
    			System.out.println("Fehler!");
    			return -1;
    		}
    			
    		if (geschw1 == 0 ^ geschw2 == 0) //Eines der beiden Fahrzeuge steht still, aber nicht beide
    		{
    			if (geschw1 == 0) {return (Math.abs(startp1 - startp2) / geschw2);}
    			else {return (Math.abs(startp1 - startp2) / geschw1);}
    		}
    		
    		if (fahrtricht1 != fahrtricht2) //Die Fahrzeuge fahren sich entgegen
    		{
    	    	float geschwindigkeit = Math.abs(geschw1) + Math.abs(geschw2);
    	    	float strecke = Math.abs(startp1) + Math.abs(startp2);
    	    	return (strecke / geschwindigkeit);
    		}
    		else //Die Fahrzeuge fahren in die selbe Richtung
    		{
    			return Math.abs((startp1 - startp2) / Math.abs(geschw1 - geschw2));
    		}
    	}
    }
    
    
    /**
     * Methode zur Berechnung des Aufprallortes zweier Fahrzeuge
     * @param mitReibung
     * @param geschw1
     * @param geschw2
     * @param fahrtricht1
     * @param fahrtricht2
     * @param startp1
     * @param startp2
     * @return
     */
    public float berechneAufprallort(boolean mitReibung, float geschw1, float geschw2, int fahrtricht1, int fahrtricht2,
    		float startp1, float startp2)
    {
    	if (mitReibung) //mit Reibung
    	{
    		if (geschw1 == geschw2) //Beide Fahrzeuge sind gleich schnell
    		{
    			System.out.println("Fehler!");
    			return -1;
    		}
    			
    		if (geschw1 == 0 ^ geschw2 == 0) //Eines der beiden Fahrzeuge steht still, aber nicht beide
    		{
    			return -1;
    		}
    		
    		if (fahrtricht1 != fahrtricht2) //Die Fahrzeuge fahren sich entgegen
    		{
    			return -1;
    		}
    		else //Die Fahrzeuge fahren in die selbe Richtung
    		{
    			return -1;
    		}
    	}
    	else //ohne Reibung
    	{
    		if (geschw1 == geschw2) //Beide Fahrzeuge sind gleich schnell
    		{
    			System.out.println("Fehler!");
    			return -1;
    		}
    			
    		if (geschw1 == 0 ^ geschw2 == 0) //Eines der beiden Fahrzeuge steht still, aber nicht beide
    		{
    			if (geschw1 == 0) {return startp1;}
    			else {return startp2;}
    		}
    		
    		if (fahrtricht1 != fahrtricht2) //Die Fahrzeuge fahren sich entgegen
    		{
    			float geschwindigkeit = Math.abs(geschw1) + Math.abs(geschw2);
    	    	float strecke = Math.abs(startp1) + Math.abs(startp2);
    	    	float aufprallzeitpunkt = (strecke / geschwindigkeit);
    	    	
    	    	return (aufprallzeitpunkt * geschw1);
    		}
    		else //Die Fahrzeuge fahren in die selbe Richtung
    		{
    			float aufprallzeitpunkt = Math.abs((startp1 - startp2) / Math.abs(geschw1 - geschw2));
    			
    			return (geschw1 * aufprallzeitpunkt);
    		}
    	}
    }
    
        
}