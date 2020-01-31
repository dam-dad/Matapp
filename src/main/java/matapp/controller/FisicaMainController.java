package matapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class FisicaMainController implements Initializable{
    //FisicaMainView
	@FXML
    private GridPane root;

    @FXML
    private JFXComboBox<String> temaCombo;

    @FXML
    private JFXComboBox<String> formulaCombo;

    @FXML
    private Label variable1Label;

    @FXML
    private JFXTextField variable1Text;

    @FXML
    private Label magnitud1Label;

    @FXML
    private Label variable2Label;

    @FXML
    private JFXTextField variable2Text;

    @FXML
    private Label magnitud2Label;

    @FXML
    private Label variable3Label;

    @FXML
    private JFXTextField variable3Text;

    @FXML
    private Label magnitud3Label;

    @FXML
    private Label variable4Label;

    @FXML
    private JFXTextField variable4Text;

    @FXML
    private Label magnitud4Label;

    @FXML
    private Label variable5Label;

    @FXML
    private JFXTextField variable5Text;

    @FXML
    private Label magnitud5Label;

    @FXML
    private Label variable6Label;

    @FXML
    private JFXTextField variable6Text;

    @FXML
    private Label magnitud6Label;

    @FXML
    private Button calcularButton;
//	model
    
    ListProperty<String> listaTemas=new SimpleListProperty<>(FXCollections.observableArrayList());
    
    ListProperty<String> listaFormulas=new SimpleListProperty<>(FXCollections.observableArrayList());
    
    
    public FisicaMainController() {
    	
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FisicaMainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }    	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		variable1Label.setText("λ");
		
		temaCombo.itemsProperty().bind(listaTemas);
		formulaCombo.itemsProperty().bind(listaFormulas);
		
		
		temaCombo.getSelectionModel().selectedItemProperty().addListener((o,ov,nv)->{
			//rellenar
		});
		
		formulaCombo.getSelectionModel().selectedItemProperty().addListener((o,ov,nv)->{
			//rellenar
		});
		
	}
	public GridPane getRoot() {
		return root;
	}
}
