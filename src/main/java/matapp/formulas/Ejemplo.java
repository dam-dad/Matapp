package matapp.formulas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import matapp.utils.FormulaUtils;

import static matapp.expreval.ExprEval.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.hildan.fxgson.FxGson;
import org.hildan.fxgson.FxGsonBuilder;

public class Ejemplo {

	public static void main(String[] args) {



		Variable fuerza = new Variable();
		fuerza.setName("F");
		fuerza.setDescripcion("Fuerza");
		Variable masa = new Variable();
		masa.setName("m");
		masa.setDescripcion("Masa");
		Variable aceleracion = new Variable();
		aceleracion.setName("a");
		aceleracion.setDescripcion("aceleracion");
		
		//formula

		String expression=""; 
		expression="\u0042";//B
		expression="\u003D";//=
		//expression="\u002D";//-
		
		Formula f3=new Formula("Fuerza","Fuerza es igual a masa por aceleración","m*a","F=m·a");
		f3.setResult(fuerza);
		f3.getVariables().add(masa);
		f3.getVariables().add(aceleracion);
		
		
		//categoria
		
		Category c1=new Category("Campo Gravitatorio");
		c1.getFormulas().addAll(f3);
		

		//finalmente lo añadimos a la lista copleta
		FormulaList fL=new FormulaList();
		fL.getCategories().addAll(c1);
		
		//lo mostramos(posteriormente irá a un fichero en vez de por pantalla
		Gson gson = FxGson.fullBuilder().setPrettyPrinting().create();//cuidado si hay algun objeto que posea alguna property 
																	  //vacia este la considerará como codigo sucio
		System.out.println(gson.toJson(fL) + "\n");
		
		//  evalúa la expresión de la formula con unos valores
		Double r = (Double) eval(
				fL.getCategories().get(0).getFormulas().get(0).getExpression(), 
				param(masa.getName(), 15), 
				param(aceleracion.getName(), 6)
			);
		
		File file=new File("PruebaFormula.txt"); 
		FileWriter fW=null;
		try {
			fW = new FileWriter(file);
			BufferedWriter bW=new BufferedWriter(fW);
			bW.write(gson.toJson(fL));
			bW.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fL.getCategories().get(0).getFormulas().get(0).getName() + "\\=" + r);
		
		System.out.println(fL.getCategories().get(0).getFormulas().get(0).getImgExpresion());
		
		//leemos del fichero creado
		String fichero = "";
		 
		try (BufferedReader br = new BufferedReader(new FileReader("PruebaFormula.txt"))) {
		    String linea;
		    while ((linea = br.readLine()) != null) {
		        fichero += linea;
		    }
		 
		} catch (FileNotFoundException ex) {
		    System.out.println(ex.getMessage());
		} catch (IOException ex) {
		    System.out.println(ex.getMessage());
		}
		
		FormulaList fLLeer;
		fLLeer=gson.fromJson(fichero, FormulaList.class);
		System.out.println("****");
		System.out.println(fLLeer.getCategories().get(0).getFormulas().get(0).getImgExpresion());
	}

}
