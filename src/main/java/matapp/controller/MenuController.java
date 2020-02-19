package matapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable{
	//MenuView

    @FXML
    private AnchorPane root;
    
    @FXML
    private BorderPane contentBorder;

    @FXML
    private JFXHamburger menuHamburger;

    @FXML
    private Label tipoCalculadoraLabel;

    @FXML
    private JFXDrawer menuDrawer;
    //tranciciones
    HamburgerBackArrowBasicTransition transiction;
    //controllers
    private SlidePaneMenuController slidePaneMenuController; 
    
    private FisicaMainController fisicaMainController;
    
    private MatrixController matrizController;
    
    private BinarioController binarioController;

    
    public MenuController() {
    	
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }    	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//transciciones
		transiction=new HamburgerBackArrowBasicTransition(menuHamburger);
		//controllers
		slidePaneMenuController=new SlidePaneMenuController();
		fisicaMainController=new FisicaMainController();
		matrizController = new MatrixController();
		binarioController= new BinarioController();
		
		
		
		VBox vBox=slidePaneMenuController.getRoot();
		menuDrawer.setSidePane(vBox);
		
		
		//funciones del drawer
		menuDrawer.open();
		transiction.setRate(-1);
		menuHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e->{
				transiction.setRate(transiction.getRate()*-1);				
				transiction.play();
				 
				if(menuDrawer.isOpened()) {
					menuDrawer.close();//se cierra
				}else {
					menuDrawer.open();//se abrirá
				}
		});
		//actions
		slidePaneMenuController.getEstandarButton().setOnAction(e->onEstandarButton());
		slidePaneMenuController.getFisicaButton().setOnAction(e->onFisicaButton());
		slidePaneMenuController.getMatricesButton().setOnAction(e->onMatrizButton());
		slidePaneMenuController.getBinariaButton().setOnAction(e->onBinarioButton());
	}
	
	private void onMatrizButton() {
		tipoCalculadoraLabel.setText("Matriz");
		contentBorder.setCenter(matrizController.getRoot());
		
		transiction.setRate(transiction.getRate()*-1);
		transiction.play();
		menuDrawer.open();//realmente lo estamos cerrando
	}
	
	private void onBinarioButton() {
		tipoCalculadoraLabel.setText("Binario");
		contentBorder.setCenter(binarioController.getRoot());

		
		transiction.setRate(transiction.getRate()*-1);
		transiction.play();
		menuDrawer.open();//realmente lo estamos cerrando
	}
	
	private void onEstandarButton() {//aquí nos encargaríamos de que se mostrase la calculadora especificada
		
		//root.setCenter(); aqui colocamos la calculadora esperada
		
		transiction.setRate(transiction.getRate()*-1);
		transiction.play();
		menuDrawer.open();
	}
	
	private void onFisicaButton() {
		tipoCalculadoraLabel.setText("Física");
		contentBorder.setCenter(fisicaMainController.getRoot());
		
		transiction.setRate(transiction.getRate()*-1);
		transiction.play();
		menuDrawer.open();
		
	}

	public AnchorPane getRoot() {
		return root;
	}
}