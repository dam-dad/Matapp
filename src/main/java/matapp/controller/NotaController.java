package matapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NotaController implements Initializable {
	/**
	 * 
	 */

	StringProperty bindeoRespuestaStringPoperty = new SimpleStringProperty();

	@FXML
	private VBox root;

	@FXML
	private Label notaLabel;

	@FXML
	private HBox valorBox;

	@FXML
	private Spinner<Integer> valorSpinner;

	@FXML
	private Button valorarButton;

	@FXML
	private Label respuestaLabel;

	public NotaController() {//carga el FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NotaView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize(URL location, ResourceBundle resources) {
		respuestaLabel.textProperty().bind(bindeoRespuestaStringPoperty);// Bindeo de la respuesta

		// Aciones de el boton
		valorarButton.setOnAction(e -> valorarButtonAction(e));
		// inicio el Spinner de la nota
		valorSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
	}

	private void valorarButtonAction(ActionEvent e) {

		// Respuesta dependiendo de la nota
		int valor = valorSpinner.getValue();
		if (valor < 5) {
			bindeoRespuestaStringPoperty.setValue("¿Enserio, suspendidos? Deberías considerarlo de nuevo");
		} else if (valor >= 5 & valor < 9) {
			bindeoRespuestaStringPoperty.setValue("Está bien, pero puedes subir un poco más");
		} else if (valor >= 9 & valor < 10) {
			bindeoRespuestaStringPoperty.setValue("Sube un poquito más, para redondear la nota ");
		} else if (valor == 10) {
			bindeoRespuestaStringPoperty.setValue("Así me gusta, ves como no era tan complicado");
		}
	}

	public VBox getRoot() {
		return root;
	}

}
