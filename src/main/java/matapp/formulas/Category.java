package matapp.formulas;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private String name;
	private List<Formula> formulas = new ArrayList<>();

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
