package matapp.formulas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

public class FicheroFormulas {
	/**
	 * 
	 * @author Kilian González
	 * @param args
	 * 
	 * Clase para generar el fichero base en formato gson de las formulas físicas
	 * 
	 */
	public static void main(String[] args) {
		
		//segundo de bachillerato
		//primero creamos las variables 
		Variable fuerza=new Variable("F", "Fuerza", "N");
		Variable masa1= new Variable("m", "Masa","Kg");
		Variable masa2= new Variable("M", "Masa","Kg");
		Variable aceleracion=new Variable("a","Aceleración","m/s^2");
		Variable gravedad=new Variable("g","Gravedad","m/s^2");
		Variable gravedadVector=new Variable("g#","Intensidad campo gravitatorio ","m/s^2");
		Variable potencialGravitatorio1=new Variable ( "V","Potencial gravitatorio","J/Kg");
		Variable potencialGravitatorio2=new Variable ( "V2","Potencial gravitatorio","J/Kg");
		Variable velocidad= new Variable("v","Velocidad","m/s ");
		Variable velocidadAngular=new Variable("w","VelocidadAngular","rad/s");
		Variable tiempo= new Variable ("t","Tiempo","s");
		Variable distancia= new Variable("r","Distancia, radio de la órbita","m");
		Variable periodo= new Variable("T","Periodo","s");
		Variable frecuencia= new Variable("f","Frecuencia","Hz");
		Variable energiaMecanica= new Variable("Em","Energía mecánica","J");
		Variable energiaPotencial= new Variable("Ep","Energía potencial","J");
		Variable energiaCinetica= new Variable("Ec","Energía cinética","J");
		Variable radio=new Variable ("R","Radio","m");
		Variable trabajo=new Variable ("W","Trabajo","J");
		
		//formulas
		
		//--Campo Gravitatorio 
		//Nota: acordarse de añadir expresion como imagen para el usuario
		/*Formula f1=new Formula("Intensidad del Campo Gravitatorio","Intensidad del campo gravitatorio en el\r\n" + 
				"punto 2 creado por una masa colocada en el punto 1 por la masa partida por la distancia",
				"(6.674*Math.pow(10, -11))*m/Math.pow(r,2)");
		f1.setResult(gravedad);
		f1.getVariables().addAll(masa1,distancia);*/
		
		Formula f2=new Formula("Fuerza Gravitatoria","Fuerza sobre una masa m1 en presencia de otra masa m2",
				"(6.674*Math.pow(10, -11))*m*M/Math.pow(r,2)","F =G\\frac{m·M}{r^2} ");
		f2.setResult(fuerza);
		f2.getVariables().addAll(masa1,masa2,distancia);
		
		
		Formula f3=new Formula("Fuerza","Fuerza es igual a masa por aceleración","m*a","F =m·a");
		f3.setResult(fuerza);
		f3.getVariables().add(masa1);
		f3.getVariables().add(aceleracion);
		
		Formula f4=new Formula("Potencial Gravitatorio","Potencial Gravitatorio es igual a menos la constante de gravitación universal "
				+ "por la masa partida por la distancia","(-6.674*Math.pow(10, -11))*m/r","V =-G \\frac{m}{r}");
		f4.setResult(potencialGravitatorio1);
		f4.getVariables().add(masa1);
		f4.getVariables().add(distancia);
		
		Formula f5=new Formula("Energía Potencial Gravitatoria",
				"Energía Potencial es igual a menos la constante gravitacional por ambas masas entre la distancia entre ellas",
				"(-6.674*Math.pow(10, -11))*m*M/Math.pow(r,2)","E_p = \\frac{m·M}{r^2}");
		f5.setResult(energiaPotencial);
		f5.getVariables().addAll(masa1,masa2,distancia);
		
		Formula f6=new Formula("Energía Cinética",
				"Energía es igual a un medio de la constante gravitacional por ambas masas dividido entre la distancia",
				"0.5*(6.674*Math.pow(10, -11))*m*M/r","E_c =\\frac{1}{2} · G \\frac{m·M}{r}");
		f6.setResult(energiaCinetica);
		f6.getVariables().addAll(masa1,masa2,distancia);
		
		Formula f6_2=new Formula("Energía Cinética v2",
				"Energía es igual a un medio de  la velocidad al cuadrado",
				"0.5*m*v","E_c = \\frac{1}{2}m·v^2");
		f6_2.setResult(energiaCinetica);
		f6_2.getVariables().addAll(masa1,velocidad);
		
		Formula f7=new Formula("Energía Mecánica",
				"Energía Mecánica es igual a la suma de la energía cinética y la potencial",
				"Ec+Ep","E_m =E_c+E_p");
		f7.setResult(energiaMecanica);
		f7.getVariables().addAll(energiaCinetica,energiaPotencial);
		
		Formula f7_2=new Formula("Energía Mecánica v2",
				"Energía Mecánica es igual a un medio de la energía potencial",
				"0.5*Ep","E_m=\\frac{1}{2} E_p"); 
		f7_2.setResult(energiaMecanica);
		f7_2.getVariables().addAll(energiaPotencial);
		
		Formula f7_3=new Formula("Energía Mecánica v3",
				"Energía Mecánica es igual a menos un medio de la constante gravitacional por ambas masas dividido entre la distancia",
				"-0.5*(6.674*Math.pow(10, -11))*m*M/r","E_m = -\\frac{1}{2} G \\frac{m·M}{r}");
		f7_3.setResult(energiaMecanica);
		f7_3.getVariables().addAll(masa1,masa2,distancia);
		
		
		Formula f8=new Formula("Velocidad de Escape",
				"Velocidad de escape desde la superficie de un planeta de masa m y radio r(altura del objeto inclusive)",
				"Math.sqrt(2*(6.674*Math.pow(10, -11))*m/(R))","V_e =\\sqrt{\\frac{2·G·m}{R}");
		f8.setResult(velocidad);
		f8.getVariables().addAll(masa1,radio);
	
		Formula f9=new Formula("Trabajo",  
		"Trabajo del campo para mover una masa m desde un punto A hasta un punto b",
		"-m*(V2-V)","W =-m ·(V_2- V)");
		f9.setResult(trabajo);
		f9.getVariables().addAll(potencialGravitatorio1,potencialGravitatorio2);

		//--Orbitas 
		Formula f10=new Formula("Velocidad angular",
				"Velocidad es igual a dos pi por la frecuencia",
				"Math.PI * 2 * f","w= \\Pi·2·f");
		f10.setResult(velocidadAngular);
		f10.getVariables().addAll(frecuencia);
		
		
		//categorias
		Category c1=new Category("Campo Gravitatorio");
		c1.getFormulas().addAll(f2,f3,f4,f5,f6,f6_2,f7,f7_2,f7_3,f8,f9);
		Category c2=new Category("Órbitas");
		c2.getFormulas().addAll(f10);
		
		//finalmente lo añadimos a la lista completa
		FormulaList fL=new FormulaList();
		fL.getCategories().addAll(c1,c2);
		
		//lo mostramos(posteriormente irá a un fichero en vez de por pantalla
		Gson gson = FxGson.fullBuilder().setPrettyPrinting().create();//cuidado si hay algun objeto que posea alguna property vacia este la considerará como codigo sucio
		System.out.println(gson.toJson(fL) + "\n");
		
		File file=new File("Formulas.txt"); 
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
	}
}
