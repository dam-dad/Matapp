package matapp.expreval;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import matapp.expreval.Param;
import static matapp.expreval.ExprEval.*;
public class EjemploDeUso {
	//ejemplo de uso de expresiones evaluables 
	
		public static void main(String[] args) {
			sample1();
			sample2();
			sample3();
			sample4();
			sample5();
		}
		private static void sample1() {
			Map<String, Object> params = new HashMap<>();
			params.put("ancho", 15);
			params.put("alto", 6);
			Double result = (Double) eval("2*Math.pow(10,-4)", params);
			System.out.println(result);
		}

		private static void sample2() {
			Double result = (Double) eval("Math.sqrt(ancho / alto)", param("ancho", 15), param("alto", 6));
			System.out.println(result);
		}

		private static void sample3() {
			Param<?> [] params = { param("ancho", 15), param("alto", 6) };
			Double result = (Double) eval("Math.sqrt(ancho / alto)", params);
			System.out.println(result);
		}	
		private static void sample4() {
			Param<?> [] params = { param("masa2", 15), param("gravedad", 9.8) };
			Double result = (Double) eval("Math.pow(masa2, 2)", params);
			
			System.out.println(result);
		}
		private static void sample5() {//ejemplo para un arrayList
			
			
			ArrayList<Param<?>> params=new ArrayList<>();
			
			params.add(param("m", 15));
			params.add(param("R", 9.8));
			Param<?>[] auxParams= new Param<?>[params.size()];

			Double result = (Double) eval("Math.sqrt(2*(6.674*Math.pow(10, -11))*m/(R))", params.toArray(auxParams)); 
			Param<?>[] auxParams2= { param("m", 15), param("R", 9.8) };
			Double result2 = (Double) eval("Math.sqrt(2*(6.674*Math.pow(10, -11))*m/(R))",auxParams2);
			System.out.println(result);
			System.out.println(result2);
		}
		
	}
