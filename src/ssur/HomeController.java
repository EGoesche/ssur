package ssur;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController
{
	//Items in der Menueleiste
    @FXML private Label lb_programmname;
    @FXML private RadioButton rbtn_simulation;
    @FXML private RadioButton rbtn_rekonstruktion;
    @FXML private Button btn_start;
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
    @FXML private Label lb_fahrtrichtung;
    @FXML private ChoiceBox<String> cb_fahrtrichtung;
    @FXML private Label lb_startpunkt;
    @FXML private TextField tf_startpunkt;
    @FXML private Button btn_parameterspeichern;
    @FXML private Button btn_zuruecksetzen;
    
    //Items im Pane
    @FXML private ToggleButton tbtn_fahrzeug1;
    @FXML private ToggleButton tbtn_fahrzeug2;
    @FXML private ImageView iv_fahrzeug1;
    @FXML private ImageView iv_fahrzeug2;
    @FXML private Label lb_aufprallort;
    @FXML private Label lb_aufprallzeitpunkt;
    
    //ObservableListen fuer ChoiceBoxen
    ObservableList<String> fahrzeugtypen = FXCollections.observableArrayList("PKW", "LKW", "E-Scooter","Fahrrad");
    ObservableList<String> fahrtrichtungen = FXCollections.observableArrayList("rechts", "links");
    
	//Die zu kollidierenen Fahrzeuge werden erstellt
	Fahrzeug f1 = new Fahrzeug();
	Fahrzeug f2 = new Fahrzeug();
	
	//Bilder fuer die Fahrzeuge
	Image auto_s = new Image("ssur/icons/auto_s.png");
	Image auto_s_r = new Image("ssur/icons/auto_s_r.png");
	Image lkw_s = new Image("ssur/icons/lkw_s.png");
	Image lkw_s_r = new Image("ssur/icons/lkw_s_r.png");
	Image escooter_s = new Image("ssur/icons/escooter_s.png");
	Image escooter_s_r = new Image("ssur/icons/escooter_s_r.png");
	Image fahrrad_s = new Image("ssur/icons/fahrrad_s.png");
	Image fahrrad_s_r = new Image("ssur/icons/fahrrad_s_r.png");
	
	
    /**
     * Initialisierungsmethode
     * In dieser Methode werden die passenden Items den ChoiceBoxen zugeordnet, Tooltipps werden erstellt und die ersten Parameter werden
     * in die TextFielder und ChoiceBoxen geladen.
     */
    @FXML
    public void initialize()
    {
    	//ChoiceBoxen in der Parameterleiste werden gefuellt
    	cb_fahrzeugtyp.setItems(fahrzeugtypen);
    	cb_fahrtrichtung.setItems(fahrtrichtungen);
    	
    	//Tooltipps werden erstellt
    	cb_fahrzeugtyp.setTooltip(new Tooltip("W�hlen Sie den Typ des aktuellen Fahrzeuges."));
    	cb_fahrtrichtung.setTooltip(new Tooltip("W�hlen Sie die Fahrtrichtung des aktuellen Fahrzeuges."));
    	
    	//Textlabel fuer die Berechnungen werden versteckt
    	lb_aufprallort.setVisible(false);
    	lb_aufprallzeitpunkt.setVisible(false);
    	
    	//Parameter es ersten Fahrzeuges laden (standardmaessig)
    	ladeParameter(f1);
    }
    
    
    /**
     * Zur Einstellungs-Scene wechseln
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
    
    
    /**
     * Wechsel in den Rekonstruktionsmodus
     * @param event
     * @throws IOException
     */
    @FXML
    public void goToRekonstruktion(ActionEvent event) throws IOException
    {
    	
    }
    
    
    /**
     * Parameter speichern
     * Methode fuer das Speichern der eingegebenen Parameter. Je nachdem welches Fahrzeug ausgewaehlt wurde werden die Eingaben
     * auf dieses gespeichert. Ist kein Fahrzeug ausgewaehlt, wird eine Fehlermeldung ausgegeben.
     * @param event
     * @throws IOException
     */
    @FXML
    public void speichereParameter(ActionEvent event) throws IOException
    {
    	if (tbtn_fahrzeug1.isSelected())
    	{
    		//Lade alle Parameter von Fahrzeug 1 in die Kontrollelemente
    		f1.setGewicht(Float.parseFloat(tf_gewicht.getText()));
    		f1.setGeschwindigkeit(Float.parseFloat(tf_geschwindigkeit.getText()));
    		f1.setImpuls(Float.parseFloat(tf_impuls.getText()));
    		f1.setEkin(Float.parseFloat(tf_energie.getText()));
    		f1.setFahrzeugtyp(cb_fahrzeugtyp.getValue());
    		f1.setFahrtrichtung(cb_fahrtrichtung.getValue());
    		f1.setStartpunkt(Float.parseFloat(tf_startpunkt.getText()));
    		if (f1.getImpuls() == 0.0f) {f1.setImpuls(berechneImpuls());} //Falls der Impuls nicht eingetragen wurde, wird er berechnet
    		if (f1.getEkin() == 0.0f) {f1.setEkin(berechneEkin());} //Fals die Ekin nicht eingetragen wurde, wird sie berechnet
    		
    		ladeParameter(f1); //Parameter werden neu in die Felder geladen, da Berechnungen stattgefunden haben koennten
    		
    		//Setze das Icon fuer das Fahrzeug
    		switch(f1.getFahrzeugtyp())
    		{
    		case "PKW":
    			if (f1.getFahrtrichtung() == "rechts") {iv_fahrzeug1.setImage(auto_s);}
    			else {iv_fahrzeug1.setImage(auto_s_r);}
    			break;
    			
    		case "LKW":
    			if (f1.getFahrtrichtung() == "rechts") {iv_fahrzeug1.setImage(lkw_s);}
    			else {iv_fahrzeug1.setImage(lkw_s_r);}
    			break;
    			
    		case "E-Scooter":
    			if (f1.getFahrtrichtung() == "rechts") {iv_fahrzeug1.setImage(escooter_s);}
    			else {iv_fahrzeug1.setImage(escooter_s_r);}
    			break;
    			
    		case "Fahrrad":
    			if (f1.getFahrtrichtung() == "rechts") {iv_fahrzeug1.setImage(fahrrad_s);}
    			else {iv_fahrzeug1.setImage(fahrrad_s_r);}
    			break;
    			
    		default:
    			System.out.println("Fehler beim Wechseln des Fahrzeugicons!");
    			break;
    		}
    	}
    	
    	else if (tbtn_fahrzeug2.isSelected())
    	{
    		//Lade alle Parameter von Fahrzeug 2 in die Kontrollelemente
    		f2.setGewicht(Float.parseFloat(tf_gewicht.getText()));
    		f2.setGeschwindigkeit(Float.parseFloat(tf_geschwindigkeit.getText()));
    		f2.setImpuls(Float.parseFloat(tf_impuls.getText()));
    		f2.setEkin(Float.parseFloat(tf_energie.getText()));
    		f2.setFahrzeugtyp(cb_fahrzeugtyp.getValue());
    		f2.setFahrtrichtung(cb_fahrtrichtung.getValue());
    		f2.setStartpunkt(Float.parseFloat(tf_startpunkt.getText()));
    		if (f2.getImpuls() == 0.0f) {f2.setImpuls(berechneImpuls());} //Falls der Impuls nicht eingetragen wurde, wird er berechnet
    		if (f2.getEkin() == 0.0f) {f2.setEkin(berechneEkin());} //Fals die Ekin nicht eingetragen wurde, wird sie berechnet
    		
    		ladeParameter(f2); //Parameter werden neu in die Felder geladen, da Berechnungen stattgefunden haben koennten
    		
    		//Setze das Icon fuer das Fahrzeug
    		switch(f2.getFahrzeugtyp())
    		{
    		case "PKW":
    			if (f2.getFahrtrichtung() == "rechts") {iv_fahrzeug2.setImage(auto_s);}
    			else {iv_fahrzeug2.setImage(auto_s_r);}
    			break;
    			
    		case "LKW":
    			if (f2.getFahrtrichtung() == "rechts") {iv_fahrzeug2.setImage(lkw_s);}
    			else {iv_fahrzeug2.setImage(lkw_s_r);}
    			break;
    			
    		case "E-Scooter":
    			if (f2.getFahrtrichtung() == "rechts") {iv_fahrzeug2.setImage(escooter_s);}
    			else {iv_fahrzeug2.setImage(escooter_s_r);}
    			break;
    			
    		case "Fahrrad":
    			if (f2.getFahrtrichtung() == "rechts") {iv_fahrzeug2.setImage(fahrrad_s);}
    			else {iv_fahrzeug2.setImage(fahrrad_s_r);}
    			break;
    			
    		default:
    			System.out.println("Fehler beim Wechseln des Fahrzeugicons!");
    			break;
    		}
    	}
    	else
    	{
    		//Falls kein Fahrzeug gewaehlt wurde wird ein Fehler ausgegeben
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Fehlermeldung");
    		alert.setHeaderText("Fehler beim Speichern");
    		alert.setContentText("Bitte w�hlen Sie zuerst ein Fahrzeug!");
    		alert.showAndWait();
    	}
    }
    
    
    /**
     * FXML Event-Methode um die Parameter des jeweiligen Fahrzeuges in die TextFelder bzw. in die ChoiceBoxen zu laden
     * @param event
     */
    @FXML
    public void ladeParameter(MouseEvent event)
    {
    	ToggleButton tbtn = (ToggleButton) event.getSource();
    	if (tbtn == tbtn_fahrzeug1) {ladeParameter(f1);}
    	if (tbtn == tbtn_fahrzeug2) {ladeParameter(f2);}
    }
    
    
    @FXML
    public void berechnungZuruecksetzen(ActionEvent event)
    {
    	if (lb_aufprallort.isVisible()) //Wenn dieses Label sichtbar ist, fand bereits eine Berechnung statt
    	{
    		//Fahrzeuge werden in Ausgangsposition gebracht
    		Path path1 = new Path();
    		path1.getElements().add(new MoveTo(275, 75));  //Startpunkt der Animation	
    		path1.getElements().add(new LineTo(76, 75));
    	
    		Path path2 = new Path();
    		path2.getElements().add(new MoveTo(-109, 75));  //Startpunkt der Animation	
    		path2.getElements().add(new LineTo(76, 75));
    	
    		PathTransition ptr1 = new PathTransition();
    		ptr1.setDuration(Duration.seconds(0.5));
    		ptr1.setPath(path1);
    		ptr1.setNode(iv_fahrzeug1);
    		ptr1.setAutoReverse(false);
    		
    		PathTransition ptr2 = new PathTransition();
    		ptr2.setDuration(Duration.seconds(0.5));
    		ptr2.setPath(path2);
    		ptr2.setNode(iv_fahrzeug2);
    		ptr2.setAutoReverse(false);
    		
    		ptr1.play();
    		ptr2.play();
        
    		//Label fuer die letzte Berechnung werden ausgeblendet
    		lb_aufprallort.setVisible(false);
    		lb_aufprallzeitpunkt.setVisible(false);
    	}
        
    	//Alle Parameter der Fahrzeuge werden zurueckgesetzt
    	f1.setEkin(0);
    	f1.setFahrtrichtung("rechts");
    	f1.setFahrzeugtyp("PKW");
    	f1.setGeschwindigkeit(0);
    	f1.setGewicht(0);
    	f1.setImpuls(0);
    	f1.setStartpunkt(0);
    	
    	f2.setEkin(0);
    	f2.setFahrtrichtung("links");
    	f2.setFahrzeugtyp("PKW");
    	f2.setGeschwindigkeit(0);
    	f2.setGewicht(0);
    	f2.setImpuls(0);
    	f2.setStartpunkt(0);
    	
    	//Parameter werden neugeladen
    	ladeParameter(f1);
    }
    
    
    @FXML
    public void starteBerechnung(ActionEvent event)
    {
    	float aufprallort = berechneAufprallort(f1.getGeschwindigkeit(), f2.getGeschwindigkeit(), f1.getFahrtrichtung(), f2.getFahrtrichtung(), f1.getStartpunkt(), f2.getStartpunkt());
    	float aufprallzeitpunkt = berechneAufprallzeitpunkt(f1.getGeschwindigkeit(), f2.getGeschwindigkeit(), f1.getFahrtrichtung(), f2.getFahrtrichtung(), f1.getStartpunkt(), f2.getStartpunkt());
    	System.out.println("Aufprallort: " + rundeFloat(aufprallort) + "\n" + "Aufprallzeitpunkt: " + rundeFloat(aufprallzeitpunkt));
    	
    	if (aufprallort != -1.0 && aufprallzeitpunkt != -1.0) //Abfrage, ob Berechnung erfolgreich war
    	{
    		//Unnoetige Einblendung des Aufprallortes
    		FadeTransition fadeInaufprallort = new FadeTransition(Duration.millis(1000));
    		fadeInaufprallort.setNode(lb_aufprallort);
    	    fadeInaufprallort.setFromValue(0.0);
    	    fadeInaufprallort.setToValue(1.0);
    	    fadeInaufprallort.setCycleCount(1);
    	    fadeInaufprallort.setAutoReverse(false);
    		lb_aufprallort.setVisible(true);
    		fadeInaufprallort.playFromStart();
    		
    		//Unnoetige Einblendung des Aufprallzeitpunktes
    		FadeTransition fadeInaufprallzeitpunkt = new FadeTransition(Duration.millis(1000));
    		fadeInaufprallzeitpunkt.setNode(lb_aufprallzeitpunkt);
    	    fadeInaufprallzeitpunkt.setFromValue(0.0);
    	    fadeInaufprallzeitpunkt.setToValue(1.0);
    	    fadeInaufprallzeitpunkt.setCycleCount(1);
    	    fadeInaufprallzeitpunkt.setAutoReverse(false);
        	lb_aufprallzeitpunkt.setVisible(true);
    		fadeInaufprallzeitpunkt.playFromStart();
    		
    		starteAnimationFirst(); //Animation bis zum Stoss wird gestartet
    		
    		//Berechnungen werden in die Label geschrieben
    		lb_aufprallort.setText("Aufprallort: " + rundeFloat(aufprallort) + "m");
        	lb_aufprallzeitpunkt.setText("Aufprallzeitpunkt: " + rundeFloat(aufprallzeitpunkt) + "s");
        	
        	//Alle Fahrzeuge werden nicht mehr selektiert
        	tbtn_fahrzeug1.setSelected(false);
        	tbtn_fahrzeug2.setSelected(false);
        	
        	float nachstossgeschwindgkeit = (f1.getImpuls() + f2.getImpuls()) / (f1.getGewicht() + f2.getGewicht());
        	System.out.println("Geschwindigkeit nach dem Sto�: " + nachstossgeschwindgkeit);
    	}
    	else
    	{
    		//Falls Berechnung erfolglos war
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Fehlermeldung");
    		alert.setHeaderText("Fehler bei der Berechnung");
    		alert.setContentText("Es kann zu keinem Zusammensto� der Fahrzeuge kommen!");
    		alert.showAndWait();
    	}
    	
    }
    
    
    /**
     * Parameter des jeweiligen Fahrzeuges werden in die TextFelder bzw. in die ChoiceBoxen geladen
     * @param fahrzeug
     */
    public void ladeParameter(Fahrzeug fahrzeug)
    {
 	   setTextField(tf_gewicht, Float.toString(fahrzeug.getGewicht()));
 	   setTextField(tf_geschwindigkeit, Float.toString(fahrzeug.getGeschwindigkeit()));
 	   setChoiceBox(cb_fahrtrichtung, fahrzeug.getFahrtrichtung());
 	   setTextField(tf_impuls, Float.toString(fahrzeug.getImpuls()));
 	   setTextField(tf_energie, Float.toString(fahrzeug.getEkin()));
 	   setChoiceBox(cb_fahrzeugtyp, fahrzeug.getFahrzeugtyp());
 	   setTextField(tf_startpunkt, Float.toString(fahrzeug.getStartpunkt()));
    }
    
    
    /**
     * Animation starten
     * Methode fuer die Animation der beiden Fahrzeuge
     */
    public void starteAnimationFirst()
    {
    	Path path1 = new Path();
    	path1.getElements().add(new MoveTo(76, 75));  //Startpunkt der Animation	
    	path1.getElements().add(new LineTo(275, 75));
    	
    	Path path2 = new Path();
    	path2.getElements().add(new MoveTo(76, 75));  //Startpunkt der Animation	
    	path2.getElements().add(new LineTo(-109, 75));
    	
    	PathTransition ptr1 = new PathTransition();
    	ptr1.setDuration(Duration.seconds(2));
        ptr1.setPath(path1);
        ptr1.setNode(iv_fahrzeug1);
        ptr1.setAutoReverse(false);
        
        PathTransition ptr2 = new PathTransition();
        ptr2.setDuration(Duration.seconds(2));
        ptr2.setPath(path2);
        ptr2.setNode(iv_fahrzeug2);
        ptr2.setAutoReverse(false);
        
        ptr1.play();
        ptr2.play();
        
        
        
    }
    
    
    /**
     * Float runden
     * Methode zum Runden einer Floatzahl
     * @param eingabe
     * @return
     */
    public float rundeFloat(float eingabe)
    {
    	return (Math.round(100.0f * eingabe) / 100.0f);
    }
    
    
    /**
     * Impuls berechnen
     * Methode zum Berechnen des Impulses des ausgewaehlten Fahrzueges aus den aktuellen TextFields
     * @return
     */
    public float berechneImpuls()
    {
    	if (cb_fahrtrichtung.getValue() == "links") {return (Float.parseFloat(tf_geschwindigkeit.getText()) * Float.parseFloat(tf_gewicht.getText())) * -1;} //Falls sich das Fahrzeug nach links bewegt, ist die Geschwundigkeit negativ
    	return Float.parseFloat(tf_geschwindigkeit.getText()) * Float.parseFloat(tf_gewicht.getText());
    }
    
    
    /**
     * Kinetische Energie berechnen
     * Methode zum Berechnen der kinetischen Energie desd ausgewaehlten Fahrzeuges aus den aktuellen TextFields
     * @return
     */
    public float berechneEkin()
    {
    	return (Float.parseFloat(tf_gewicht.getText()) / 2.0f) * (Float.parseFloat(tf_geschwindigkeit.getText()) * Float.parseFloat(tf_geschwindigkeit.getText()));
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
     * Methode um Inhalt fuer ein TextField setzen zu koennen
     * @param textField
     * @param value
     */
    public void setTextField(TextField textField, String value)
    {
    	textField.setText(value);
    }
    
    
    /**
     * Berechnung Aufprallzeitpunkt
     * Methode zur Berechnung des Aufprallzeitpunktes zweier Fahrzeuge. Kommt es zu keinem Aufprall, wird ein Fehler ausgegeben
     * und der Wert -1 zurueckgegeben.
     * @param geschw1
     * @param geschw2
     * @param fahrtricht1
     * @param fahrtricht2
     * @param startp1
     * @param startp2
     * @return
     */
    public float berechneAufprallzeitpunkt(float geschw1, float geschw2, String fahrtricht1, String fahrtricht2,
    		float startp1, float startp2)
    {
    	if (geschw1 == geschw2 && fahrtricht1 == fahrtricht2) //Fahrzeuge fahren gleichschnell in selbe Richtung
    	{
    		System.out.println("Fehler! Fahrzeuge treffen sich nie.");
    		return -1;
    	}
    	
    	else if (geschw1 == 0 && geschw2 == 0) //Fahrzeuge stehen beide still
    	{
    		System.out.println("Fehler! Fahrzeuge treffen sich nie.");
    		return -1;
    	}
    			
    	else if (geschw1 == 0 ^ geschw2 == 0) //Eines der beiden Fahrzeuge steht still, aber nicht beide
    	{
    		if (geschw1 == 0) {return (Math.abs(startp1 - startp2) / (geschw2 / 3.6f));}
    		else {return (Math.abs(startp1 - startp2) / (geschw1 / 3.6f));}
    	}
    	
    	else if (fahrtricht1 != fahrtricht2) //Die Fahrzeuge fahren sich entgegen
    	{
    	   	float geschwindigkeit = Math.abs((geschw1 / 3.6f)) + Math.abs((geschw2 / 3.6f));
    	   	float strecke = Math.abs(startp1) + Math.abs(startp2);
    	   	return (strecke / geschwindigkeit);
    	}
    	else //Die Fahrzeuge fahren in die selbe Richtung
    	{
    		return Math.abs((startp1 - startp2) / Math.abs((geschw1 / 3.6f) - (geschw2 / 3.6f)));
    	}
    }
    
    
    /**
     * Berechnung Aufprallort
     * Methode zur Berechnung des Aufprallortes zweier Fahrzeuge. Kommt es zu keinem Aufprall, wird ein Fehler ausgegeben
     * und der Wert -1 zurueckgegeben.
     * @param geschw1
     * @param geschw2
     * @param fahrtricht1
     * @param fahrtricht2
     * @param startp1
     * @param startp2
     * @return
     */
    public float berechneAufprallort(float geschw1, float geschw2, String fahrtricht1, String fahrtricht2,
    		float startp1, float startp2)
    {
    	if (geschw1 == geschw2 && fahrtricht1 == fahrtricht2) //Fahrzeuge fahren gleichschnell in selbe Richtung
    	{
    		System.out.println("Fehler! Fahrzeuge treffen sich nie.");
    		return -1;
    	}
    	
    	else if (geschw1 == 0 && geschw2 == 0) //Fahrzeuge stehen beide still
    	{
    		System.out.println("Fehler! Fahrzeuge treffen sich nie.");
    		return -1;
    	}
    			
    	else if (geschw1 == 0 ^ geschw2 == 0) //Eines der beiden Fahrzeuge steht still, aber nicht beide
    	{
    		if (geschw1 == 0) {return startp1;}
    		else {return startp2;}
    	}
    		
    	else if (fahrtricht1 != fahrtricht2) //Die Fahrzeuge fahren sich entgegen
    	{
    		float geschwindigkeit = Math.abs((geschw1 / 3.6f)) + Math.abs((geschw2 / 3.6f));
    	    float strecke = Math.abs(startp1) + Math.abs(startp2);
    	    float aufprallzeitpunkt = (strecke / geschwindigkeit);
    	    	
    	    return (aufprallzeitpunkt * (geschw1 / 3.6f));
    	}
    	else //Die Fahrzeuge fahren in die selbe Richtung
    	{
    		float aufprallzeitpunkt = Math.abs((startp1 - startp2) / Math.abs((geschw1 / 3.6f) - (geschw2 / 3.6f)));
    			
    		return ((geschw1 / 3.6f) * aufprallzeitpunkt);
    	}
    }
        
}