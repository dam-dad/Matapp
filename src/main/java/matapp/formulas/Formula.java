package matapp.formulas;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public class Formula {
	/**
	 * @author Kilian González, Francisco Vargas
	 * 
	 * Clase de objetos tipo Formula los cuales poseeran una serie de atributos
	 * 
	 */

	private StringProperty name=new SimpleStringProperty();//nombre dado a la formula
	private StringProperty description=new SimpleStringProperty();// descripcion de lo que debería hacer la fórmula
	private StringProperty expression=new SimpleStringProperty();// expresion matematica de la formula( se usara para su calculo)
	private StringProperty imgExpresion=new SimpleStringProperty();//consultar si debe ser un object o string y que luego sea cuando se llame a la clase FOrmulaUtils
	private ObjectProperty<Variable> result=new SimpleObjectProperty<>();// variable resultado, se expresará en la magnitud que corresponda
	private ListProperty<Variable> variables = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>())); //listado de variables que poseerá la formula para su cálculo
	
	
	public Formula() {
		
	}
	
	
	public Formula(String name,String description,String expression,String imgExpresion) {
		setName(name);
		setDescription(description);
		setExpression(expression);
		setImgExpresion(imgExpresion);
	}

	
	
	public final StringProperty nameProperty() {
		return this.name;
	}
	
	public final String getName() {
		return this.nameProperty().get();
	}
	
	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	
	public final StringProperty descriptionProperty() {
		return this.description;
	}
	
	public final String getDescription() {
		return this.descriptionProperty().get();
	}
	
	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
	}
	
	public final StringProperty expressionProperty() {
		return this.expression;
	}
	
	public final String getExpression() {
		return this.expressionProperty().get();
	}
	
	public final void setExpression(final String expression) {
		this.expressionProperty().set(expression);
	}
	
	public final ObjectProperty<Variable> resultProperty() {
		return this.result;
	}
	
	public final Variable getResult() {
		return this.resultProperty().get();
	}
	
	public final void setResult(final Variable result) {
		this.resultProperty().set(result);
	}
	
	public final ListProperty<Variable> variablesProperty() {
		return this.variables;
	}
	
	public final ObservableList<Variable> getVariables() {
		return this.variablesProperty().get();
	}
	
	public final void setVariables(final ObservableList<Variable> variables) {
		this.variablesProperty().set(variables);
	}
	public final StringProperty imgExpresionProperty() {
		return this.imgExpresion;
	}
	
	public final String getImgExpresion() {
		return this.imgExpresionProperty().get();
	}
	
	public final void setImgExpresion(final String imgExpresion) {
		this.imgExpresionProperty().set(imgExpresion);
	}


	@Override
	public String toString() {
		return "Formula [name=" + getName() + ", description=" + getDescription() + ", expression=" + getExpression()
				+ ", imgExpresion=" + getImgExpresion() + ", result=" + getResult() + ", variables=" + getVariables() + "]";
	}
	

	
	
	

}
