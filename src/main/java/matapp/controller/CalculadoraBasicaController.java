package matapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;



public class CalculadoraBasicaController implements Initializable {

	private boolean puntoPuesto = false;//el punto esta puesto o no 
	
	private String primerValor = "0";// Guarda el primer numero de la operacion

	private StringProperty bindeoValorStringPoperty = new SimpleStringProperty();// String a mostrar los numeros
	private StringProperty bindeoOperacionStringPoperty = new SimpleStringProperty();// String para mostrar que
																						// operacion estas
																						// haciendo(suma,resta...)


	// valores del view FXML
	@FXML
	private GridPane root;
	@FXML
	private Label valorLabel, operacionLabel;
	@FXML
	private Button ceroButton, unoButton, dosButton, tresButton, cuatroButton, cincoButton, seisButton, sieteButton,
			ochoButton, nueveButton, multiButton, divButton, menosButton, masButton, igualButton, puntoButton, ceButton,
			cButton;
	public CalculadoraBasicaController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BasicaView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		valorLabel.textProperty().bind(bindeoValorStringPoperty);// Bindeo del valor que introduces
		operacionLabel.textProperty().bind(bindeoOperacionStringPoperty);// Bindeo de la operacion a realizar

		// Aciones de los botones
		ceroButton.setOnAction(e -> numeroButtonAction(e, "0"));
		unoButton.setOnAction(e -> numeroButtonAction(e, "1"));
		dosButton.setOnAction(e -> numeroButtonAction(e, "2"));
		tresButton.setOnAction(e -> numeroButtonAction(e, "3"));
		cuatroButton.setOnAction(e -> numeroButtonAction(e, "4"));
		cincoButton.setOnAction(e -> numeroButtonAction(e, "5"));
		seisButton.setOnAction(e -> numeroButtonAction(e, "6"));
		sieteButton.setOnAction(e -> numeroButtonAction(e, "7"));
		ochoButton.setOnAction(e -> numeroButtonAction(e, "8"));
		nueveButton.setOnAction(e -> numeroButtonAction(e, "9"));
		ceButton.setOnAction(e -> ceButtonAction(e));
		cButton.setOnAction(e -> cButtonAction(e));
		masButton.setOnAction(e -> sumaButtonAction(e));
		menosButton.setOnAction(e -> restaButtonAction(e));
		multiButton.setOnAction(e -> multiplicacionButtonAction(e));
		divButton.setOnAction(e -> divisionButtonAction(e));
		igualButton.setOnAction(e -> igualButtonAction(e));
		puntoButton.setOnAction(e -> puntoButtonAction(e));

	}

	// Funcion "Action" para los numeros recibe el evento "e" y el numero del boton
	private void numeroButtonAction(ActionEvent e, String numero) {
		try {
			// Si es "Null" lo coloca como primer valor y si no lo concatena a lo anterior
			if (bindeoValorStringPoperty.getValue() == null) {
				bindeoValorStringPoperty.setValue(numero);
				;
			} else {
				bindeoValorStringPoperty.setValue(bindeoValorStringPoperty.getValue() + numero);
			}
		} catch (java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo con el número");
			alert.setContentText("El elemento es demaciado grande");
			alert.showAndWait();
		}
	}

	// Funcion "Action" para el boton "CE"
	private void ceButtonAction(ActionEvent e) {
		bindeoValorStringPoperty.setValue("");// Limpia todo
		primerValor = "0";// El primer valor lo elimina
		puntoPuesto = false;
		// Elimina los valores
		bindeoValorStringPoperty.setValue("");
		bindeoOperacionStringPoperty.setValue("");
	}

	// Funcion "Action" para el boton "C"
	private void cButtonAction(ActionEvent e) { 
		try {
		
			String nuevo = bindeoValorStringPoperty.getValue();// recoge el valor
			char[]eliminar = nuevo.toCharArray();
					if (eliminar[eliminar.length-1] == '.') {//si elimino el punto cambio la variable 
						puntoPuesto= false;
					}
			// borra el ultimo valor y lo guarda
			nuevo = nuevo.substring(0, nuevo.length() - 1);
			bindeoValorStringPoperty.setValue(nuevo);
			// Muestra una alerta si el valor esta vacio
		} catch (java.lang.NullPointerException | java.lang.ArrayIndexOutOfBoundsException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al borrar");
			alert.setContentText("No borres un elemento vacio");
			alert.showAndWait();
		}
	}

	// Funcion "Action" para el boton suma
	private void sumaButtonAction(ActionEvent e) {
		try {
			bindeoOperacionStringPoperty.setValue("+");// muestro que esta sumando
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {// Comprueba que no este vacio
				if (!(primerValor == "0")) {// si ya tiene un valor lo suma
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();// Guardo el primer valor
				}
				
				bindeoValorStringPoperty.setValue("");// lo vacio para introducir el segundo valor
				puntoPuesto = false;

			}
			// muestra una alerta si el valor esta vacio
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}

	}

	// Funcion "Action" para el boton resta
	private void restaButtonAction(ActionEvent e) {
		try {
			bindeoOperacionStringPoperty.setValue("-");
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {
				if (!(primerValor == "0")) {// si ya tiene un valor lo resta
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();
				}
				bindeoValorStringPoperty.setValue("");
				puntoPuesto = false;
			}
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}

	}

	// Funcion "Action" para el boton multiplicar
	private void multiplicacionButtonAction(ActionEvent e) {
		try {
			bindeoOperacionStringPoperty.setValue("*");
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {
				if (!(primerValor == "0")) {// si ya tiene un valor lo multiplica
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();
				}
				
				bindeoValorStringPoperty.setValue("");
				puntoPuesto = false;
			}
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}

	}

	// Funcion "Action" para el boton dividir
	private void divisionButtonAction(ActionEvent e) {
		try {
			bindeoOperacionStringPoperty.setValue("/");
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {
				if (!(primerValor == "0")) {// si ya tiene un valor lo divide
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();
				}
				bindeoValorStringPoperty.setValue("");
				puntoPuesto = false;
			}
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}

	}
	
	private void puntoButtonAction(ActionEvent e) {
		if (puntoPuesto == false) {
			if (bindeoValorStringPoperty.getValue().isEmpty()) {
				bindeoValorStringPoperty.setValue("0.");
			} else {
				bindeoValorStringPoperty.setValue(bindeoValorStringPoperty.getValue() + ".");
			}
			puntoPuesto = true;
		}
	}

	// Funcion "Action" para el boton igual
	private void igualButtonAction(ActionEvent e) {
		try {
			double primero = Double.parseDouble(primerValor);
			double segundo = Double.parseDouble(bindeoValorStringPoperty.getValue());// guarda el segundo valor
			double resultado = 0;
			
			
			switch (bindeoOperacionStringPoperty.getValue()) {// compruebo que operacion esta haciendo
			case "+":
				resultado = primero + segundo;
				break;
			case "-":
				resultado = primero - segundo;
				break;
			case "*":
				resultado = primero * segundo;
				break;
			case "/":
				resultado = primero / segundo;
				break;
			}

			bindeoOperacionStringPoperty.setValue("=");

			bindeoValorStringPoperty.setValue(String.valueOf(resultado));
			puntoPuesto = false;


			// si no puede calcular muestra una alerta
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}
	}

	public GridPane getRoot() {
		return root;
	}

}
