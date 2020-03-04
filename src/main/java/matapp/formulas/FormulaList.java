package matapp.formulas;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FormulaList {
	/**
	 * @author Kilian González, Francisco Vargas
	 * 
	 * Clase de objetos tipo FormulaList los cuales poseeran un listado de objetos tipo Formula
	 * 
	 */

	private ListProperty<Category> categories = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));//listado de categorias
//	private ListProperty<Category> categories = new SimpleListProperty<>(FXCollections.observableArrayList(c -> new Observable[] {c.nameProperty()}));//listado de categorias
	public final ListProperty<Category> categoriesProperty() {
		return this.categories;
	}
	

	public final ObservableList<Category> getCategories() {
		return this.categoriesProperty().get();
	}
	

	public final void setCategories(final ObservableList<Category> categories) {
		this.categoriesProperty().set(categories);
	}


	@Override
	public String toString() {
		return "FormulaList [categories=" + getCategories() + "]";
	}
	


}
