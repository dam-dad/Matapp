package matapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BinarioController implements Initializable {

	private String primerValor = "0";// Guarda el primer numero de la operacion

	private StringProperty bindeoValorStringPoperty = new SimpleStringProperty();// String a mostrar los numeros
	private StringProperty bindeoOperacionStringPoperty = new SimpleStringProperty();// String para mostrar que
																						// operacion estas
																						// haciendo(suma,resta...)

	// bindeos para las conversiones
	private StringProperty bindeoHexPoperty = new SimpleStringProperty();
	private StringProperty bindeoDecPoperty = new SimpleStringProperty();
	private StringProperty bindeoOctPoperty = new SimpleStringProperty();
	private StringProperty bindeoBinPoperty = new SimpleStringProperty();

	// valores del view FXML
	@FXML
	private GridPane root;
	@FXML
	private Label valorLabel, operacionLabel, hexLabel, decLabel, octLabel, binLabel;
	@FXML
	private Button ceroButton, unoButton, dosButton, tresButton, cuatroButton, cincoButton, seisButton, sieteButton,
			ochoButton, nueveButton, multiButton, divButton, menosButton, masButton, igualButton, puntoButton, ceButton,
			cButton, andButton, orButton, xorButton, notButton, aHexaButton, bHexaButton, cHexaButton, dHexaButton,
			eHexaButton, fHexaButton;
	@FXML
	private VBox labelBox, cambioBox;

	@FXML
	private JFXRadioButton hexButton, decButton, octButton, binButton;

	public BinarioController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BinarioView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GridPane getRoot() {
		return root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		valorLabel.textProperty().bind(bindeoValorStringPoperty);// Bindeo del valor que introduces
		operacionLabel.textProperty().bind(bindeoOperacionStringPoperty);// Bindeo de la operacion a realizar

		// Bindeo de las conversiones
		hexLabel.textProperty().bind(bindeoHexPoperty);
		decLabel.textProperty().bind(bindeoDecPoperty);
		octLabel.textProperty().bind(bindeoOctPoperty);
		binLabel.textProperty().bind(bindeoBinPoperty);

		// Grupo para que solo puedas seleccionar un RadioButton
		ToggleGroup conversionGroup = new ToggleGroup();
		conversionGroup.getToggles().addAll(hexButton, decButton, octButton, binButton);

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
		andButton.setOnAction(e -> andButtonAction(e));
		orButton.setOnAction(e -> orButtonAction(e));
		xorButton.setOnAction(e -> xorButtonAction(e));
		notButton.setOnAction(e -> notButtonAction(e));
		aHexaButton.setOnAction(e -> letraHexaButtonAction(e, "a"));
		bHexaButton.setOnAction(e -> letraHexaButtonAction(e, "b"));
		cHexaButton.setOnAction(e -> letraHexaButtonAction(e, "c"));
		dHexaButton.setOnAction(e -> letraHexaButtonAction(e, "d"));
		eHexaButton.setOnAction(e -> letraHexaButtonAction(e, "e"));
		fHexaButton.setOnAction(e -> letraHexaButtonAction(e, "v"));

		// Desactivar botones
		// Desactivo todos los botones de letras al iniciar en decimal
		aHexaButton.setDisable(true);
		bHexaButton.setDisable(true);
		cHexaButton.setDisable(true);
		dHexaButton.setDisable(true);
		eHexaButton.setDisable(true);
		fHexaButton.setDisable(true);

		puntoButton.setDisable(true);// el punto siempre va desactivados

		// Listener para que se activen o desactiven los botones de letras cuando elijas
		// el "radioButton"
		// Al cambiar de seleccion cambia el valor mostrado
		conversionGroup.selectedToggleProperty().addListener((o, ov, nv) -> {
			if (decButton.isSelected()) {
				dosButton.setDisable(false);
				tresButton.setDisable(false);
				cuatroButton.setDisable(false);
				cincoButton.setDisable(false);
				seisButton.setDisable(false);
				sieteButton.setDisable(false);
				ochoButton.setDisable(false);
				nueveButton.setDisable(false);

				aHexaButton.setDisable(true);
				bHexaButton.setDisable(true);
				cHexaButton.setDisable(true);
				dHexaButton.setDisable(true);
				eHexaButton.setDisable(true);
				fHexaButton.setDisable(true);

				bindeoValorStringPoperty.setValue(bindeoDecPoperty.getValue());

			} else if (octButton.isSelected()) {
				dosButton.setDisable(false);
				tresButton.setDisable(false);
				cuatroButton.setDisable(false);
				cincoButton.setDisable(false);
				seisButton.setDisable(false);
				sieteButton.setDisable(false);

				ochoButton.setDisable(true);
				nueveButton.setDisable(true);
				aHexaButton.setDisable(true);
				bHexaButton.setDisable(true);
				cHexaButton.setDisable(true);
				dHexaButton.setDisable(true);
				eHexaButton.setDisable(true);
				fHexaButton.setDisable(true);

				bindeoValorStringPoperty.setValue(bindeoOctPoperty.getValue());
			} else if (binButton.isSelected()) {
				dosButton.setDisable(true);
				tresButton.setDisable(true);
				cuatroButton.setDisable(true);
				cincoButton.setDisable(true);
				seisButton.setDisable(true);
				sieteButton.setDisable(true);
				ochoButton.setDisable(true);
				nueveButton.setDisable(true);
				aHexaButton.setDisable(true);
				bHexaButton.setDisable(true);
				cHexaButton.setDisable(true);
				dHexaButton.setDisable(true);
				eHexaButton.setDisable(true);
				fHexaButton.setDisable(true);

				bindeoValorStringPoperty.setValue(bindeoBinPoperty.getValue());
			} else {
				dosButton.setDisable(false);
				tresButton.setDisable(false);
				cuatroButton.setDisable(false);
				cincoButton.setDisable(false);
				seisButton.setDisable(false);
				sieteButton.setDisable(false);
				ochoButton.setDisable(false);
				nueveButton.setDisable(false);
				aHexaButton.setDisable(false);
				bHexaButton.setDisable(false);
				cHexaButton.setDisable(false);
				dHexaButton.setDisable(false);
				eHexaButton.setDisable(false);
				fHexaButton.setDisable(false);

				bindeoValorStringPoperty.setValue(bindeoHexPoperty.getValue());
			}

		});

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
			convertirValor();
		} catch (java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo con el número");
			alert.setContentText("El elemento es demaciado grande");
			alert.showAndWait();
		}
	}

	private void letraHexaButtonAction(ActionEvent e, String letra) {
		try {
			if (bindeoValorStringPoperty.getValue() == null) {
				bindeoValorStringPoperty.setValue(letra);
				;
			} else {
				bindeoValorStringPoperty.setValue(bindeoValorStringPoperty.getValue() + letra);
			}
			convertirValor();
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
		// Elimina los valores
		bindeoValorStringPoperty.setValue("");
		bindeoOperacionStringPoperty.setValue("");
		bindeoDecPoperty.setValue("");
		bindeoHexPoperty.setValue("");
		bindeoOctPoperty.setValue("");
		bindeoBinPoperty.setValue("");
	}

	// Funcion "Action" para el boton "C"
	private void cButtonAction(ActionEvent e) {
		try {
			String nuevo = bindeoValorStringPoperty.getValue();// recoge el valor
			// borra el ultimo valor y lo guarda
			nuevo = nuevo.substring(0, nuevo.length() - 1);
			bindeoValorStringPoperty.setValue(nuevo);
			// Muestra una alerta si el valor esta vacio
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al borrar");
			alert.setContentText("No borres un elemento vacio");
			alert.showAndWait();
		}
	}

	// Funcion "Action" para el boton suma
	private void sumaButtonAction(ActionEvent e) {
		try {
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {// Comprueba que no este vacio
				if (!(primerValor == "0")) {// si ya tiene un valor lo suma
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();// Guardo el primer valor
				}
				bindeoOperacionStringPoperty.setValue("+");// muestro que esta sumando
				bindeoValorStringPoperty.setValue("");// lo vacio para introducir el segundo valor

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
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {
				if (!(primerValor == "0")) {// si ya tiene un valor lo resta
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();
				}
				bindeoOperacionStringPoperty.setValue("-");
				bindeoValorStringPoperty.setValue("");
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
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {
				if (!(primerValor == "0")) {// si ya tiene un valor lo multiplica
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();
				}
				bindeoOperacionStringPoperty.setValue("*");
				bindeoValorStringPoperty.setValue("");
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
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {
				if (!(primerValor == "0")) {// si ya tiene un valor lo divide
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();
				}
				bindeoOperacionStringPoperty.setValue("/");
				bindeoValorStringPoperty.setValue("");
			}
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}

	}

	private void andButtonAction(ActionEvent e) {
		try {
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {
				if (!(primerValor == "0")) {
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();
				}
				bindeoOperacionStringPoperty.setValue("AND");
				bindeoValorStringPoperty.setValue("");
			}
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}
	}

	private void orButtonAction(ActionEvent e) {
		try {
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {
				if (!(primerValor == "0")) {
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();
				}
				bindeoOperacionStringPoperty.setValue("OR");
				bindeoValorStringPoperty.setValue("");
			}
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}
	}

	private void xorButtonAction(ActionEvent e) {
		try {
			if (!bindeoValorStringPoperty.getValue().isEmpty()) {
				if (!(primerValor == "0")) {
					this.igualButtonAction(e);
					primerValor = bindeoValorStringPoperty.getValue();
				} else {
					primerValor = bindeoValorStringPoperty.getValue();
				}
				bindeoOperacionStringPoperty.setValue("XOR");
				bindeoValorStringPoperty.setValue("");
			}
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}
	}

	private void notButtonAction(ActionEvent e) {
		try {
			int valor = Integer.parseInt(bindeoValorStringPoperty.getValue());// guarda el valor
			valor = ~valor;
			bindeoValorStringPoperty.setValue(String.valueOf(valor));
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}
	}

	// Funcion "Action" para el boton igual
	private void igualButtonAction(ActionEvent e) {
		try {
			int primero = Integer.parseInt(primerValor);
			int segundo = Integer.parseInt(bindeoValorStringPoperty.getValue());// guarda el segundo valor
			int resultado = 0;

			if (hexButton.isSelected()) {
				primero = Integer.parseInt(primerValor, 16);
				segundo = Integer.parseInt(bindeoValorStringPoperty.getValue(), 16);// guarda el segundo valor
			} else if (octButton.isSelected()) {
				primero = Integer.parseInt(primerValor, 8);
				segundo = Integer.parseInt(bindeoValorStringPoperty.getValue(), 8);// guarda el segundo valor
			} else if (binButton.isSelected()) {
				primero = Integer.parseInt(primerValor, 2);
				segundo = Integer.parseInt(bindeoValorStringPoperty.getValue(), 2);// guarda el segundo valor
			}

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
			case "AND":
				resultado = primero & segundo;
				break;
			case "OR":
				resultado = primero | segundo;
				break;
			case "XOR":
				resultado = primero ^ segundo;
				break;
			case "NOT":
				// No hace nada aqui porque lo hace en su funcion para que no de error
				break;

			}

			bindeoOperacionStringPoperty.setValue("=");

			if (hexButton.isSelected()) {
				resultado = Integer.parseInt(Integer.toHexString(resultado));
			} else if (octButton.isSelected()) {
				resultado = Integer.parseInt(Integer.toOctalString(resultado));
			} else if (binButton.isSelected()) {
				resultado = Integer.parseInt(Integer.toBinaryString(resultado));
			}

			bindeoValorStringPoperty.setValue(String.valueOf(resultado));

			convertirValor();

			// si no puede calcular muestra una alerta
		} catch (java.lang.NullPointerException | java.lang.NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Fallo al operar");
			alert.setContentText("No se puede operar sin elementos");
			alert.showAndWait();
		}
	}

	// Funcion para las conversiones
	public void convertirValor() {
		int decimal = 0;
		if (decButton.isSelected()) {
			decimal = Integer.parseInt(bindeoValorStringPoperty.getValue());
		} else if (hexButton.isSelected()) {
			decimal = Integer.parseInt(bindeoValorStringPoperty.getValue(), 16);
		} else if (octButton.isSelected()) {
			decimal = Integer.parseInt(bindeoValorStringPoperty.getValue(), 8);
		} else if (binButton.isSelected()) {
			decimal = Integer.parseInt(bindeoValorStringPoperty.getValue(), 2);
		}

		bindeoDecPoperty.setValue(Integer.toString(decimal));
		bindeoOctPoperty.setValue(Integer.toOctalString(decimal));
		bindeoBinPoperty.setValue(Integer.toBinaryString(decimal));
		bindeoHexPoperty.setValue(Integer.toHexString(decimal));

	}

}
