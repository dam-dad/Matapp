package matapp.formulas;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private String name;//nombre de la categoria Campo gravitatorio,campo electrico...
	private List<Formula> formulas = new ArrayList<>(); //listado de formulas  pertenecientes a la categoría

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Formula> getFormulas() {
		return formulas;
	}

	public void setFormulas(List<Formula> formulas) {
		this.formulas = formulas;
	}

}
