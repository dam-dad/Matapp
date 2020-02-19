package matapp.componentes;

import java.awt.Color;
import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import matapp.utils.FormulaUtils;

public class FormulaVista extends VBox{
	
	//property
	StringProperty nombreFormula=new SimpleStringProperty();
	ObjectProperty<Image> formula=new SimpleObjectProperty<>();
//	public FormulaVista() {
//		super();
//		setPrefSize(150, 140);
//		setPadding(new Insets(5, 5, 5, 20));
//		setSpacing(5);
//		setAlignment(Pos.TOP_CENTER);
//		
//		Label nombreFormulaLabel=new Label();
//		nombreFormulaLabel.textProperty().bind(nombreFormula);
//		ImageView formulaImage=new ImageView();
//		formulaImage.fitWidthProperty();
//		formulaImage.fitHeightProperty();
//		formulaImage.imageProperty().bind(formula);
//		
//		getChildren().addAll(nombreFormulaLabel,formulaImage);
//	}
	public FormulaVista(String nombre,String formulaExpression) {
		super();
//		setPrefSize(150, 140);
		setPrefHeight(80);
		setPadding(new Insets(5, 5, 5, 20));
		setSpacing(5);
		setAlignment(Pos.TOP_CENTER);
		
		Label nombreFormulaLabel=new Label();
		nombreFormulaLabel.textProperty().bind(nombreFormula);
		ImageView formulaImage=new ImageView();
		formulaImage.fitWidthProperty();
//		formulaImage.fitHeightProperty();
		formulaImage.imageProperty().bind(formula);
		
		setNombreFormula(nombre);
		crearImagen(formulaExpression);
		
		getChildren().addAll(nombreFormulaLabel,formulaImage);
	}
	
	private void crearImagen(String formulaExpression) {
		try {
			this.formula.set(FormulaUtils.formulaToImage(formulaExpression, 30, Color.black));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final StringProperty nombreFormulaProperty() {
		return this.nombreFormula;
	}
	
	public final String getNombreFormula() {
		return this.nombreFormulaProperty().get();
	}
	
	public final void setNombreFormula(final String nombreFormula) {
		this.nombreFormulaProperty().set(nombreFormula);
	}
	
	public final ObjectProperty<Image> formulaProperty() {
		return this.formula;
	}
	
	public final Image getFormula() {
		return this.formulaProperty().get();
	}
	
	public final void setFormula(final Image formula) {
		this.formulaProperty().set(formula);
	}
	
}
