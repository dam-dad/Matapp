package matapp.formulas;

import java.util.ArrayList;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Category {

	private StringProperty name=new SimpleStringProperty();//nombre de la categoria Campo gravitatorio,campo electrico...
	
	private ListProperty<Formula> formulas = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>())); //listado de formulas  pertenecientes a la categoría
	
	public Category() {
		
	}
	public Category(String name) {
		setName(name);
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
	

	public final ListProperty<Formula> formulasProperty() {
		return this.formulas;
	}
	

	public final ObservableList<Formula> getFormulas() {
		return this.formulasProperty().get();
	}
	

	public final void setFormulas(final ObservableList<Formula> formulas) {
		this.formulasProperty().set(formulas);
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
