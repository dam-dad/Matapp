package matapp.formulas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Ejemplo {

	public static void main(String[] args) {

		Variable v1 = new Variable();
		v1.setName("d");
		v1.setDescripcion("distancia");

		Variable v2 = new Variable();
		v2.setName("t");
		v2.setDescripcion("tiempo");

		Formula f1 = new Formula();
		f1.setName("Velocidad");
		f1.setDescription("Rapidez en la que viaja un objeto en una dirección específica");
		f1.setExpression("d/t");
		f1.getVariables().add(v1);
		f1.getVariables().add(v2);

		Category c1 = new Category();
		c1.setName("Cinemática");
		c1.getFormulas().add(f1);

		FormulaList fl = new FormulaList();
		fl.getCategories().add(c1);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(fl));

	}

}
