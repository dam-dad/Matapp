package matapp.formulas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static matapp.expreval.ExprEval.*;

public class Ejemplo {

	public static void main(String[] args) {

		Variable v = new Variable();
		v.setName("v");
		v.setDescripcion("velocidad");

		Variable d = new Variable();
		d.setName("d");
		d.setDescripcion("distancia");

		Variable t = new Variable();
		t.setName("t");
		t.setDescripcion("tiempo");

		Formula f = new Formula();
		f.setName("Velocidad");
		f.setDescription("Rapidez en la que viaja un objeto en una dirección específica");
		f.setExpression("d/t");
		f.setResult(v);
		f.getVariables().add(d);
		f.getVariables().add(t);

		Category c = new Category();
		c.setName("Cinemática");
		c.getFormulas().add(f);

		FormulaList fl = new FormulaList();
		fl.getCategories().add(c);

		// muestra la lista de fórmulas en json
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(fl) + "\n");

		// evalúa la expresión de la formula con unos valores
		Double r = (Double) eval(
				f.getExpression(), 
				param(d.getName(), 15), 
				param(t.getName(), 6)
			);
		System.out.println(v.getName() + "=" + r);
		
	}

}
