package matapp.formulas;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Variable {
	/**
	 * @author Kilian González, Francisco Vargas
	 * 
	 * Clase de objetos tipo Variable los cuales poseeran una serie de atributos
	 * 
	 */
	private StringProperty name = new SimpleStringProperty();//letra asignada F,v...
	private StringProperty descripcion = new SimpleStringProperty();// significado de la variable Fuerza,velocidad...
	private StringProperty magnitud =new SimpleStringProperty(); //Magnitud en la que se mide la variable kg, m/s ...
	
	public Variable() {
		
	}
	public Variable(String name, String descripcion,String magnitud) {
		setName(name);
		setDescripcion(descripcion);
		setMagnitud(magnitud);
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

	public final StringProperty descripcionProperty() {
		return this.descripcion;
	}

	public final String getDescripcion() {
		return this.descripcionProperty().get();
	}

	public final void setDescripcion(final String descripcion) {
		this.descripcionProperty().set(descripcion);
	}

	public final StringProperty magnitudProperty() {
		return this.magnitud;
	}
	

	public final String getMagnitud() {
		return this.magnitudProperty().get();
	}
	

	public final void setMagnitud(final String magnitud) {
		this.magnitudProperty().set(magnitud);
	}
	@Override
	public String toString() {
		return "Variable [name=" + getName() + ", descripcion=" + getDescripcion() + ", magnitud=" + getMagnitud() + "]";
	}
	
	
	

}
