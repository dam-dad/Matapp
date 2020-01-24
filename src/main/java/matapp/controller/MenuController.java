package matapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable{
	//MenuView
	@FXML
    private BorderPane root;

    @FXML
    private JFXHamburger menuHamburger;

    @FXML
    private JFXDrawer menuDrawer;
    
    
    //SlidePaneMenuView //no funciona, no puede acceder a dichos id 

    @FXML
    private JFXButton estandarButton;

    @FXML
    private JFXButton binariaButton;

    @FXML
    private JFXButton fisicaButton;

    @FXML
    private JFXButton matricesButton;

    @FXML
    private JFXButton notaButton;
    
    private SlidePaneMenuController slidePaneMenuController; 
    
    
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
		slidePaneMenuController=new SlidePaneMenuController();

		VBox vBox=slidePaneMenuController.getRoot();
		menuDrawer.setSidePane(vBox);
		/*try {
//			basicamente cargamos otro fxml en el vBox el cual usaremos luego en nuestro drawer
			VBox vBox = FXMLLoader.load(getClass().getResource("/fxml/SlidePaneMenuView.fxml"));
			menuDrawer.setSidePane(vBox);//aquí le asignamos el contenido a drawer
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		
		//funciones del drawer
		HamburgerBackArrowBasicTransition transiction=new HamburgerBackArrowBasicTransition(menuHamburger);
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
		
		slidePaneMenuController.getEstandarButton().setOnAction(e->onEstandarButton());
	}
	
	public BorderPane getRoot() {
		return root;
	}
	
	private void onEstandarButton() {//aquí nos encargaríamos de que se mostrase la calculadora especificada
		System.out.println("Hola");
		//this.root.setCenter(); aqui colocamos la calculadora esperada
		
	}

}
