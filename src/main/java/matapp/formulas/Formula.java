package matapp.formulas;

import java.util.ArrayList;
import java.util.List;

public class Formula {

	private String name;//nombre dado a la formula
	private String description;// descripcion de lo que debería hacer la fórmula
	private String expression;// expresion matematica de la formula( se usara para su calculo)
	private Variable result;// variable resultado, se expresará en la magnitud que corresponda
	private List<Variable> variables = new ArrayList<>(); //listado de variables que poseerá la formula para su cálculo

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Variable getResult() {
		return result;
	}

	public void setResult(Variable result) {
		this.result = result;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

}
