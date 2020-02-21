package matapp.controller;

import java.awt.Color;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import matapp.utils.FormulaUtils;

public class MenuController implements Initializable {
	// MenuView

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
		// creamos una imagen que no vamos a usar
		try {
			Image image = (FormulaUtils.formulaToImage("", 30, Color.black));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// transciciones
		transiction = new HamburgerBackArrowBasicTransition(menuHamburger);
		// controllers
		slidePaneMenuController = new SlidePaneMenuController();
		fisicaMainController = new FisicaMainController();
		matrizController = new MatrixController();
		binarioController = new BinarioController();
		basicaController = new CalculadoraBasicaController();
		notaController = new NotaController();
		
		contentBorder.setCenter(basicaController.getRoot());

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

	private void onMatrizButton() {
		tipoCalculadoraLabel.setText("Matriz");
		contentBorder.setCenter(matrizController.getRoot());

		transiction.setRate(transiction.getRate() * -1);
		transiction.play();
		menuDrawer.open();// realmente lo estamos cerrando
	}

	private void onBinarioButton() {
		tipoCalculadoraLabel.setText("Programador");
		contentBorder.setCenter(binarioController.getRoot());

		transiction.setRate(transiction.getRate() * -1);
		transiction.play();
		menuDrawer.open();// realmente lo estamos cerrando
	}

	private void onEstandarButton() {// aquí nos encargaríamos de que se mostrase la calculadora especificada
		tipoCalculadoraLabel.setText("Estándar");
		contentBorder.setCenter(basicaController.getRoot());// aqui colocamos la calculadora esperada

		transiction.setRate(transiction.getRate() * -1);
		transiction.play();
		menuDrawer.open();
	}
	
	private void onNotaButton() {// aquí nos encargaríamos de que se mostrase la calculadora especificada
		tipoCalculadoraLabel.setText("Nota");
		contentBorder.setCenter(notaController.getRoot());// aqui colocamos la calculadora esperada

		transiction.setRate(transiction.getRate() * -1);
		transiction.play();
		menuDrawer.open();
	}

	private void onFisicaButton() {
		tipoCalculadoraLabel.setText("Física");
		contentBorder.setCenter(fisicaMainController.getRoot());

		transiction.setRate(transiction.getRate() * -1);
		transiction.play();
		menuDrawer.open();

	}

	public AnchorPane getRoot() {
		return root;
	}
}