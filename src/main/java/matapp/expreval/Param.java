package matapp.expreval;

public class Param<T> {
	/**
	 * @author Fran Vargas, Kilian González
	 * 
	 * Clase aux donde guardar los nombres y el valor de las variables que ejecutará ExprEval
	 *  
	 */
	
	private String name;
	private T value;

	public Param(String name, T value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}