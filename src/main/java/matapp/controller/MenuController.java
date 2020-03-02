package matapp.controller;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import matapp.utils.FormulaUtils;

public class MenuController implements Initializable {
	/**
	 * @author Kilian González, Andrea Morales, Cristofer Díaz
	 * 
	 * Menú principal de la calculadora Matapp
	 * 
	 */
	//View

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
	
	// tranciciones
	HamburgerBackArrowBasicTransition transiction;
	
	// controllers
	private SlidePaneMenuController slidePaneMenuController;

	private FisicaMainController fisicaMainController;

	private MatrixController matrizController;

	private BinarioController binarioController;

	private CalculadoraBasicaController basicaController;
	
	private NotaController notaController;

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
		// precargamos una imagen para acelerar la creación del resto de imagenes
		try {
			@SuppressWarnings("unused")
			Image image = (FormulaUtils.formulaToImage("", 30, Color.black));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//transciciones
		transiction=new HamburgerBackArrowBasicTransition(menuHamburger);
	
		// controllers
		slidePaneMenuController = new SlidePaneMenuController();
		fisicaMainController=new FisicaMainController(this);
		matrizController = new MatrixController();
		binarioController = new BinarioController();
		basicaController = new CalculadoraBasicaController();
		notaController = new NotaController();
		
		//establecemos esta calculadora como la predeterminada para abrir el programa
		contentBorder.setCenter(basicaController.getRoot());
		//cargamos nuestro slidePanel
		VBox vBox = slidePaneMenuController.getRoot();
		menuDrawer.setSidePane(vBox);

		// funciones del drawer
		menuDrawer.open();
		transiction.setRate(-1);
		menuHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			transiction.setRate(transiction.getRate() * -1);
			transiction.play();

			if (menuDrawer.isOpened()) {
				menuDrawer.close();// se cierra
			} else {
				menuDrawer.open();// se abrirá
			}
		});
		
		// actions
		slidePaneMenuController.getEstandarButton().setOnAction(e -> onEstandarButton());
		slidePaneMenuController.getFisicaButton().setOnAction(e -> onFisicaButton());
		slidePaneMenuController.getMatricesButton().setOnAction(e -> onMatrizButton());
		slidePaneMenuController.getBinariaButton().setOnAction(e -> onBinarioButton());
		slidePaneMenuController.getNotaButton().setOnAction(e -> onNotaButton());
	}

	private void onMatrizButton() {//calculadora Matriz
		tipoCalculadoraLabel.setText("Matriz");
		contentBorder.setCenter(matrizController.getRoot());

		transiction.setRate(transiction.getRate() * -1);
		transiction.play();
		menuDrawer.open();// realmente lo estamos cerrando
	}

	private void onBinarioButton() {//caculadora de Programación
		tipoCalculadoraLabel.setText("Programador");
		contentBorder.setCenter(binarioController.getRoot());

		transiction.setRate(transiction.getRate() * -1);
		transiction.play();
		menuDrawer.open();// realmente lo estamos cerrando
	}

	private void onEstandarButton() {// Calculadora Estándar
		tipoCalculadoraLabel.setText("Estándar");
		contentBorder.setCenter(basicaController.getRoot());// aqui colocamos la calculadora esperada

		transiction.setRate(transiction.getRate() * -1);
		transiction.play();
		menuDrawer.open();
	}
	
	private void onNotaButton() {// Nota
		tipoCalculadoraLabel.setText("Nota");
		contentBorder.setCenter(notaController.getRoot());// aqui colocamos la calculadora esperada

		transiction.setRate(transiction.getRate() * -1);
		transiction.play();
		menuDrawer.open();
	}

	private void onFisicaButton() {//Calculadora Física
		tipoCalculadoraLabel.setText("Física");
		contentBorder.setCenter(fisicaMainController.getRoot());
		
		transiction.setRate(transiction.getRate() * -1);
		transiction.play();
		menuDrawer.open();
	}

	public AnchorPane getRoot() {
		return root;
	}
	
	public BorderPane getContent() {
		return contentBorder;
	}
	public void setContent(Node value) {//nos permitirá cambiar el contenido de manera externa
		contentBorder.setCenter(value);
	}
	
}