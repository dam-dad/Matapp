package matapp.controller;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import matapp.componentes.Parametro;
import matapp.formulas.Formula;
import matapp.formulas.Variable;
import matapp.utils.FormulaUtils;

public class FisicaFormulaController implements Initializable{
	@FXML
    private VBox root;

    @FXML
    private Label nombreFormulaLabel;

    @FXML
    private TextArea descripcionTextArea;

    @FXML
    private VBox parametrosContenedorVBox;

    @FXML
    private ImageView expresionImageView;
    
    @FXML
    private ImageView resultadoImageView;

    @FXML
    private JFXButton calcularButton;
    
    @FXML
    private JFXButton volverButton;
    //model
    ObjectProperty<Formula>formula=new SimpleObjectProperty<>();

    ObjectProperty<Image> imagen=new SimpleObjectProperty<>();
    
    ObjectProperty<Image> resultadoImagen=new SimpleObjectProperty<>();
    
    Parametro parametro;
    
    public FisicaFormulaController(Formula formula) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FisicaFormulaView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    	this.formula.set(formula);
    	rellenarDatos();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	private void rellenarDatos() {
		nombreFormulaLabel.textProperty().bind(formula.get().nameProperty());
    	descripcionTextArea.textProperty().bind(formula.get().descriptionProperty());
    	
    	parametro=new Parametro(formula.get());

    	
    	parametrosContenedorVBox.getChildren().clear();
    	parametrosContenedorVBox.getChildren().add(parametro);
    	expresionImageView.imageProperty().bind(imagen);
    	resultadoImageView.imageProperty().bind(resultadoImagen);
    	crearImagen(formula.get().getImgExpresion());
//    	calcularButton.disableProperty().bind(parametro.valorTextProperty()); buscar la menarea de desabilitar elboton cuando no esta rellando los datos
    	
	}
	private void crearImagen(String formulaExpression) {
		try {
			this.imagen.set(FormulaUtils.formulaToImage(formulaExpression, 30, Color.black));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void onCalcular(ActionEvent event) {
		try {
			this.resultadoImagen.set(FormulaUtils.formulaToImage(formula.get().getResult().getName()+"="
		+ parametro.calcular(), 30, Color.black));
		} catch (IOException e) {
			
		}
	}
	@FXML
	void onVolver(ActionEvent event) {
		FisicaMainController fisicaMainController=new FisicaMainController();
	}
	
	public VBox getRoot() {
		return root;
	}
	
}
